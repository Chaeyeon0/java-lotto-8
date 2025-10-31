package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.util.LottoValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validateNumbers(numbers);
        this.numbers = List.copyOf(numbers)
                .stream()
                .sorted()
                .toList();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
