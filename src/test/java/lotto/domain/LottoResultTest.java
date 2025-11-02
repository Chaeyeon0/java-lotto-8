package lotto.domain;
import lotto.dto.LottoResult;
import lotto.service.LottoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private final LottoService lottoService = new LottoService();

    @Test
    @DisplayName("각 로또의 등수를 올바르게 집계한다.")
    void 등수별_집계_테스트() {
        // given
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> purchased = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),   // 3등
                new Lotto(List.of(1, 2, 3, 4, 10, 11)), // 4등
                new Lotto(List.of(1, 2, 3, 20, 21, 22)) // 5등
        );
        LottoResult result = lottoService.calculateResult(purchased, winningLotto, 5000);

        assertThat(result.getRankCountMap().get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getRankCountMap().get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getRankCountMap().get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.getRankCountMap().get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.getRankCountMap().get(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 소수점 둘째 자리에서 반올림한다.")
    void 수익률_계산() {
        // given
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> purchased = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)) // 1등
        );
        LottoResult result = lottoService.calculateResult(purchased, winningLotto, 1000);

        assertThat(result.getProfitRate()).isEqualTo(200000000.00);
    }
}
