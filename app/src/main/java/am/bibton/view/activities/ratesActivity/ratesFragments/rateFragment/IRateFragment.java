package am.bibton.view.activities.ratesActivity.ratesFragments.rateFragment;

import java.util.List;

import am.bibton.model.rateModel.RateResponse;
import am.bibton.view.activities.IBaseView;

public interface IRateFragment extends IBaseView {
    void getRateList(List<RateResponse> rateResponsesList);
}
