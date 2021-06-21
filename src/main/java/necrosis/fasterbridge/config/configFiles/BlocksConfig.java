package necrosis.fasterbridge.config.configFiles;

import cornerlesscube.craftkit.utils.file.yaml.YamlClass;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public final class BlocksConfig {

    private final String configName = "blockConfig";

    private final FasterBridge plugin;
    private final YamlClass blockConfig;

    public BlocksConfig(FasterBridge plugin){
        this.plugin = plugin;
        YamlClass tempConfig;
        try {
            tempConfig = plugin.configYml().getYamlClass(configName);
        }catch (Exception e){
            tempConfig = plugin.configYml().createYaml(configName);
        }
        this.blockConfig = tempConfig;
        tempConfig=null;
    }

    public boolean isBlockSet(Player player){
        PlayerClass playerClass = plugin.getPlayerManager().getPlayerClass(player);
        if(blockConfig.YamlConfig.isSet(String.valueOf(playerClass.getPlayerUUID()))) return true;
        else if(!blockConfig.YamlConfig.isSet(String.valueOf(playerClass.getPlayerUUID()))) return false;
        return false;
    }

    public void setBlock(Player player, Material block){
        PlayerClass playerClass = plugin.getPlayerManager().getPlayerClass(player);
        this.blockConfig.write(String.valueOf(playerClass.getPlayerUUID()),block.name());
    }

    public ItemStack getBlock(Player player){
        PlayerClass playerClass = plugin.getPlayerManager().getPlayerClass(player);
        Material material = Material.getMaterial((String)this.blockConfig.read(String.valueOf(playerClass.getPlayerUUID())));
        return new ItemStack(Objects.requireNonNull(material),64);
    }

}
