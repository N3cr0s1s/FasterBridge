package necrosis.fasterbridge.utils;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.utils.utilities.ActionBarUtil;
import necrosis.fasterbridge.utils.utilities.DirectionCalculator;

public class UtilsManager {

    private final FasterBridge plugin;
    private final DirectionCalculator directionCalculator;
    private final ActionBarUtil actionBarUtil;

    public UtilsManager(FasterBridge plugin){
        this.directionCalculator = new DirectionCalculator();
        this.plugin = plugin;

        this.actionBarUtil = new ActionBarUtil();
    }

    public DirectionCalculator getDirectionCalculator() {
        return directionCalculator;
    }

    public ActionBarUtil getActionBarUtil() {
        return actionBarUtil;
    }
}
