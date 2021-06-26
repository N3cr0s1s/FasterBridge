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
                            "&5[ &8&l" + super.arenaHolder.getArenaName() + " &r&5]",
                            arena.isActive(),
                            new String[]{
                                    "&8Max players: ",
                                    "&5[&7" + super.arenaHolder.getMaxPlayer() + " : &8&l" + super.arenaHolder.getFreeSlots() + "&r&5]",
                                    "&8Is active: ",
                                    "&5[&7 " + super.arenaHolder.isActive() + " &5]"
                            }
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
