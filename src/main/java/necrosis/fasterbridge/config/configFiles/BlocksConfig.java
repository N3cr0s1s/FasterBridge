package necrosis.fasterbridge.config.configFiles;

import cornerlesscube.craftkit.utils.file.yaml.YamlClass;
import cornerlesscube.craftkit.utils.file.yaml.exceptions.FileAlreadyExistException;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public final class BlocksConfig {

    private final String configName = "blockConfig";

    private final FasterBridge plugin;
    private final YamlClass blockConfig;

    public BlocksConfig(FasterBridge plugin){
        this.plugin = plugin;
        YamlClass tempConfig = null;
        try {
            tempConfig = plugin.configYml().getYamlClass(configName);
        }catch (Exception e){
            try {
                tempConfig = plugin.configYml().createYaml(configName);
            } catch (FileAlreadyExistException fileAlreadyExistException) {
                fileAlreadyExistException.printStackTrace();
            }
        }
        this.blockConfig = tempConfig;
    }

    public boolean isBlockSet(Player player){
        PlayerClass playerClass = plugin.getPlayerManager().getPlayerClass(player);
        if(blockConfig.YamlConfig.isSet(String.valueOf(playerClass.getPlayerUUID()))) return true;
        else if(!blockConfig.YamlConfig.isSet(String.valueOf(playerClass.getPlayerUUID()))) return false;
        return false;
    }

    public void setBlock(Player player, Material block,String displayName){
        PlayerClass playerClass = plugin.getPlayerManager().getPlayerClass(player);
        this.blockConfig.write(playerClass.getPlayerUUID() + ".material",block.name());
        this.blockConfig.write(playerClass.getPlayerUUID() + ".displayName",ChatColor.translateAlternateColorCodes('&',displayName));
    }

    public ItemStack getBlock(Player player){
        PlayerClass playerClass = plugin.getPlayerManager().getPlayerClass(player);
        Material material = Material.getMaterial(String.valueOf(this.blockConfig.read(playerClass.getPlayerUUID() + ".material")));
        ItemStack toReturn = new ItemStack(Objects.requireNonNull(material),64);
        ItemMeta meta = toReturn.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(ChatColor.translateAlternateColorCodes('&', String.valueOf(this.blockConfig.read(playerClass.getPlayerUUID() + ".displayName"))));
        toReturn.setItemMeta(meta);
        return toReturn;
    }

}
