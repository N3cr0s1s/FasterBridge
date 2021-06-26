package necrosis.fasterbridge.events.playerEvents;

import cornerlesscube.craftkit.ExcludeFromAutoRegister;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
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
    public void onPlayerLeave(PlayerQuitEvent event) throws MaxSlotException, ArenaNotFoundException, NotInArenaException {
        if(this.plugin.getPlayerManager().getPlayerClass(event.getPlayer()).getGame().isInGame()) {
            this.plugin.getArenaManager().player().getArenaPlayerLeave().leaveArena(event.getPlayer());
        }
        if(this.plugin.getPlayerManager().getPlayerClass(event.getPlayer()).getEditor().isInEditor()) {
            this.plugin.getArenaManager().player().getArenaPlayerLeaveEditor().leaveEditor(event.getPlayer());
        }
        plugin.getPlayerManager().removePlayerClass(event.getPlayer());
    }
}
