package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.currencyModel.CurrencyParentModel;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.ratesActivity.addAlertActivity.IAddAlertActivity;
import io.reactivex.disposables.Disposable;

public class AddAlertListPresenter extends BasePresenter<IAddAlertActivity> {
    private final AuthorizationService mService;

    @Inject
    AddAlertListPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCurrencyList() {
        Disposable disposable = mService.getCurrencyList().subscribe(this::currencyResponse, this::errorView);
        addDisposable(disposable);
    }

    private void currencyResponse(ResponseModel<CurrencyParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getAlertList(responseModel.getData().getList());
            Constants.SYMBOL = responseModel.getData().getList().get(0).getSymbol();
        } else {
            Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();
        }
    }

    public void addAlert(int currencyId, int toCurrencyId, float amount) {
        Disposable disposable = mService.addAlert(currencyId, toCurrencyId, amount).subscribe(this::addAlertResponse, this::errorView);
        addDisposable(disposable);
    }

    private void addAlertResponse(ResponseModel<List> responseModel) {
        if (responseModel.isSuccess()) {
            Toast.makeText(mContext, "Convert Item Was Added Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Rates already exists", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();

        }
    }


}
