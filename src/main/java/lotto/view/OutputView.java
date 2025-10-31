package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    public static void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printStatistics(Map<Rank, Integer> rankCountMap) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        Rank[] ranks = {Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST};

        for (Rank rank : ranks) {
            String bonusInfo = rank == Rank.SECOND ? ", 보너스 볼 일치" : "";
            System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                    rank.getMatchCount(),
                    bonusInfo,
                    rank.getPrize(),
                    rankCountMap.get(rank));
        }
    }

    public static void printProfitRate(double rate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }
}
