package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.customevents.player.PlayerJoinEditorEvent;
import necrosis.fasterbridge.exceptions.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ArenaPlayerJoinEditor {

    private FasterBridge plugin;

    public ArenaPlayerJoinEditor(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass joinEditor(Player player,ArenaClass arenaClass){
        this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setPrevInv(
                player.getInventory().getContents()
        );
        player.getInventory().clear();
        try {
            //  ARENAEDITOR
            player.getInventory().setItem(4,
                    this.plugin.getGadgetManager().getGadget("arenaEditorGadget").getGadget()
            );
            //  SLOTSELECT
            player.getInventory().setItem(0,
                    this.plugin.getFunctionBlockManager().getFunctionBlock("slotBlock").getFunctionBlock()
            );
            //  DEATHZONE SET
            player.getInventory().setItem(8,
                    this.plugin.getFunctionBlockManager().getFunctionBlock("deathBlock").getFunctionBlock()
            );
        } catch (GadgetNotRegisteredException|GadgetNotExistException e) {
            e.printStackTrace();
        } catch (FuncBlockDoesNotExistException e) {
            e.printStackTrace();
        } catch (FunctionBlockNotRegisteredException e) {
            e.printStackTrace();
        }

        //  Call PlayerJoinEditorEvent
        Bukkit.getServer().getPluginManager().callEvent(
                new PlayerJoinEditorEvent(
                        player,
                        arenaClass.getArenaName(),
                        this.plugin.getPlayerManager().getPlayerClass(player),
                        arenaClass
                )
        );

        //  Send fake block packets to player
        //  to see slot spawns location
        try { if(arenaClass.getSlotLocation() == null || arenaClass.getSlotLocation().length < 1 || arenaClass.getSlotLocation(0) == null) return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass); } catch (Exception e) { return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass); }
        for (Location loc:arenaClass.getSlotLocation()){
            player.sendBlockChange(loc, Material.getMaterial("RED_STAINED_GLASS"), (byte)0);
        }

        return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass);
    }

    public ArenaClass joinEditor(Player player,String arenaName) throws ArenaNotFoundException {
        ArenaClass arenaClass = this.plugin.getArenaManager().getArena(arenaName);
        return this.joinEditor(player,arenaClass);
    }
}
