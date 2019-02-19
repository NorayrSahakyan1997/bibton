package am.spaysapps.bibton.view.activities.homeActivity.homeFragments.exchangeFragment;

import java.util.List;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.exchangeModel.ExchangeParentModel;
import am.spaysapps.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.spaysapps.bibton.view.activities.IBaseView;

public interface IExchangeActivity extends IBaseView {

    void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList);
    void getExchangeRate(ExchangeParentModel exchangeParentModel);

}
