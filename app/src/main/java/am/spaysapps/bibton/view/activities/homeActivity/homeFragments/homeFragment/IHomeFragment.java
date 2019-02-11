package am.spaysapps.bibton.view.activities.homeActivity.homeFragments.homeFragment;

import java.util.List;

import am.spaysapps.bibton.model.getTransactionList.TransactionResponse;
import am.spaysapps.bibton.view.activities.IBaseView;

public interface IHomeFragment extends IBaseView {
    void getTransactionList(List<TransactionResponse> getTransactionList);
}
