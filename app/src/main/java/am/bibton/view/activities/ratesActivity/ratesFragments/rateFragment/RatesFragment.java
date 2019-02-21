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
import am.bibton.view.activities.BaseFragment;
import am.bibton.view.activities.ratesActivity.addCurrencyActivity.AddCurrencyActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RatesFragment extends BaseFragment implements IRateFragment {
    private View mainView;
    private Context context;
    private ConstraintLayout addCurrencyConstraint;
    private RecyclerView ratesRecycle;
    @Inject
    RatePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.rates_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getRatesList();
        addCurrencyConstraint = mainView.findViewById(R.id.add_currency_constraint);
        init();
        goToAddCurrencyActivity();
        return mainView;

    }

    public void init() {
        ratesRecycle = mainView.findViewById(R.id.recycler_view_rates_fragment);

    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void goToAddCurrencyActivity() {
        addCurrencyConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddCurrencyActivity = new Intent(context, AddCurrencyActivity.class);
                context.startActivity(goToAddCurrencyActivity);
            }
        });
    }

    @Override
    public void getRateList(List<RateResponse> rateResponsesList) {
        ratesRecycle.setLayoutManager(new LinearLayoutManager(context));
        RatesAdapter ratesAdapter = new RatesAdapter(context, rateResponsesList);
        ratesRecycle.setAdapter(ratesAdapter);
    }
}
