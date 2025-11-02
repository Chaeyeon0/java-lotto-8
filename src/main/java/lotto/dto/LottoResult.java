package lotto.dto;

import java.util.Map;
import lotto.domain.Rank;

public class LottoResult {
    private final Map<Rank, Integer> rankCountMap;
    private final double profitRate;

    public LottoResult(Map<Rank, Integer> rankCountMap, double profitRate) {
        this.rankCountMap = Map.copyOf(rankCountMap);
        this.profitRate = profitRate;
    }

    public Map<Rank, Integer> getRankCountMap() {
        return rankCountMap;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
