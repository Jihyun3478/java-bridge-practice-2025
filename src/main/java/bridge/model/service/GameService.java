package bridge.model.service;

import bridge.model.domain.Bridge;
import bridge.model.domain.BridgeGame;
import bridge.model.domain.BridgeStatus;

public class GameService {
    private final BridgeGame bridgeGame;

    public GameService(Bridge bridge) {
        this.bridgeGame = new BridgeGame(bridge);
    }

    public String processMove(String playerMove, int position) {
        return bridgeGame.move(playerMove, position);
    }

    public void retryGame() {
        bridgeGame.retry();
    }

    public BridgeStatus getCurrentStatus() {
        return bridgeGame.getCurrentStatus();
    }

    public int getBridgeSize() {
        return bridgeGame.getBridgeSize();
    }
}
