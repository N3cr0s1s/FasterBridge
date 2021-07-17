package necrosis.fasterbridge.arena;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.arenaCreatorFunction.ArenaEditorClass;
import necrosis.fasterbridge.arena.arenaPlayerFunction.ArenaPlayerClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;

import java.util.HashMap;

public final class ArenaManager {

    private final FasterBridge plugin;

    private HashMap<String,ArenaClass> arenaMap = new HashMap<String,ArenaClass>();

    private final ArenaEditorClass editorClass;
    private final ArenaPlayerClass playerClass;

    public ArenaManager(FasterBridge plugin){
        this.plugin = plugin;

        this.editorClass = new ArenaEditorClass(plugin);
        this.playerClass = new ArenaPlayerClass(plugin);
    }

    //  Get arenas hash map
    public HashMap<String,ArenaClass> getArenaMap(){
        return arenaMap;
    }

    //  Register arena
    //  Add to arenas hash map
    public ArenaClass registerArena(String arenaName,ArenaClass arenaClass){
        this.arenaMap.put(arenaName,arenaClass);
        return arenaClass;
    }

    //  Unregister arena
    //  Remove arena from hash map
    public void unloadArena(String arenaName) throws ArenaNotFoundException {
        if(!isArenaExist(arenaName))throw new ArenaNotFoundException("Arena does not exist!");
        this.arenaMap.remove(arenaName);
    }

    //  Get arena by name
    public ArenaClass getArena(String arenaName) throws ArenaNotFoundException {
        if(!this.arenaMap.containsKey(arenaName)) throw new ArenaNotFoundException("Arena does not exist.");
        return this.arenaMap.get(arenaName);
    }

    //  Get editor classes/methods
    public ArenaEditorClass editor() {
        return editorClass;
    }

    //  Get player classes/methods
    public ArenaPlayerClass player() {
        return playerClass;
    }

    public boolean isArenaExist(String arenaName){
        if(this.arenaMap.containsKey(arenaName)){
            return true;
        }
        return false;
    }
}
