package am.spaysapps.bibton.presenter;

import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.countryModel.CountryParentModel;
import am.spaysapps.bibton.presenter.root.BasePresenter;
import am.spaysapps.bibton.shared.data.services.AuthorizationService;
import am.spaysapps.bibton.shared.di.scopes.AuthorizationScope;
import am.spaysapps.bibton.view.fragments.phoneNumberFragment.IPhoneNumberFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class PhoneNumberPresenter extends BasePresenter<IPhoneNumberFragment> {

    private final AuthorizationService mService;

    @Inject
    PhoneNumberPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getBalanceList() {
        Disposable disposable = mService.signIn().subscribe(this::response, throwable -> {
            errorView(throwable);

           // mView.hidePageLoading();
        });
        addDisposable(disposable);
    }

    private void response(ResponseModel<CountryParentModel> responseModel) {
        if (responseModel.getSuccess() && responseModel.getData() != null) {
            mView.showBalanceList(responseModel.getData().getList());

        }
        else
            mView.showServerError();
      //  mView.hidePageLoading();
    }
}