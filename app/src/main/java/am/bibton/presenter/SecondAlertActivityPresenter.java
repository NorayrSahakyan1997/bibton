package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.ratesActivity.addSecondAlertPairActivity.ISecondAlertPairActivity;
import io.reactivex.disposables.Disposable;

public class SecondAlertActivityPresenter extends BasePresenter<ISecondAlertPairActivity> {
    private final AuthorizationService mService;

    @Inject
    SecondAlertActivityPresenter(AuthorizationService service) {
        mService = service;
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

    public void addAlert(int fromAmount, int toAmount, float amount) {
        Disposable disposable = mService.addAlert(fromAmount, toAmount, amount).subscribe(this::addAlertResponse, this::errorView);
        addDisposable(disposable);
    }

    private void addAlertResponse(ResponseModel<List> responseModel) {
        if (responseModel.isSuccess()) {
            Toast.makeText(mContext, "Alert Item Was Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
