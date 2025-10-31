package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoValidatorTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외 발생")
    void 번호_개수_검증() {
        assertThatThrownBy(() -> LottoValidator.validateNumbers(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야 합니다");
    }

    @Test
    @DisplayName("중복된 번호가 있으면 예외 발생")
    void 중복_번호_검증() {
        assertThatThrownBy(() -> LottoValidator.validateNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다");
    }

    @Test
    @DisplayName("1 미만 또는 45 초과 번호가 있으면 예외 발생")
    void 범위_검증() {
        assertThatThrownBy(() -> LottoValidator.validateNumbers(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45 사이의 숫자여야 합니다");
    }

    @Test
    @DisplayName("올바른 번호면 예외 없이 통과")
    void 정상_번호_검증() {
        LottoValidator.validateNumbers(List.of(1, 2, 3, 4, 5, 6)); // 예외 없어야 함
    }
}
