package am.bibton.view.activities.ratesActivity.addAlertActivity;

import java.util.List;

import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IAddAlertActivity extends IBaseView {
    void getAlertList(List<CurrencyResponse> getAlertResponse) ;

}
