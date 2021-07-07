package necrosis.fasterbridge.events.gameEvents;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.arena.Direction;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
import necrosis.fasterbridge.player.PlayerClass;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class GameEvents implements Listener {

    private final FasterBridge plugin;

    public GameEvents(){
       this.plugin = FasterBridge.instance;
    }

    public boolean inGame(Player player){
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(player);
        if(!playerClass.getGame().isInGame()) return false;
        return true;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) throws ArenaNotFoundException {
        if(!inGame(event.getPlayer())) return;
        ArenaClass arena = this.plugin.getArenaManager().getArena(
                this.plugin.getPlayerManager().getPlayerClass(event.getPlayer()).getGame().getInArena()
        );
        if(event.getPlayer().getLocation().getY() < arena.getDeathZoneVertical()){
            this.plugin.getGameManager().getGamePlayerDeath().playerDeath(event.getPlayer());
        }
    }

    public double absoluteValue(double value){
        double toReturn = 0;
        if(value < 0){
            toReturn = value * -1;
        }else{
            return value;
        }
        return toReturn;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) throws ArenaNotFoundException {
        if(!inGame(event.getPlayer())) return;
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(event.getPlayer());
        ArenaClass arena = this.plugin.getArenaManager().getArena(
                playerClass.getGame().getInArena()
        );
        if(!playerClass.getGame().getStopwatch().isRunning()) playerClass.getGame().getStopwatch().start();
    }

    @EventHandler
    public void itemBack(BlockPlaceEvent event){
        if(!inGame(event.getPlayer())) return;
        try{event.getPlayer().getItemInHand().setAmount(64);}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) throws ArenaNotFoundException, MaxSlotException {
        if(!inGame(event.getPlayer())) return;
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(event.getPlayer());
        ArenaClass arena = this.plugin.getArenaManager().getArena(playerClass.getGame().getInArena());

        int slot_ = playerClass.getGame().getSlot();
        int distance_ = arena.getDeathZoneHorizontal();
        int slotLocX = arena.getSlotLocation(slot_).getBlockX();
        int slotLocZ = arena.getSlotLocation(slot_).getBlockZ();

        double diffX = event.getBlock().getLocation().getX()-slotLocX;
        double diffZ = event.getBlock().getLocation().getZ()-slotLocZ;

        if(arena.getDirection().equals(Direction.NORTH) || arena.getDirection().equals(Direction.SOUTH)){
            if(absoluteValue(diffX) > distance_){
                event.setCancelled(true);
                return;
            }
        }else{
            if(absoluteValue(diffZ) > distance_){
                event.setCancelled(true);
                return;
            }
        }
        playerClass.getGame().addBlock(event.getBlock());
    }

    @EventHandler
    public void blockBack(BlockPlaceEvent event) throws ArenaNotFoundException, MaxSlotException {
        if(!inGame(event.getPlayer())) return;
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(event.getPlayer());
        ArenaClass arena = this.plugin.getArenaManager().getArena(playerClass.getGame().getInArena());

        int slot_ = playerClass.getGame().getSlot();
        int slotLocX = arena.getSlotLocation(slot_).getBlockX();
        int slotLocZ = arena.getSlotLocation(slot_).getBlockZ();

        switch (arena.getDirection()){
            case NORTH:
                if(slotLocZ+arena.getDeathZoneHorizontal() < event.getBlock().getZ()){
                    event.setCancelled(true);
                }
                break;
            case SOUTH:
                if(slotLocZ-arena.getDeathZoneHorizontal() > event.getBlock().getZ()){
                    event.setCancelled(true);
                }
                break;
            case EAST:
                if(slotLocX-arena.getDeathZoneHorizontal() > event.getBlock().getX()){
                    event.setCancelled(true);
                }
                break;
            case WEST:
                if(slotLocX+arena.getDeathZoneHorizontal() < event.getBlock().getX()){
                    event.setCancelled(true);
                }
                break;
            default:
                break;
        }
    }

    @EventHandler
    public void onPressurePlateStep(PlayerInteractEvent event) throws ArenaNotFoundException, NotInArenaException {
        if(!inGame(event.getPlayer())) return;
        if (!event.getAction().equals(Action.PHYSICAL)) return;
        PlayerClass playerClass = this.plugin.getPlayerManager().getPlayerClass(event.getPlayer());
        ArenaClass arena = this.plugin.getArenaManager().getArena(playerClass.getGame().getInArena());
        if(playerClass.getGame().getStopwatch().toDouble() < 2) return; //  TRY TO BLOCK ANY CHEATING / LAG / TPS DROP / SERVER LAG / EXPLOIT
        this.plugin.getGameManager().getGamePlayerWin().playerWin(event.getPlayer());
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event){
        if(!inGame(event.getPlayer())) return;
        event.setCancelled(true);
    }
}