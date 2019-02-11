package am.spaysapps.bibton.presenter;


import android.widget.Toast;

import javax.inject.Inject;

import am.spaysapps.bibton.model.ResponseModel;
import am.spaysapps.bibton.model.TransactionRequestModel;
import am.spaysapps.bibton.model.getTransactionList.TransactionParentModel;
import am.spaysapps.bibton.presenter.root.BasePresenter;
import am.spaysapps.bibton.shared.data.services.AuthorizationService;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.homeFragment.IHomeFragment;
import io.reactivex.disposables.Disposable;

public class HomeFragmentPresenter extends BasePresenter<IHomeFragment> {

    private final AuthorizationService mService;

    @Inject
    HomeFragmentPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getTransactionList(TransactionRequestModel transactionRequestModel) {
        Disposable disposable = mService.getTransaction(transactionRequestModel)
                .subscribe(this::transactionResponse, this::errorView);
        addDisposable(disposable);
    }


    private void transactionResponse(ResponseModel<TransactionParentModel> responseModel) {
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getTransactionList(responseModel.getData().getData());

        } else

        mView.showServerError();
        //  mView.hidePageLoading();
    }
}
