package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.LLSDArray;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Some general purpose useful static functions.
 *
 * @author Iain Price
 */
public abstract class BotUtils {
	// ---------- STATICS ----------

	/**
	 * Turns a byte array into a hex string
	 *
	 * @param in Byte array
	 *
	 * @return Hex string representation.
	 */
	@Nonnull
	public static String hex(@Nonnull final byte[] in) {
		final StringBuilder builder=new StringBuilder();
		for (final byte b: in) {
			builder.append(String.format("%02x",b));
		}
		return builder.toString();
	}

	/**
	 * Get the machine's mac address as a hex string, required by the SL login.
	 *
	 * @return Mac address in hex form.
	 */
	@Nonnull
	public static String getMac() {
		try {
			// this used to be more intelligent but now we just iterate through cards and grab /a/ mac address.
			final Enumeration<NetworkInterface> e=NetworkInterface.getNetworkInterfaces();
			byte[] mac=null;
			while (e.hasMoreElements() && mac==null) {
				final NetworkInterface ni=e.nextElement();
				mac=ni.getHardwareAddress();
			}
			if (mac==null) {
				throw new IllegalArgumentException("No network interfaces found");
			}
			//System.out.println("Using mac "+hex(mac)+" from "+stored.toString());
			return hex(mac);
		}
		catch (@Nonnull final SocketException ex) {
			throw new AssertionError("Unable to retrieve any network interfaces MAC addres; unsupported platform or no networking present???",ex);
		}
	}

	/**
	 * Hash a password using MD5 and prefix with $1$, used by SL login request.
	 * If already prefixed with $!$, returned verbatim
	 *
	 * @param password Password, cleartext or MD5 with $1$ prefix
	 *
	 * @return MD5 hex hash of the password, with $1$ prefix, as used in SL login protocol
	 */
	@Nonnull
	public static String md5hash(@Nonnull final String password) {
		if (password.startsWith("$1$")) {
			return password; // already hashed, or you have a really unfortunate choice of password :P
		}
		final MessageDigest md5;
		try {
			md5=MessageDigest.getInstance("MD5");
		}
		catch (@Nonnull final NoSuchAlgorithmException ex) {
			throw new AssertionError("MD5 hashing is not supported on this platform?");
		}
		final byte[] digest;
		digest=md5.digest(password.getBytes(StandardCharsets.UTF_8));
		return "$1$"+hex(digest);
		//return hex(digest);
	}

	/**
	 * ZeroEncode the input byte array.
	 * Add acks /after/ zerocoding, they are not included.
	 * See the SL protocol documentation.
	 *
	 * @param input raw byte array
	 *
	 * @return ZeroCoded byte array
	 */
	@Nonnull
	public static byte[] zeroEncode(@Nonnull final byte[] input) {
		final List<Byte> output=new ArrayList<>();
		// first 5 bytes (header) are not encoded
		for (int i=0;i<6;i++) { output.add(input[i]); }
		int zerocount=0;
		// rest is
		for (int i=6;i<input.length;i++) {
			if (input[i]==0) {
				zerocount++;
			}
			else {
				if (zerocount>0) {
					output.add((byte) 0);
					output.add((byte) zerocount);
					zerocount=0;
				}
				output.add(input[i]);
			}
		}
		if (zerocount>0) {
			output.add((byte) 0);
			output.add((byte) zerocount);
		}
		final byte[] outputbytes=new byte[output.size()];
		int offset=0;
		// and put it back into the byte array :P
		for (final Byte b: output) {
			outputbytes[offset]=b;
			offset++;
		}
		return outputbytes;
	}

	/**
	 * Read a zero byte terminated string from a byte buffer.
	 *
	 * @param buffer Byte buffer containing a zero terminated string.
	 *
	 * @return String read up to the zero byte.
	 */
	@Nonnull
	public static String readZeroTerminatedString(@Nonnull final ByteBuffer buffer) {
		final List<Byte> bytes=new ArrayList<>();
		byte b=-1;
		while (b!=0) {
			b=buffer.get();
			if (b>0) { bytes.add(b); }
		}
		final Byte[] bytesarray=bytes.toArray(new Byte[0]);
		final byte[] ba=new byte[bytesarray.length];
		for (int i=0;i<bytesarray.length;i++) { ba[i]=bytesarray[i]; }
		return new String(ba);
	}

