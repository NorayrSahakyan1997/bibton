package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.currencyModel.CurrencyParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.ratesActivity.addConvertActivity.IAddConvertActivity;
import io.reactivex.disposables.Disposable;

public class AddConvertListPresenter extends BasePresenter<IAddConvertActivity> {
    private final AuthorizationService mService;

    @Inject
    AddConvertListPresenter(AuthorizationService service) {
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
    public void addConvertItem(int currency_id) {
        Disposable disposable = mService.addConvertItem(currency_id).subscribe(this::addConvertItemResponse, this::errorView);
        addDisposable(disposable);
    }

    private void addConvertItemResponse(ResponseModel<List> responseModel) {
        if (responseModel.isSuccess()) {
            // mView.addConvertItem(true);
            Toast.makeText(mContext, "Convert Item Was Added Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Rates already exists", Toast.LENGTH_SHORT).show();
            mView.showNetworkError();

        }
    }
}
