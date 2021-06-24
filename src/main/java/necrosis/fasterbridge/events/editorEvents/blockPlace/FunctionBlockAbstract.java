package necrosis.fasterbridge.events.editorEvents.blockPlace;

import cornerlesscube.craftkit.ExcludeFromAutoRegister;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.exceptions.FunctionBlockNotRegisteredException;
import necrosis.fasterbridge.gadget.GadgetAbstract;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class FunctionBlockAbstract {

    public FunctionBlockAbstract(){
        init();
    }

    private ItemStack functionBlock;

    public abstract String name();
    public abstract Material itemMaterial();
    public abstract String displayName();
    public abstract String[] lore();
    public abstract void function(Player player, Location location);

    public ItemStack getFunctionBlock()throws  FunctionBlockNotRegisteredException{
        if(this.functionBlock == null) throw new FunctionBlockNotRegisteredException("Function block not registered,or created",name());
        return this.functionBlock;
    }

    public void init(){
        this.createItem();
        Bukkit.getPluginManager().registerEvents(new FunctionBlockAbstract.FunctionBlockListener(this), FasterBridge.instance);
        FasterBridge.instance.getFunctionBlockManager().registerFunctionBlock(this);
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
        this.functionBlock = item;
    }

    @ExcludeFromAutoRegister
    public class FunctionBlockListener implements Listener{

        private FunctionBlockAbstract functionBlock;

        public FunctionBlockListener(FunctionBlockAbstract functionBlock){
            this.functionBlock = functionBlock;
        }

        @EventHandler
        public void onPlace(BlockPlaceEvent event){
            if(
                    !FasterBridge.instance.getPlayerManager().getPlayerClass(event.getPlayer())
                            .getEditor().isInEditor()
            ) return;
            if (this.functionBlock.functionBlock.getItemMeta() == null) return;
            if (!event.getPlayer().getItemInHand().getItemMeta().equals(this.functionBlock.functionBlock.getItemMeta())) return;
            event.setCancelled(true);
            functionBlock.function(event.getPlayer(),
                    new Location(
                            event.getPlayer().getWorld(),
                            event.getBlock().getX(),
                            event.getBlock().getY(),
                            event.getBlock().getZ(),
                            event.getPlayer().getLocation().getYaw(),
                            event.getBlock().getLocation().getPitch()
                    )
                    );
        }

        @EventHandler
        public void onDrop(PlayerDropItemEvent event){
            if(event.getItemDrop().getItemStack().getItemMeta().equals(this.functionBlock.functionBlock.getItemMeta())){
                event.setCancelled(true);
            }
        }
    }
}
