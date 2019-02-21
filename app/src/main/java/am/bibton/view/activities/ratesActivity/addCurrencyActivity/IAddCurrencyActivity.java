package am.bibton.view.activities.ratesActivity.addCurrencyActivity;

import java.util.List;

import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IAddCurrencyActivity extends IBaseView {
    void getCurrencyList(List<WalletCurrencyResponse> getWalletCurrencyList);
}
