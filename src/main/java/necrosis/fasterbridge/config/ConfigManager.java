package necrosis.fasterbridge.config;


import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.config.configFiles.ArenasConfig;
import necrosis.fasterbridge.config.configFiles.BlocksConfig;
import necrosis.fasterbridge.config.configFiles.PlayerRecordsConfig;

public final class ConfigManager {
    private FasterBridge plugin;

    //  CLASSES
    private final BlocksConfig blocksConfig;
    private final ArenasConfig arenasConfig;
    private final PlayerRecordsConfig playerRecordsConfig;

    public ConfigManager(FasterBridge plugin){
        this.plugin = plugin;

        //  CLASSES
        this.blocksConfig = new BlocksConfig(plugin);
        this.arenasConfig = new ArenasConfig(plugin);
        this.playerRecordsConfig = new PlayerRecordsConfig(plugin);
    }

    public BlocksConfig getBlocksConfig() {
        return blocksConfig;
    }

    public ArenasConfig getArenasConfig() {
        return arenasConfig;
    }

    public PlayerRecordsConfig getPlayerRecordsConfig() {
        return playerRecordsConfig;
    }
}
