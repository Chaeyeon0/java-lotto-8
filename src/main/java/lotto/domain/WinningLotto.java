package lotto.domain;

import static lotto.util.LottoConstants.*;
import java.util.List;
import lotto.util.LottoValidator;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        LottoValidator.validateNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningLotto = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 "
                    + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이의 숫자여야 합니다.");
        }
    }

    public static class MatchResult {
        private final int matchCount;
        private final boolean bonusMatch;

        public MatchResult(int matchCount, boolean bonusMatch) {
            this.matchCount = matchCount;
            this.bonusMatch = bonusMatch;
        }

        public int getMatchCount() {
            return matchCount;
        }

        public boolean isBonusMatch() {
            return bonusMatch;
        }
    }
}
