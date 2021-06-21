package necrosis.fasterbridge;

import cornerlesscube.craftkit.PluginEntry;
import necrosis.fasterbridge.arena.ArenaManager;
import necrosis.fasterbridge.config.ConfigManager;
import necrosis.fasterbridge.events.playerEvents.PlayerEventsJoin;
import necrosis.fasterbridge.player.PlayerManager;
import necrosis.fasterbridge.utils.UtilsManager;

public final class FasterBridge extends PluginEntry {

    public static FasterBridge instance;

    //  CLASSES
    private final PlayerManager playerManager = new PlayerManager(this);
    private final ConfigManager configManager = new ConfigManager(this);
    private final ArenaManager arenaManager = new ArenaManager(this);
    private final UtilsManager utilsManager = new UtilsManager(this);

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
}