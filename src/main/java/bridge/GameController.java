package bridge;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.startMessage();

        int bridgeSize = getBridgeSize();

        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<String> bridge = bridgeMaker.makeBridge(bridgeSize);
        List<String> upBridge = new ArrayList<>();
        List<String> downBridge = new ArrayList<>();

        BridgeGame bridgeGame = new BridgeGame();
        for (int count = 0; count < bridgeSize; count++) {
            String currentBridgePosition = bridge.get(count);
            String movingSpace = getMovingSpace();

            String currentBridgeResult = bridgeGame.move(currentBridgePosition, movingSpace);
            if (currentBridgePosition.equals("U")) {
                upBridge.add(currentBridgeResult);
                downBridge.add(" ");
            }
            if (currentBridgePosition.equals("D")) {
                downBridge.add(currentBridgeResult);
                upBridge.add(" ");
            }
            outputView.printMap(upBridge, downBridge);
        }
    }

    private int getBridgeSize() {
        while (true) {
            try {
                return inputView.readBridgeSize();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }

    }

    private String getMovingSpace() {
        while (true) {
            try {
                return inputView.readMoving();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
