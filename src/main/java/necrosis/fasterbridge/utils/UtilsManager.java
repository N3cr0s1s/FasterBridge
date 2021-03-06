package necrosis.fasterbridge.utils;

import cornerlesscube.craftkit.CraftKit;
import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.utils.utilities.ActionBarUtil;
import necrosis.fasterbridge.utils.utilities.DirectionCalculator;
import necrosis.fasterbridge.utils.utilities.ScoreboardHandler;
import necrosis.fasterbridge.utils.utilities.SendTitleUtil;

public class UtilsManager {

    private final FasterBridge plugin;
    private final DirectionCalculator directionCalculator;
    private ActionBarUtil actionBarUtil;
    private SendTitleUtil sendTitleUtil;
    private final ScoreboardHandler scoreboardHandler;

    public UtilsManager(FasterBridge plugin){
        this.directionCalculator = new DirectionCalculator();
        this.plugin = plugin;
        this.scoreboardHandler = new ScoreboardHandler(plugin);

        try{this.actionBarUtil = new ActionBarUtil();}catch (NoClassDefFoundError e){};
        try{this.sendTitleUtil = new SendTitleUtil();}catch (NoClassDefFoundError e){};
    }

    public DirectionCalculator getDirectionCalculator() {
        return directionCalculator;
    }

    public ActionBarUtil getActionBarUtil() {
        return actionBarUtil;
    }

    public SendTitleUtil getSendTitleUtil() {
        return sendTitleUtil;
    }

    public ScoreboardHandler getScoreboardHandler() {
        return scoreboardHandler;
    }
}
