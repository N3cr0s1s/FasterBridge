package necrosis.fasterbridge.customevents.creator;

import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.arena.Direction;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaCreateEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Event variables
    private String arenaName;
    private int maxPlayer;
    private boolean isActive;
    private Location[] locations;
    private int deathZoneHorizontal;
    private int deathZoneVertical;
    private Direction direction;

    public ArenaCreateEvent(String arenaName,int maxPlayer,boolean isActive,Location[] locations,int deathZoneHorizontal,int deathZoneVertical,Direction direction){
        this.arenaName          =   arenaName;
        this.maxPlayer          =   maxPlayer;
        this.isActive           =   isActive;
        this.locations          =   locations;
        this.deathZoneHorizontal=   deathZoneHorizontal;
        this.deathZoneVertical  =   deathZoneVertical;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public String getArenaName() {
        return arenaName;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public boolean isActive() {
        return isActive;
    }

    public Location[] getLocations() {
        return locations;
    }

    public int getDeathZoneHorizontal() {
        return deathZoneHorizontal;
    }

    public int getDeathZoneVertical() {
        return deathZoneVertical;
    }

    public Direction getDirection() {
        return direction;
    }
}
