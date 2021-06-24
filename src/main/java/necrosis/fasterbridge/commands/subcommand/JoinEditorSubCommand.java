package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JoinEditorSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "editor";
    }

    @Override
    public String description() {
        return "Join or leave editor.";
    }

    @Override
    public String syntax() {
        return "/fb editor <arenaName>";
    }

    @Override
    public String permission() {
        return "craftkit.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().isInEditor()){
            FasterBridge.instance.getArenaManager().player().getArenaPlayerLeaveEditor().leaveEditor(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&aSuccessfully leaved editor."
            ));
            return;
        }
        String arenaName = "";
        try{
            arenaName = args[1];
        }catch (Exception e){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c"+syntax()
            ));
            return;
        }
        try {
            FasterBridge.instance.getArenaManager().player().getArenaPlayerJoinEditor().joinEditor(
                    player,arenaName
            );
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&aSuccessfully joined editor. &5[&7"+arenaName+"&5]"
            ));
        } catch (ArenaNotFoundException e) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&4Arena not found. &5[&7"+arenaName+"&5]"
            ));
            return;
        }
    }
}