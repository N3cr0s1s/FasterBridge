package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArenaTestSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "test";
    }

    @Override
    public String description() {
        return FasterBridge.instance.cfs("messages.ArenaTest.desc");
    }

    @Override
    public String syntax() {
        return "/fb test <arenaName> <testObject>";
    }

    @Override
    public String permission() {
        return "craftkit.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        String arenaName = null;
        String testObject = null;
        try{
            arenaName = args[1];
            testObject = args[2];

            ArenaClass arenaClass = FasterBridge.instance.getArenaManager().getArena(arenaName);

            switch (testObject){
                case "direction":
                    player.sendMessage(
                            arenaClass.getDirection().toString()
                    );
                    arenaClass.setDirection(arenaClass.getSlotLocation(0));
                    player.sendMessage(
                            arenaClass.getDirection().toString()
                    );
                    break;
                default:
                    player.sendMessage(ChatColor.RED + syntax());
                    break;
            }
        }catch (Exception e){
            player.sendMessage(ChatColor.RED + syntax());
        }
    }
}
