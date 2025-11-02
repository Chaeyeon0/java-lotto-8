package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.dto.LottoResult;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoService lottoService = new LottoService();

    public void run() {
        try {
            int amount = InputView.inputPurchaseAmount();
            List<Lotto> purchased = lottoService.buyLottos(amount);
            OutputView.printPurchasedLottos(purchased);

            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            int bonusNumber = InputView.inputBonusNumber();
            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

            LottoResult lottoResult = lottoService.calculateResult(purchased, winningLotto, amount);
            OutputView.printStatistics(lottoResult.getRankCountMap());
            OutputView.printProfitRate(lottoResult.getProfitRate());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run(); // 예외 발생 시 입력 다시 받기
        }
    }
}
