package necrosis.fasterbridge.player.classes;

import cornerlesscube.craftkit.utils.Stopwatch;
import org.bukkit.Location;

public class GameClass {

    private boolean inGame;
    private String inArena;
    private int slot;
    private Location startLoc;
    private final Stopwatch stopwatch;

    public GameClass(){
        this.inGame = false;
        this.inArena = "";
        this.slot = 0;
        this.startLoc = null;
        this.stopwatch = new Stopwatch();
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
}
