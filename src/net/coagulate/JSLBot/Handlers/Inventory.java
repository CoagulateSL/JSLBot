package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Messages.ImprovedInstantMessage;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;

import static java.util.logging.Level.SEVERE;

/**
 * Manage the inventory.
 *
 * @author Iain Price
 */
public class Inventory extends Handler implements Runnable {

	private final Map<LLUUID,Set<LLUUID>> inventorytree=new HashMap<>(); // parent id -> set of descendants - items OR folders
	private final Map<LLUUID,InventoryAtom> inventory=new HashMap<>();
	private final Set<LLUUID> downloadqueue=new HashSet<>();
	private boolean inventorycomplete;


	public Inventory(@Nonnull final JSLBot bot,
	                 final Configuration config) {
		super(bot,config);
	}

	// ---------- INSTANCE ----------
	@Override
	public void loggedIn() {
		inventorytree.clear();
		inventory.clear();
		inventorycomplete=false;
		downloadqueue.add(bot.getInventoryRoot());
		@Nonnull final Thread t=new Thread(this);
		t.setName("Inventory download thread");
		log.fine("Inventory download initiated.");
		t.start();
	}

	/**
	 * Start the inventory downloader thread.
	 */
	@Override
	public void run() {
		while (!downloadqueue.isEmpty()) {
			@Nonnull final Iterator<LLUUID> i=downloadqueue.iterator();
			@Nonnull final Set<LLUUID> download=new HashSet<>(downloadqueue);
			downloadqueue.clear();
			try { fetchInventory(download); }
			catch (@Nonnull final IOException e) {
				log.log(SEVERE,"Inventory download gave IO exception",e);
			}
			if (inventorytree.isEmpty()) {
				log.fine("Inventory download complete - there is no inventory (?)");
				break;
			}
			final int percent=(int) (Math.round((100.0*inventorytree.size())/(inventorytree.size()+downloadqueue.size())));
			log.fine("Inventory download: ["+percent+"%] "+inventorytree.size()+" branches complete, "+downloadqueue.size()+" to go ("+inventory.size()+" elements)");
		}
		log.info("Inventory download complete");
		inventorycomplete=true;
	}

	@Nonnull
	@CmdHelp(description="Dump inventory map to the console")
	public String inventoryDumpCommand(final CommandEvent event) {
		synchronized (inventorytree) {
			@Nonnull String map="";
			map=map+inventoryDump(bot.getInventoryRoot(),"");
			System.out.println(map);
			return "Dumped to console.";
		}
	}
	@Nonnull
	@CmdHelp(description="Find an item by name")
	public String inventoryFindCommand(final CommandEvent event,
									   @Nonnull @JSLBot.Param(description = "Substring of item name to find",name="name") final String name) {
		synchronized (inventorytree) {
            if (!inventorycomplete) {
                return "2 - Inventory download is not yet complete";
            }
            String output = "";
            if (name == null) {
                return "1 - No search name supplied";
            }
            for (final Map.Entry<LLUUID, InventoryAtom> entry : inventory.entrySet()) {
                final InventoryAtom atom = entry.getValue();
                if (atom instanceof InventoryItem) {
                    final InventoryItem item = (InventoryItem) atom;
                    if (item.name == null) {
                        System.out.println("An item has a null name (?)");
                    }
                    if (item.name.toLowerCase().contains(name.toLowerCase())) {
                        if (!output.isBlank()) {
                            output = output + "\n";
                        }
                        output = output + entry.getKey().toUUIDString() + " : " + item.name;
                    }
                }
            }
            if (output.isBlank()) {
                output = "1 - No items found containing that substring";
            }
			return output;
		}
	}
	@Nonnull @CmdHelp(description = "Send an inventory item to a recipient")
	public String inventorySendCommand(final CommandEvent event,
									   @Nonnull @JSLBot.Param(description="UUID of item to send",name="item") final String item,
									   @Nonnull @JSLBot.Param(description="UUID of avatar to send item to",name="target") final String target) {
        if (!inventorycomplete) {
            return "2 - Inventory download is not yet complete";
        }
        final LLUUID itemuuid = new LLUUID(item);
        final LLUUID targetuuid = new LLUUID(target);
        final InventoryItem invitem = inventoryFind(itemuuid);
        if (invitem == null) {
            return "1 - Could not find item by that UUID";
        }
        sendInventory(invitem, targetuuid);
        return "0 - Inventory transfer message sent";
    }

