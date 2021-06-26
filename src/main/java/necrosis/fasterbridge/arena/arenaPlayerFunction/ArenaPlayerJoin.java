package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.*;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.entity.Player;

public class ArenaPlayerJoin {

    private final FasterBridge plugin;

    public ArenaPlayerJoin(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass joinArena(Player player, ArenaClass arena) throws ArenaNotValidException, FullSlotException, MaxSlotException {
        if(!this.plugin.getArenaManager().editor().getValidator().isArenaValid(arena)) throw new ArenaNotValidException("Arena not valid.");
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);

        int slot = arena.getFreeSlot();

        //  PLAYER CLASS
        playerClass.getGame().setInGame(true);
        playerClass.getGame().setInArena(arena.getArenaName());
        playerClass.getGame().setStartLoc(player.getLocation());
        playerClass.getGame().setSlot(slot);
        playerClass.getGame().setPrevInv(player.getInventory().getContents());

        //  ARENA CLASS
        arena.setSlot(slot,true);

        //  PLAYER
        player.getInventory().clear();

        player.getInventory().setItem(0,playerClass.getBlock());
        try {
            player.getInventory().setItem(4,
                    this.plugin.getGadgetManager().getGadget("arenaLeaveGadget").getGadget()
                    );
        } catch (GadgetNotRegisteredException e) {
            e.printStackTrace();
        } catch (GadgetNotExistException e) {
            e.printStackTrace();
        }

        player.teleport(arena.getSlotLocation(slot));

        this.plugin.getGameManager().getActionTimer().startActionTimer(player);

        return arena;
    }

    public ArenaClass joinArena(Player player,String arenaName) throws ArenaNotValidException, FullSlotException, MaxSlotException, ArenaNotFoundException {
        ArenaClass arena = this.plugin.getArenaManager().getArena(arenaName);
        return this.joinArena(player,arena);
    }
}
