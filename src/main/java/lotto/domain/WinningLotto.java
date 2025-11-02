package lotto.domain;

import static lotto.util.LottoConstants.*;
import java.util.List;
import java.util.Set;
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

    // 주어진 로또와 당첨 번호를 비교하여 일치 개수와 보너스 일치 여부를 반환
    public MatchResult match(Lotto lotto) {
        Set<Integer> winningNumbers = Set.copyOf(winningLotto.getNumbers());
        Set<Integer> userNumbers = Set.copyOf(lotto.getNumbers());

        long matchCount = userNumbers.stream()
                .filter(winningNumbers::contains)
                .count();

        boolean bonusMatch = userNumbers.contains(bonusNumber);

        return new MatchResult((int) matchCount, bonusMatch);
    }
}