	/** Send an inventory item to a target UUID.
	 *
	 * This is implemented as an instant message over the primary circuit.
	 *
	 * @param itemuuid UUID of item to send
	 * @param target UUID of avatar to receive item
	 */
	public void sendInventory(@Nonnull final LLUUID itemuuid,@Nonnull final LLUUID target) {
        final InventoryItem item = inventoryFind(itemuuid);
		if (item==null) { throw new NullPointerException("Unable to resolve UUID "+itemuuid.toUUIDString()); }
		sendInventory(item,target);
	}
	/** Send an inventory item to a target UUID.
	 *
	 * This is implemented as an instant message over the primary circuit.
	 *
	 * @param item InventoryItem of item to send
	 * @param target UUID of avatar to receive item
	 */
	public void sendInventory(@Nonnull final InventoryItem item,@Nonnull final LLUUID target) {
		@Nonnull final ImprovedInstantMessage im=new ImprovedInstantMessage(bot);
		im.bmessageblock.vtoagentid=target;
		im.bmessageblock.vdialog=ImprovedInstantMessage.InstantMessageDialog.InventoryOffered.getValue();
		im.bmessageblock.vfromagentname=new Variable1(bot.getUsername());
		im.bmessageblock.vmessage=new Variable2(item.name);
		im.bmessageblock.voffline=new U8(0);
		im.bmessageblock.vposition=new LLVector3(0,0,0);
		//im.bmessageblock.vregionid=circuit().getRegionUUID();
		im.bmessageblock.vid=LLUUID.random();
        final byte[] binary = new byte[17];
		binary[0]=(byte)(item.type & 0xff);
		System.arraycopy(item.id.content().array(),0,binary,1,16);
        final Variable2 sendme = new Variable2(binary);
		im.bmessageblock.vbinarybucket=sendme;
		bot.send(im,true);
	}

	@Nonnull
	@CmdHelp(description="Dump item data by inventory UUID (see inventoryFind)")
	public String inventoryDetailCommand(final CommandEvent event,
									   @Nonnull @JSLBot.Param(description = "UUID of item to report on",name="uuid") final String uuid) {
        if (!inventorycomplete) {
            return "2 - Inventory download is not yet complete";
        }
        if (uuid == null) {
            return "1 - No UUID supplied";
        }
        final LLUUID lluuid = new LLUUID(uuid);
        final InventoryItem item = inventoryFind(lluuid);
        if (item == null) {
            return "1 - Could not find object by uuid " + lluuid.toUUIDString();
        }
        return item.id.toUUIDString() + "\nName:" + item.name + "\nAssetID:" + item.assetid.toUUIDString() + "\nDescription: " + item.desc + "\nType: " + item.invtype + "/" + item.type;
    }

	private InventoryItem inventoryFind(@Nonnull final LLUUID uuid) {
		if (!inventorycomplete) { throw new IllegalStateException("Unable to search inventory, not yet completed initial download"); }
		synchronized (inventorytree) {
            for (final Map.Entry<LLUUID, InventoryAtom> entry : inventory.entrySet()) {
                if (entry.getKey().equals(uuid)) {
                    final InventoryAtom atom = entry.getValue();
                    if (atom instanceof InventoryItem) {
                        final InventoryItem item = (InventoryItem) atom;
                        return item;
                    }
                }
            }
		}
		return null;
	}

	// ----- Internal Instance -----
	private void processCategory(final int type,
	                             final LLUUID agentid,
	                             final LLUUID id,
	                             final LLUUID parentid,
	                             final String name,
	                             final int version) {
		synchronized (inventory) {
			inventory.put(id,new InventoryCategory(type,agentid,id,parentid,name,version));
		}
		addInventoryChild(parentid,id);
	}

	private void addInventoryChild(final LLUUID parentid,
	                               final LLUUID id) {
		synchronized (inventorytree) {
			Set<LLUUID> children=inventorytree.get(parentid);
			if (children==null) { children=new HashSet<>(); }
			children.add(id);
			inventorytree.put(parentid,children);
		}
	}

	private void processItem(final LLUUID id,
	                         final LLUUID parent,
	                         final String name,
	                         final LLUUID assetid,
	                         final int type,
	                         final int invtype,
	                         final String desc) {
		synchronized (inventory) {
			inventory.put(id,new InventoryItem(id,parent,name,desc,assetid,type,invtype));
		}
		addInventoryChild(parent,id);
	}

	private void fetchInventory(final LLUUID uuid) throws IOException {
		@Nonnull final Set<LLUUID> uuids=new HashSet<>();
		uuids.add(uuid);
		fetchInventory(uuids);
	}

