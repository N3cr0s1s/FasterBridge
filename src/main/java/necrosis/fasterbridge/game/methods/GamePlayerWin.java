package necrosis.fasterbridge.game.methods;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;

public class GamePlayerWin {

    private final FasterBridge plugin;

    public GamePlayerWin(FasterBridge plugin){
        this.plugin = plugin;
    }

    public void playerWin(Player player) throws NotInArenaException {
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        if(!playerClass.getGame().isInGame()) throw new NotInArenaException("Player not ingame.");
        ArenaClass arena = null;
        try {
            arena = this.plugin.getArenaManager().getArena(playerClass.getGame().getInArena());
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
        playerClass.getGame().getStopwatch().stop();
        if(!this.plugin.getConfigManager().getPlayerRecordsConfig().isPlayerSet(player,arena.getArenaName())){
            this.plugin.getConfigManager().getPlayerRecordsConfig().saveRecord(player,playerClass.getGame().getStopwatch().toDouble());
            //  TODO NEW RECORD
        }else if(this.plugin.getConfigManager().getPlayerRecordsConfig().getRecord(player) > playerClass.getGame().getStopwatch().toDouble()){
            this.plugin.getConfigManager().getPlayerRecordsConfig().saveRecord(player,playerClass.getGame().getStopwatch().toDouble());
            //  TODO NEW RECORD
        }
        this.plugin.getGameManager().getGamePlayerDeath().playerDeath(player);
    }
}