	@Nonnull
	public static String unravel(@Nullable Throwable t) {
		final StringBuilder response=new StringBuilder();
		while (t!=null) {
			response.append(" - [").append(t.getLocalizedMessage()).append("]");
			t=t.getCause();
		}
		return response.toString();
	}

	// ----- Internal Statics -----

	/**
	 * Create and execute the XMLRPC logon command.
	 *
	 * @param bot       The owning bot making the connection
	 * @param firstname Login first name
	 * @param lastname  Login last name (maybe 'resident')
	 * @param password  Password, in clear text or MD5 hex string preceeded by $!$
	 * @param location  Login location
	 *
	 * @return Map of KV pairs from login server
	 *
	 * @throws MalformedURLException Login URL is somehow invalid
	 * @throws XmlRpcException       Failure to connect to or communicate with XML RPC service
	 */
	@Nonnull
	static Map<Object,Object> loginXMLRPC(@Nonnull final JSLBot bot,
	                                      final String firstname,
	                                      final String lastname,
	                                      @Nonnull String password,
	                                      final String location,
										  @Nonnull final String loginuri) throws MalformedURLException, XmlRpcException {
		final XmlRpcClientConfigImpl config=new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(loginuri));
		final XmlRpcClient client=new XmlRpcClient();
		client.setConfig(config);
		final HashMap<String,Object> params=new HashMap<>();
		params.put("first",firstname);
		params.put("last",lastname);
		params.put("extended_errors",1);
		params.put("start",location);
		params.put("channel","JSLBot <Iain Maltz@Second Life>");
		params.put("platform","Lin");
		final List<String> options=new ArrayList<>();
		/*options.add("inventory-root");
		options.add("adult-compliant");
		options.add("buddy-list");
		options.add("login-flags");*/
		options.add("inventory-root");
		options.add("inventory-skeleton");
		options.add("inventory-lib-root");
		options.add("inventory-lib-owner");
		options.add("inventory-skel-lib");
		options.add("initial-outfit");
		options.add("gestures");
		options.add("display_names");
		options.add("event_categories");
		options.add("event_notifications");
		options.add("classified_categories");
		options.add("adult_compliant");
		options.add("buddy-list");
		options.add("newuser-config");
		options.add("ui-config");
		options.add("advanced-mode");
		params.put("options",options);
		final String mac=BotUtils.getMac();
		params.put("mac",mac);
		// MD-5 =)
		// TURNS OUT SECOND LIFE ONLY USES THE FIRST 16 CHARS
		// but silently discards the rest in the user interface, so you can have >16 chars, but the rest dont do anything.
		// However, if you MD5sum more than 16 chars you break the world.
		if (password.length()>16 && (!password.startsWith("$1$"))) { password=password.substring(0,16); }
		params.put("passwd",BotUtils.md5hash(password));
		if (Debug.AUTH) {
			for (final Map.Entry<String,Object> entry: params.entrySet()) {
				System.out.println(entry.getKey()+"="+entry.getValue());
			}
		}
		final Object resultobject=(client.execute("login_to_simulator",new Object[]{params}));
		@SuppressWarnings("unchecked") final HashMap<Object,Object> result=(HashMap<Object,Object>) resultobject;
		if (Debug.AUTH) {
			// dump the result
			for (final Map.Entry<Object,Object> entry: result.entrySet()) {
				String printline=(entry.getKey()+" -> ");
				final Object output=entry.getValue();
				if (output instanceof String) { printline+=("[String] "+output); }
				else {
					if (output instanceof Integer) { printline+=("[Integer] "+output); }
					else {
						final String clas=entry.getValue().getClass().getTypeName();
						printline+="["+clas+"] "+(output);
					}
				}
				bot.getLogger("Login").finer(printline);
			}
		}
		return result;
	}

	/**
	 * A list of caps we request from a sim.
	 *
	 * @return Array of CAPS
	 */
	@Nonnull
	static LLSDArray getCAPSArray() {
		//Mostly here because its a giant horrible block of text
		final LLSDArray req=new LLSDArray();
		req.add("AbuseCategories");
		req.add("AcceptFriendship");
		req.add("AcceptGroupInvite");
		req.add("AgentPreferences");
		req.add("AgentState");
		req.add("AttachmentResources");
		req.add("AvatarPickerSearch");
		req.add("AvatarRenderInfo");
		req.add("CharacterProperties");
		req.add("ChatSessionRequest");
		req.add("CopyInventoryFromNotecard");
		req.add("CreateInventoryCategory");
		req.add("DeclineFriendship");
		req.add("DeclineGroupInvite");
		req.add("DispatchRegionInfo");
		req.add("DirectDelivery");
		req.add("EnvironmentSettings");
		req.add("EstateAccess");
		req.add("EstateChangeInfo");
		req.add("EventQueueGet");
		req.add("ExtEnvironment");
		req.add("FetchLib2");
		req.add("FetchLibDescendents2");
		req.add("FetchInventory2");
		req.add("FetchInventoryDescendents2");
		req.add("IncrementCOFVersion");
		req.add("GetDisplayNames");
		req.add("GetExperiences");
		req.add("AgentExperiences");
		req.add("FindExperienceByName");
		req.add("GetExperienceInfo");
		req.add("GetAdminExperiences");
		req.add("GetCreatorExperiences");
		req.add("ExperiencePreferences");
		req.add("GroupExperiences");
		req.add("UpdateExperience");
		req.add("IsExperienceAdmin");
		req.add("IsExperienceContributor");
		req.add("RegionExperiences");
		req.add("ExperienceQuery");
		req.add("GetMesh");
		req.add("GetMesh2");
		req.add("GetMetadata");
		req.add("GetObjectCost");
		req.add("GetObjectPhysicsData");
		req.add("GetTexture");
		req.add("GroupAPIv1");
		req.add("GroupMemberData");
		req.add("GroupProposalBallot");
		req.add("HomeLocation");
		req.add("LandResources");
		req.add("LSLSyntax");
		req.add("MapLayer");
		req.add("MapLayerGod");
		req.add("MeshUploadFlag");
		req.add("NavMeshGenerationStatus");
		req.add("NewFileAgentInventory");
		req.add("ObjectAnimation");
		req.add("ObjectMedia");
		req.add("ObjectMediaNavigate");
		req.add("ObjectNavMeshProperties");
		req.add("ParcelPropertiesUpdate");
		req.add("ParcelVoiceInfoRequest");
		req.add("ProductInfoRequest");
		req.add("ProvisionVoiceAccountRequest");
		req.add("ReadOfflineMsgs");
		req.add("RemoteParcelRequest");
		req.add("RenderMaterials");
		req.add("RequestTextureDownload");
		req.add("ResourceCostSelected");
		req.add("RetrieveNavMeshSrc");
		req.add("SearchStatRequest");
		req.add("SearchStatTracking");
		req.add("SendPostcard");
		req.add("SendUserReport");
		req.add("SendUserReportWithScreenshot");
		req.add("ServerReleaseNotes");
		req.add("SetDisplayName");
		req.add("SimConsoleAsync");
		req.add("SimulatorFeatures");
		req.add("StartGroupProposal");
		req.add("TerrainNavMeshProperties");
		req.add("TextureStats");
		req.add("UntrustedSimulatorMessage");
		req.add("UpdateAgentInformation");
		req.add("UpdateAgentLanguage");
		req.add("UpdateAvatarAppearance");
		req.add("UpdateGestureAgentInventory");
		req.add("UpdateGestureTaskInventory");
		req.add("UpdateNotecardAgentInventory");
		req.add("UpdateNotecardTaskInventory");
		req.add("UpdateScriptAgent");
		req.add("UpdateScriptTask");
		req.add("UpdateSettingsAgentInventory");
		req.add("UpdateSettingsTaskInventory");
		req.add("UploadBakedTexture");
		req.add("UserInfo");
		req.add("ViewerAsset");
		req.add("ViewerBenefits");
		req.add("ViewerMetrics");
		req.add("ViewerStartAuction");
		req.add("ViewerStats");
		return req;
	}
}
