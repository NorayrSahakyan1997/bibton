package am.bibton.presenter;

import javax.inject.Inject;

import am.bibton.model.ResponseModel;
import am.bibton.model.phoneNumberCodeModel.CountryCode;
import am.bibton.model.rateModel.RateParentModel;
import am.bibton.model.rateModel.RateResponse;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.api.IAuthorizationService;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.ratesActivity.ratesFragments.rateFragment.IRateFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class RatePresenter extends BasePresenter<IRateFragment> {

    private final AuthorizationService mService;
    @Inject
    RatePresenter(AuthorizationService service) {
        mService = service;
    }

    public void getRatesList() {
        Disposable disposable = mService.getRateList().subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

    private void response(ResponseModel<RateParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getRateList(responseModel.getData().getRateList());
        } else
            mView.showServerError();
    }

}
