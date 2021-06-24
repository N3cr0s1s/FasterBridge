package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;

public class ArenaPlayerLeaveEditor {

    private FasterBridge plugin;

    public ArenaPlayerLeaveEditor(FasterBridge plugin){
        this.plugin = plugin;
    }

    public PlayerClass leaveEditor(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        player.getInventory().clear();
        player.getInventory().setContents(playerClass.getEditor().getPrevInv());
        playerClass.getEditor().leaveEditor();
        return playerClass;
    }
}
