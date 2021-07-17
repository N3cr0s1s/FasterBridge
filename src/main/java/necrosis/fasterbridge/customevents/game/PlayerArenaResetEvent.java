package necrosis.fasterbridge.customevents.game;

import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerArenaResetEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Variables
    private final Player player;
    private final String arenaName;
    private final PlayerClass playerClass;
    private final ArenaClass arenaClass;

    public PlayerArenaResetEvent(Player player,String arenaName,PlayerClass playerClass,ArenaClass arenaClass){
        this.player     =   player;
        this.arenaName  =   arenaName;
        this.playerClass=   playerClass;
        this.arenaClass =   arenaClass;
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
}
