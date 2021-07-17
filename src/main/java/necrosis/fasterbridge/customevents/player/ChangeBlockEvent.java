package necrosis.fasterbridge.customevents.player;

import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChangeBlockEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Variables
    private final Player player;
    private final PlayerClass playerClass;
    private final Material material;
    private final String displayName;

    public ChangeBlockEvent(Player player,PlayerClass playerClass,Material material,String displayName){
        this.player     =   player;
        this.playerClass=   playerClass;
        this.material   =   material;
        this.displayName=   displayName;
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

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }
}
