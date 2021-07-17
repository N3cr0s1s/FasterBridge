package necrosis.fasterbridge.ui.uis;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.config.configFiles.SavedBlocksConfig;
import necrosis.fasterbridge.player.PlayerClass;
import necrosis.fasterbridge.ui.ButtonInterface;
import necrosis.fasterbridge.ui.UIAbstract;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockSelectUI extends UIAbstract {

    private final FasterBridge plugin;

    public BlockSelectUI(FasterBridge plugin){
        this.plugin = plugin;
    }

    @Override
    public String invDisplayName() {
        return plugin.cfs("ui.block-selector.title");
    }

    @Override
    public int invRows() {
        return 54;
    }

    @Override
    public List<ButtonInterface> buttons(Player player) {
        //  Temp List
        List<ButtonInterface> list = new ArrayList<>();

        //  Get all blocks from config
        for(int i = 0; i<this.plugin.getConfigManager().getSavedBlocksConfig().getBlocks().size(); i++){
            if(i >= 54) continue;
            //  Create a new button,
            //  to make selectable objects
            int finalI = i+1;
            SavedBlocksConfig.BlockObject blockObject = this.plugin.getConfigManager().getSavedBlocksConfig().getBlocks().get(i);
            list.add(new ButtonInterface() {
                @Override
                public int slot() {
                    return finalI;
                }

                @Override
                public ItemStack item(Player player) {
                    return FasterBridge.instance.getUiManager().createItem(
                            Material.getMaterial(blockObject.getMaterial()),
                            blockObject.getName(),
                            false,
                            new String[]{}
                    );
                }

                @Override
                public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                    PlayerClass playerClass = FasterBridge.instance.getPlayerManager().getPlayerClass(player);
                    playerClass.setBlock(Material.getMaterial(blockObject.getMaterial()), blockObject.getName());
                    player.sendMessage(FasterBridge.instance.cfs("ui.block-selector.succ","%blockName%",blockObject.getName()));

                    //  Update inventory if in game
                    if(playerClass.getGame().isInGame()){
                        player.getInventory().setItem(
                                //  Slot
                                FasterBridge.instance.getConfig().getInt("config.gadgets-slot.player-block-slot"),
                                //  Block
                                playerClass.getBlock()
                                );
                    }
                }
            });
        }
        return list;
    }
}
