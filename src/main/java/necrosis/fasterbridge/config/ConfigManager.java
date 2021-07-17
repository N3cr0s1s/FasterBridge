package necrosis.fasterbridge.config;


import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.config.configFiles.ArenasConfig;
import necrosis.fasterbridge.config.configFiles.BlocksConfig;
import necrosis.fasterbridge.config.configFiles.PlayerRecordsConfig;
import necrosis.fasterbridge.config.configFiles.SavedBlocksConfig;

public final class ConfigManager {
    private FasterBridge plugin;

    //  CLASSES
    private final BlocksConfig blocksConfig;
    private final ArenasConfig arenasConfig;
    private final PlayerRecordsConfig playerRecordsConfig;
    private final SavedBlocksConfig savedBlocksConfig;

    public ConfigManager(FasterBridge plugin){
        this.plugin = plugin;

        //  CLASSES
        this.blocksConfig = new BlocksConfig(plugin);
        this.arenasConfig = new ArenasConfig(plugin);
        this.playerRecordsConfig = new PlayerRecordsConfig(plugin);
        this.savedBlocksConfig = new SavedBlocksConfig(plugin);
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

    public SavedBlocksConfig getSavedBlocksConfig() {
        return savedBlocksConfig;
    }
}
