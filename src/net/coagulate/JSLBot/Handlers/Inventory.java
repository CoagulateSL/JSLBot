package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;
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

	@Override
	public void loggedIn() {
		inventorytree.clear();
		inventory.clear();
		inventorycomplete=false;
		downloadqueue.add(bot.getInventoryRoot());
		final Thread t=new Thread(this);
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
			final Iterator<LLUUID> i=downloadqueue.iterator();
			final Set<LLUUID> download=new HashSet<>(downloadqueue);
			downloadqueue.clear();
			try { fetchInventory(download); }
			catch (@Nonnull final IOException e) {
				log.log(SEVERE,"Inventory download gave IO exception",e);
			}
			if (inventorytree.size()==0) {
				log.fine("Inventory download complete - there is no inventory (?)");
				break;
			}
			final int percent=(int) (Math.round((100.0*inventorytree.size())/(inventorytree.size()+downloadqueue.size())));
			log.fine("Inventory download: ["+percent+"%] "+inventorytree.size()+" branches complete, "+downloadqueue.size()+" to go ("+inventory.size()+" elements)");
		}
		log.info("Inventory download complete");
		inventorycomplete=true;
	}

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
		final Set<LLUUID> uuids=new HashSet<>();
		uuids.add(uuid);
		fetchInventory(uuids);
	}

	@SuppressWarnings("ConstantConditions")
	private void fetchInventory(@Nonnull final Set<LLUUID> uuids) throws IOException {
		final boolean debugqueries=false;
		final LLSDMap outer=new LLSDMap();
		final LLSD document=new LLSD(outer);
		final LLSDArray array=new LLSDArray();
		outer.put("folders",array);
		// "a body containing a map, with one key - "folders", whose value is an array of maps, each with one key, "folder_id", containing the caterogy you want info about
		// sounds fun

		final Set<LLUUID> query=new HashSet<>();
		if (debugqueries) { System.out.println("Querying "+uuids.size()+" uuids"); }
		for (final LLUUID uuid: uuids) {
			final LLSDMap map=new LLSDMap();
			map.put("fetch_items",new LLSDBoolean(true));
			map.put("fetch_folders",new LLSDBoolean(true));
			map.put("folder_id",new LLSDUUID(uuid));
			array.add(map);
		}

		final LLSDMap result=bot.getCAPS().invokeCAPS("FetchInventoryDescendents2","",document);
		final LLSDArray folders=(LLSDArray) result.get("folders");
		if (debugqueries) { System.out.println("Outer array:"+folders.get().size()); }
		for (final Atomic a: folders.get()) {
			final LLSDMap innermap=(LLSDMap) a;
			final LLSDArray inneritems=(LLSDArray) innermap.get("items");
			if (debugqueries) { System.out.println("Inner items:"+inneritems.get().size()); }
			for (final Atomic itemmap: inneritems.get()) {
				final LLSDMap item=(LLSDMap) itemmap;
				final LLUUID item_id=((LLSDUUID) item.get("item_id")).toLLUUID();
				final LLUUID parent_id=((LLSDUUID) item.get("parent_id")).toLLUUID();
				final String name=item.get("name").toString();
				final LLUUID asset_id=((LLSDUUID) item.get("asset_id")).toLLUUID();
				final int type=((LLSDInteger) (item.get("type"))).get();
				final int inv_type=((LLSDInteger) (item.get("inv_type"))).get();
				final String desc=item.get("desc").toString();
				processItem(item_id,parent_id,name,asset_id,type,inv_type,desc);
			}
			final LLSDArray innercategories=(LLSDArray) innermap.get("categories");
			if (debugqueries) { System.out.println("Inner categories:"+innercategories.get().size()); }
			for (final Atomic categorymap: innercategories.get()) {
				final LLSDMap category=(LLSDMap) categorymap;
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
	@CmdHelp(description="Dump inventory map to the console")
	public String inventoryDumpCommand(final CommandEvent event) {
		synchronized (inventorytree) {
			String map="";
			map=map+inventoryDump(bot.getInventoryRoot(),"");
			System.out.println(map);
			return "Dumped to console.";
		}
	}

	@Nonnull
	private String inventoryDump(final LLUUID parent,
	                             final String prefix) {
		final StringBuilder ret=new StringBuilder();
		final Set<LLUUID> children=inventorytree.get(parent);
		if (children==null || children.isEmpty()) { return ""; }
		for (final LLUUID item: children) {
			ret.append(prefix);
			final InventoryAtom child=inventory.get(item);
			if (child instanceof InventoryCategory) {
				final InventoryCategory i=(InventoryCategory) child;
				ret.append(i.name).append("\n");
				ret.append(inventoryDump(i.id,prefix+"  "));
			}
			if (child instanceof InventoryItem) {
				final InventoryItem i=(InventoryItem) child;
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
