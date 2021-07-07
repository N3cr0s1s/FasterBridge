package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;

public class ArenaCreatorArenaValidator {

    private final FasterBridge plugin;

    public ArenaCreatorArenaValidator(FasterBridge plugin){
        this.plugin = plugin;
    }

    public boolean returnFalse(String message){
        this.plugin.logger().getError(message);
        return false;
    }

    public boolean isArenaValid(ArenaClass arena){
        if(arena.getArenaName() == null || arena.getArenaName().equals("")) return returnFalse("ArenaName is not correct/not set.");
        if(arena.getMaxPlayer() == 0) return returnFalse("Max player can't be lower than 1.");
        if(arena.getDirection() == null) return returnFalse("Arena direction is null.");
        for(int i = 0; i < arena.getMaxPlayer(); i++) {
            if (arena.getSlotLocation()[i] == null) return returnFalse("Slot location " + i + " not set.");
        }
        return true;
    }

    public boolean isArenaValid(String arenaName) throws ArenaNotFoundException {
        return this.isArenaValid(this.plugin.getArenaManager().getArena(arenaName));
    }
}
