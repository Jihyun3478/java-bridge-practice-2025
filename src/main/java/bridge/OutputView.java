package bridge;

import static bridge.Constant.NEW_LINE;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    public void startMessage() {
        System.out.println("다리 건너기 게임을 시작합니다." + NEW_LINE);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> upBridge, List<String> downBridge) {
        String formattingUpBridge = String.join(" | ", upBridge);
        String formattingDownBridge = String.join(" | ", downBridge);

        System.out.println("[ " + formattingUpBridge + " ]");
        System.out.println("[ " + formattingDownBridge + " ]");
    }

    public void printFinalMessage() {
        System.out.println(NEW_LINE + "최종 게임 결과");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean gameSuccess, int attemptCount) {
        System.out.print(NEW_LINE + "게임 성공 여부: ");
        if (gameSuccess) {
            System.out.println("성공");
        }
        if (!gameSuccess) {
            System.out.println("실패");
        }

        System.out.println("총 시도한 횟수: " + attemptCount);
    }

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
