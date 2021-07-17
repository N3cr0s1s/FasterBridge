package necrosis.fasterbridge.config.configFiles;

import cornerlesscube.craftkit.utils.file.yaml.YamlClass;
import cornerlesscube.craftkit.utils.file.yaml.exceptions.FileAlreadyExistException;
import necrosis.fasterbridge.FasterBridge;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class SavedBlocksConfig {
    private final String configName = "savedBlocks";

    private final FasterBridge plugin;
    private final YamlClass blockConfig;

    private List<BlockObject> blocks = new ArrayList<>();

    public SavedBlocksConfig(FasterBridge plugin){
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

    public SavedBlocksConfig saveBlock(ItemStack item){
        //  Add to blocks
        this.blocks.add(new BlockObject(item.getItemMeta().getDisplayName(),item.getType()));

        //  Get block number
        int blockNumber = this.blockConfig.YamlConfig.getConfigurationSection("").getKeys(false).size()-1;

        //  If don't have custom name,
        //  name equals the type
        if(item.getItemMeta().getDisplayName().equals("") || item.getItemMeta().getDisplayName() == null){
            //  Write config
            this.blockConfig.write(blockNumber + ".name",item.getType().name());
            this.blockConfig.write(blockNumber + ".material",item.getType().toString());
        }else{
            //  Write config
            this.blockConfig.write(blockNumber + ".name",item.getItemMeta().getDisplayName());
            this.blockConfig.write(blockNumber + ".material",item.getType().toString());
        }

        return this;
    }

    public List<BlockObject> getBlocks(){
        List<BlockObject> n = new ArrayList<>();
        for(String section:this.blockConfig.YamlConfig.getConfigurationSection("").getKeys(false)){

            //  Exclude CraftKit config manager
            //  annotation from yaml
            if(section.contains("hashMap-Name_")) continue;

            //  Create a BlockObject
            //  and add to temp List
            n.add(new BlockObject(
                    String.valueOf(this.blockConfig.read(section+".name")),
                    Material.getMaterial(String.valueOf(this.blockConfig.read(section + ".material")))
            ));
        }
        return n;
    }

    public class BlockObject{
        private String displayName;
        private Material material;
        BlockObject(String displayName, Material material){
            this.displayName = displayName;
            this.material = material;
        }

        public String getName(){
            return ChatColor.translateAlternateColorCodes('&',this.displayName);
        }

        public String getMaterial(){
            return this.material.toString();
        }
    }
}
