package am.bibton.presenter;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.createAccountModel.CreateAccountModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.createPassCodeFragment.ICreatePassCode;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class CreatePassCodePresenter extends BasePresenter<ICreatePassCode> {

    private final AuthorizationService mService;

    @Inject
    CreatePassCodePresenter(AuthorizationService service) {
        mService = service;
    }

    public void createAccountResponse(String unique_id, String code, String passcode) {
        Disposable disposable = mService.createAccount(unique_id, code, passcode)
                .subscribe(this::createUserAccount, this::errorView);
        addDisposable(disposable);
    }

    private void createUserAccount(ResponseModel<CreateAccountModel> createAccount) {
        mView.getUserToken(createAccount.getData().getToken());

    }
}
