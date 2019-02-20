package am.bibton.presenter;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.exchangeActivity.IExchangeActivity;
import io.reactivex.disposables.Disposable;

public class ExchangeActivityPresenter extends BasePresenter<IExchangeActivity> {
    private final AuthorizationService mService;

    @Inject
    ExchangeActivityPresenter(AuthorizationService service) {
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

            mView.showNetworkError();
        }
    }

    public void getExchange(int from_currency,int to_currency,int value){
        Disposable disposable=mService.changeMoney(from_currency,to_currency,value).subscribe(this::getExchangeRateResponse,this::errorView);
        addDisposable(disposable);
    }
    private void getExchangeRateResponse(ResponseModel<ExchangeParentModel> responseModel){
        if(responseModel.isSuccess() && responseModel.getData()!=null){
            mView.getExchangeRate(responseModel.getData());
        }
        else{
            mView.showNetworkError();
        }
    }
}
