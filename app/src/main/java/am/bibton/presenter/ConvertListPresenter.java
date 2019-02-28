package am.bibton.presenter;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import am.bibton.model.ConvertModel.ConvertParentModel;
import am.bibton.model.ResponseModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.shared.di.scopes.AuthorizationScope;
import am.bibton.view.activities.ratesActivity.ratesFragments.convertFragment.IConvertFragment;
import io.reactivex.disposables.Disposable;

@AuthorizationScope
public class ConvertListPresenter extends BasePresenter<IConvertFragment> {
    private final AuthorizationService mService;

    @Inject
    ConvertListPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getRatesList(float amount) {
        Disposable disposable = mService.getConvertList(amount).subscribe(this::response, this::errorView);
        addDisposable(disposable);
    }

    private void response(ResponseModel<ConvertParentModel> responseModel) {
        List<Integer> convertFromId = new ArrayList<>();
        if (responseModel.isSuccess() && responseModel.getData() != null) {
            mView.getConvertList(responseModel.getData().getList());
            for (int i = 0; i < responseModel.getData().getList().size(); i++) {
                convertFromId.add(responseModel.getData().getList().get(i).getCurrency_id());
            }
            mView.getConvertFromId(convertFromId);

        } else
            mView.showServerError();
    }

    public void deleteConvertItem(int compare_id) {
        Disposable disposable = mService.deleteConvertItem(compare_id).subscribe(this::deleteItemResponse, this::errorView);
        addDisposable(disposable);
    }

    private void deleteItemResponse(ResponseModel<List> deleteResponse) {
        if (deleteResponse.isSuccess()) {
            //getRatesList();
            Toast.makeText(mContext, "Item was deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            mView.showNetworkError();
        }
    }

    public void makeCurrencyMain(int currency_id) {
        Disposable disposable = mService.makeCurrencyMain(currency_id).subscribe(this::makeCurrencyMainResponse, this::errorView);
        addDisposable(disposable);
    }

    private void makeCurrencyMainResponse(ResponseModel<List> makeMainCurrency) {
        if (makeMainCurrency.isSuccess()) {

            Toast.makeText(mContext, "Main Currency Has Changed Successfully", Toast.LENGTH_SHORT).show();
        } else {
            mView.showNetworkError();
        }
    }
}
