package am.bibton.view.activities.homeActivity.homeFragments.homeFragment;

import java.util.List;

import am.bibton.model.getTransactionList.TransactionDateResponse;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IHomeFragment extends IBaseView {

    void getTransactionList(List<TransactionDateResponse> data);


    void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList);


}
