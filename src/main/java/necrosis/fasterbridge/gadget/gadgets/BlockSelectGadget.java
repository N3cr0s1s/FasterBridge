package necrosis.fasterbridge.gadget.gadgets;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.gadget.GadgetAbstract;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BlockSelectGadget extends GadgetAbstract {
    @Override
    public String name() {
        return "blockSelectorGadget";
    }

    @Override
    public Material itemMaterial() {
        return Material.getMaterial(FasterBridge.instance.getConfig().getString("gadgets.block-selector.material"));
    }

    @Override
    public String displayName() {
        return FasterBridge.instance.cfs("gadgets.block-selector.name");
    }

    @Override
    public String[] lore() {
        return new String[]{
                FasterBridge.instance.getConfig().getString("gadgets.block-selector.lore0"),
                FasterBridge.instance.getConfig().getString("gadgets.block-selector.lore1"),
                FasterBridge.instance.getConfig().getString("gadgets.block-selector.lore2"),
                FasterBridge.instance.getConfig().getString("gadgets.block-selector.lore3")
        };
    }

    @Override
    public String permission() {
        return "fasterbridge.blockSelectorGadget";
    }

    @Override
    public void itemFunction(Player player) {
        FasterBridge.instance.getUiManager().openBlocks(player);
    }
}
