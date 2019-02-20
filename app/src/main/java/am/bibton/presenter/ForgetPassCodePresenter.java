package am.bibton.presenter;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.forgetPassCodeModel.ForgetPassCodeModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.forgetPassCodeFragment.IForgetPassCodeFragment;
import io.reactivex.disposables.Disposable;

public class ForgetPassCodePresenter extends BasePresenter<IForgetPassCodeFragment> {
    private final AuthorizationService mService;


    @Inject
    ForgetPassCodePresenter(AuthorizationService service) {
        mService = service;
    }

    public void forgetPassCodeResponse(String unique_id,String passCode){

        Disposable disposable = mService.forgetPass(unique_id, passCode)
                .subscribe(this::forgetPassCode, this::errorView);
        addDisposable(disposable);
    }

    private void forgetPassCode(ResponseModel<ForgetPassCodeModel> forgetPassCodeModel) {
        mView.getToken(forgetPassCodeModel.getData().getToken());

    }

}
