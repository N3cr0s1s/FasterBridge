package necrosis.fasterbridge.game.methods;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GamePlayerDeath {

    private final FasterBridge plugin;

    public GamePlayerDeath(FasterBridge plugin){
        this.plugin = plugin;
    }

    public void playerDeath(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        ArenaClass arena = null;
        try {
            arena = this.plugin.getArenaManager().getArena(playerClass.getGame().getInArena());
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
        try {
            player.teleport(
                    arena.getSlotLocation(
                            playerClass.getGame().getSlot()
                    )
            );
            playerClass.getGame().getStopwatch().stop();
            for(Block block:playerClass.getGame().getBlocks()){
                block.setType(Material.AIR);
            }
        } catch (MaxSlotException e) {
            e.printStackTrace();
        }
    }
}
