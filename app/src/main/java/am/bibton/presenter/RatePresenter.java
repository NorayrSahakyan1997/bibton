package am.bibton.presenter;

import android.widget.Toast;
import java.util.List;
import javax.inject.Inject;
import am.bibton.model.ResponseModel;
import am.bibton.model.rateModel.RateParentModel;
import am.bibton.presenter.root.BasePresenter;
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
            mView.getRateList(responseModel.getData().getList());
            Constants.RATELIST=responseModel.getData().getList();
        } else
            mView.showServerError();
    }

    public void deleteRateItem(int pair_id) {
        Disposable disposable = mService.deleteRateItem(pair_id).subscribe(this::deleteItemResponse, this::errorView);
        addDisposable(disposable);
    }

    private void deleteItemResponse(ResponseModel<List> deleteResponse) {
        if (deleteResponse.isSuccess()) {
            Toast.makeText(mContext, "Item was deleted successfully", Toast.LENGTH_SHORT).show();

        } else {

            mView.showNetworkError();
        }
    }

}
