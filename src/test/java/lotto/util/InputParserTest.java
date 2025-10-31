package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputParserTest {

    @Test
    @DisplayName("쉼표로 구분된 문자열을 정수 리스트로 변환한다.")
    void 문자열을_리스트로_변환() {
        List<Integer> result = InputParser.parseNumbers("1, 2, 3, 4, 5, 6");
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("입력에 숫자가 아닌 값이 포함되면 예외 발생")
    void 숫자가_아닌_입력_예외() {
        assertThatThrownBy(() -> InputParser.parseNumbers("1, a, 3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자만 입력할 수 있습니다");
    }

    @Test
    @DisplayName("빈 문자열 입력 시 예외 발생")
    void 빈_문자열_입력_예외() {
        assertThatThrownBy(() -> InputParser.parseNumbers(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력이 비어 있습니다");
    }

    @Test
    @DisplayName("보너스 번호 단일 숫자 변환 테스트")
    void 보너스_번호_단일_숫자_변환() {
        int result = InputParser.parseSingleNumber("7");
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호 입력이 숫자가 아니면 예외 발생")
    void 보너스_번호_입력_예외() {
        assertThatThrownBy(() -> InputParser.parseSingleNumber("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자만 입력할 수 있습니다");
    }
}
