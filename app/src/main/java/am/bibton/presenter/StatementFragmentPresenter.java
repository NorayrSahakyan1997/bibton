package am.bibton.presenter;

import android.widget.Toast;
import javax.inject.Inject;
import am.bibton.model.ResponseModel;
import am.bibton.model.getTransactionList.TransactionFilterRequestModel;
import am.bibton.model.getTransactionList.TransactionParentModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.homeActivity.homeFragments.statementFragment.IStatementFragment;
import io.reactivex.disposables.Disposable;

public class StatementFragmentPresenter extends BasePresenter<IStatementFragment> {

    private final AuthorizationService mService;

    @Inject
    StatementFragmentPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getTransactionList(TransactionFilterRequestModel transactionFilterRequestModel) {
        Disposable disposable = mService.getTransactionFiltered(transactionFilterRequestModel)
                .subscribe(this::transactionResponseFiltered, this::errorView);
        addDisposable(disposable);
    }
    private void transactionResponseFiltered(ResponseModel<TransactionParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getTransactionList(responseModel.getData().getData());

        } else

            mView.showServerError();
    }



    public void getTransactionList() {
        Disposable disposable = mService.getTransactionsUnfiltered()
                .subscribe(this::transactionResponseList, this::errorView);
        addDisposable(disposable);
    }
    private void transactionResponseList(ResponseModel<TransactionParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getTransactionList(responseModel.getData().getData());
        } else
            mView.showServerError();
    }

    public void getCurrencyList() {
        Disposable disposable = mService.getCurrency().subscribe(this::currencyResponse, this::errorView);
        addDisposable(disposable);
    }
    private void currencyResponse(ResponseModel<WalletCurrencyParentResponse> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getWalletCurrency(responseModel.getData().getList());
            Constants.SYMBOL = responseModel.getData().getList().get(0).getSymbol();
        } else {
            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();
        }
    }


    public void getTransactionListWithCurrency(int from_currency) {
        Disposable disposable = mService.getTransactionWithCurrency(from_currency)
                .subscribe(this::transactionResponseWithCurrency, this::errorView);
        addDisposable(disposable);
    }

    private void transactionResponseWithCurrency(ResponseModel<TransactionParentModel> responseModel) {

        if (responseModel.isSuccess() && responseModel.getData() != null) {

            mView.getTransactionList(responseModel.getData().getData());


        } else
            mView.showServerError();
    }

}
