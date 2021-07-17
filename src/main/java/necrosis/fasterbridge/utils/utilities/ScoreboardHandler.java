package necrosis.fasterbridge.utils.utilities;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreboardHandler {

    private JPerPlayerScoreboard scoreboard;
    private final FasterBridge plugin;

    public ScoreboardHandler(FasterBridge plugin){
        this.plugin = plugin;

        scoreboard = new JPerPlayerScoreboard(
                //  TITLE
                (player)->{
                    PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
                    String arenaName = playerClass.getGame().getInArena();
                    return this.plugin.cfs("scoreboard.title","%arenaName%",arenaName);
                },
                //  LINES
                (player)->{
                    PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
                    String arenaName = playerClass.getGame().getInArena();
                    String record = null;
                    try {
                        record = this.plugin.getConfigManager().getPlayerRecordsConfig().getRecord(player,arenaName) + "";
                    } catch (ArenaNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(record.equals(0) || record == null){
                        record = "0";
                    }

                    List<String> list = new ArrayList<>();
                    for(String lines:this.plugin.getConfig().getStringList("scoreboard.list")){
                        while(lines.contains("%arenaName%") || lines.contains("%record%")){
                            lines = lines.replace("%arenaName%",arenaName);
                            lines = lines.replace("%record%",record);
                        }
                        list.add(lines);
                    }

                    return list;
                }
        );
    }

    public void addToScoreboard(Player player){
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }

    public void removeScoreboard(Player player){
        scoreboard.removePlayer(player);
    }

    public void updateScoreboard(){
        scoreboard.updateScoreboard();
    }

}
