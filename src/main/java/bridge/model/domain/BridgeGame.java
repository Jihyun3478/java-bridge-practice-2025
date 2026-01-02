package bridge.model.domain;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Bridge bridge;
    private BridgeStatus currentStatus;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
        this.currentStatus = new BridgeStatus();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public String move(String playerMove, int position) {
        String bridgePosition = bridge.getPosition(position);

        if (bridgePosition.equals(playerMove)) {
            currentStatus.addMove(bridgePosition, "O");
            return "O";
        }

        currentStatus.addMove(bridgePosition, "X");
        return "X";
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.currentStatus = new BridgeStatus();
    }

    public BridgeStatus getCurrentStatus() {
        return currentStatus;
    }

    public int getBridgeSize() {
        return bridge.size();
    }
}
