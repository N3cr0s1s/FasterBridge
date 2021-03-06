package necrosis.fasterbridge.game.methods;

import cornerlesscube.craftkit.CraftKit;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.customevents.game.PlayerNewRecordEvent;
import necrosis.fasterbridge.customevents.game.PlayerWinEvent;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Bukkit;
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
            //  Save record
            this.plugin.getConfigManager().getPlayerRecordsConfig().saveRecord(player,playerClass.getGame().getStopwatch().toDouble());

            //  New record
            this.newRecord(player, String.valueOf(playerClass.getGame().getStopwatch().toDouble()));

            //  Call PlayerNewRecordEvent
            Bukkit.getServer().getPluginManager().callEvent(
                    new PlayerNewRecordEvent(
                            player,
                            arena.getArenaName(),
                            playerClass,
                            arena,
                            playerClass.getGame().getStopwatch().toDouble()
                    )
            );
            //  -----------
        }else if(this.plugin.getConfigManager().getPlayerRecordsConfig().getRecord(player) > playerClass.getGame().getStopwatch().toDouble()){
            //  Save record
            this.plugin.getConfigManager().getPlayerRecordsConfig().saveRecord(player,playerClass.getGame().getStopwatch().toDouble());

            //  New record
            this.newRecord(player, String.valueOf(playerClass.getGame().getStopwatch().toDouble()));

            //  Call PlayerNewRecordEvent
            Bukkit.getServer().getPluginManager().callEvent(
                    new PlayerNewRecordEvent(
                            player,
                            arena.getArenaName(),
                            playerClass,
                            arena,
                            playerClass.getGame().getStopwatch().toDouble()
                    )
            );
        }
        //  Call PlayerWinEvent
        Bukkit.getServer().getPluginManager().callEvent(
                new PlayerWinEvent(
                        player,
                        arena.getArenaName(),
                        playerClass,
                        arena,
                        playerClass.getGame().getStopwatch().toDouble()
                )
        );

        //  Back to spawn
        this.plugin.getGameManager().getGamePlayerDeath().playerDeath(player);
    }

    private void newRecord(Player player,String time){
        this.plugin.getUtilsManager().getScoreboardHandler().updateScoreboard();
        if(this.plugin.getVersionManager().getVersion() < 9){
            this.plugin.getUtilsManager().getSendTitleUtil().sendFullTitle(player,1,20,2,this.plugin.cfs("messages.newRecord.title","%time%",time),this.plugin.cfs("messages.newRecord.subTitle","%time%",time));
            return;
        }
        player.sendTitle(this.plugin.cfs("messages.newRecord.title","%time%",time),this.plugin.cfs("messages.newRecord.subTitle","%time%",time),1,20,20);
    }
}
