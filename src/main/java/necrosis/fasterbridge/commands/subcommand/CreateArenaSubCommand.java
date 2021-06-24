package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaAlreadyExist;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateArenaSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "create";
    }

    @Override
    public String description() {
        return "Create arena.";
    }

    @Override
    public String syntax() {
        return "/fastbridge create <arenaName> <maxPlayer>";
    }

    @Override
    public String permission() {
        return "craftkit.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        String arenaName="";
        int maxPlayer=0;
        try{
            arenaName = args[1];
            maxPlayer = Integer.parseInt(args[2]);
        }catch (Exception e){
            player.sendMessage(ChatColor.RED + syntax());
            return;
        }
        try {
            FasterBridge.instance.getArenaManager().editor().getCreateArena().createArena(arenaName,maxPlayer);
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&2Arena successfully created. &5[&7"+arenaName+"&5]"
                            )
            );
        } catch (ArenaAlreadyExist arenaAlreadyExist) {
            player.sendMessage(ChatColor.DARK_RED + "Arena already exist. " + ChatColor.LIGHT_PURPLE + "[" + ChatColor.GRAY + arenaName + ChatColor.LIGHT_PURPLE + "]" );
        } catch (Exception e){
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&2Arena successfully created. &5[&7"+arenaName+"&5]"
                    )
            );
        }
    }
}
