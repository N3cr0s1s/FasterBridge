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

    public HashMap<String,ArenaClass> getArenaMap(){
        return arenaMap;
    }

    public ArenaClass registerArena(String arenaName,ArenaClass arenaClass){
        this.arenaMap.put(arenaName,arenaClass);
        return arenaClass;
    }

    public void unloadArena(String arenaName) throws ArenaNotFoundException {
        if(!isArenaExist(arenaName))throw new ArenaNotFoundException("Arena does not exist!");
        this.arenaMap.remove(arenaName);
    }

    public ArenaClass getArena(String arenaName) throws ArenaNotFoundException {
        if(!this.arenaMap.containsKey(arenaName)) throw new ArenaNotFoundException("Arena does not exist.");
        return this.arenaMap.get(arenaName);
    }

    public ArenaEditorClass editor() {
        return editorClass;
    }

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
