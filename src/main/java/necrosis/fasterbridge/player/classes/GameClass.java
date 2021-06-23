package necrosis.fasterbridge.player.classes;

import cornerlesscube.craftkit.utils.Stopwatch;
import necrosis.fasterbridge.arena.ArenaClass;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class GameClass {

    private boolean inGame;
    private String inArena;
    private int slot;
    private Location startLoc;
    private final Stopwatch stopwatch;
    private ItemStack[] prevInv;

    public GameClass(){
        this.inGame = false;
        this.inArena = "";
        this.slot = 0;
        this.startLoc = null;
        this.stopwatch = new Stopwatch();
        this.prevInv = null;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getInArena() {
        return inArena;
    }

    public void setInArena(String inArena) {
        this.inArena = inArena;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Location getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Location startLoc) {
        this.startLoc = startLoc;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    public ItemStack[] getPrevInv() {
        return prevInv;
    }

    public void setPrevInv(ItemStack[] prevInv) {
        this.prevInv = prevInv;
    }
}
