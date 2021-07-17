package necrosis.fasterbridge.ui;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.ui.uis.ArenaSelectorUI;
import necrosis.fasterbridge.ui.uis.BlockSelectUI;
import necrosis.fasterbridge.ui.uis.EditorUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private FasterBridge plugin;

    private EditorUI editorUI;
    private ArenaSelectorUI arenaSelectorUI;
    private BlockSelectUI blockSelectUI;

    public UIManager(FasterBridge plugin){
        this.plugin = plugin;
    }

    public ItemStack createItem(Material material, String displayName,boolean enchant,String... lore){
        ItemStack item = new ItemStack(material,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',displayName));
        List<String> lores = new ArrayList<>();
        for(String s:lore){
            if(s == null || s.equals("")) continue;
            lores.add(ChatColor.translateAlternateColorCodes('&',s));
        }
        meta.setLore(lores);
        if(enchant){
            meta.addEnchant(Enchantment.LUCK,1,false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    public void registerUIs(){
        this.editorUI = new EditorUI(plugin,this);
        this.arenaSelectorUI = new ArenaSelectorUI(this.plugin);
        this.blockSelectUI = new BlockSelectUI(this.plugin);
    }

    public void openEditor(Player player){
        player.openInventory(this.editorUI.GUI(player));
    }

    public void openArenaSelector(Player player){
        player.openInventory(this.arenaSelectorUI.GUI(player));
    }

    public void openBlocks(Player player) { player.openInventory(this.blockSelectUI.GUI(player)); }
}
