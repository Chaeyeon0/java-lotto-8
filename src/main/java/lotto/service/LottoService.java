package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.domain.*;
import lotto.dto.LottoStatisticsResult;

public class LottoService {

    private final LottoMachine lottoMachine = new LottoMachine();
    private final LottoStatisticsCalculator lottoCalculator = new LottoStatisticsCalculator();

    // 로또 구매
    public List<Lotto> buyLottos(int amount) {
        return lottoMachine.purchaseLottos(amount);
    }

    // 당첨 결과 계산
    public LottoStatisticsResult calculateResult(List<Lotto> purchased, WinningLotto winningLotto, int purchaseAmount) {
        Map<Rank, Integer> rankCountMap = lottoCalculator.calculateRankCounts(purchased, winningLotto);
        double profitRate = lottoCalculator.calculateProfitRate(rankCountMap, purchaseAmount);
        return new LottoStatisticsResult(rankCountMap, profitRate);
    }
}
