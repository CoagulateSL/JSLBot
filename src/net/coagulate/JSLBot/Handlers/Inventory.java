package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import java.io.IOException;
import java.util.*;

import static java.util.logging.Level.SEVERE;

/** Manage the inventory.
 *
 * @author Iain Price
 */
public class Inventory extends Handler implements Runnable {

    public Inventory(JSLBot bot, Configuration config) {
        super(bot, config);
    }

    @Override
    public void loggedIn() {
        inventorytree.clear(); inventory.clear(); inventorycomplete=false;
        downloadqueue.add(bot.getInventoryRoot());
        Thread t=new Thread(this);
        t.setName("Inventory download thread");
        log.fine("Inventory download initiated.");
        t.start();
    }
    
    private final Map<LLUUID,Set<LLUUID>> inventorytree=new HashMap<>(); // parent id -> set of descendants - items OR folders
    private final Map<LLUUID,InventoryAtom> inventory=new HashMap<>();

    
    private final Set<LLUUID> downloadqueue=new HashSet<>();
    @Override
    /** Start the inventory downloader thread. */
    public void run() {
        while (!downloadqueue.isEmpty()) {
            Set<LLUUID> download=new HashSet<>();
            Iterator<LLUUID> i=downloadqueue.iterator();
            download.addAll(downloadqueue); downloadqueue.clear();
            try { fetchInventory(download); }
            catch (IOException e) { log.log(SEVERE,"Inventory download gave IO exception",e); }
            if (inventorytree.size()==0) { log.fine("Inventory download complete - there is no inventory (?)"); break; }
            int percent=Math.round((100*inventorytree.size())/(inventorytree.size()+downloadqueue.size()));
            log.fine("Inventory download: ["+percent+"%] "+inventorytree.size()+" branches complete, "+downloadqueue.size()+" to go ("+inventory.size()+" elements)");
        }
        log.info("Inventory download complete");
        inventorycomplete=true;
    }

    private boolean inventorycomplete=false;
    
    
    
    public static class InventoryAtom {}
    public class InventoryItem extends InventoryAtom {
        final LLUUID id;
        final LLUUID parent;
        final String name;
        final String desc;
        final LLUUID assetid;
        final int type;
        final int invtype;
        private InventoryItem(LLUUID id, LLUUID parent, String name, String desc, LLUUID assetid, int type, int invtype) {
            this.id=id;
            this.parent=parent;
            this.name=name;
            this.desc=desc;
            this.assetid=assetid;
            this.type=type;
            this.invtype=invtype;
        }
    }
    public class InventoryCategory extends InventoryAtom {
        public final int type;
        public final LLUUID agentid;
        public final LLUUID id;
        public final LLUUID parentid;
        public final String name;
        public final int version;
        public InventoryCategory(int type,LLUUID agentid,LLUUID id,LLUUID parentid,String name,int version) {
            this.type=type;
            this.agentid=agentid;
            this.id=id;
            this.parentid=parentid;
            this.name=name;
            this.version=version;
        }
    }
    
    private void processCategory(int type,LLUUID agentid,LLUUID id,LLUUID parentid,String name,int version) {
        synchronized(inventory) {
            inventory.put(id,new InventoryCategory(type, agentid, id, parentid, name, version));
        }
        addInventoryChild(parentid,id);
    }
    
    private void addInventoryChild(LLUUID parentid,LLUUID id) {
        synchronized(inventorytree) {
            Set<LLUUID> children = inventorytree.get(parentid);
            if (children==null) { children=new HashSet<>(); }
            children.add(id);
            inventorytree.put(parentid,children);
        }
    }

    private void processItem(LLUUID id, LLUUID parent, String name, LLUUID assetid, int type, int invtype, String desc) {
        synchronized(inventory) {
            inventory.put(id,new InventoryItem(id,parent,name,desc,assetid,type,invtype));
        }
        addInventoryChild(parent,id);
    }
    
