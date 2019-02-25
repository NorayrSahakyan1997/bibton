package am.bibton.view.activities.ratesActivity.ratesFragments.rateFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;

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
    private RecyclerView ratesRecycle;
    private ConstraintLayout addCurrencyParentConstraint;
    private ConstraintLayout addCurrencyPairEmpty;
    private ConstraintLayout addCurrencyImageEmpty;
    private AppBarLayout appBarLayoutRate;
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
        addCurrencyParentConstraint = mainView.findViewById(R.id.addCurrencyParentConstraint);
        addCurrencyPairEmpty = mainView.findViewById(R.id.addCurrencyPairEmptyRate);
        addCurrencyImageEmpty = mainView.findViewById(R.id.constraintAddCurrencyImageEmpty);
        appBarLayoutRate = mainView.findViewById(R.id.appBarLayoutRate);
    }

    private void goToAddCurrencyActivity() {

        addCurrencyImageEmpty.setOnClickListener(v -> {
            Intent goToAddCurrencyActivity = new Intent(context, AddCurrencyActivity.class);
            context.startActivity(goToAddCurrencyActivity);
        });
        addCurrencyParentConstraint.setOnClickListener(v -> {
            Intent goToAddCurrencyActivity = new Intent(context, AddCurrencyActivity.class);
            context.startActivity(goToAddCurrencyActivity);
        });
    }


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private RatesAdapter ratesAdapter;

    @Override
    public void getRateList(List<RateResponse> rateResponsesList) {
        if (rateResponsesList.isEmpty()) {
            addCurrencyParentConstraint.setVisibility(View.GONE);
            appBarLayoutRate.setVisibility(View.GONE);
            addCurrencyPairEmpty.setVisibility(View.VISIBLE);
            ratesRecycle.setVisibility(View.GONE);

        } else {
            addCurrencyPairEmpty.setVisibility(View.GONE);
            addCurrencyParentConstraint.setVisibility(View.VISIBLE);
            ratesRecycle.setVisibility(View.VISIBLE);
            appBarLayoutRate.setVisibility(View.VISIBLE);

        }
        ratesRecycle.setLayoutManager(new LinearLayoutManager(context));
        ratesAdapter = new RatesAdapter(context, rateResponsesList, new RatesAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mPresenter.deleteRateItem(position);

            }

            @Override
            public void onSize(int size) {
                appBarLayoutRate.setVisibility(View.VISIBLE);
                if (size == 0) {
                    appBarLayoutRate.setVisibility(View.GONE);
                    addCurrencyParentConstraint.setVisibility(View.GONE);
                    addCurrencyPairEmpty.setVisibility(View.VISIBLE);
                    ratesRecycle.setVisibility(View.GONE);
                } else {
                    addCurrencyParentConstraint.setVisibility(View.VISIBLE);
                    addCurrencyPairEmpty.setVisibility(View.GONE);
                    ratesRecycle.setVisibility(View.VISIBLE);
                    appBarLayoutRate.setVisibility(View.VISIBLE);

                }

            }
        });
        ratesRecycle.setAdapter(ratesAdapter);
    }


}
