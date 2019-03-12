package am.bibton.presenter;


import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.phoneNumberCodeModel.CountryCode;
import am.bibton.model.singUpModel.SignUp;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.phoneNumberFragment.IPhoneNumberFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class PhoneNumberPresenter extends BasePresenter<IPhoneNumberFragment> {

    private final AuthorizationService mService;

    @Inject
    PhoneNumberPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCountryCode() {
        Disposable disposable = mService.getCountryCode().subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

    private void response(ResponseModel<CountryCode> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.showCountryCode(responseModel.getData().getPhone_code());
            Constants.COUNTRY_SHORT_NAME=responseModel.getData().getShort_name();
        } else
            mView.showServerError();
    }

    public void responseValidationPhoneNumber(String shortName, String phoneNumber) {
        Disposable disposable = mService.checkValidationPhoneNumber(shortName, phoneNumber).subscribe(this::checkValidation, this::errorView);
        addDisposable(disposable);
    }

    private void checkValidation(ResponseModel <SignUp> responseModel) {
          mView.checkPhoneNumberValidation(responseModel.isSuccess());
          mView.showUniqueID(responseModel.getData().getUnique_id());
          Constants.IS_REGISTERED=responseModel.getData().is_registred();
    }




}