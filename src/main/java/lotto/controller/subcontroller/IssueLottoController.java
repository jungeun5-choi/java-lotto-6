package lotto.controller.subcontroller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.repository.LottoRepository;
import lotto.view.InputView;
import lotto.view.OutputView;

public class IssueLottoController implements Controller {
    private static final int LOTTO_UNIT = 1000;

    private final InputView inputView;
    private final OutputView outputView;

    public IssueLottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public void process() {
        int purchase = inputView.inputPurchaseLotto();
        int amount = getLottoAmount(purchase);
        for(int i = 0; i < amount; i++) {
            Lotto lotto = new LottoMachine().issueLotto();
            LottoRepository.add(lotto);
        }
        outputView.outputLottoNumbers(LottoRepository.lotties());
    }

    private int getLottoAmount(int purchase) {
        return purchase / LOTTO_UNIT;
    }
}
