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

    public void startActionTimer(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        if(CraftKit.getInstance().getNms().getNmsVersion().contains("v1_8")){
            playerClass.getGame().setBukkitTask(
                    new BukkitRunnable() {
                        public void run() {
                            String message = "§f§l[ §r§8"+ playerClass.getGame().getStopwatch().toDouble() + " §f§l]";
                            FasterBridge.instance.getUtilsManager().getActionBarUtil().sendActionBarMessage(player,message);
                        }
                    }.runTaskTimer(this.plugin, 0, 5)
            );
        }else{
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

    public void stopActionTimer(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        playerClass.getGame().getBukkitTask().cancel();
    }
}
