package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.currencyModel.CurrencyParentModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.ratesActivity.addCurrencyActivity.IAddCurrencyActivity;
import io.reactivex.disposables.Disposable;

public class CurrencyListPresenter extends BasePresenter<IAddCurrencyActivity> {
    private final AuthorizationService mService;

    @Inject
    CurrencyListPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCurrencyList() {
        Disposable disposable = mService.getCurrencyList().subscribe(this::currencyResponse, this::errorView);
        addDisposable(disposable);
    }

    private void currencyResponse(ResponseModel<CurrencyParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getCurrencyList(responseModel.getData().getCurrencyResponseList());
            Constants.SYMBOL = responseModel.getData().getCurrencyResponseList().get(0).getSymbol();
        } else {
            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();
        }
    }

    public void addCurrencyPair(int firstCurrencyId, int secondCurrencyId) {
        Disposable disposable = mService.addCurrencyPair(firstCurrencyId, secondCurrencyId).subscribe(this::addCurrencyPairResponse, this::errorView);
        addDisposable(disposable);
    }

    private void addCurrencyPairResponse(ResponseModel<List> responseModel) {
        if (responseModel.isSuccess()) {
            mView.addCurrencyPair(true);
        } else {
            mView.addCurrencyPair(false);
            Toast.makeText(mContext, "Rates already exists", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();

        }
    }
}
