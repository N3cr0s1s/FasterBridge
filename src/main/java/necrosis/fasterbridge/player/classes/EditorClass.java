package necrosis.fasterbridge.player.classes;

import necrosis.fasterbridge.arena.ArenaClass;
import org.bukkit.inventory.ItemStack;

public class EditorClass {

    private boolean inEditor;
    private String inArena;
    private ArenaClass arenaClass;
    private ItemStack[] prevInv;

    public EditorClass(){
        this.inEditor = false;
        this.inArena = "";
        this.arenaClass = null;
        this.prevInv = null;
    }

    public boolean isInEditor() {
        return inEditor;
    }

    public void setInEditor(boolean inEditor) {
        this.inEditor = inEditor;
    }

    public String getInArena() {
        return inArena;
    }

    public void setInArena(String inArena) {
        this.inArena = inArena;
    }

    public ArenaClass getArenaClass() {
        return arenaClass;
    }

    public void setArenaClass(ArenaClass arenaClass) {
        this.arenaClass = arenaClass;
    }

    public ItemStack[] getPrevInv() {
        return prevInv;
    }

    public void setPrevInv(ItemStack[] prevInv) {
        this.prevInv = prevInv;
    }

    public ArenaClass setEditor(ArenaClass arenaClass) {
        this.inEditor = true;
        this.inArena = arenaClass.getArenaName();
        this.arenaClass = arenaClass;
        return arenaClass;
    }

    public void removeEditor(){
        this.inEditor = false;
        this.inArena = "";
        this.arenaClass = null;
    }

    public void leaveEditor(){
        this.inEditor = false;
        this.inArena = "";
        this.arenaClass = null;
        this.prevInv = null;
    }
}
