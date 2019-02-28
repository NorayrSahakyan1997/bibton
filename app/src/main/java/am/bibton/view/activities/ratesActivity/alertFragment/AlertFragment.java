package am.bibton.view.activities.ratesActivity.alertFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import javax.inject.Inject;
import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AlertListAdapter;
import am.bibton.model.alertModel.AlertResponse;
import am.bibton.presenter.AlertPresenter;
import am.bibton.view.activities.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlertFragment extends BaseFragment implements IAlertFragment {

    private View mainView;
    private Context context;
    @Inject
    AlertPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.alert_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getAlertList();
        return mainView;
    }

    @Override
    public void getAlertList(List<AlertResponse> getAlertList) {
        RecyclerView recyclerView = mainView.findViewById(R.id.recycler_view_alert);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AlertListAdapter alertListAdapter = new AlertListAdapter(context, getAlertList, position -> mPresenter.deleteAlertItem(position));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(alertListAdapter);

    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
