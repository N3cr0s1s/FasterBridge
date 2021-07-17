package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.customevents.player.PlayerLeaveEditorEvent;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArenaPlayerLeaveEditor {

    private FasterBridge plugin;

    public ArenaPlayerLeaveEditor(FasterBridge plugin){
        this.plugin = plugin;
    }

    public PlayerClass leaveEditor(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        ArenaClass arena = null;
        try {
            arena = this.plugin.getArenaManager().getArena(playerClass.getEditor().getInArena());
        } catch (ArenaNotFoundException e) {
            e.printStackTrace();
        }
        player.getInventory().clear();
        player.getInventory().setContents(playerClass.getEditor().getPrevInv());
        playerClass.getEditor().leaveEditor();

        //  Call PlayerLeaveEditorEvent
        Bukkit.getServer().getPluginManager().callEvent(
                new PlayerLeaveEditorEvent(
                        player,
                        arena.getArenaName(),
                        playerClass,
                        arena
                )
        );

        //  Remove fake blocks
        //  from slot spawn locations
        if(arena.getSlotLocation() == null) return playerClass;
        for(Location loc:arena.getSlotLocation()){
            player.sendBlockChange(loc, Material.getMaterial("AIR"),(byte)0);
        }

        return playerClass;
    }

    public PlayerClass leaveEditor(PlayerClass playerClass){
        Player player = Bukkit.getPlayer(playerClass.getPlayerUUID());
        return this.leaveEditor(player);
    }
}
