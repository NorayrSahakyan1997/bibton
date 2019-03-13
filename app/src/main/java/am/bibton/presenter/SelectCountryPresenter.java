package am.bibton.presenter;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.countryModel.CountryParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.counrtySearchActivity.ICountrySearchActivity;
import io.reactivex.disposables.Disposable;

public class SelectCountryPresenter extends BasePresenter<ICountrySearchActivity> {
    private final AuthorizationService mService;

    @Inject
    SelectCountryPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getCountryList() {


        Disposable disposable = mService.getCountryList().subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

    private void response(ResponseModel<CountryParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.showCountryList(responseModel.getData().getList());
        }
        else
            mView.showServerError();
        //  mView.hidePageLoading();
    }
}
