package am.bibton.view.activities.ratesActivity.alertFragment;

import java.util.List;

import am.bibton.model.alertModel.AlertResponse;
import am.bibton.view.activities.IBaseView;

public interface IAlertFragment extends IBaseView {
    void getAlertList(List<AlertResponse> getAlertList);
}
