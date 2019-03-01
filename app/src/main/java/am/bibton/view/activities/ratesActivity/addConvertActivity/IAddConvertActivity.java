package am.bibton.view.activities.ratesActivity.addConvertActivity;
import java.util.List;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.view.activities.IBaseView;

public interface IAddConvertActivity extends IBaseView {
    void getCurrencyList(List<CurrencyResponse> getCurrencyList);


}
