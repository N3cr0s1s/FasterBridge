package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.GadgetNotExistException;
import necrosis.fasterbridge.exceptions.GadgetNotRegisteredException;
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
            player.getInventory().setItem(4,
                this.plugin.getGadgetManager().getGadget("arenaEditorGadget").getGadget()
            );
        } catch (GadgetNotRegisteredException|GadgetNotExistException e) {
            e.printStackTrace();
        }
        return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass);
    }

    public ArenaClass joinEditor(Player player,String arenaName) throws ArenaNotFoundException {
        this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setPrevInv(
                player.getInventory().getContents()
        );
        player.getInventory().clear();
        try {
            player.getInventory().setItem(4,
                    this.plugin.getGadgetManager().getGadget("arenaEditorGadget").getGadget()
            );
        } catch (GadgetNotRegisteredException|GadgetNotExistException e) {
            e.printStackTrace();
        }
        ArenaClass arenaClass = this.plugin.getArenaManager().getArena(arenaName);
        return this.plugin.getPlayerManager().getPlayerClass(player).getEditor().setEditor(arenaClass);
    }
}
