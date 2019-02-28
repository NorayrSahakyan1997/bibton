package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.currencyModel.CurrencyParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.ratesActivity.addRateActivity.IAddRateActivity;
import io.reactivex.disposables.Disposable;

public class AddRateListPresenter extends BasePresenter<IAddRateActivity> {
    private final AuthorizationService mService;

    @Inject
    AddRateListPresenter(AuthorizationService service) {
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
            Toast.makeText(mContext,"Pair was added successfully",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext,"Pair was already exists",Toast.LENGTH_SHORT).show();
            mView.addCurrencyPair(false);
            mView.showNetworkError();

        }
    }

//    public void addConvertItem(int currency_id) {
//        Disposable disposable = mService.addConvertItem(currency_id).subscribe(this::addConvertItemResponse, this::errorView);
//        addDisposable(disposable);
//    }
//
//    private void addConvertItemResponse(ResponseModel<List> responseModel) {
//        if (responseModel.isSuccess()) {
//            Toast.makeText(mContext, "Convert Item Was Added Successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            mView.addCurrencyPair(false);
//            Toast.makeText(mContext, "Rates already exists", Toast.LENGTH_SHORT).show();
//            mView.showNetworkError();
//
//        }
//    }
}
