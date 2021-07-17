package necrosis.fasterbridge.customevents.other;

import necrosis.fasterbridge.gadget.GadgetAbstract;
import necrosis.fasterbridge.ui.ButtonInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ButtonClickEvent extends Event {
    //  Make handler list
    private static final HandlerList handlers = new HandlerList();

    //  Event variables
    private final Player player;
    private final int slot;
    private final ItemStack clicked;
    private final Inventory inventory;
    private final ButtonInterface button;

    public ButtonClickEvent(Player player, int slot, ItemStack clicked, Inventory inv,ButtonInterface button){
        this.player     =   player;
        this.slot       =   slot;
        this.clicked    =   clicked;
        this.inventory  =   inv;
        this.button     =   button;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getClicked() {
        return clicked;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ButtonInterface getButton() {
        return button;
    }
}
