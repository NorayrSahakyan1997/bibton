package am.bibton.presenter;

import android.widget.Toast;
import javax.inject.Inject;
import am.bibton.model.ResponseModel;
import am.bibton.model.checkPassCode.CheckPassCodeModel;
import am.bibton.model.singUpModel.SignUp;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.inputCodeFragment.IInputCodeFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class InputCodePresenter extends BasePresenter<IInputCodeFragment> {

    private final AuthorizationService mService;

    @Inject
    InputCodePresenter(AuthorizationService service) {
        mService = service;
    }

    public void getUserInfo(String shortName, String phoneNumber) {
        Disposable disposable = mService.getUserInfo(shortName, phoneNumber)
                .subscribe(this::getUserInfoSuccess, this::errorView);
        addDisposable(disposable);
    }

    private void getUserInfoSuccess(ResponseModel<SignUp> response) {

        if (response.isSuccess()) {
            mView.showPassCode(response.getData().getUnique_id());
            Toast.makeText(mContext, response.getData().getUnique_id(), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(mContext, "false", Toast.LENGTH_SHORT).show();
    }

    public void checkUserPassCodeResponse(String unique_id, String code) {
        Disposable disposable = mService.checkPassCode(unique_id, code)
                .subscribe(this::checkUserPassCode, this::errorView);
        addDisposable(disposable);
    }

    private void checkUserPassCode(ResponseModel<CheckPassCodeModel> responseModel) {
        mView.isValidPassCode(responseModel.isSuccess());
    }
}
