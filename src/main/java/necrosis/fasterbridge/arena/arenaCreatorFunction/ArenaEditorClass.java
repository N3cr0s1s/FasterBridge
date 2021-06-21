package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.arenaCreatorFunction.ArenaCreatorSetLocation;

public class ArenaEditorClass {

    private final FasterBridge plugin;

    //  CLASSES
    private final ArenaCreatorSetLocation setLocation;
    private final ArenaCreatorCreateArena createArena;

    public ArenaEditorClass(FasterBridge plugin){
        this.plugin = plugin;

        this.setLocation = new ArenaCreatorSetLocation(plugin);
        this.createArena = new ArenaCreatorCreateArena(plugin);
    }

    public ArenaCreatorSetLocation location() {
        return setLocation;
    }

    public ArenaCreatorCreateArena getCreateArena() {
        return createArena;
    }
}
