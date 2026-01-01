package bridge;

import java.util.Objects;

public class InputValidator {
    public static void validateNotBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateMovingSpace(String input) {
        if (!(input.equals("U") || input.equals("D"))) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateGameCommand(String input) {
        if (!(input.equals("R") || input.equals("Q"))) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다. 다시 입력해 주세요.");
        }
    }
}
