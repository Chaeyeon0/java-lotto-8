package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
    private final int purchaseAmount;

    public LottoResult(List<Lotto> purchasedLottos, WinningLotto winningLotto, int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        initializeRankCounts();

        for (Lotto lotto : purchasedLottos) {
            WinningLotto.MatchResult result = winningLotto.match(lotto);
            Rank rank = Rank.findByMatchCountAndBonus(result.getMatchCount(), result.isBonusMatch());
            rankCountMap.put(rank, rankCountMap.get(rank) + 1);
        }
    }

    // 초기화
    private void initializeRankCounts() {
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }
    }

    public Map<Rank, Integer> getRankCountMap() {
        return Map.copyOf(rankCountMap);
    }

    // 총 상금 계산
    public int getTotalPrize() {
        return rankCountMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    // 수익률 계산
    public double getProfitRate() {
        double rate = (double) getTotalPrize() / purchaseAmount * 100;
        return Math.round(rate * 100) / 100.0;
    }
}
