#!/usr/bin/perl -w

# silly little script to download the protocol (known officially as the Message Template file) and parse it into its data structures

# master Message Template available from LL repo at https://bitbucket.org/lindenlab/master-message-template/raw/2c23c816e48327484e03f0e4d92d151f0cd7873c/message_template.msg
#
# this file probably tracks a specific version (given that hash), so may need updating if they update the protocol
#
# that said, the raw protocol hasn't changed since at least 2011 (date that file was checked in, and thats just a check in, doesn't mean it changed then)
#
## wget the above URL by hand if you need to, given it almost never changes I decided not to auto download it


open FILE,"message_template.msg";
my $block="";
my %messagelookup;
while (<FILE>) {
	# split it into message blocks by doing some crappy matching on curley braces.  A real parser would be nice, but this is just a (probably one shot) supporting tool really
	chomp;
	my $line=$_;
	if ($line eq "version 2.0") { $line=""; } # fudge it, this line is nice but i have no cares, and it violates the block expectations :P
	if ($line=~/^([^\/]*)\/\/.*/) { $line="$1"; } # strip comments
	$block=$block.$line;
	# check if we got a complete block, that is, number of { = number of }, and number of { is at least 1 :P
	my $open=()=$block=~/{/g;
	my $close=()=$block=~/}/g;
	if ($open>0 and $open==$close) { 
		# a block
		#print "BLOCK [".$open."==".$close."]: ".$block."\n\n";
		processblock($block);
		$block="";
	}
}
close FILE;
open FILE,">Lookup.java";
print FILE "package net.coagulate.JSLBot.Packets.Messages;\n";
print FILE "public class Lookup {\n";
print FILE "\tpublic static String lookup(long v) {\n";
foreach my $id (keys %messagelookup) {
	my $hex;
	if ($id=~/^0x/) { $hex=$id; } else { $hex="0x".sprintf("%x",$id); }
	print FILE "\t\tif (v==$hex) { return \"".$messagelookup{$id}."\"; }\n";
}
print FILE "\t\treturn \"FAILED:\"+v;\n";
print FILE "\t}\n";
print FILE "}\n";
close FILE;

exit 0;

