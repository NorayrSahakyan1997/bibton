package am.bibton.presenter;
import javax.inject.Inject;
import am.bibton.model.ResponseModel;
import am.bibton.model.bibtonToBibtonList.BibtonToBibtonParentModel;
import am.bibton.presenter.root.BasePresenter;
import am.bibton.shared.data.services.AuthorizationService;
import am.bibton.view.activities.bibtnToBibtonActivity.bibtonToBibtonList.IBibtonToBibtonListActivity;
import io.reactivex.disposables.Disposable;

public class BibtonToBibtonListPresenter extends BasePresenter<IBibtonToBibtonListActivity> {
    private final AuthorizationService mService;

    @Inject
    BibtonToBibtonListPresenter(AuthorizationService service) {
        mService = service;
    }

    public void getBibtontoBibtonList() {
        Disposable disposable = mService.getBibtonToBibtonList().subscribe(this::bibtonToBibtonList, this::errorView);
        addDisposable(disposable);
    }

    private void bibtonToBibtonList(ResponseModel<BibtonToBibtonParentModel> responseModel) {
        if (responseModel.isSuccess()) {
            mView.getBibtonToBibtonList(responseModel.getData().getData());
        } else {
            mView.showServerError();
        }
    }
}
