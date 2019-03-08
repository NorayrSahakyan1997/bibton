package am.bibton.presenter;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.transferMoneyModel.TransferMoneyModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.addAccountDetailsActivity.writeCodeActivityForMoneyTranfer.IWritePassCodeActivity;
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
            Toast.makeText(mContext, "Money was sent successfully", Toast.LENGTH_SHORT).show();
        } else
            mView.getMessage(responseModel.getMessage());

        mView.showServerError();
    }

}
