package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;

public class ArenaPlayerClass {

    private final FasterBridge plugin;

    private final ArenaPlayerJoin arenaPlayerJoin;
    private final ArenaPlayerLeave arenaPlayerLeave;
    private final ArenaPlayerJoinEditor arenaPlayerJoinEditor;
    private final ArenaPlayerLeaveEditor arenaPlayerLeaveEditor;

    public ArenaPlayerClass(FasterBridge plugin){
        this.plugin = plugin;

        this.arenaPlayerJoin        =   new ArenaPlayerJoin(plugin);
        this.arenaPlayerLeave       =   new ArenaPlayerLeave(plugin);
        this.arenaPlayerJoinEditor  =   new ArenaPlayerJoinEditor(plugin);
        this.arenaPlayerLeaveEditor =   new ArenaPlayerLeaveEditor(plugin);
    }

    public ArenaPlayerJoin getArenaPlayerJoin() {
        return arenaPlayerJoin;
    }

    public ArenaPlayerLeave getArenaPlayerLeave() {
        return arenaPlayerLeave;
    }

    public ArenaPlayerJoinEditor getArenaPlayerJoinEditor() {
        return arenaPlayerJoinEditor;
    }

    public ArenaPlayerLeaveEditor getArenaPlayerLeaveEditor() {
        return arenaPlayerLeaveEditor;
    }
}
