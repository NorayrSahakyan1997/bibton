package am.bibton.view.activities.exchangeActivity;

import java.util.List;

import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IExchangeActivity extends IBaseView {

    void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList);
    void getExchangeRate(ExchangeParentModel exchangeParentModel);

}
