package necrosis.fasterbridge.gadget.gadgets;

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
        return "&7.../-&aArena&bSelect&7-\\...";
    }

    @Override
    public String[] lore() {
        return new String[]{
                "&7____|--|____",
                "&a  Right click",
                "&a to use this",
                "&7----|__|----"
        };
    }

    @Override
    public String permission() {
        return "craftkit.player";
    }

    @Override
    public void itemFunction(Player player) {
        //  TODO OPEN ARENA SELECTOR UI
    }
}
