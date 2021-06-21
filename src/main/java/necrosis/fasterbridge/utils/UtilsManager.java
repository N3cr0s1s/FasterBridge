package necrosis.fasterbridge.utils;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.utils.utilities.DirectionCalculator;

public class UtilsManager {

    private final FasterBridge plugin;
    private final DirectionCalculator directionCalculator;

    public UtilsManager(FasterBridge plugin){
        this.directionCalculator = new DirectionCalculator();
        this.plugin = plugin;
    }

    public DirectionCalculator getDirectionCalculator() {
        return directionCalculator;
    }
}
