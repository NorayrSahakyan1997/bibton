package am.bibton.view.activities.homeActivity.homeFragments.statementFragment;

import java.util.List;

import am.bibton.model.getTransactionList.TransactionDateResponse;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IStatementFragment extends IBaseView {
    void getFilteredTransactionList(List<TransactionDateResponse> transactionResponses);

    void getTransactionList(List<TransactionDateResponse> transactionResponses);

    void getWalletCurrency(List<WalletCurrencyResponse> getWalletCurrencyResponse);


}
