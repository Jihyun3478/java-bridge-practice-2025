package bridge.model.domain.dto;

import bridge.model.domain.BridgeStatus;

public class GameResult {
    private BridgeStatus bridgeStatus;
    private boolean isSuccess;
    private int attemptCount;

    private GameResult(BridgeStatus bridgeStatus, boolean isSuccess, int attemptCount) {
        this.bridgeStatus = bridgeStatus;
        this.isSuccess = isSuccess;
        this.attemptCount = attemptCount;
    }

    public static GameResult from(BridgeStatus bridgeStatus, boolean isSuccess, int attemptCount) {
        return new GameResult(bridgeStatus, isSuccess, attemptCount);
    }

    public BridgeStatus getBridgeStatus() {
        return bridgeStatus;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
