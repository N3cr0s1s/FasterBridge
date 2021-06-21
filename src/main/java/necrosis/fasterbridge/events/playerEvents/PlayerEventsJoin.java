package necrosis.fasterbridge.events.playerEvents;

import cornerlesscube.craftkit.ExcludeFromAutoRegister;
import necrosis.fasterbridge.FasterBridge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@ExcludeFromAutoRegister
public class PlayerEventsJoin implements Listener {

    private FasterBridge plugin;

    public PlayerEventsJoin(FasterBridge plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        plugin.getPlayerManager().createPlayerClass(event.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        plugin.getPlayerManager().removePlayerClass(event.getPlayer());
    }
}
