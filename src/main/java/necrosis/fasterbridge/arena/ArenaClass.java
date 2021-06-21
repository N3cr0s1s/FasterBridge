package necrosis.fasterbridge.arena;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.Location;

public class ArenaClass {

    private final FasterBridge plugin;
    private final String arenaName;
    private final int maxPlayer;
    private boolean isActive;
    private Location[] slotLocation;
    private int deathZoneVertical;
    private int deathZoneHorizontal;
    private Direction direction;

    public ArenaClass(String arenaName,int maxPlayer,Location[] locations,FasterBridge plugin){
        this.plugin = plugin;
        this.arenaName = arenaName;
        this.maxPlayer = maxPlayer;
        this.slotLocation = locations;
        this.isActive = false;
        this.deathZoneHorizontal = 10;
        this.deathZoneHorizontal = 5;
        this.direction = Direction.NORTH;
    }

    public ArenaClass(String arenaName,int maxPlayer,FasterBridge plugin){
        this.plugin = plugin;
        this.arenaName = arenaName;
        this.maxPlayer = maxPlayer;
        this.isActive = false;
        this.slotLocation = new Location[maxPlayer];
        this.deathZoneHorizontal = 10;
        this.deathZoneHorizontal = 5;
        this.direction = Direction.NORTH;
    }

    public ArenaClass(String arenaName,int maxPlayer,boolean isActive,Location[] locations,int deathZoneHorizontal,int deathZoneVertical,Direction direction,FasterBridge plugin){
        this.plugin = plugin;
        this.arenaName = arenaName;
        this.maxPlayer = maxPlayer;
        this.isActive = isActive;
        this.slotLocation = locations;
        this.deathZoneHorizontal = deathZoneHorizontal;
        this.deathZoneHorizontal = deathZoneVertical;
        this.direction = direction;
    }

    public String getArenaName() {
        return arenaName;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public Location[] getSlotLocation() {
        return slotLocation;
    }

    public ArenaClass setSlotLocation(Location[] slotLocation) {
        this.slotLocation = slotLocation;
        this.save();
        return this;
    }

    public ArenaClass setSlotLocation(int slot,Location slotLocation) throws MaxSlotException{
        if(slot > this.getMaxPlayer()){
            throw new MaxSlotException("Slot number is bigger than max player.",this.arenaName,this.maxPlayer,slot);
        }
        this.slotLocation[slot] = slotLocation;
        this.save();
        return this;
    }

    public Location getSlotLocation(int slot) throws MaxSlotException{
        if(slot > this.getMaxPlayer()){
            throw new MaxSlotException("Slot number is bigger than max player.");
        }
        return getSlotLocation()[slot];
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        this.save();
    }

    public ArenaClass save(){
        try {
            this.plugin.getConfigManager().getArenasConfig().saveArena(this.arenaName);
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getDeathZoneVertical() {
        return deathZoneVertical;
    }

    public void setDeathZoneVertical(int deathZoneVertical) {
        this.deathZoneVertical = deathZoneVertical;
    }

    public int getDeathZoneHorizontal() {
        return deathZoneHorizontal;
    }

    public void setDeathZoneHorizontal(int deathZoneHorizontal) {
        this.deathZoneHorizontal = deathZoneHorizontal;
    }

    public ArenaClass register(){
        return plugin.getArenaManager().registerArena(this.arenaName,this);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
