package necrosis.fasterbridge.game.methods;

import cornerlesscube.craftkit.CraftKit;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.player.PlayerClass;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameActionTimer {

    private final FasterBridge plugin;

    public GameActionTimer(FasterBridge plugin){
        this.plugin = plugin;
    }

    //  Start player action timer
    //  to see his timer
    public void startActionTimer(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);

        //  If version under 9
        //  Use NMS action bar
        if(this.plugin.getVersionManager().getVersion() < 9){
            //  Define repeatable bukkit task
            playerClass.getGame().setBukkitTask(
                    new BukkitRunnable() {
                        public void run() {
                            String message = "§f§l[ §r§8"+ playerClass.getGame().getStopwatch().toDouble() + " §f§l]";
                            FasterBridge.instance.getUtilsManager().getActionBarUtil().sendActionBarMessage(player,message);
                        }
                    }.runTaskTimer(this.plugin, 0, 5)
            );
        }else{
            //  Above 1.8^

            //  Define repeatable bukkit task
            playerClass.getGame().setBukkitTask(
                    new BukkitRunnable() {
                        public void run() {
                            String message = "§f§l[ §r§8"+ playerClass.getGame().getStopwatch().toDouble() + " §f§l]";
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                        }
                    }.runTaskTimer(this.plugin, 0, 5)
            );
        }
    }

    //  Stop action timer for player
    public void stopActionTimer(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        playerClass.getGame().getBukkitTask().cancel();
    }
}
