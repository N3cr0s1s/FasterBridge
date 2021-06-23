package necrosis.fasterbridge.gadget.gadgets;

import necrosis.fasterbridge.gadget.GadgetAbstract;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArenaLeaveGadget extends GadgetAbstract {

    @Override
    public String name() {
        return "arenaLeaveGadget";
    }

    @Override
    public String permission() {
        return "craftkit.player";
    }

    @Override
    public Material itemMaterial() {
        return Material.BARRIER;
    }

    @Override
    public String displayName() {
        return "&7...../-&4Exit&7-\\.....";
    }

    @Override
    public String[] lore() {
        return new String[]{
                "____|--|____",
                "  Right click",
                " to use this",
                "----|__|----"
        };
    }

    @Override
    public void itemFunction(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&4EXIT ARENA"));
        //  TODO EXIT ARENA
    }
}