	@SuppressWarnings("ConstantConditions")
	private void fetchInventory(@Nonnull final Set<LLUUID> uuids) throws IOException {
		final boolean debugqueries=false;
		@Nonnull final LLSDMap outer=new LLSDMap();
		@Nonnull final LLSD document=new LLSD(outer);
		@Nonnull final LLSDArray array=new LLSDArray();
		outer.put("folders",array);
		// "a body containing a map, with one key - "folders", whose value is an array of maps, each with one key, "folder_id", containing the caterogy you want info about
		// sounds fun

		@Nonnull final Set<LLUUID> query=new HashSet<>();
		if (debugqueries) { System.out.println("Querying "+uuids.size()+" uuids"); }
		for (final LLUUID uuid: uuids) {
			@Nonnull final LLSDMap map=new LLSDMap();
			map.put("fetch_items",new LLSDBoolean(true));
			map.put("fetch_folders",new LLSDBoolean(true));
			map.put("folder_id",new LLSDUUID(uuid));
			array.add(map);
		}

		@Nullable final LLSDMap result=bot.getCAPS().invokeCAPS("FetchInventoryDescendents2","",document);
		@Nonnull final LLSDArray folders=(LLSDArray) result.get("folders");
		if (debugqueries) { System.out.println("Outer array:"+folders.get().size()); }
		for (final Atomic a: folders.get()) {
			@Nonnull final LLSDMap innermap=(LLSDMap) a;
			@Nonnull final LLSDArray inneritems=(LLSDArray) innermap.get("items");
			if (debugqueries) { System.out.println("Inner items:"+inneritems.get().size()); }
			for (final Atomic itemmap: inneritems.get()) {
				@Nonnull final LLSDMap item=(LLSDMap) itemmap;
				final LLUUID item_id=((LLSDUUID) item.get("item_id")).toLLUUID();
				final LLUUID parent_id=((LLSDUUID) item.get("parent_id")).toLLUUID();
				final String name=item.get("name").toString();
				final LLUUID asset_id=((LLSDUUID) item.get("asset_id")).toLLUUID();
				final int type=((LLSDInteger) (item.get("type"))).get();
				final int inv_type=((LLSDInteger) (item.get("inv_type"))).get();
				final String desc=item.get("desc").toString();
				processItem(item_id,parent_id,name,asset_id,type,inv_type,desc);
			}
			@Nonnull final LLSDArray innercategories=(LLSDArray) innermap.get("categories");
			if (debugqueries) { System.out.println("Inner categories:"+innercategories.get().size()); }
			for (final Atomic categorymap: innercategories.get()) {
				@Nonnull final LLSDMap category=(LLSDMap) categorymap;
				//System.out.println("---------------");
				//for (String s:item.keys()) { System.out.println(s+"="+item.get(s).toString()); }
				//System.out.println(category.toXML());
				query.add(new LLUUID(category.get("category_id").toString()));
				final int type_default=((LLSDInteger) (category.get("type_default"))).get();
				final LLUUID agent_id=((LLSDUUID) (category.get("agent_id"))).toLLUUID();
				final LLUUID category_id=((LLSDUUID) (category.get("category_id"))).toLLUUID();
				final LLUUID parent_id=((LLSDUUID) (category.get("parent_id"))).toLLUUID();
				final String name=category.get("name").toString();
				final int version=((LLSDInteger) (category.get("version"))).get();
				processCategory(type_default,agent_id,category_id,parent_id,name,version);
			}
		}
		if (query.isEmpty()) {
			if (debugqueries) { System.out.println("No new queries to generate"); }
		}
		else {
			if (debugqueries) { System.out.println("Generated new queries:"+query.size()); }
			downloadqueue.addAll(query);
		}
	}

	@Nonnull
	private String inventoryDump(final LLUUID parent,
	                             final String prefix) {
		@Nonnull final StringBuilder ret=new StringBuilder();
		final Set<LLUUID> children=inventorytree.get(parent);
		if (children==null || children.isEmpty()) { return ""; }
		for (final LLUUID item: children) {
			ret.append(prefix);
			final InventoryAtom child=inventory.get(item);
			if (child instanceof InventoryCategory) {
				@Nonnull final InventoryCategory i=(InventoryCategory) child;
				ret.append(i.name).append("\n");
				ret.append(inventoryDump(i.id,prefix+"  "));
			}
			if (child instanceof InventoryItem) {
				@Nonnull final InventoryItem i=(InventoryItem) child;
				ret.append("-").append(i.name).append(" [").append(i.desc).append("]\n");
			}
		}
		return ret.toString();
	}

	public static class InventoryAtom {}

	public static class InventoryItem extends InventoryAtom {
		final LLUUID id;
		final LLUUID parent;
		final String name;
		final String desc;
		final LLUUID assetid;
		final int type;
		final int invtype;

		private InventoryItem(final LLUUID id,
		                      final LLUUID parent,
		                      final String name,
		                      final String desc,
		                      final LLUUID assetid,
		                      final int type,
		                      final int invtype) {
			this.id=id;
			this.parent=parent;
			this.name=name;
			this.desc=desc;
			this.assetid=assetid;
			this.type=type;
			this.invtype=invtype;
		}
	}

	public static class InventoryCategory extends InventoryAtom {
		public final int type;
		public final LLUUID agentid;
		public final LLUUID id;
		public final LLUUID parentid;
		public final String name;
		public final int version;

		public InventoryCategory(final int type,
		                         final LLUUID agentid,
		                         final LLUUID id,
		                         final LLUUID parentid,
		                         final String name,
		                         final int version) {
			this.type=type;
			this.agentid=agentid;
			this.id=id;
			this.parentid=parentid;
			this.name=name;
			this.version=version;
		}
	}
}
