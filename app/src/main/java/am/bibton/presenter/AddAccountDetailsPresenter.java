package am.bibton.presenter;

import android.widget.Toast;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.userInfoForTranferModel.UserInfoForTransferModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.addAccountDetailsActivity.IAddAccountDetails;
import io.reactivex.disposables.Disposable;

public class AddAccountDetailsPresenter extends BasePresenter<IAddAccountDetails> {
    private final AuthorizationService mService;

    @Inject
    AddAccountDetailsPresenter(AuthorizationService service) {
        mService = service;
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

    public void getUserInfo(String uniqueId) {
        Disposable disposable = mService.getUserInfoForTransfer(uniqueId).subscribe(this::getUserInfoResponse, this::errorView);
        addDisposable(disposable);
    }

    private void getUserInfoResponse(ResponseModel<UserInfoForTransferModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {

            mView.getUserInfo(responseModel.getData(),responseModel.isSuccess());

        } else {
            Toast.makeText(mContext,"User does not exist",Toast.LENGTH_SHORT).show();
            mView.getUserInfo(responseModel.getData(),responseModel.isSuccess());
            mView.showNetworkError();

        }

    }

    public void getExchange(int from_currency, int to_currency, int value) {
        Disposable disposable = mService.changeMoney(from_currency, to_currency, value).subscribe(this::getExchangeRateResponse, this::errorView);
        addDisposable(disposable);
    }

    private void getExchangeRateResponse(ResponseModel<ExchangeParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getExchangeRate(responseModel.getData());
        } else {
            mView.showNetworkError();
        }
    }
}
