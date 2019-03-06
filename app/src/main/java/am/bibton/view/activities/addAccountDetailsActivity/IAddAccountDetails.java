package am.bibton.view.activities.addAccountDetailsActivity;

import java.util.List;

import am.bibton.model.userInfoForTranferModel.UserInfoForTransferModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IAddAccountDetails extends IBaseView {
    void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList);
    void getUserInfo(UserInfoForTransferModel userInfoForTransferModel,boolean isUser);

}
