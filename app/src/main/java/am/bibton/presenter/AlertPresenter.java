package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import am.bibton.model.ResponseModel;
import am.bibton.model.alertModel.AlertParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.view.activities.ratesActivity.ratesFragments.alertFragment.IAlertFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class AlertPresenter extends BasePresenter<IAlertFragment> {

    private final AuthorizationService mService;
    @Inject
    AlertPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getAlertList() {
        Disposable disposable = mService.getAlertList().subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

    private void response(ResponseModel<AlertParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getAlertList(responseModel.getData().getList());
        } else
            mView.showServerError();
    }

    public void deleteAlertItem(int alert_id) {
        Disposable disposable = mService.deleteAlertItem(alert_id).subscribe(this::deleteItemResponse, this::errorView);
        addDisposable(disposable);
    }

    private void deleteItemResponse(ResponseModel<List> deleteResponse) {
        if (deleteResponse.isSuccess()) {
            Toast.makeText(mContext, "Item was deleted successfully", Toast.LENGTH_SHORT).show();

        } else {
            mView.showNetworkError();
        }
    }

    public void switchAlert(int alert_id) {
        Disposable disposable = mService.switchAlert(alert_id).subscribe(this::switchAlertResponse, this::errorView);
        addDisposable(disposable);
    }

    private void switchAlertResponse(ResponseModel<List> switchAlert) {
        if (switchAlert.isSuccess()) {
            Toast.makeText(mContext, "Alert was switched", Toast.LENGTH_SHORT).show();
        } else {
            mView.showNetworkError();
        }
    }

}