    private void fetchInventory(LLUUID uuid) throws IOException { Set<LLUUID> uuids=new HashSet<>(); uuids.add(uuid); fetchInventory(uuids); }
    private void fetchInventory(Set<LLUUID> uuids) throws IOException {
        final boolean debugqueries=false;
        LLSDMap outer=new LLSDMap();
        LLSD document=new LLSD(outer);
        LLSDArray array=new LLSDArray(); 
        outer.put("folders",array);
        // "a body containing a map, with one key - "folders", whose value is an array of maps, each with one key, "folder_id", containing the caterogy you want info about
        // sounds fun
        
        Set<LLUUID> query=new HashSet<>();
        if (debugqueries) { System.out.println("Querying "+uuids.size()+" uuids"); }
        for (LLUUID uuid:uuids) {
            LLSDMap map=new LLSDMap();        
            map.put("fetch_items",new LLSDBoolean(true));
            map.put("fetch_folders",new LLSDBoolean(true));
            map.put("folder_id",new LLSDUUID(uuid));
            array.add(map);
        }

        LLSDMap result = bot.getCAPS().invokeCAPS("FetchInventoryDescendents2", "", document);
        LLSDArray folders=(LLSDArray) result.get("folders");
        if (debugqueries) { System.out.println("Outer array:"+folders.get().size()); }
        for (Atomic a:folders.get()) {
            LLSDMap innermap=(LLSDMap) a;
            LLSDArray inneritems=(LLSDArray)innermap.get("items");
            if (debugqueries) { System.out.println("Inner items:"+inneritems.get().size()); }
            for (Atomic itemmap:inneritems.get()) {
                LLSDMap item=(LLSDMap)itemmap;
                LLUUID item_id=((LLSDUUID)item.get("item_id")).toLLUUID();
                LLUUID parent_id=((LLSDUUID)item.get("parent_id")).toLLUUID();
                String name= item.get("name").toString();
                LLUUID asset_id=((LLSDUUID)item.get("asset_id")).toLLUUID();
                int type=((LLSDInteger)(item.get("type"))).get();
                int inv_type=((LLSDInteger)(item.get("inv_type"))).get();
                String desc= item.get("desc").toString();
                processItem(item_id,parent_id,name,asset_id,type,inv_type,desc);
            }
            LLSDArray innercategories=(LLSDArray) innermap.get("categories");
            if (debugqueries) { System.out.println("Inner categories:"+innercategories.get().size()); }
            for (Atomic categorymap:innercategories.get()) {
                LLSDMap category=(LLSDMap) categorymap;
                //System.out.println("---------------");
                //for (String s:item.keys()) { System.out.println(s+"="+item.get(s).toString()); }
                //System.out.println(category.toXML());
                query.add(new LLUUID(category.get("category_id").toString()));
                int type_default=((LLSDInteger)(category.get("type_default"))).get();
                LLUUID agent_id=((LLSDUUID)(category.get("agent_id"))).toLLUUID();
                LLUUID category_id=((LLSDUUID)(category.get("category_id"))).toLLUUID();
                LLUUID parent_id=((LLSDUUID)(category.get("parent_id"))).toLLUUID();
                String name= category.get("name").toString();
                int version=((LLSDInteger)(category.get("version"))).get();
                processCategory(type_default, agent_id, category_id, parent_id, name, version);
            }
        }
        if (query.isEmpty()) {
            if (debugqueries) { System.out.println("No new queries to generate"); }
        } else { 
            if (debugqueries) { System.out.println("Generated new queries:"+query.size()); }
            downloadqueue.addAll(query);
        }
    }
 
    @CmdHelp(description="Dump inventory map to the console")
    public String inventoryDumpCommand(CommandEvent event) {
        synchronized(inventorytree) {
            String map="";
            map=map+inventoryDump(bot.getInventoryRoot(),"");
            System.out.println(map);
            return "Dumped to console.";
        }
    }
    
    private String inventoryDump(LLUUID parent,String prefix) {
        String ret="";
        Set<LLUUID> children = inventorytree.get(parent);
        if (children==null || children.isEmpty()) { return ""; }
        for (LLUUID item:children) {
            ret=ret+prefix;
            InventoryAtom child=inventory.get(item);
            if (child instanceof InventoryCategory) {
                InventoryCategory i = (InventoryCategory)child;
                ret+=i.name+"\n";
                ret+=inventoryDump(i.id,prefix+"  ");
            }
            if (child instanceof InventoryItem) {
                InventoryItem i = (InventoryItem)child;
                ret+="-"+i.name+" ["+i.desc+"]\n";
            }
        }
        return ret;
    }
}
