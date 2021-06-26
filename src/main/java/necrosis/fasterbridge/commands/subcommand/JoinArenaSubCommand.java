package necrosis.fasterbridge.commands.subcommand;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.ArenaNotValidException;
import necrosis.fasterbridge.exceptions.FullSlotException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JoinArenaSubCommand implements SubCommandInterface{
    @Override
    public String name() {
        return "join";
    }

    @Override
    public String description() {
        return FasterBridge.instance.cfs("messages.JoinArena.desc");
    }

    @Override
    public String syntax() {
        return "/fb join <arenaName>";
    }

    @Override
    public String permission() {
        return "craftkit.player";
    }

    @Override
    public void execute(Player player, String[] args) {
        PlayerClass playerClass = FasterBridge.instance.getPlayerManager().getPlayerClass(player);
        if(playerClass.getGame().isInGame()) {
            player.sendMessage(FasterBridge.instance.cfs("messages.JoinArena.already","%arenaName%",playerClass.getGame().getInArena()));
            return;
        }
        String arenaName = "";
        try{
            arenaName = args[1];
        }catch (Exception e){
            player.sendMessage(ChatColor.RED + this.syntax());
            return;
        }
        try {
            FasterBridge.instance.getArenaManager().player().getArenaPlayerJoin().joinArena(player,arenaName);
        } catch (ArenaNotValidException e) {
            e.printStackTrace();
        } catch (FullSlotException e) {
            player.sendMessage(FasterBridge.instance.cfs("messages.JoinArena.full","%arenaName%",arenaName));
        } catch (MaxSlotException e) {
            e.printStackTrace();
        } catch (ArenaNotFoundException e) {
            player.sendMessage(FasterBridge.instance.cfs("messages.JoinArena.notfound","%arenaName%",arenaName));
        }
    }
}
