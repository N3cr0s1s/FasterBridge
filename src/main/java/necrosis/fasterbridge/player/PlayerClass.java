package necrosis.fasterbridge.player;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.arena.ArenaClass;
import necrosis.fasterbridge.customevents.player.ChangeBlockEvent;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.player.classes.EditorClass;
import necrosis.fasterbridge.player.classes.GameClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerClass {
    private final FasterBridge plugin;

    private final String playerName;
    private final UUID playerUUID;
    private ItemStack block;

    private GameClass game;
    private EditorClass editor;

    public PlayerClass(Player player,FasterBridge plugin){
        this.playerName = player.getName();
        this.playerUUID = player.getUniqueId();
        this.game = new GameClass();
        this.editor = new EditorClass();
        this.plugin = plugin;
        this.block = new ItemStack(Material.SANDSTONE,64);
    }

    public PlayerClass isBlockSet(){
        Player player = Bukkit.getPlayer(getPlayerUUID());
        if(plugin.getConfigManager().getBlocksConfig().isBlockSet(player)) {
            this.block = plugin.getConfigManager().getBlocksConfig().getBlock(player);
        } else{
            this.block = new ItemStack(Material.SANDSTONE,64);
        }
        return this;
    }

    public ItemStack getBlock() {
        this.isBlockSet();
        return this.block;
    }

    public void setBlock(ItemStack block) {
        Player player = Bukkit.getPlayer(getPlayerUUID());
        this.plugin.getConfigManager().getBlocksConfig().setBlock(player,block.getType(),block.getItemMeta().getDisplayName());
        this.block = new ItemStack(block.getType(),64);

        //  Call ChangeBlockEvent
        Bukkit.getServer().getPluginManager().callEvent(
                new ChangeBlockEvent(
                        player,
                        this.plugin.getPlayerManager().getPlayerClass(player),
                        block.getType(),
                        block.getItemMeta().getDisplayName()
                )
        );
    }

    public void setBlock(Material block,String name) {
        Player player = Bukkit.getPlayer(getPlayerUUID());
        this.plugin.getConfigManager().getBlocksConfig().setBlock(player,block,block.name());
        this.block = new ItemStack(block,64);

        //  Call ChangeBlockEvent
        Bukkit.getServer().getPluginManager().callEvent(
                new ChangeBlockEvent(
                        player,
                        this.plugin.getPlayerManager().getPlayerClass(player),
                        block,
                        name
                )
        );
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public GameClass getGame() {
        return this.game;
    }

    public EditorClass getEditor() {
        return this.editor;
    }

    public ArenaClass setEditor(ArenaClass arenaClass){
        return this.getEditor().setEditor(arenaClass);
    }

    public ArenaClass setEditor(String arenaName) throws ArenaNotFoundException {
        ArenaClass arenaClass = this.plugin.getArenaManager().getArena(arenaName);
        return this.getEditor().setEditor(arenaClass);
    }

    public void removeEditor(){
        this.getEditor().removeEditor();
    }

    public boolean inEditor(){
        return this.editor.isInEditor();
    }
}
