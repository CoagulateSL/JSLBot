package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.Packets.Messages.UUIDNameReply;
import net.coagulate.JSLBot.Packets.Messages.UUIDNameReply_bUUIDNameBlock;
import net.coagulate.JSLBot.Packets.Messages.UUIDNameRequest;
import net.coagulate.JSLBot.Packets.Messages.UUIDNameRequest_bUUIDNameBlock;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class Avatars extends Handler {

    public Avatars(@Nonnull JSLBot bot, Configuration config) {
        super(bot, config);
    }

    private static final Map<LLUUID,String> resolvedUUIDs=new HashMap<>();
    private static final Object resolveUUIDLock=new Object();

    @JSLBot.CmdHelp(description="Look up an Avatar's profile from UUID")
    public String resolveUUIDCommand(final CommandEvent command,
                                      @Nullable @JSLBot.Param(name="uuid", description="UUID of Avatar to look up, or comma separated list of UUIDs") String uuid) {
        if (uuid==null || "".equals(uuid)) { return "No UUID parameter passed."; }
        List<String> param=new ArrayList<>();
        Collections.addAll(param, uuid.split(","));
        StringBuilder output=new StringBuilder();
        for (Map.Entry<String,String> entry:resolveUUIDStrings(param).entrySet()) {
            if (output.length()>0) { output.append(" "); }
            output.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return output.toString();
    }

    public Map<String,String> resolveUUIDStrings(final List<String> uuids) {
        List<LLUUID> converted=new ArrayList<>(uuids.size());
        for (String uuid:uuids) {
            converted.add(new LLUUID(uuid));
        }
        Map<String,String> map=new HashMap<>();
        for (Map.Entry<LLUUID,String> entry:resolveUUIDs(converted).entrySet()) {
            map.put(entry.getKey().toUUIDString(),entry.getValue());
        }
        return map;
    }
    public Map<LLUUID,String> resolveUUIDs(final List<LLUUID> uuids) {
        UUIDNameRequest req=new UUIDNameRequest();
        req.buuidnameblock=new ArrayList<>();
        Map<LLUUID,String> map=new HashMap<>();
        for (LLUUID uuid:uuids) {
            if (resolvedUUIDs.containsKey(uuid)) { map.put(uuid,resolvedUUIDs.get(uuid)); }
            else {
                UUIDNameRequest_bUUIDNameBlock lookup = new UUIDNameRequest_bUUIDNameBlock();
                lookup.vid = uuid;
                req.buuidnameblock.add(lookup);
            }
        }
        if (req.buuidnameblock.isEmpty()) { return map; }
        bot.send(req);
        boolean complete=false;
        final Date now=new Date();
        while (!complete && (new Date().getTime()-(now.getTime()))<5000) {
            try {
                synchronized (resolveUUIDLock) {
                    resolveUUIDLock.wait(1000);
                }
            }
            catch (@Nonnull final InterruptedException ignored) {}
            complete=true;
            for (LLUUID exists:uuids) {
                if (!resolvedUUIDs.containsKey(exists)) {
                    complete = false;
                    break;
                }
            }
        }
        for (LLUUID uuid:uuids) {
            map.put(uuid, resolvedUUIDs.getOrDefault(uuid, null));
        }
        return map;
    }

    public void uUIDNameReplyUDPImmediate(@Nonnull final UDPEvent event) {
        UUIDNameReply reply= (UUIDNameReply) event.body();
        for (UUIDNameReply_bUUIDNameBlock element:reply.buuidnameblock) {
            if (element.vlastname.toString().equalsIgnoreCase("Resident")) {
                resolvedUUIDs.put(element.vid, element.vfirstname.toString());
            } else {
                resolvedUUIDs.put(element.vid, element.vfirstname.toString() + " " + element.vlastname.toString());
            }
        }
        synchronized (resolveUUIDLock) { resolveUUIDLock.notifyAll(); }
    }


}
