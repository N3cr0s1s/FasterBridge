package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ArenaCreatorSetLocation {

    private final FasterBridge plugin;

    public ArenaCreatorSetLocation(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass setLocation(String arenaName, Location location,int slot) throws ArenaNotFoundException, MaxSlotException {
        ArenaClass arena = plugin.getArenaManager().getArena(arenaName);
        arena.setSlotLocation(slot,location);
        return arena;
    }

    public ArenaClass setLocation(String arenaName, Player player,int slot) throws MaxSlotException, ArenaNotFoundException {
        return this.setLocation(arenaName,player.getLocation(),slot);
    }

    public ArenaClass setLocation(ArenaClass arenaClass,Location location,int slot) throws MaxSlotException, ArenaNotFoundException {
        return this.setLocation(arenaClass.getArenaName(),location,slot);
    }

    public ArenaClass setLocation(ArenaClass arenaClass, Player player,int slot) throws MaxSlotException, ArenaNotFoundException {
        return this.setLocation(arenaClass.getArenaName(),player.getLocation(),slot);
    }
}
