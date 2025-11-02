package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.MatchResult;

public class LottoStatisticsCalculator {

    public Map<Rank, Integer> calculateRankCounts(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        Map<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCountMap.put(rank, 0);
        }

        for (Lotto lotto : purchasedLottos) {
            MatchResult result = winningLotto.match(lotto);
            Rank rank = Rank.findByMatchCountAndBonus(result.getMatchCount(), result.isBonusMatch());
            rankCountMap.put(rank, rankCountMap.get(rank) + 1);
        }
        return rankCountMap;
    }

    public double calculateProfitRate(Map<Rank, Integer> rankCountMap, int purchaseAmount) {
        int totalPrize = rankCountMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
        double rate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(rate * 100) / 100.0;
    }
}
