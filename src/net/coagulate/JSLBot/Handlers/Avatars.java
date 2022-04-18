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

    public Avatars(@Nonnull final JSLBot bot, final Configuration config) {
        super(bot, config);
    }

    private static final Map<LLUUID,String> resolvedUUIDs=new HashMap<>();
    private static final Object resolveUUIDLock=new Object();

    @Nonnull
    @JSLBot.CmdHelp(description = "Look up an Avatar's profile from UUID")
    public String resolveUUIDCommand(final CommandEvent command,
                                     @Nullable @JSLBot.Param(name = "uuid", description = "UUID of Avatar to look up, or comma separated list of UUIDs") final String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return "No UUID parameter passed.";
        }
        @Nonnull final List<String> param = new ArrayList<>();
        Collections.addAll(param, uuid.split(","));
        @Nonnull final StringBuilder output = new StringBuilder();
        for (@Nonnull final Map.Entry<String, String> entry : resolveUUIDStrings(param).entrySet()) {
            if (!output.isEmpty()) {
                output.append(" ");
            }
            output.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return output.toString();
    }

    @Nonnull
    public Map<String,String> resolveUUIDStrings(@Nonnull final List<String> uuids) {
        @Nonnull final List<LLUUID> converted = new ArrayList<>(uuids.size());
        for (final String uuid : uuids) {
            converted.add(new LLUUID(uuid));
        }
        @Nonnull final Map<String, String> map = new HashMap<>();
        for (@Nonnull final Map.Entry<LLUUID, String> entry : resolveUUIDs(converted).entrySet()) {
            map.put(entry.getKey().toUUIDString(), entry.getValue());
        }
        return map;
    }
    @Nonnull
    public Map<LLUUID,String> resolveUUIDs(@Nonnull final List<LLUUID> uuids) {
        @Nonnull final UUIDNameRequest req = new UUIDNameRequest();
        req.buuidnameblock = new ArrayList<>();
        @Nonnull final Map<LLUUID, String> map = new HashMap<>();
        for (final LLUUID uuid : uuids) {
            if (resolvedUUIDs.containsKey(uuid)) {
                map.put(uuid, resolvedUUIDs.get(uuid));
            } else {
                @Nonnull final UUIDNameRequest_bUUIDNameBlock lookup = new UUIDNameRequest_bUUIDNameBlock();
                lookup.vid = uuid;
                req.buuidnameblock.add(lookup);
            }
        }
        if (req.buuidnameblock.isEmpty()) {
            return map;
        }
        bot.send(req);
        boolean complete = false;
        @Nonnull final Date now=new Date();
        while (!complete && (new Date().getTime()-(now.getTime()))<5000) {
            try {
                synchronized (resolveUUIDLock) {
                    resolveUUIDLock.wait(1000);
                }
            }
            catch (@Nonnull final InterruptedException ignored) {}
            complete=true;
            for (final LLUUID exists : uuids) {
                if (!resolvedUUIDs.containsKey(exists)) {
                    complete = false;
                    break;
                }
            }
        }
        for (final LLUUID uuid : uuids) {
            map.put(uuid, resolvedUUIDs.getOrDefault(uuid, null));
        }
        return map;
    }

    public void uUIDNameReplyUDPImmediate(@Nonnull final UDPEvent event) {
        @Nonnull final UUIDNameReply reply = (UUIDNameReply) event.body();
        for (@Nonnull final UUIDNameReply_bUUIDNameBlock element : reply.buuidnameblock) {
            if ("Resident".equalsIgnoreCase(element.vlastname.toString())) {
                resolvedUUIDs.put(element.vid, element.vfirstname.toString());
            } else {
                resolvedUUIDs.put(element.vid, element.vfirstname + " " + element.vlastname);
            }
        }
        synchronized (resolveUUIDLock) {
            resolveUUIDLock.notifyAll();
        }
    }


}
