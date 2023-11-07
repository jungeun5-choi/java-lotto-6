package lotto.domain.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.repository.LottoRepository;
import lotto.domain.repository.WinningLottoRepository;
import lotto.util.Util;
import lotto.util.enumerator.LottoRank;

public class LottoResultService {
    private static final int MATCH_THREE = 3;
    private static final int MATCH_FOUR = 4;
    private static final int MATCH_FIVE = 5;
    private static final int MATCH_SIX = 6;

    public List<LottoRank> compare(List<Integer> winningNumbers) {
        List<LottoRank> ranks = new ArrayList<>();
        for (Lotto lotto : LottoRepository.lotties()) {
            List<Integer> lottoNumbers = lotto.getNumbers();
            int match = compareWithOneLotto(lottoNumbers, winningNumbers);
            ranks.add(checkLottoRank(match, lottoNumbers));
        }
        return ranks;
    }

    private int compareWithOneLotto(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int match = 0; // 숫자 일치 개수
        for (int number : lottoNumbers) {
            if (isMatch(number, winningNumbers)) {
                match++;
            }
        }
        return match;
    }

    private boolean isMatch(int number, List<Integer> winningNumbers) {
        for (Integer winning : winningNumbers) {
            if (Util.isEqual(number, winning)) {
                return true;
            }
        }
        return false;
    }

    private LottoRank checkLottoRank(int match, List<Integer> lottoNumbers) {
        if (Util.isEqual(match, MATCH_THREE)) {
            return LottoRank.FIFTH;
        }
        if (Util.isEqual(match, MATCH_FOUR)) {
            return LottoRank.FOURTH;
        }
        if (Util.isEqual(match, MATCH_FIVE)) {
            return compareWithBonusNumber(lottoNumbers);
        }
        if (Util.isEqual(match, MATCH_SIX)) {
            return LottoRank.FIRST;
        }
        return LottoRank.NONE;
    }

    private LottoRank compareWithBonusNumber(List<Integer> lottoNumbers) {
        BonusNumber bonusNumber = WinningLottoRepository.findBonusNumber();
        return bonusNumber.compareWithLottoNumber(lottoNumbers);
    }
}
