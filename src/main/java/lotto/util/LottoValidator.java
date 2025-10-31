package lotto.util;

import static lotto.util.LottoConstants.*;
import java.util.HashSet;
import java.util.List;

public class LottoValidator {

    private LottoValidator() {} // 인스턴스화 방지

    public static void validateNumbers(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 " + LOTTO_SIZE + "개여야 합니다.");
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        boolean invalidRange = numbers.stream()
                .anyMatch(num -> num < MIN_NUMBER || num > MAX_NUMBER);
        if (invalidRange) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 " + MIN_NUMBER + "부터 " + MAX_NUMBER + " 사이의 숫자여야 합니다.");
        }
    }
}
