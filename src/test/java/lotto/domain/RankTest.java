package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("6개 일치 시 1등을 반환한다.")
    void 일치_6개면_1등() {
        Rank rank = Rank.findByMatchCountAndBonus(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 + 보너스 번호 일치 시 2등을 반환한다.")
    void 일치_5개_보너스_있으면_2등() {
        Rank rank = Rank.findByMatchCountAndBonus(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개 일치, 보너스 불일치 시 3등을 반환한다.")
    void 일치_5개_보너스_없으면_3등() {
        Rank rank = Rank.findByMatchCountAndBonus(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("3개 일치 시 5등을 반환한다.")
    void 일치_3개면_5등() {
        Rank rank = Rank.findByMatchCountAndBonus(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 시 MISS(미당첨)을 반환한다.")
    void 일치_2개_이하면_미당첨() {
        Rank rank = Rank.findByMatchCountAndBonus(2, false);
        assertThat(rank).isEqualTo(Rank.MISS);
    }

    @Test
    @DisplayName("각 등수별 상금이 정확하다.")
    void 등수별_상금_확인() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000);
    }
}
