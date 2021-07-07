package necrosis.fasterbridge.gadget;

import cornerlesscube.craftkit.CraftKit;
import cornerlesscube.craftkit.ExcludeFromAutoRegister;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.GadgetNotRegisteredException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@ExcludeFromAutoRegister
public abstract class GadgetAbstract implements GadgetMethodInterface{

    public GadgetAbstract(){
        init();
    }

    private ItemStack gadget;

    public abstract String name();
    public abstract Material itemMaterial();
    public abstract String displayName();
    public abstract String[] lore();
    public abstract String permission();

    public void init(){
        this.createItem();
        Bukkit.getPluginManager().registerEvents(new GadgetListener(this), FasterBridge.instance);
        FasterBridge.instance.getGadgetManager().registerGadget(this);
    };

    private void createItem(){
        ItemStack item = new ItemStack(this.itemMaterial(),1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',this.displayName()));
        List<String> lores = new ArrayList<>();
        for(String s:lore()) lores.add(ChatColor.translateAlternateColorCodes('&',s));
        meta.setLore(lores);
        meta.addEnchant(Enchantment.LUCK,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        this.gadget = item;
    }

    public ItemStack getGadget() throws GadgetNotRegisteredException {
        if(this.gadget == null) throw new GadgetNotRegisteredException("Gadget not registered.",this.displayName());
        return this.gadget;
    }

    @Override
    public abstract void itemFunction(Player player);

    //  LISTENER
    @ExcludeFromAutoRegister
    public class GadgetListener implements Listener{

        private GadgetAbstract gadget;

        public GadgetListener(GadgetAbstract gadget){
            this.gadget = gadget;
        }

        @EventHandler
        public void onRightClick(PlayerInteractEvent event){
            if(!event.getPlayer().hasPermission(gadget.permission())) return;
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (event.getItem() == null) return;
                if (this.gadget.gadget.getItemMeta() == null) return;
                if (!event.getItem().getItemMeta().equals(this.gadget.gadget.getItemMeta())) return;
                this.gadget.itemFunction(event.getPlayer());
                event.setCancelled(true);
            }
        }

        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event) throws GadgetNotRegisteredException {
            if(CraftKit.getInstance().getNms().getNmsVersion().contains("v1_8")){
                if(event.getPlayer().getInventory().getItemInHand().getItemMeta().
                        equals(this.gadget.gadget.getItemMeta())){
                    event.setCancelled(true);
                }
                return;
            }
            if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().
                    equals(this.gadget.getGadget().getItemMeta())){
                event.setCancelled(true);
            }
        }

        @EventHandler
        public void onDrop(PlayerDropItemEvent event){
            if(event.getItemDrop().getItemStack().getItemMeta().equals(this.gadget.gadget.getItemMeta())){
                event.setCancelled(true);
            }
        }
    }
}
