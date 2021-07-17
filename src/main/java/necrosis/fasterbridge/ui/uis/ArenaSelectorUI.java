package necrosis.fasterbridge.ui.uis;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotValidException;
import necrosis.fasterbridge.exceptions.FullSlotException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.exceptions.TooManyArenasException;
import necrosis.fasterbridge.ui.ButtonInterface;
import necrosis.fasterbridge.ui.UIAbstract;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArenaSelectorUI extends UIAbstract {

    private final FasterBridge plugin;

    public ArenaSelectorUI(FasterBridge plugin){
        this.plugin = plugin;
    }

    @Override
    public String invDisplayName() {
        return "&5ArenaSelector";
    }

    @Override
    public int invRows() {
        return 54;
    }

    @Override
    public List<ButtonInterface> buttons(Player player) {
        List<ButtonInterface> list = new ArrayList<>();
        int i = 0;
        for(ArenaClass arena:this.plugin.getArenaManager().getArenaMap().values()){
            list.add(new ButtonInterface(i,arena) {
                @Override
                public int slot() {
                    return super.slotHolder;
                }

                @Override
                public ItemStack item(Player player) {
                    return FasterBridge.instance.getUiManager().createItem(
                            Material.PAPER,
                            getPlaceholderTitle(FasterBridge.instance.cfs("ui.arena-selector.title"), super.arenaHolder),
                            arena.isActive(),
                            getPlaceholderLore(super.arenaHolder)
                    );
                }

                @Override
                public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                    if(!super.arenaHolder.isActive()){
                        player.sendMessage(FasterBridge.instance.cfs("buttons.arenaSelector.notactive"));
                        return;
                    }
                    try {
                        FasterBridge.instance.getArenaManager().player().getArenaPlayerJoin().joinArena(player,super.arenaHolder);
                    } catch (ArenaNotValidException e) {
                        e.printStackTrace();
                    } catch (FullSlotException e) {
                        player.sendMessage(FasterBridge.instance.cfs("buttons.arenaSelector.isfull"));
                    } catch (MaxSlotException e) {
                        player.sendMessage(FasterBridge.instance.cfs("buttons.arenaSelector.isfull"));
                    }
                }
            });
            i++;
        }
        return list;
    }

    private String[] getPlaceholderLore(ArenaClass arena){
        List<String> list = new ArrayList<>();
        for(String message:FasterBridge.instance.getConfig().getStringList("ui.arena-selector.lore")){
            if(message.equals("") || message == null) continue;
            while(message.contains("%freeSlot%") || message.contains("%maxPlayers%") || message.contains("%isActive%") || message.contains("%arenaName%")){
                message = message.replace("%freeSlot%",arena.getFreeSlots() + "")
                        .replace("%maxPlayers%",arena.getMaxPlayer()+"")
                        .replace("%isActive%",arena.isActive()+"")
                        .replace("%arenaName%",arena.getArenaName());
            }
            list.add(message);
        }
        for(int i = 0; i < 4; i++){
            if(list.get(i) == null) list.set(i,"");
        }
        return new String[]{list.get(0),list.get(1),list.get(2),list.get(3)};
    }

    public String getPlaceholderTitle(String message,ArenaClass arena){
        while(message.contains("%freeSlot%") || message.contains("%maxPlayers%") || message.contains("%isActive%") || message.contains("%arenaName%")){
            message = message.replace("%freeSlot%",arena.getFreeSlots() + "")
                    .replace("%maxPlayers%",arena.getMaxPlayer()+"")
                    .replace("%isActive%",arena.isActive()+"")
                    .replace("%arenaName%",arena.getArenaName());
        }
        return message;
    }

    @Override
    public Inventory GUI(Player player){
        this.finalName = ChatColor.translateAlternateColorCodes('&',invDisplayName());
        Inventory toReturn = Bukkit.createInventory(null,invRows(),this.finalName);

        for(ButtonInterface butt:buttons(player)){
            this.inv.setItem(butt.slot(),butt.item(player));
        }

        toReturn.setContents(this.inv.getContents());
        return toReturn;
    }
}
