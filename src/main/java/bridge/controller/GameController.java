package bridge.controller;

import bridge.BridgeRandomNumberGenerator;
import bridge.model.domain.Bridge;
import bridge.model.domain.BridgeMaker;
import bridge.model.domain.BridgeStatus;
import bridge.model.domain.dto.GameResult;
import bridge.model.service.GameService;
import bridge.util.InputParser;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

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
        Bridge bridge = createBridge(bridgeSize);
        GameService gameService = new GameService(bridge);

        GameResult result = playGame(gameService);
        printFinalResult(result);
    }

    private Bridge createBridge(int bridgeSize) {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        List<String> positions = bridgeMaker.makeBridge(bridgeSize);
        return new Bridge(positions);
    }

    private GameResult playGame(GameService gameService) {
        int attemptCount = 1;
        boolean success = playOneRound(gameService);

        while (!success) {
            String command = getGameCommand();

            if (command.equals("Q")) {
                return GameResult.from(gameService.getCurrentStatus(), false, attemptCount);
            }

            gameService.retryGame();
            attemptCount++;
            success = playOneRound(gameService);
        }

        return GameResult.from(gameService.getCurrentStatus(), true, attemptCount);
    }

    private boolean playOneRound(GameService gameService) {
        for (int i = 0; i < gameService.getBridgeSize(); i++) {
            String playerMove = getMovingSpace();
            String result = gameService.processMove(playerMove, i);

            BridgeStatus status = gameService.getCurrentStatus();
            outputView.printMap(status.getUpBridge(), status.getDownBridge());

            if (result.equals("X")) {
                return false;
            }
        }
        return true;
    }

    private void printFinalResult(GameResult result) {
        outputView.printFinalMessage();
        BridgeStatus status = result.getBridgeStatus();
        outputView.printMap(status.getUpBridge(), status.getDownBridge());
        outputView.printResult(result.isSuccess(), result.getAttemptCount());
    }

    private int getBridgeSize() {
        return retryUntilSuccess(() -> {
            String input = inputView.readBridgeSize();
            return InputParser.parseNumber(input);
        });
    }

    private String getMovingSpace() {
        return retryUntilSuccess(inputView::readMoving);
    }

    private String getGameCommand() {
        return retryUntilSuccess(inputView::readGameCommand);
    }

    private <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
