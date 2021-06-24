package necrosis.fasterbridge.commands;

import cornerlesscube.craftkit.command.CommandAccessibility;
import cornerlesscube.craftkit.command.CommandBase;
import cornerlesscube.craftkit.command.RegisterCommand;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.commands.subcommand.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@RegisterCommand(
        name = "fasterbridge",
        permission = "nincs",
        aliases = {"fbridge","fastbridge","fastb","fb"},
        accessibility = CommandAccessibility.PLAYER
)
public class SubCommandHandler extends CommandBase{

    public SubCommandHandler(){
        this.registerCommand(
                new GiveGadgetSubCommand(),
                new CreateArenaSubCommand(),
                new SetSlotLocSubCommand(),
                new JoinEditorSubCommand(),
                new GetFunctionBlockSubCommand()
        );
    }
    private List<SubCommandInterface> subcommands = new ArrayList<>();

    @Override
    public boolean playerExecutor(Player player, String s, String[] args) {
        try {
            if (args[0].equals("") || args[0] == null) {
            }
        }catch (Exception e){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "\n"+FasterBridge.instance.getConfig().getString("messages.subcommand.no-sub-command")
                    )
            );
            String commands = "";
            for (SubCommandInterface subCommandsInterface : this.subcommands) {
                commands = commands + "&7/fasterbridge &b&l" + subCommandsInterface.name() + " &r&c[&6" + subCommandsInterface.description() + "&c]\n&d" + subCommandsInterface.syntax() + "\n";
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    commands
            ));
            commands = null;
            return false;
        }
        for(SubCommandInterface cmd:subcommands){
            if(args[0].equals(cmd.name())){
                if(!player.hasPermission(cmd.permission())){
                    player.sendMessage(
                            ChatColor.translateAlternateColorCodes('&',
                                FasterBridge.instance.getConfig().getString("messages.subcommand.no-permission")
                            )
                    );
                    return false;
                }
                cmd.execute(player,args);
                return true;
            }
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

    public void registerCommand(SubCommandInterface... command){
        for(SubCommandInterface cmd:command){
            this.subcommands.add(cmd);
        }
    }
}
