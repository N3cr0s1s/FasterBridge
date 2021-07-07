package necrosis.fasterbridge.gadget.gadgets;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.gadget.GadgetAbstract;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArenaSelectorGadget extends GadgetAbstract {
    @Override
    public String name() {
        return "arenaSelectorGadget";
    }

    @Override
    public Material itemMaterial() {
        return Material.COMPASS;
    }

    @Override
    public String displayName() {
        return FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".name");
    }

    @Override
    public String[] lore() {
        return new String[]{
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore0"),
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore1"),
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore2"),
                FasterBridge.instance.getConfig().getString("gadgets."+this.name()+".lore3")
        };
    }

    @Override
    public String permission() {
        return "fastbridge.arenaSelectorGadget";
    }

    @Override
    public void itemFunction(Player player) {
        FasterBridge.instance.getUiManager().openArenaSelector(player);
    }
}
