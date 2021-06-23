package necrosis.fasterbridge.arena.arenaCreatorFunction;

import necrosis.fasterbridge.FasterBridge;

public class ArenaEditorClass {

    private final FasterBridge plugin;

    //  CLASSES
    private final ArenaCreatorSetLocation setLocation;
    private final ArenaCreatorCreateArena createArena;
    private final ArenaCreatorArenaValidator validateArena;

    public ArenaEditorClass(FasterBridge plugin){
        this.plugin = plugin;

        this.setLocation    =       new ArenaCreatorSetLocation(plugin);
        this.createArena    =       new ArenaCreatorCreateArena(plugin);
        this.validateArena  =       new ArenaCreatorArenaValidator(plugin);
    }

    public ArenaCreatorSetLocation location() {
        return setLocation;
    }

    public ArenaCreatorCreateArena getCreateArena() {
        return createArena;
    }

    public ArenaCreatorArenaValidator getValidator() {
        return validateArena;
    }
}
