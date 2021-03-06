package necrosis.fasterbridge.player.classes;

import cornerlesscube.craftkit.utils.Stopwatch;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class GameClass {

    private boolean inGame;
    private String inArena;
    private int slot;
    private Location startLoc;
    private final Stopwatch stopwatch;
    private ItemStack[] prevInv;
    private List<Block> blocks;
    private BukkitTask bukkitTask;

    public GameClass(){
        this.inGame = false;
        this.inArena = "";
        this.slot = 0;
        this.startLoc = null;
        this.stopwatch = new Stopwatch();
        this.prevInv = null;
        this.blocks = new ArrayList<>();
        this.bukkitTask = null;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public String getInArena() {
        return inArena;
    }

    public void setInArena(String inArena) {
        this.inArena = inArena;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Location getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Location startLoc) {
        this.startLoc = startLoc;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    public ItemStack[] getPrevInv() {
        return prevInv;
    }

    public void setPrevInv(ItemStack[] prevInv) {
        this.prevInv = prevInv;
    }

    public void addBlock(Block block){
        this.blocks.add(block);
    }

    public void deleteBlock(){
        try {
            FasterBridge.instance.getServer().getScheduler().scheduleSyncDelayedTask(FasterBridge.instance, () -> {
                int itemMax = 10;
                try {
                    itemMax = FasterBridge.instance.getConfig().getInt("config.destroy-item.spawnitem-max");
                }catch (Exception ignored){}
                ArrayList<Item> items = new ArrayList<>();
                int itemsCount = 0;
                for (Block b : this.blocks) {
                    try {
                        if(itemsCount < itemMax) {
                            items.add(b.getWorld().dropItem(b.getLocation(), new ItemStack(b.getType(), FasterBridge.instance.getConfig().getInt("config.destroy-item.item-amount"))));
                            itemsCount++;
                        }
                    } catch (Exception ignored) { }
                    b.setType(Material.AIR);
                }
                this.blocks.clear();
                FasterBridge.instance.getServer().getScheduler().scheduleSyncDelayedTask(FasterBridge.instance, () -> {
                    for (Item item : items) {
                        item.remove();
                    }
                }, FasterBridge.instance.getConfig().getLong("config.destroy-item.itemalive-tick"));
            }, 2L);
        }catch (IllegalPluginAccessException wtf){}
    }

    public BukkitTask getBukkitTask() {
        return bukkitTask;
    }

    public void setBukkitTask(BukkitTask bukkitTask) {
        this.bukkitTask = bukkitTask;
    }
}
