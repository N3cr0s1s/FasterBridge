package necrosis.fasterbridge.ui;

import cornerlesscube.craftkit.ExcludeFromAutoRegister;
import necrosis.fasterbridge.FasterBridge;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class UIAbstract {

    public Inventory inv;
    public String finalName;
    public abstract String invDisplayName();
    public abstract int invRows();
    public abstract List<ButtonInterface> buttons(Player player);

    public UIAbstract(){
        this.inv = Bukkit.createInventory(null,this.invRows());
        Bukkit.getPluginManager().registerEvents(new UIListener(this), FasterBridge.instance);
    }

    public Inventory GUI(Player player){
        this.finalName = invDisplayName();
        Inventory toReturn = Bukkit.createInventory(null,invRows(),this.finalName);

        for(ButtonInterface butt:buttons(player)){
            this.inv.setItem(butt.slot()-1,butt.item(player));
        }

        toReturn.setContents(this.inv.getContents());
        return toReturn;
    }

    @ExcludeFromAutoRegister
    public class UIListener implements Listener {

        private UIAbstract uiAbstract;

        public UIListener(UIAbstract uiAbstract){
            this.uiAbstract = uiAbstract;
        }

        @EventHandler
        public void onClick(InventoryClickEvent e){
            if(e.getCurrentItem() == null) return;
            if(!e.getView().getTitle().equals(this.uiAbstract.finalName)) return;
            for(ButtonInterface b:this.uiAbstract.buttons((Player) e.getWhoClicked())){
                if(e.getCurrentItem().equals(b.item((Player) e.getWhoClicked()))){
                    b.click((Player) e.getWhoClicked(),e.getSlot(),e.getCurrentItem(),e.getInventory());
                    e.setCancelled(true);
                }
            }
        }
    }
}
