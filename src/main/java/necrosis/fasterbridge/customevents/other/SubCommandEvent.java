package necrosis.fasterbridge.customevents.other;

import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SubCommandEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Event variables
    private final Player player;
    private final String command;

    public SubCommandEvent(Player player,String command){
        this.player     =   player;
        this.command    =   command;
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

    public String getCommand() {
        return command;
    }
}
