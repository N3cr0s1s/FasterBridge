package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.FuncBlockDoesNotExistException;
import necrosis.fasterbridge.exceptions.FunctionBlockNotRegisteredException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetFunctionBlockSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "func";
    }

    @Override
    public String description() {
        return "Give function block to player.";
    }

    @Override
    public String syntax() {
        return "/fb func <FunctionBlockName>";
    }

    @Override
    public String permission() {
        return "craftkit.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(
                !FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                        .getEditor().isInEditor()
        ){
            player.sendMessage("Only in editor mode.");
            return;
        }
        try {
            try {
                player.getInventory().addItem(
                    FasterBridge.instance.getFunctionBlockManager().getFunctionBlock(args[1]).getFunctionBlock()
                );
            } catch (FunctionBlockNotRegisteredException e) {
                player.sendMessage("Function block not registered.");
            }
        } catch (FuncBlockDoesNotExistException e) {
            player.sendMessage("Function block does not exist.");
        } catch (ArrayIndexOutOfBoundsException ex){
            player.sendMessage(ChatColor.RED + syntax());
        }
    }
}
