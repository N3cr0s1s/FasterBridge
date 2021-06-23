package necrosis.fasterbridge.commands;

import cornerlesscube.craftkit.command.CommandAccessibility;
import cornerlesscube.craftkit.command.CommandBase;
import cornerlesscube.craftkit.command.RegisterCommand;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.GadgetNotExistException;
import necrosis.fasterbridge.exceptions.GadgetNotRegisteredException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@RegisterCommand(
        name = "acca",
        permission = "nincs",
        aliases = {"ta"},
        accessibility = CommandAccessibility.PLAYER
)
public class TestCommands extends CommandBase {

    @Override
    public boolean playerExecutor(Player player, String s, String[] strings) {
        try {
            player.getInventory().setItem(0,FasterBridge.instance.getGadgetManager().getGadget(strings[0]).getGadget());
        } catch (GadgetNotExistException | GadgetNotRegisteredException e) {
            e.printStackTrace();
            player.sendMessage("Gadget does not exist. Gadget: " + strings[0]);
        }
        return false;
    }

    @Override
    public boolean consoleExecutor(ConsoleCommandSender consoleCommandSender, String s, String[] strings) {
        return false;
    }

    @Override
    public boolean generalExecutor(CommandSender commandSender, String s, String[] strings) {

        return false;
    }
}
