package am.bibton.presenter;

import android.widget.Toast;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.ratesActivity.addCurrencyActivity.IAddCurrencyActivity;
import io.reactivex.disposables.Disposable;

public class AddCurrencyPresenter extends BasePresenter<IAddCurrencyActivity> {
    private final AuthorizationService mService;

    @Inject
    AddCurrencyPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCurrencyList() {
        Disposable disposable = mService.getCurrency().subscribe(this::currencyResponse, this::errorView);
        addDisposable(disposable);
    }

    private void currencyResponse(ResponseModel<WalletCurrencyParentResponse> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getCurrencyList(responseModel.getData().getList());
            Constants.SYMBOL = responseModel.getData().getList().get(0).getSymbol();
        } else {
            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();
        }
    }
}
