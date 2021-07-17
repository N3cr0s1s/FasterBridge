package necrosis.fasterbridge.arena.arenaPlayerFunction;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.customevents.player.PlayerJoinArenaEvent;
import necrosis.fasterbridge.exceptions.*;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ArenaPlayerJoin {

    private final FasterBridge plugin;

    public ArenaPlayerJoin(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ArenaClass joinArena(Player player, ArenaClass arena) throws ArenaNotValidException, FullSlotException, MaxSlotException {
        if(!this.plugin.getArenaManager().editor().getValidator().isArenaValid(arena)) throw new ArenaNotValidException("Arena not valid.");
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        //  If palyer in game
        if(playerClass.getGame().isInGame()){
            player.sendMessage(ChatColor.DARK_RED + "Can't connect while in game!");
            return null;
        }
        //  If player in editor
        if(playerClass.getEditor().isInEditor()){
            player.sendMessage(ChatColor.DARK_RED + "Can't connect while in editor!");
            return null;
        }

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

        player.getInventory().setItem(this.plugin.getConfig().getInt("config.gadgets-slot.player-block-slot"),playerClass.getBlock());
        try {
            //  Set ExitArena gadget to inventory
            player.getInventory().setItem(this.plugin.getConfig().getInt("config.gadgets-slot.exit-arena-slot"),
                    this.plugin.getGadgetManager().getGadget("arenaLeaveGadget").getGadget()
                    );

            //  Set RestartArena gadget to inventory
            player.getInventory().setItem(this.plugin.getConfig().getInt("config.gadgets-slot.restart-arena-slot"),
                    this.plugin.getGadgetManager().getGadget("arenaRestartGadget").getGadget()
                    );

            //  Set BlockSelect gadget to inventory
            player.getInventory().setItem(this.plugin.getConfig().getInt("config.gadgets-slot.block-selector-slot"),
                    this.plugin.getGadgetManager().getGadget("blockSelectorGadget").getGadget()
            );
        } catch (GadgetNotRegisteredException e) {
            e.printStackTrace();
        } catch (GadgetNotExistException e) {
            e.printStackTrace();
        }

        player.teleport(arena.getSlotLocation(slot));

        //  Start action timer, and add player to scoreboard
        this.plugin.getGameManager().getActionTimer().startActionTimer(player);
        this.plugin.getUtilsManager().getScoreboardHandler().addToScoreboard(player);

        //  Call event { PlayerJoinArenaEvent }
        Bukkit.getServer().getPluginManager().callEvent(
                new PlayerJoinArenaEvent(
                    player,
                    arena.getArenaName(),
                    playerClass,
                    arena
        ));

        return arena;
    }

    public ArenaClass joinArena(Player player,String arenaName) throws ArenaNotValidException, FullSlotException, MaxSlotException, ArenaNotFoundException {
        ArenaClass arena = this.plugin.getArenaManager().getArena(arenaName);
        return this.joinArena(player,arena);
    }
}
