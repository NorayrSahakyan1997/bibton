package am.bibton.view.activities.bibtonToBibtonActivity.bibtonToBibton;

import java.util.List;

import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.userInfoForTranferModel.UserInfoForTransferModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IBibtonToBibtonActivity extends IBaseView {
    void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList);
    void getUserInfo(UserInfoForTransferModel userInfoForTransferModel,boolean isUser);
    void getExchangeRate(ExchangeParentModel exchangeParentModel);


}