sub processblock {
	my ($block)=@_;
	my $sequence=0;
	#make sure we split adjacent block markers
	$block=~s/{/ { /g;
	$block=~s/}/ } /g;
	# split on all kinds of whitespace (tabs, spaces, etc)
	my @p=split /\s+/,$block;
	shift @p;
	# blocks start with {
	if ( (shift @p) ne "{" ) { die "Parse error, no initial brace"; }
	# message name
	my $messagename=shift @p;
	print $messagename." ";
	my $frequency=shift @p;
	my $id=shift @p;
	print "($frequency:$id) ";
	my $trust=shift @p;  # we dont really care, we only can use untrusted messages, but we write the decoders anyway
	my $encoding=shift @p;
	# write the message id to the lookup
	my $converted=$id;
	if ($frequency eq "Medium") { $converted+=0xff00; }
	if ($frequency eq "Low") { $converted +=0xffff0000; }
	$messagelookup{$converted}=$messagename;
	# we expect the opener to the first "block" in the message here, but there may also be other keywords about the block, such as:
	if ( $p[0] eq "UDPBlackListed") { print "UDPBLACKLIST "; shift @p; } # maybe we could ditch this, but in case it comes back from the server, decoding is important
	if ( $p[0] eq "UDPDeprecated") { print "UDPDeprecated "; shift @p; } # thats nice...
	if ( $p[0] eq "Deprecated") { print "Deprecated "; shift @p; } # thats nice... :P
	###
	###
	#REJECTS ABOVE HERE, START WRITING OUTPUT
	###
	###
	open OUTPUT,">".$messagename.".java";
	print OUTPUT "package net.coagulate.JSLBot.Packets.Messages;\n";
	print OUTPUT "import java.util.*;\n";
	print OUTPUT "import net.coagulate.JSLBot.JSLBot;\n";
	print OUTPUT "import net.coagulate.JSLBot.Packets.*;\n";
	print OUTPUT "import net.coagulate.JSLBot.Packets.Types.*;\n";
	print OUTPUT "public class $messagename extends Block implements Message {\n";
	print OUTPUT "\tpublic final int getFrequency() { return Frequency.".uc($frequency)."; }\n";
	print OUTPUT "\tpublic final int getId() { return $id; }\n";
	print OUTPUT "\tpublic final String getName() { return \"$messagename\"; }\n";
	# could actually just be a close block if the message has no body too
	#if ( $p[0] eq "}" ) { #bodyless message
	#	print "\n";
	#	print OUTPUT "}\n";
	#	close OUTPUT;
	#}
	#if ( $p[0] ne "{" ) { die "Unexpected message header keyword: ".$p[0]; }

	# process the inner blocks now...
	my $innerblockcount=0;
	my $hasagentblock=0; my $hasagentid=0; my $hassessionid=0;
	while ((shift @p) eq "{") {
		my $blockname="b".shift @p;
		my $qty=shift @p;
		my $repeat=0;
		if ($qty eq "Single" and lc($blockname) eq "bagentdata") { $hasagentblock=1; }
		if ($qty eq "Multiple") { $repeat=shift @p; }

		if ($qty eq "Single") {
			print OUTPUT "\t\@Sequence($sequence)\n"; $sequence++;
			print OUTPUT "\tpublic $messagename"."_$blockname ".lc($blockname)."=new $messagename"."_$blockname();\n";
		}
		if ($qty eq "Multiple") {
			print OUTPUT "\t\@Sequence($sequence)\n"; $sequence++;
			print OUTPUT "\tpublic $messagename"."_$blockname ".lc($blockname)."[]=new $messagename"."_$blockname\[$repeat\];\n";
		}
		if ($qty eq "Variable") {
			print OUTPUT "\t\@Sequence($sequence)\n"; $sequence++;
			print OUTPUT "\tpublic List<$messagename"."_$blockname> ".lc($blockname).";\n";
		}


		if (($p[0]) ne "{") { die "Inner block parse failure"; }
		open OUTPUT2,">".$messagename."_".$blockname.".java";
		print OUTPUT2 "package net.coagulate.JSLBot.Packets.Messages;\n";
		print OUTPUT2 "import java.util.*;\n";
		print OUTPUT2 "import net.coagulate.JSLBot.Packets.*;\n";
		print OUTPUT2 "import net.coagulate.JSLBot.Packets.Types.*;\n";
		print OUTPUT2 "public class $messagename"."_$blockname extends Block {\n";
		print "[$blockname:";
		my $varcount=0;
		my @variables;
		my $innersequence=0;
		while ($p[0] ne "}") {
			#foreach my $printme (@p) { print $printme."\n"; }
			# parse variables
			shift @p; #shift off the line's {
			my $varname="v".shift @p;
			if (lc($varname) eq "final") { $varname="Final_"; }
			my $vartype=shift @p;
			if ($vartype eq "LLUUID" and lc($blockname) eq "bagentdata" and lc($varname) eq "vsessionid") { $hassessionid=1; }
			if ($vartype eq "LLUUID" and lc($blockname) eq "bagentdata" and lc($varname) eq "vagentid") { $hasagentid=1; }
			if ($vartype eq "Variable") { $vartype.=shift @p; } #unimaginative solution
			if ($vartype eq "Fixed") { $vartype.=shift @p; } #unimaginative solution
			shift @p; #shift off the line's }
			print OUTPUT2 "\t\@Sequence($innersequence)\n"; $innersequence++;
			print OUTPUT2 "\tpublic $vartype ".lc($varname)."=new $vartype();\n";
			print lc($varname)." ";
			push @variables,lc($varname);
			$varcount++; if ($varcount>50) { die "Recursive var level loop?"; }
		}
		print "] ";
		shift @p; # discard close brace
		print OUTPUT2 "}\n";
		$innerblockcount++; if ($innerblockcount>10) { die "Recursive block level loop?"; }
	}
	if ($hasagentblock==1) {
		print OUTPUT "\tpublic $messagename(){}\n";
		print OUTPUT "\tpublic $messagename(JSLBot bot) {\n";
		if ($hassessionid==1) { print OUTPUT "\t\tbagentdata.vsessionid=bot.getSession();\n"; }
		if ($hasagentid==1) { print OUTPUT "\t\tbagentdata.vagentid=bot.getUUID();\n"; }
		print OUTPUT "\t}\n";
	} 
	print OUTPUT "}\n";
	close OUTPUT;
	print "\n";
}
