package am.bibton.presenter;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.phoneNumberCodeModel.CountryCode;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.IChangePhoneNumberActivity;
import io.reactivex.disposables.Disposable;

public class ChangePhoneNumberPresenter extends BasePresenter<IChangePhoneNumberActivity> {
    private final AuthorizationService mService;

    @Inject
    ChangePhoneNumberPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCountryCode() {
        Disposable disposable = mService.getCountryCode().subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

    private void response(ResponseModel<CountryCode> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.showCountryCode(responseModel.getData().getPhone_code());
        } else
            mView.showServerError();
    }


}
