package am.bibton.view.activities.ratesActivity.addCurrencyActivity;

import java.util.List;

import am.bibton.model.ResponseModel;
import am.bibton.model.currencyModel.CurrencyParentModel;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IAddCurrencyActivity extends IBaseView {
    void getCurrencyList(List<CurrencyResponse> getWalletCurrencyList);
    void addCurrencyPair(boolean isAdded);
}
