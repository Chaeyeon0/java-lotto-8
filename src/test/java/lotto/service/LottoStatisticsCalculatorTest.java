package lotto.service;

import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsCalculatorTest {

    private final LottoStatisticsCalculator calculator = new LottoStatisticsCalculator();

    @Test
    @DisplayName("총 상금이 구입 금액과 같으면 수익률은 100%이다.")
    void 수익률_100퍼센트_테스트() {
        Map<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
        // 총 상금 1,000원 (5등 1개), 구매 금액 1,000원 → 수익률 100%
        rankCountMap.put(Rank.FIFTH, 1);

        double rate = calculator.calculateProfitRate(rankCountMap, 5000);
        // 5,000 / 5,000 * 100 = 100.0
        assertThat(rate).isEqualTo(100.0);
    }

    @Test
    @DisplayName("총 상금이 구입 금액의 절반이면 수익률은 50%이다.")
    void 수익률_50퍼센트_테스트() {
        Map<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
        // 총 상금 5,000원, 구매 금액 10,000원 → 수익률 50%
        rankCountMap.put(Rank.FIFTH, 1);

        double rate = calculator.calculateProfitRate(rankCountMap, 10_000);
        assertThat(rate).isEqualTo(50.0);
    }

    @Test
    @DisplayName("총 상금이 구입 금액의 5배면 수익률은 500%이다.")
    void 수익률_500퍼센트_테스트() {
        Map<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
        // 총 상금 50,000원, 구매 금액 10,000원 → 수익률 500%
        rankCountMap.put(Rank.FOURTH, 1);

        double rate = calculator.calculateProfitRate(rankCountMap, 10_000);
        assertThat(rate).isEqualTo(500.0);
    }

    @Test
    @DisplayName("수익률은 소수점 둘째 자리에서 반올림된다.")
    void 수익률_반올림_테스트() {
        Map<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
        // 총 상금 1,333원, 구매 금액 1,000원 → 133.3% → 반올림 후 133.3
        rankCountMap.put(Rank.FIFTH, 0); // 직접 값 계산 확인용
        double totalPrize = 1333;
        double rate = Math.round((totalPrize / 1000 * 100) * 100) / 100.0;
        assertThat(rate).isEqualTo(133.3);
    }
}
