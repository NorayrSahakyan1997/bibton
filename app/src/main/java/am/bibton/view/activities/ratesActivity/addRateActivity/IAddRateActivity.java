package am.bibton.view.activities.ratesActivity.addRateActivity;

import java.util.List;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IAddRateActivity extends IBaseView {
    void getCurrencyList(List<CurrencyResponse> getWalletCurrencyList);
    void addCurrencyPair(boolean isAdded);
}
