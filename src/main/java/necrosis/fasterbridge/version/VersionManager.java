package necrosis.fasterbridge.version;

import cornerlesscube.craftkit.CraftKit;
import necrosis.fasterbridge.FasterBridge;

public class VersionManager {

    private final FasterBridge plugin;
    private Versions version;

    public VersionManager(FasterBridge plugin){
        this.plugin =   plugin;
    }

    //  Set version manager version
    public VersionManager setVersion(){
        this.version = Versions.valueOf(CraftKit.getInstance().getNms().getNmsVersion().toUpperCase());
        this.plugin.logger().getInfo("FasterBridge version is: " + version.getVersion() + ", and: " + version.getName());
        return this;
    }

    //  Get simple version integer
    public int getVersion(){
        return this.version.getVersion();
    }
}
