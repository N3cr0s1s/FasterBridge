package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetHandBlockSubCommand implements SubCommandInterface {
    @Override
    public String name() {
        return "setblock";
    }

    @Override
    public String description() {
        return FasterBridge.instance.cfs("messages.setBlock.desc");
    }

    @Override
    public String syntax() {
        return "/fb setblock <player>\n/fb setblock";
    }

    @Override
    public String permission() {
        return "fasterbridge.setblock";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.getItemInHand() == null || player.getItemInHand().getType().equals(Material.AIR)){
            player.sendMessage(FasterBridge.instance.cfs("messages.setBlock.air"));
            return;
        }
        Player getPlayer = null;
        try{
            getPlayer = Bukkit.getPlayer(args[1]);
            PlayerClass playerClass = FasterBridge.instance.getPlayerManager().getPlayerClass(getPlayer);
            playerClass.setBlock(player.getItemInHand());
            player.sendMessage(setPlaceholders(FasterBridge.instance.cfs("messages.setBlock.succ"),player.getItemInHand()));
            FasterBridge.instance.getConfigManager().getSavedBlocksConfig().saveBlock(player.getItemInHand());
        }catch (Exception ignored){
            PlayerClass playerClass = FasterBridge.instance.getPlayerManager().getPlayerClass(player);
            playerClass.setBlock(player.getItemInHand());
            player.sendMessage(setPlaceholders(FasterBridge.instance.cfs("messages.setBlock.succ"),player.getItemInHand()));
            FasterBridge.instance.getConfigManager().getSavedBlocksConfig().saveBlock(player.getItemInHand());
        }
    }

    private String setPlaceholders(String message, ItemStack itemStack){
        while (message.contains("%material%") || message.contains("%displayName%")){
            message = message.replace("%material%",itemStack.getType().toString());
            message = message.replace("%displayName%",itemStack.getItemMeta().getDisplayName());
        }
        return message;
    }
}
