package am.bibton.view.activities.ratesActivity.ratesFragments.alertFragment;

import android.content.Context;
import android.content.Intent;
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
import am.bibton.view.activities.ratesActivity.addAlertActivity.AddAlertActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlertFragment extends BaseFragment implements IAlertFragment {

    private View mainView;
    private Context context;
    private ConstraintLayout addCurrencyParentConstraintAlert;
    private ConstraintLayout addAlertPairEmptyConvert;
    private RecyclerView recyclerView;
    @Inject
    AlertPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.alert_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getAlertList();
        init();
        goToAddAlertActivity();
        return mainView;
    }

    private void init() {
        addCurrencyParentConstraintAlert = mainView.findViewById(R.id.addCurrencyParentConstraintAlert);
        addAlertPairEmptyConvert = mainView.findViewById(R.id.addAlertPairEmptyConvert);
        recyclerView = mainView.findViewById(R.id.recycler_view_alert);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


    }

    private void goToAddAlertActivity() {
        addCurrencyParentConstraintAlert.setOnClickListener(v -> {
            Intent goToAddAlertActivity = new Intent(context, AddAlertActivity.class);
            context.startActivity(goToAddAlertActivity);
        });
        addAlertPairEmptyConvert.setOnClickListener(v -> {
                    Intent goToAddAlertActivity = new Intent(context, AddAlertActivity.class);
                    context.startActivity(goToAddAlertActivity);
                }
        );
    }

    @Override
    public void getAlertList(List<AlertResponse> getAlertList) {
        setVisibilityOfAddCurrencyConstraint(getAlertList.isEmpty());
        AlertListAdapter alertListAdapter = new AlertListAdapter(context, getAlertList, new AlertListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mPresenter.deleteAlertItem(position);
            }

            @Override
            public void setSwitcher(int iD) {
                mPresenter.switchAlert(iD);
            }

            @Override
            public void getSize(int size) {
                if (size == 0) {
                    addAlertPairEmptyConvert.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    addCurrencyParentConstraintAlert.setVisibility(View.GONE);
                } else {
                    addAlertPairEmptyConvert.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    addCurrencyParentConstraintAlert.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(alertListAdapter);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void setVisibilityOfAddCurrencyConstraint(boolean isActive) {
        if (isActive) {
            addAlertPairEmptyConvert.setVisibility(View.VISIBLE);
            addCurrencyParentConstraintAlert.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);

        } else {
            addAlertPairEmptyConvert.setVisibility(View.GONE);
            addCurrencyParentConstraintAlert.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}
