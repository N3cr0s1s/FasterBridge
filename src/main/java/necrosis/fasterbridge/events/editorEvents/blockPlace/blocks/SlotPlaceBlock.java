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
        return "&5[&l&fSlotSet&r&5]";
    }

    @Override
    public String[] lore() {
        return new String[]{
                "Set the next",
                "slot location"
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
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&2Arena slot location successfully setted. &5[&7"+arenaName+",slot location:"+slot+"&5]"
                    )
            );
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        } catch (MaxSlotException e) {
            e.printStackTrace();
        } catch (AllSlotSetException e) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&aAll slot setted. &5[&7" +FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena()+"&5]"
            ));
        }
    }
}
