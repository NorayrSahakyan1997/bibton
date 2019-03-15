package am.bibton.presenter;

import android.widget.Toast;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.getTransactionList.TransactionParentModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.homeActivity.homeFragments.homeFragment.IHomeFragment;
import io.reactivex.disposables.Disposable;

public class HomeFragmentPresenter extends BasePresenter<IHomeFragment> {


    private final AuthorizationService mService;

    @Inject
    HomeFragmentPresenter(AuthorizationService service) {
        mService = service;
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


    public void getTransactionList() {
        Disposable disposable = mService.getTransaction()
                .subscribe(this::transactionResponse, this::errorView);
        addDisposable(disposable);
    }


    private void transactionResponse(ResponseModel<TransactionParentModel> responseModel) {

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
            mView.getCurrencyWallet(responseModel.getData().getList());
            Constants.SYMBOL = responseModel.getData().getList().get(0).getSymbol();

        } else {
            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();
        }
    }
}
