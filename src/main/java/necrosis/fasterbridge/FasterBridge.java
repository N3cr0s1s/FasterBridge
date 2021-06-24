package necrosis.fasterbridge;

import cornerlesscube.craftkit.PluginEntry;
import necrosis.fasterbridge.arena.ArenaManager;
import necrosis.fasterbridge.config.ConfigManager;
import necrosis.fasterbridge.events.editorEvents.blockPlace.FunctionBlockManager;
import necrosis.fasterbridge.events.playerEvents.PlayerEventsJoin;
import necrosis.fasterbridge.gadget.GadgetManager;
import necrosis.fasterbridge.gadget.gadgets.ArenaEditorGadget;
import necrosis.fasterbridge.gadget.gadgets.ArenaLeaveGadget;
import necrosis.fasterbridge.gadget.gadgets.ArenaSelectorGadget;
import necrosis.fasterbridge.player.PlayerManager;
import necrosis.fasterbridge.ui.UIManager;
import necrosis.fasterbridge.utils.UtilsManager;

public final class FasterBridge extends PluginEntry {

    public static FasterBridge instance;

    //  CLASSES
    private final PlayerManager playerManager = new PlayerManager(this);
    private final ConfigManager configManager = new ConfigManager(this);
    private final ArenaManager arenaManager = new ArenaManager(this);
    private final UtilsManager utilsManager = new UtilsManager(this);
    private final GadgetManager gadgetManager = new GadgetManager(this);
    private final UIManager uiManager = new UIManager(this);
    private final FunctionBlockManager functionBlockManager = new FunctionBlockManager(this);

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
    }

    @Override
    public void onDisable() {
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
}
