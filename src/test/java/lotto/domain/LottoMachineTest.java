package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoMachineTest {

    @DisplayName("구입 금액이 1,000원 단위가 아니면 예외 발생")
    @Test
    void 구입금액이_단위에_맞지_않으면_예외() {
        LottoMachine lottoMachine = new LottoMachine();
        assertThatThrownBy(() -> lottoMachine.purchaseLottos(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1,000원 단위");
    }

    @DisplayName("구입 금액이 1,000원 미만이면 예외 발생")
    @Test
    void 구입금액이_너무_작으면_예외() {
        LottoMachine lottoMachine = new LottoMachine();
        assertThatThrownBy(() -> lottoMachine.purchaseLottos(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1,000원 이상");
    }

    @DisplayName("금액에 맞는 개수만큼 로또를 생성한다.")
    @Test
    void 금액에_맞게_로또_생성() {
        LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> lottos = lottoMachine.purchaseLottos(8000);
        assertThat(lottos).hasSize(8);
    }
}
