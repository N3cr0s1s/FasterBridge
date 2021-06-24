package necrosis.fasterbridge.arena;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.AllSlotSetException;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.FullSlotException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ArenaClass {

    private final FasterBridge plugin;
    private final String arenaName;
    private final int maxPlayer;
    private boolean isActive;
    private Location[] slotLocation;
    private int deathZoneVertical;
    private int deathZoneHorizontal;
    private Direction direction;
    private boolean[] slots;

    public ArenaClass(String arenaName,int maxPlayer,Location[] locations,FasterBridge plugin){
        this.plugin = plugin;
        this.arenaName = arenaName;
        this.maxPlayer = maxPlayer;
        this.slotLocation = locations;
        this.isActive = false;
        this.deathZoneHorizontal = 10;
        this.deathZoneHorizontal = 5;
        this.direction = Direction.NORTH;
        this.slots = new boolean[maxPlayer];
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
        this.slots = new boolean[maxPlayer];
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
        this.slots = new boolean[maxPlayer];
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
        if(slot >= this.getMaxPlayer()) throw new MaxSlotException("Slot number is bigger than max player.",this.arenaName,this.maxPlayer,slot);
        if(this.slotLocation.length <= slot) throw new MaxSlotException("Slot number is bigger than max player.",this.arenaName,this.maxPlayer,slot);
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
        } catch (MaxSlotException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getDeathZoneVertical() {
        return deathZoneVertical;
    }

    public ArenaClass setDeathZoneVertical(int deathZoneVertical) {
        this.deathZoneVertical = deathZoneVertical;
        return this;
    }

    public int getDeathZoneHorizontal() {
        return deathZoneHorizontal;
    }

    public ArenaClass setDeathZoneHorizontal(int deathZoneHorizontal) {
        this.deathZoneHorizontal = deathZoneHorizontal;
        return this;
    }

    public ArenaClass register(){
        return plugin.getArenaManager().registerArena(this.arenaName,this);
    }

    public Direction getDirection() {
        return direction;
    }

    public ArenaClass setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public ArenaClass setDirection(float yaw){
        this.setDirection(plugin.getUtilsManager().getDirectionCalculator().getDirection(yaw));
        return this;
    }

    public ArenaClass setDirection(Location location){
        this.setDirection(plugin.getUtilsManager().getDirectionCalculator().getDirection(location.getYaw()));
        return this;
    }

    public ArenaClass setDirection(Player player){
        this.setDirection(player.getLocation());
        return this;
    }

    public ArenaClass setSlot(int slot,boolean slotBool) throws MaxSlotException{
        if(slot>this.maxPlayer) throw new MaxSlotException("Slot is heigher than max player.",this.getArenaName(),this.getMaxPlayer(),slot);
        this.slots[slot] = slotBool;
        return this;
    }

    public boolean isFreeSlot(){
        for(boolean b:this.slots){
            if(!b){
                return true;
            }
        }
        return false;
    }

    public int getFreeSlot()throws FullSlotException {
        if(!this.isFreeSlot()) throw new FullSlotException("Arena don't have free slots",this);
        int slot = 0;
        for(boolean b:this.slots){
            if(b) return slot;
            slot++;
        }
        return slot;
    }

    public int getUnsetSlot()throws AllSlotSetException {
        for(int i = 0; i<maxPlayer; i++){
            if(this.slotLocation[i] == null){
                return i;
            }
        }
        throw new AllSlotSetException("All slot setted correctly.");
    }
}
