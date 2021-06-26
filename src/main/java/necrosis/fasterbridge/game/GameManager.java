package necrosis.fasterbridge.game;

import necrosis.fasterbridge.FasterBridge;
import necrosis.fasterbridge.game.methods.GameActionTimer;
import necrosis.fasterbridge.game.methods.GamePlayerDeath;
import necrosis.fasterbridge.game.methods.GamePlayerWin;

public class GameManager {

    private final FasterBridge plugin;

    private final GamePlayerDeath gamePlayerDeath;
    private final GamePlayerWin gamePlayerWin;
    private final GameActionTimer actionTimer;

    public GameManager(FasterBridge plugin){
        this.plugin = plugin;

        this.gamePlayerDeath = new GamePlayerDeath(plugin);
        this.gamePlayerWin = new GamePlayerWin(plugin);
        this.actionTimer = new GameActionTimer(plugin);
    }

    public GamePlayerDeath getGamePlayerDeath() {
        return gamePlayerDeath;
    }

    public GamePlayerWin getGamePlayerWin() {
        return gamePlayerWin;
    }

    public GameActionTimer getActionTimer() {
        return actionTimer;
    }
}
