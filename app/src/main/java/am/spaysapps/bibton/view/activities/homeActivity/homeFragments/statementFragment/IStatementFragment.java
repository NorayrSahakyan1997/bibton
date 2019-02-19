package am.spaysapps.bibton.view.activities.homeActivity.homeFragments.statementFragment;

import java.util.List;

import am.spaysapps.bibton.model.getTransactionList.TransactionDateResponse;
import am.spaysapps.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.spaysapps.bibton.view.activities.IBaseView;

public interface IStatementFragment extends IBaseView {
    void getFilteredTransactionList(List<TransactionDateResponse> transactionResponses);

    void getTransactionList(List<TransactionDateResponse> transactionResponses);

    void getWalletCurrency(List<WalletCurrencyResponse> getWalletCurrencyResponse);


}
