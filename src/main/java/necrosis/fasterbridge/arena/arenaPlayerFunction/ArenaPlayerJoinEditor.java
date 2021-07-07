package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.*;
import necrosis.fasterbridge.player.PlayerClass;
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
            player.getInventory().setItem(0,
                    this.plugin.getFunctionBlockManager().getFunctionBlock("deathBlock").getFunctionBlock()
            );
        } catch (GadgetNotRegisteredException|GadgetNotExistException e) {
            e.printStackTrace();
        } catch (FuncBlockDoesNotExistException e) {
            e.printStackTrace();
        } catch (FunctionBlockNotRegisteredException e) {
            e.printStackTrace();
        }
        return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass);
    }

    public ArenaClass joinEditor(Player player,String arenaName) throws ArenaNotFoundException {
        ArenaClass arenaClass = this.plugin.getArenaManager().getArena(arenaName);
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
        return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass);
    }
}
