package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.*;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;

public class ArenaPlayerLeave {

    private final FasterBridge plugin;

    public ArenaPlayerLeave(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass leaveArena(Player player) throws NotInArenaException, ArenaNotFoundException, MaxSlotException {
        this.plugin.getUtilsManager().getScoreboardHandler().removeScoreboard(player);
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        if(!playerClass.getGame().isInGame()) throw new NotInArenaException("Player not in arena.");

        ArenaClass arena = this.plugin.getArenaManager().getArena(playerClass.getGame().getInArena());

        //  PLAYER CLASS
        playerClass.getGame().setInGame(false);
        playerClass.getGame().setInArena("");
        playerClass.getGame().getStopwatch().stop();

        //  ARENA CLASS
        arena.setSlot(playerClass.getGame().getSlot(),false);

        //  PLAYER
        player.getInventory().clear();
        player.updateInventory();
        player.getInventory().setContents(playerClass.getGame().getPrevInv());
        player.updateInventory();
        player.teleport(playerClass.getGame().getStartLoc());

        //  CLEAR MEMORY
        playerClass.getGame().setPrevInv(null);
        playerClass.getGame().setStartLoc(null);

        this.plugin.getGameManager().getActionTimer().stopActionTimer(player);
        playerClass.getGame().deleteBlock();

        return arena;
    }
}
