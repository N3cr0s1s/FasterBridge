package necrosis.fasterbridge.ui;

import necrosis.fasterbridge.arena.ArenaClass;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class ButtonInterface {

    public int slotHolder;
    public ArenaClass arenaHolder;

    public ButtonInterface(){

    }

    public ButtonInterface(int i){
        this.slotHolder = i;
    }

    public ButtonInterface(int i, ArenaClass arenaClass){
        this.arenaHolder = arenaClass;
        this.slotHolder = i;
    }

    public abstract int slot();
    public abstract ItemStack item(Player player);
    public abstract void click(Player player, int slot, ItemStack clicked, Inventory inv);
}
