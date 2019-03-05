package am.bibton.view.activities.ratesActivity.ratesFragments.rateFragment;

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
import am.bibton.adapters.RatesAdapter;
import am.bibton.model.rateModel.RateResponse;
import am.bibton.presenter.RatePresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.BaseFragment;
import am.bibton.view.activities.ratesActivity.addRateActivity.AddRateActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RatesFragment extends BaseFragment implements IRateFragment {
    private View mainView;
    private Context context;
    private RecyclerView ratesRecycle;
    private ConstraintLayout addCurrencyParentConstraint;
    private ConstraintLayout addCurrencyPairEmpty;
    private ConstraintLayout addCurrencyImageEmpty;
    @Inject
    RatePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.rates_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getRatesList();
        init();
        goToAddCurrencyActivity();
        return mainView;

    }

    public void init() {
        ratesRecycle = mainView.findViewById(R.id.recycler_view_rates_fragment);
        addCurrencyParentConstraint = mainView.findViewById(R.id.addCurrencyParentConstraintRates);
        addCurrencyPairEmpty = mainView.findViewById(R.id.addCurrencyPairEmptyRate);
        addCurrencyImageEmpty = mainView.findViewById(R.id.constraintAddCurrencyImageEmpty);
    }

    private void goToAddCurrencyActivity() {
        addCurrencyImageEmpty.setOnClickListener(v -> {
            Intent goToAddCurrencyActivity = new Intent(context, AddRateActivity.class);
            goToAddCurrencyActivity.putExtra("fragment", "rates");
            context.startActivity(goToAddCurrencyActivity);
        });
        addCurrencyParentConstraint.setOnClickListener(v -> {
            Intent goToAddCurrencyActivity = new Intent(context, AddRateActivity.class);
            goToAddCurrencyActivity.putExtra("fragment", "rates");
            context.startActivity(goToAddCurrencyActivity);
        });
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void getRateList(List<RateResponse> rateResponsesList) {
        setVisibilityOfAddCurrencyConstraint(rateResponsesList.isEmpty());
        ratesRecycle.setLayoutManager(new LinearLayoutManager(context));

        for (int i = 0; i < rateResponsesList.size(); i++) {
            Constants.FROM_CURRENCY.add(rateResponsesList.get(i).getFrom_id());
            Constants.TO_CURRENCY.add(rateResponsesList.get(i).getTo_id());
        }
        RatesAdapter ratesAdapter = new RatesAdapter(context, rateResponsesList, new RatesAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mPresenter.deleteRateItem(position);
            }

            @Override
            public void onSize(int size) {
                if (size == 0) {
                    addCurrencyParentConstraint.setVisibility(View.GONE);
                    addCurrencyPairEmpty.setVisibility(View.VISIBLE);
                    ratesRecycle.setVisibility(View.GONE);
                } else {
                    addCurrencyParentConstraint.setVisibility(View.VISIBLE);
                    addCurrencyPairEmpty.setVisibility(View.GONE);
                    ratesRecycle.setVisibility(View.VISIBLE);
                }
            }
        });
        ratesRecycle.setAdapter(ratesAdapter);
    }

    private void setVisibilityOfAddCurrencyConstraint(boolean isActive) {
        if (isActive) {
            addCurrencyParentConstraint.setVisibility(View.GONE);
            addCurrencyPairEmpty.setVisibility(View.VISIBLE);
            ratesRecycle.setVisibility(View.GONE);

        } else {
            addCurrencyPairEmpty.setVisibility(View.GONE);
            addCurrencyParentConstraint.setVisibility(View.VISIBLE);
            ratesRecycle.setVisibility(View.VISIBLE);

        }
    }

}
