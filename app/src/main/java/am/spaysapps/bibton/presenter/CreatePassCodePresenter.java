package am.spaysapps.bibton.presenter;

import javax.inject.Inject;
import am.spaysapps.bibton.presenter.root.BasePresenter;
import am.spaysapps.bibton.shared.data.services.AuthorizationService;
import am.spaysapps.bibton.shared.di.scopes.AuthorizationScope;
import am.spaysapps.bibton.view.fragments.createPassCodeFragment.ICreatePassCode;

@AuthorizationScope
public class CreatePassCodePresenter extends BasePresenter<ICreatePassCode> {

    private final AuthorizationService mService;

    @Inject
    CreatePassCodePresenter(AuthorizationService service) {
        mService = service;
    }



}
