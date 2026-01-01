package bridge;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BridgeMakerTest {
    @Nested
    @DisplayName("다리 생성 예외 테스트")
    class 다리_생성_예외_테스트 {
        @Test
        @DisplayName("다리의 길이가 3 이상 20 이하가 아닐 경우, 예외가 발생한다.")
        void 다리의_길이가_3이상_20이하가_아닐_경우_예외가_발생한다() {
            BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
            assertThatThrownBy(() -> bridgeMaker.makeBridge(2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 입력입니다. 다시 입력해 주세요.");
        }
    }
}
