package necrosis.fasterbridge.config.configFiles;

import cornerlesscube.craftkit.utils.file.yaml.YamlClass;
import cornerlesscube.craftkit.utils.file.yaml.exceptions.FileAlreadyExistException;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;

public final class PlayerRecordsConfig {
    private final String configName = "playerRecords";

    private final FasterBridge plugin;
    private final YamlClass playerRecords;

    public PlayerRecordsConfig(FasterBridge plugin){
        this.plugin = plugin;
        YamlClass tempConfig = null;
        try {
            tempConfig = plugin.configYml().getYamlClass(configName);
        }catch (Exception e){
            try {
                tempConfig = plugin.configYml().createYaml(configName);
            } catch (FileAlreadyExistException fileAlreadyExistException) {
                fileAlreadyExistException.printStackTrace();
            }
        }
        this.playerRecords = tempConfig;
        tempConfig=null;
    }

    public PlayerRecordsConfig saveRecord(Player player,double record) throws NotInArenaException {
        if(!this.plugin.getPlayerManager().getPlayerClass(player).getGame().isInGame()) throw new NotInArenaException("Player not in arena");
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        this.playerRecords.write(player.getUniqueId() + "." + playerClass.getGame().getInArena(),record);
        return this;
    }

    public double getRecord(Player player) throws NotInArenaException {
        if(!this.plugin.getPlayerManager().getPlayerClass(player).getGame().isInGame()) throw new NotInArenaException("Player not in arena");
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        return Double.valueOf(String.valueOf(this.playerRecords.read(player.getUniqueId() + "." + playerClass.getGame().getInArena())));
    }

    public double getRecord(Player player,String arenaName) throws ArenaNotFoundException {
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        ArenaClass arena = this.plugin.getArenaManager().getArena(arenaName);
        return Double.valueOf(String.valueOf(this.playerRecords.read(player.getUniqueId() + "." + arenaName)));
    }

    public boolean isPlayerSet(Player player,String arenaName){
        if(this.playerRecords.YamlConfig.isSet(player.getUniqueId() + "." + arenaName)){
            return true;
        }else return false;
    }
}
