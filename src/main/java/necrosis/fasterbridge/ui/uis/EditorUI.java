package necrosis.fasterbridge.ui.uis;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.player.PlayerClass;
import necrosis.fasterbridge.ui.ButtonInterface;
import necrosis.fasterbridge.ui.UIAbstract;
import necrosis.fasterbridge.ui.UIManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EditorUI extends UIAbstract {

    private final FasterBridge plugin;
    private final UIManager manager;

    public EditorUI(FasterBridge plugin,UIManager manager){
        this.plugin = plugin;
        this.manager = manager;
    }

    private static String[] getLore(String buttonName){
        List<String> lores = FasterBridge.instance.getConfig().getStringList("buttons." + buttonName+ ".lore");
        for(int i = 0; i<lores.size(); i++){
            lores.set(i,
                    ChatColor.translateAlternateColorCodes('&',lores.get(i))
                    );
        }
        switch (lores.size()){
            case 0:
                return new String[]{};
            case 1:
                return new String[]{
                        lores.get(0)
                };
            case 2:
                return new String[]{
                        lores.get(0),
                        lores.get(1)
                };
            case 3:
                return new String[]{
                        lores.get(0),
                        lores.get(1),
                        lores.get(2)
                };
            case 4:
                return new String[]{
                        lores.get(0),
                        lores.get(1),
                        lores.get(2),
                        lores.get(3)
                };
            default:
                throw new IllegalStateException("Unexpected value: " + lores.size());
        }
    }

    @Override
    public Inventory GUI(Player player) {
        super.finalName =                 ChatColor.translateAlternateColorCodes('&',
                "&5[&8&l"+
                        FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                .getEditor().getInArena()
                        +"&r&5]"
        );
        Inventory toReturn = Bukkit.createInventory(null,invRows(), super.finalName);

        for(ButtonInterface butt:buttons(player)){
            super.inv.setItem(butt.slot()-1,butt.item(player));
        }

        toReturn.setContents(this.inv.getContents());
        return toReturn;
    }

    @Override
    public String invDisplayName() {
        return "&5Test";
    }

    @Override
    public int invRows() {
        return 9*3;
    }

    @Override
    public List<ButtonInterface> buttons(Player player) {
        List<ButtonInterface> list = new ArrayList<>();

        //  HORIZONTALDEATHZONE
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 3;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("ARROW"),
                        "&5[&7+&5]",
                        false,
                        getLore("deathZoneHorizontal.inc")
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                try {
                    int deathZone =                     FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).getDeathZoneHorizontal();
                    FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).setDeathZoneHorizontal(deathZone+1).save();
                    FasterBridge.instance.getUiManager().openEditor(player);
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 12;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("PAPER"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&7Horizontal deathZone &5[&7" + FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getArenaClass().getDeathZoneHorizontal()+"&5]"
                        ),
                        true,
                        new String[]{}
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
            }
        });

        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 21;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("ARROW"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&5[&7-&5]"
                        ),
                        false,
                        getLore("deathZoneHorizontal.dec")
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                try {
                    int deathZone =                     FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).getDeathZoneHorizontal();
                    FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                            .getEditor().getInArena()
                    ).setDeathZoneHorizontal(deathZone-1).save();
                    FasterBridge.instance.getUiManager().openEditor(player);
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        //  VERTICALDEATHZONE
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 5;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("ARROW"),
                        "&5[&7+&5]",
                        false,
                        getLore("deathZoneVertical.inc")
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                try {
                    int deathZone =                     FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).getDeathZoneVertical();
                    FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).setDeathZoneVertical(deathZone+1).save();
                    FasterBridge.instance.getUiManager().openEditor(player);
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 14;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("PAPER"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&7Vertical deathZone &5[&7" + FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getArenaClass().getDeathZoneVertical()+"&5]"
                        ),
                        true,
                        new String[]{}
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
            }
        });

        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 23;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("ARROW"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&5[&7-&5]"
                        ),
                        false,
                        getLore("deathZoneVertical.dec")
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                try {
                    int deathZone =                     FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).getDeathZoneVertical();
                    FasterBridge.instance.getArenaManager().getArena(
                            FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                    .getEditor().getInArena()
                    ).setDeathZoneVertical(deathZone-1).save();
                    FasterBridge.instance.getUiManager().openEditor(player);
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        //  MISC
        //  MaxPlayer
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 9;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("PAPER"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&7MaxPlayer &5[&7"+
                                        FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                .getEditor().getArenaClass().getMaxPlayer()
                                +"&5]"
                        ),
                        false,
                        new String[]{}
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {

            }
        });
        //  Arena reset
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 18;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("REDSTONE_BLOCK"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&4RESET ARENA"
                        ),
                        false,
                        getLore("arenaReset")
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                PlayerClass playerClass = FasterBridge.instance.getPlayerManager().getPlayerClass(player);
                try {
                    FasterBridge.instance.getConfigManager().getArenasConfig().resetLocations(playerClass.getEditor().getInArena());
                    player.closeInventory();
                    player.sendMessage(FasterBridge.instance.cfs("buttons.arenaReset.succ"));
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                } catch (MaxSlotException e) {
                    e.printStackTrace();
                }
            }
        });
        //  ArenaName
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 27;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("PAPER"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&7ArenaName &5[&7"+
                                        FasterBridge.instance.getPlayerManager().getPlayerClass(player)
                                .getEditor().getInArena()
                                + "&5]"
                        ),
                        false,
                        new String[]{}
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {

            }
        });
        //  EDITORLeave
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 19;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("BARRIER"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&3Leave editor"
                        ),
                        false,
                        new String[]{}
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                try {
                    ArenaClass arena =FasterBridge.instance.getArenaManager().getArena(FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena());
                    arena.setActive(arena.isValid());
                    if(arena.isActive()){
                        player.sendMessage(FasterBridge.instance.cfs("buttons.leaveEditor.valid"));
                    }else{
                        player.sendMessage(FasterBridge.instance.cfs("buttons.leaveEditor.notvalid"));
                    }
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                }
                FasterBridge.instance.getArenaManager().player().getArenaPlayerLeaveEditor().leaveEditor(player);
                player.closeInventory();
            }
        });
        //  TRIGGER ACTIVE
        try {
            list.add(new ButtonInterface(1,this.plugin.getArenaManager().getArena(this.plugin.getPlayerManager().getPlayerClass(player).getEditor().getInArena())) {
                @Override
                public int slot() {
                    return 1;
                }

                @Override
                public ItemStack item(Player player) {
                    return FasterBridge.instance.getUiManager().createItem(
                            Material.getMaterial("PAPER"),
                            ChatColor.translateAlternateColorCodes('&',
                                    "&8Tigger isActive"
                            ),
                            super.arenaHolder.isActive(),
                            new String[]{
                                    "&5[ &8&l" + super.arenaHolder.isActive() + " &r&5]"
                            }
                    );
                }

                @Override
                public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                    super.arenaHolder.setActive(!super.arenaHolder.isActive());
                    FasterBridge.instance.getUiManager().openEditor(player);
                }
            });
        }catch (ArenaNotFoundException e){

        }
        //  DELETE ARENA
        list.add(new ButtonInterface() {
            @Override
            public int slot() {
                return 17;
            }

            @Override
            public ItemStack item(Player player) {
                return FasterBridge.instance.getUiManager().createItem(
                        Material.getMaterial("REDSTONE_BLOCK"),
                        ChatColor.translateAlternateColorCodes('&',
                                "&4ARENA REMOVE"
                        ),
                        true,
                        getLore("arenaDelete")
                );
            }

            @Override
            public void click(Player player, int slot, ItemStack clicked, Inventory inv) {
                try {
                    String arenaName = FasterBridge.instance.getPlayerManager().getPlayerClass(player).getEditor().getInArena();
                    FasterBridge.instance.getArenaManager().editor().getArenaDelete().deleteArena(
                            arenaName
                    );
                    FasterBridge.instance.getArenaManager().player().getArenaPlayerLeaveEditor().leaveEditor(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            FasterBridge.instance.cfs("buttons.arenaDelete.succ","%arenaName%",arenaName)
                            ));
                    player.closeInventory();
                } catch (ArenaNotFoundException e) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            FasterBridge.instance.cfs("buttons.arenaDelete.fail")
                    ));
                    player.closeInventory();
                }
            }
        });
        return list;
    }
}
