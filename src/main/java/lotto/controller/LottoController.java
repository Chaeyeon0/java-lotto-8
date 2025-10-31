package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        try {
            int purchaseAmount = InputView.inputPurchaseAmount();

            LottoMachine lottoMachine = new LottoMachine();
            List<Lotto> purchasedLottos = lottoMachine.purchaseLottos(purchaseAmount);
            OutputView.printPurchasedLottos(purchasedLottos);

            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            int bonusNumber = InputView.inputBonusNumber();

            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

            LottoResult lottoResult = new LottoResult(purchasedLottos, winningLotto, purchaseAmount);
            OutputView.printStatistics(lottoResult.getRankCountMap());
            OutputView.printProfitRate(lottoResult.getProfitRate());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run(); // 예외 발생 시 입력 다시 받기
        }
    }
}
