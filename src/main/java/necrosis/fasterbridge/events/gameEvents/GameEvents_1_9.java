package necrosis.fasterbridge.events.gameEvents;

import cornerlesscube.craftkit.ExcludeFromAutoRegister;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

@ExcludeFromAutoRegister
public class GameEvents_1_9 implements Listener {

    //
    //  This events only active when
    //  version is above 1.8
    //

    private final FasterBridge plugin;

    public GameEvents_1_9(FasterBridge plugin){
        this.plugin     =   plugin;
    }

    //  Check player is in game
    public boolean inGame(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        if(!playerClass.getGame().isInGame()) return false;
        return true;
    }

    //  If player swap hand item
    //  then cancel it
    @EventHandler
    public void itemSwap(PlayerSwapHandItemsEvent event){
        if(!inGame(event.getPlayer())) return;
        event.setCancelled(true);
    }
}
