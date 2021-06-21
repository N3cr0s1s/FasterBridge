package necrosis.fasterbridge.player;

import necrosis.fasterbridge.FasterBridge;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public final class PlayerManager {

    private FasterBridge plugin;
    private HashMap<UUID,PlayerClass> playerClassMap = new HashMap<UUID,PlayerClass>();

    public PlayerManager(FasterBridge plugin){
        this.plugin = plugin;
    }

    public boolean isPlayerExist(Player player){
        if(playerClassMap.containsKey(player.getUniqueId())){
            return true;
        } else {
            return false;
        }
    }

    public PlayerClass createPlayerClass(Player player){
        if(isPlayerExist(player)) return getPlayerClass(player);
        PlayerClass playerClass = new PlayerClass(player,plugin);
        playerClassMap.put(player.getUniqueId(),playerClass);
        return playerClass;
    }

    public void removePlayerClass(Player player){
        if(!isPlayerExist(player)) return;
        playerClassMap.remove(player.getUniqueId());
        return;
    }

    public PlayerClass getPlayerClass(Player player){
        return playerClassMap.get(player.getUniqueId());
    }
}
