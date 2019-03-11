package am.bibton.presenter;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.transferMoneyModel.TransferMoneyModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.bibtnToBibtonActivity.writeCodeActivityForMoneyTranfer.IWritePassCodeActivity;
import io.reactivex.disposables.Disposable;

public class SendMoneyActivityPresenter extends BasePresenter<IWritePassCodeActivity> {
    private final AuthorizationService mService;

    @Inject
    SendMoneyActivityPresenter(AuthorizationService service) {
        mService = service;
    }

    public void sendMoney(TransferMoneyModel transferMoneyModel) {
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




}
