package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;

public class ArenaCreatorDelete {
    private final FasterBridge plugin;

    public ArenaCreatorDelete(FasterBridge plugin){
        this.plugin = plugin;
    }

    public void deleteArena(String arenaName) throws ArenaNotFoundException {
        if(!this.plugin.getArenaManager().isArenaExist(arenaName)) throw new ArenaNotFoundException("Arena does not exist!");
        this.plugin.getConfigManager().getArenasConfig().deleteArena(arenaName);
        this.plugin.getArenaManager().unloadArena(arenaName);
    }
}
