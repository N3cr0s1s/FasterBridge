package necrosis.fasterbridge;

import cornerlesscube.craftkit.PluginEntry;
import necrosis.fasterbridge.arena.ArenaManager;
import necrosis.fasterbridge.config.ConfigManager;
import necrosis.fasterbridge.events.editorEvents.blockPlace.FunctionBlockManager;
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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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

    @Override
    public void onEnable() {
        this.instance = this;
        logger().setPrefix("&5FasterBridge");
        autoCommands(this.getClass());
        autoListeners(this.getClass());

        listeners(
                new PlayerEventsJoin(this)
        );

        logger().getInfo("FasterBridge ");

        this.configManager.getArenasConfig().loadAllArena();

        this.gadgetManager.registerGadget();
        this.functionBlockManager.registerFunctionBlock();
        this.uiManager.registerUIs();

        for(Player player: Bukkit.getOnlinePlayers()) this.playerManager.createPlayerClass(player);
    }

    @Override
    public void onDisable() {
        for(PlayerClass p:this.playerManager.getPlayerClassMap().values()){
            if(p.getGame().isInGame()){
                try {
                    this.arenaManager.player().getArenaPlayerLeave().leaveArena(Bukkit.getPlayer(p.getPlayerUUID()));
                } catch (NotInArenaException e) {
                    e.printStackTrace();
                } catch (ArenaNotFoundException e) {
                    e.printStackTrace();
                } catch (MaxSlotException e) {
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

    public String cfs(String path){
        return ChatColor.translateAlternateColorCodes('&',this.getConfig().getString(path));
    }

    public String cfs(String path,String replace,String toReplace){
        return ChatColor.translateAlternateColorCodes('&',this.getConfig().getString(path).replace(replace,toReplace));
    }
}
