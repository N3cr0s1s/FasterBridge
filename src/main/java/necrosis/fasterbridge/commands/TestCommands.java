package necrosis.fasterbridge.commands;

import cornerlesscube.craftkit.command.CommandAccessibility;
import cornerlesscube.craftkit.command.CommandBase;
import cornerlesscube.craftkit.command.RegisterCommand;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
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
            FasterBridge.instance.getArenaManager().editor().location().setLocation("asd",player,0);
        } catch (MaxSlotException e) {
            FasterBridge.instance.logger().getError("MaxPlayer: " + e.getMaxPlayer());
            FasterBridge.instance.logger().getError("slot: " + e.getSlot());
            FasterBridge.instance.logger().getError("arenaName: " + e.getArenaName());
            FasterBridge.instance.logger().getError("Message: " + e.getMessage());
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
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
