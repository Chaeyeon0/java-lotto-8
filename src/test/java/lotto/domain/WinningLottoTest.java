package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void 보너스_번호_중복_예외() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("보너스 번호가 1보다 작으면 예외가 발생한다.")
    void 보너스_번호_범위_아래_예외() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }

    @Test
    @DisplayName("보너스 번호가 45보다 크면 예외가 발생한다.")
    void 보너스_번호_범위_위_예외() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }

    @Test
    @DisplayName("올바른 당첨 번호와 보너스 번호로 WinningLotto 객체가 정상 생성된다.")
    void 당첨_번호_정상_생성() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        assertThat(winningLotto).isNotNull();
        assertThat(winningLotto.match(new Lotto(List.of(1,2,3,4,5,6))).getMatchCount()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또와 당첨 번호를 비교하여 일치 개수를 반환한다.")
    void 일치_개수_확인() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

        MatchResult result = winningLotto.match(userLotto);

        assertThat(result.getMatchCount()).isEqualTo(3);
        assertThat(result.isBonusMatch()).isFalse();
    }

    @Test
    @DisplayName("보너스 번호가 일치하면 bonusMatch가 true를 반환한다.")
    void 보너스_번호_일치() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        MatchResult result = winningLotto.match(userLotto);

        assertThat(result.getMatchCount()).isEqualTo(5);
        assertThat(result.isBonusMatch()).isTrue();
    }
}
