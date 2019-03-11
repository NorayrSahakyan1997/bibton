package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.getUniqueIdForFingerPrint.GetUniqueIdFingerPrintModel;
import am.bibton.model.transferMoneyModel.TransferMoneyModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.bibtnToBibtonActivity.sendMoneyActivityViaFingerprint.ISendMoneyActivityViaFingerprint;
import io.reactivex.disposables.Disposable;

public class SendMoneyViaFingerptintPresenter extends BasePresenter<ISendMoneyActivityViaFingerprint> {
    private final AuthorizationService mService;

    @Inject
    SendMoneyViaFingerptintPresenter(AuthorizationService service) {
        mService = service;
    }

    public void sendMoneyViaFringerprint(TransferMoneyModel transferMoneyModel) {
        Disposable disposable = mService.transferMoney(transferMoneyModel).subscribe(this::sendMoneyResponse, this::errorView);
        addDisposable(disposable);
    }

    private void sendMoneyResponse(ResponseModel<List> responseModel) {
        if (responseModel.isSuccess()) {
            mView.getMessage(responseModel.isSuccess());
        } else {
            mView.getMessage(responseModel.isSuccess());
            mView.showServerError();
        }
    }

    public void getUniqueIdForIngerPrint() {
        Disposable disposable = mService.getUniqueIdForFingerPrint().subscribe(this::getUniqueId, this::errorView);
        addDisposable(disposable);
    }

    private void getUniqueId(ResponseModel<GetUniqueIdFingerPrintModel> responseModel) {
        if (responseModel.getData() != null && responseModel.isSuccess()) {
            mView.getUniqueId(responseModel.getData().getUnique_id());

        } else {
            mView.showNetworkError();
        }


    }
}
