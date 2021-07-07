package necrosis.fasterbridge.events.editorEvents;

import necrosis.fasterbridge.FasterBridge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EditorEvents implements Listener {
    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent event){
        if(
                !FasterBridge.instance.getPlayerManager().getPlayerClass(event.getPlayer())
                .getEditor().isInEditor()
        ) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        if(
                !FasterBridge.instance.getPlayerManager().getPlayerClass(event.getPlayer())
                        .getEditor().isInEditor()
        ) return;
        event.setCancelled(true);
    }
}
