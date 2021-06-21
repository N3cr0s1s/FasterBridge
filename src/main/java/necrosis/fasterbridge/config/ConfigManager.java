package necrosis.fasterbridge.config;


import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.config.configFiles.ArenasConfig;
import necrosis.fasterbridge.config.configFiles.BlocksConfig;

public final class ConfigManager {
    private FasterBridge plugin;

    //  CLASSES
    private final BlocksConfig blocksConfig;
    private final ArenasConfig arenasConfig;

    public ConfigManager(FasterBridge plugin){
        this.plugin = plugin;

        //  CLASSES
        this.blocksConfig = new BlocksConfig(plugin);
        this.arenasConfig = new ArenasConfig(plugin);
    }

    public BlocksConfig getBlocksConfig() {
        return blocksConfig;
    }

    public ArenasConfig getArenasConfig() {
        return arenasConfig;
    }
}
