package necrosis.fasterbridge.customevents.game;

import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWinEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Event variables
    private final Player player;
    private final String arenaName;
    private final PlayerClass playerClass;
    private final ArenaClass arenaClass;
    private final double record;

    public PlayerWinEvent(Player player,String arena,PlayerClass playerClass,ArenaClass arenaClass,double record){
        this.player     =   player;
        this.arenaName  =   arena;
        this.playerClass=   playerClass;
        this.arenaClass =   arenaClass;
        this.record     =   record;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public String getArenaName() {
        return arenaName;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public ArenaClass getArenaClass() {
        return arenaClass;
    }

    public double getRecord() {
        return record;
    }
}
