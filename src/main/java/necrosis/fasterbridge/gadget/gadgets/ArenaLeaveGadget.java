package necrosis.fasterbridge.gadget.gadgets;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
import necrosis.fasterbridge.gadget.GadgetAbstract;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArenaLeaveGadget extends GadgetAbstract {

    @Override
    public String name() {
        return "arenaLeaveGadget";
    }

    @Override
    public String permission() {
        return "fastbridge.arenaLeaveGadget";
    }

    @Override
    public Material itemMaterial() {
        return Material.BARRIER;
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
    public void itemFunction(Player player) {
        try {
            FasterBridge.instance.getArenaManager().player().getArenaPlayerLeave().leaveArena(player);
        } catch (NotInArenaException e) {
            player.sendMessage(FasterBridge.instance.cfs("gadgets.arenaSelectorGadget.notarena"));
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        } catch (MaxSlotException e) {
            e.printStackTrace();
        }
    }
}
