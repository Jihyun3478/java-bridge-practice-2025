package bridge.controller;

import bridge.BridgeRandomNumberGenerator;
import bridge.model.domain.BridgeGame;
import bridge.model.domain.BridgeMaker;
import bridge.view.InputView;
import bridge.view.OutputView;
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

        boolean gameSuccess = gameProcess(bridge, upBridge, downBridge);
        int count = 1;

        if (gameSuccess) {
            getFinalResult(upBridge, downBridge, gameSuccess, count);
        }
        if (!gameSuccess) {
            String gameCommand = getGameCommand();
            while (gameCommand.equals("R")) {
                upBridge = new ArrayList<>();
                downBridge = new ArrayList<>();
                gameSuccess = gameProcess(bridge, upBridge, downBridge);
                count++;
                if (gameSuccess) {
                    break;
                }

                gameCommand = getGameCommand();
                if (gameCommand.equals("Q")) {
                    break;
                }
            }
            getFinalResult(upBridge, downBridge, gameSuccess, count);
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

    private boolean gameProcess(List<String> bridge, List<String> upBridge, List<String> downBridge) {

        getCurrentBridge(bridge, upBridge, downBridge);

        return isSuccess(upBridge, downBridge);
    }

    private void getCurrentBridge(List<String> bridge, List<String> upBridge, List<String> downBridge) {
        BridgeGame bridgeGame = new BridgeGame();

        for (String currentBridgePosition : bridge) {
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

            if (isFail(currentBridgeResult)) {
                break;
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

    private String getGameCommand() {
        while (true) {
            try {
                return inputView.readGameCommand();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private boolean isFail(String currentBridgeResult) {
        return currentBridgeResult.equals("X");
    }

    private boolean isSuccess(List<String> upBridge, List<String> downBridge) {
        String upBridgeFinalState = upBridge.get(upBridge.size() - 1);
        String downBridgeFinalState = downBridge.get(downBridge.size() - 1);

        return upBridgeFinalState.equals("O") || downBridgeFinalState.equals("O");
    }

    private void getFinalResult(List<String> upBridge, List<String> downBridge, boolean gameSuccess, int attemptCount) {
        outputView.printFinalMessage();
        outputView.printMap(upBridge, downBridge);
        outputView.printResult(gameSuccess, attemptCount);
    }
}
