package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetSlotLocSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "setloc";
    }

    @Override
    public String description() {
        return "Set slot locations in arena.";
    }

    @Override
    public String syntax() {
        return "/fasterbridge setloc <arenaName> <slot>\n/fasterbridge setloc <slot>";
    }

    @Override
    public String permission() {
        return "craftkit.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        String arenaName = "";
        int slot = 0;
        if(args.length == 1 && FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().isInEditor()){
            arenaName = FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena();
            slot = Integer.parseInt(args[1]);
        }else {
            try {
                arenaName = args[1];
                slot = Integer.parseInt(args[2]);
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + syntax());
                return;
            }
        }
        try {
            FasterBridge.instance.getArenaManager().editor().location().setLocation(FasterBridge.instance.getArenaManager().getArena(arenaName),player,slot);
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&2Arena slot location successfully setted. &5[&7"+arenaName+",slot location:"+slot+"&5]"
                    )
            );
        } catch (ArenaNotFoundException e) {
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&4Arena not found. &d[&7" + arenaName + "&d]"
                            )
            );
        } catch (MaxSlotException e) {
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&4Slot is bigger than max player. &d[&7" + e.getSlot() + " >= "+e.getMaxPlayer() + "&d]"
                    )
            );
        }
    }
}
