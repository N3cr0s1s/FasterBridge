package necrosis.fasterbridge;

import cornerlesscube.craftkit.CraftKit;
import cornerlesscube.craftkit.PluginEntry;
import necrosis.fasterbridge.arena.ArenaManager;
import necrosis.fasterbridge.config.ConfigManager;
import necrosis.fasterbridge.events.editorEvents.blockPlace.FunctionBlockManager;
import necrosis.fasterbridge.events.gameEvents.GameEvents_1_9;
import necrosis.fasterbridge.events.playerEvents.PlayerEventsJoin;
import necrosis.fasterbridge.exceptions.ArenaNotFoundException;
import necrosis.fasterbridge.exceptions.MaxSlotException;
import necrosis.fasterbridge.exceptions.NotInArenaException;
import necrosis.fasterbridge.gadget.GadgetManager;
import necrosis.fasterbridge.game.GameManager;
import necrosis.fasterbridge.player.PlayerClass;
import necrosis.fasterbridge.player.PlayerManager;
import necrosis.fasterbridge.ui.UIManager;
import necrosis.fasterbridge.utils.UtilsManager;
import necrosis.fasterbridge.version.VersionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/*

    CREATED BY: NECROSIS
    2021-06-xx -> 2021-07-xx

 */

public final class FasterBridge extends PluginEntry {

    public static FasterBridge instance;

    //  CLASSES
    private final PlayerManager playerManager = new PlayerManager(this);
    private final ConfigManager configManager = new ConfigManager(this);
    private final ArenaManager arenaManager = new ArenaManager(this);
    private final UtilsManager utilsManager = new UtilsManager(this);
    private final GadgetManager gadgetManager = new GadgetManager(this);
    private final FunctionBlockManager functionBlockManager = new FunctionBlockManager(this);
    private final UIManager uiManager = new UIManager(this);
    private final GameManager gameManager = new GameManager(this);
    private final VersionManager versionManager = new VersionManager(this);

    @Override
    public void onEnable() {
        //  Make main class static
        this.instance = this;

        //  Set server version
        this.versionManager.setVersion();

        //  Set logger prefix
        logger().setPrefix("&5FasterBridge");

        //  Register commands and listeners
        autoCommands(this.getClass());
        autoListeners(this.getClass());
        listeners(
                new PlayerEventsJoin(this)
        );
        if(this.versionManager.getVersion() >= 9) listeners(new GameEvents_1_9(this));

        //  Load some objects
        this.configManager.getArenasConfig().loadAllArena();
        this.configManager.getSavedBlocksConfig().getBlocks();

        //  Register main objects
        this.gadgetManager.registerGadget();
        this.functionBlockManager.registerFunctionBlock();
        this.uiManager.registerUIs();

        //  If server reloaded, add playerclass to online players
        for(Player player: Bukkit.getOnlinePlayers()) this.playerManager.createPlayerClass(player);
    }

    @Override
    public void onDisable() {
        //  Force exit players from arena
        for(PlayerClass p:this.playerManager.getPlayerClassMap().values()){
            if(p.getEditor().isInEditor()){
                this.arenaManager.player().getArenaPlayerLeaveEditor().leaveEditor(p);
            }
            if(p.getGame().isInGame()){
                try {
                    this.arenaManager.player().getArenaPlayerLeave().leaveArena(Bukkit.getPlayer(p.getPlayerUUID()));
                } catch (NotInArenaException | ArenaNotFoundException | MaxSlotException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public UtilsManager getUtilsManager() {
        return utilsManager;
    }

    public GadgetManager getGadgetManager() {
        return gadgetManager;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public FunctionBlockManager getFunctionBlockManager() {
        return functionBlockManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public VersionManager getVersionManager() {
        return versionManager;
    }

    public String cfs(String path){
        return ChatColor.translateAlternateColorCodes('&',this.getConfig().getString(path));
    }

    public String cfs(String path,String replace,String toReplace){
        return ChatColor.translateAlternateColorCodes('&',this.getConfig().getString(path).replace(replace,toReplace));
    }

}
