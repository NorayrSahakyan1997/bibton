package am.spaysapps.bibton.view.activities.homeActivity.homeFragments.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import javax.inject.Inject;
import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.ServiceAdapterHorizontal;
import am.spaysapps.bibton.adapters.TransactionListAdapter;
import am.spaysapps.bibton.model.TransactionRequestModel;
import am.spaysapps.bibton.model.getTransactionList.TransactionResponse;
import am.spaysapps.bibton.presenter.HomeFragmentPresenter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements IHomeFragment {

    private Context context;
    private View mainView;
    private RecyclerView transaction_recycler_view;
    @Inject
    HomeFragmentPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.service_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);

        mPresenter.onViewCreated(this);

        TransactionRequestModel transactionRequestModel = new TransactionRequestModel();
        transactionRequestModel.setFrom_currency_id(1);
        mPresenter.getTransactionList(transactionRequestModel);
        init();
        return mainView;
    }

    private void init() {
        RecyclerView recyclerView_service_horizontal = mainView.findViewById(R.id.recyclerView_service_horizontal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_service_horizontal.setLayoutManager(layoutManager);
        ServiceAdapterHorizontal serviceAdapterHorizontal = new ServiceAdapterHorizontal(context);
        recyclerView_service_horizontal.setAdapter(serviceAdapterHorizontal);
        transaction_recycler_view = mainView.findViewById(R.id.recycler_view_transaction);
        RecyclerView.LayoutManager transaction_manager = new LinearLayoutManager(getContext());
        transaction_recycler_view.setLayoutManager(transaction_manager);


    }

    @Override
    public void onDestroy() {
        mPresenter.stopSubscriptions();
        super.onDestroy();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void getTransactionList(List<TransactionResponse> getTransactionList) {
        List<TransactionResponse> transactionResponseList;
        transactionResponseList = getTransactionList;
        TransactionListAdapter transactionListAdapter = new TransactionListAdapter(getContext(), transactionResponseList);
        transaction_recycler_view.setAdapter(transactionListAdapter);

    }

    @Override
    public void onError(String errorMessage) {
    }

    @Override
    public void showServerError() {

    }

    @Override
    public void showNetworkError() {

    }
}
