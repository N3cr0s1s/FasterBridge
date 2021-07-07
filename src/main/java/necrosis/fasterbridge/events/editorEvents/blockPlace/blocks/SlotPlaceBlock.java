package necrosis.fasterbridge.events.editorEvents.blockPlace.blocks;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.events.editorEvents.blockPlace.FunctionBlockAbstract;
import necrosis.fasterbridge.exceptions.AllSlotSetException;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SlotPlaceBlock extends FunctionBlockAbstract {
    @Override
    public String name() {
        return "slotBlock";
    }

    @Override
    public Material itemMaterial() {
        return Material.CLAY;
    }

    @Override
    public String displayName() {
        return FasterBridge.instance.cfs("functionBlocks.slotPlace.name");
    }

    @Override
    public String[] lore() {
        return new String[]{
                FasterBridge.instance.cfs("functionBlocks.slotPlace.lore0"),
                FasterBridge.instance.cfs("functionBlocks.slotPlace.lore1"),
                FasterBridge.instance.cfs("functionBlocks.slotPlace.lore2"),
                FasterBridge.instance.cfs("functionBlocks.slotPlace.lore3")
        };
    }

    @Override
    public void function(Player player, Location location) {
        try {
            String arenaName =FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena();
            int slot = FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getArenaClass().getUnsetSlot();
            FasterBridge.instance.getArenaManager().editor().location().setLocation(
                    arenaName,
                    location,
                    slot
            );
            player.sendMessage(FasterBridge.instance.cfs("functionBlocks.slotPlace.succ","%placeHolder%",arenaName+",slot location:"+slot));
            FasterBridge.instance.getArenaManager().getArena(arenaName).setDirection(location);

        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        } catch (MaxSlotException e) {
            e.printStackTrace();
        } catch (AllSlotSetException e) {
            player.sendMessage(FasterBridge.instance.cfs("functionBlocks.slotPlace.done","%arenaName%",FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena()));
        }
    }
}
