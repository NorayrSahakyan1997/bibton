package am.spaysapps.bibton.presenter;


import javax.inject.Inject;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.countryModel.CountryParentModel;
import am.spaysapps.bibton.presenter.root.BasePresenter;
import am.spaysapps.bibton.shared.data.services.AuthorizationService;
import am.spaysapps.bibton.shared.di.scopes.AuthorizationScope;
import am.spaysapps.bibton.view.fragments.countrySearchFragment.ICountrySearchFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class CountrySearchPresenter extends BasePresenter<ICountrySearchFragment> {

    private final AuthorizationService mService;

    @Inject
    CountrySearchPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCountryList() {


        Disposable disposable = mService.getCountryList().subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

//    private void getUserInfoSuccess(ResponseModel<SignUp> response) {
//
//        if (response.isSuccess()) {
//            Toast.makeText(mContext, "true", Toast.LENGTH_SHORT).show();
//
//        } else
//            Toast.makeText(mContext, "false", Toast.LENGTH_SHORT).show();
//    }

    private void response(ResponseModel<CountryParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.showCountryList(responseModel.getData().getList());
        }
        else
            mView.showServerError();
        //  mView.hidePageLoading();
    }
}
