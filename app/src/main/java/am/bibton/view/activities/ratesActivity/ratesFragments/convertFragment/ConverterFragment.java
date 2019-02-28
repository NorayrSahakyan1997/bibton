package am.bibton.view.activities.ratesActivity.ratesFragments.convertFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.ConvertListAdapter;
import am.bibton.model.convertModel.ConvertResponse;
import am.bibton.presenter.ConvertListPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseFragment;
import am.bibton.view.activities.ratesActivity.addConvertActivity.AddConvertActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConverterFragment extends BaseFragment implements IConvertFragment {

    private RecyclerView recyclerViewConverts;
    private Context context;
    private ConstraintLayout addCurrencyParentConstraintConverts;
    private View mainView;
    private ConstraintLayout addCurrencyPairEmpty;
    @Inject
    ConvertListPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.convert_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getRatesList(1);
        init();
        goToAddCurrencyActivity();
        setupUI(mainView);
        return mainView;
    }

    private void init() {
        addCurrencyParentConstraintConverts = mainView.findViewById(R.id.addCurrencyParentConstraintConverts);
        addCurrencyPairEmpty = mainView.findViewById(R.id.addCurrencyPairEmptyConvert);
        recyclerViewConverts = mainView.findViewById(R.id.recycler_view_converts);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    KeyboardUtils.hideSoftInput(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void goToAddCurrencyActivity() {
        addCurrencyPairEmpty.setOnClickListener(v -> {
            Intent goToAddConvertActivity = new Intent(context, AddConvertActivity.class);
            goToAddConvertActivity.putExtra("fragment", "convertFragment");

            context.startActivity(goToAddConvertActivity);
        });
        addCurrencyParentConstraintConverts.setOnClickListener(v -> {
            Intent goToAddConvertActivity = new Intent(context, AddConvertActivity.class);
            goToAddConvertActivity.putExtra("fragment", "convertFragment");
            context.startActivity(goToAddConvertActivity);
        });
    }

    @Override
    public void getConvertList(List<ConvertResponse> getConvertList) {
        setVisibilityOfAddCurrencyConstraint(getConvertList.isEmpty());
        recyclerViewConverts.setLayoutManager(new LinearLayoutManager(context));
        ConvertListAdapter currencyAdapter = new ConvertListAdapter(context, getConvertList, new ConvertListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mPresenter.deleteConvertItem(position);
            }

            @Override
            public void makeCurrencyMain(int currencyId, float amount) {
                mPresenter.makeCurrencyMain(currencyId);
                //mPresenter.getRatesList(amount);
            }

            @Override
            public void getAdapterSize(int position) {
                if (position == 0) {
                    Constants.FromCurrencyConvert.clear();
                    addCurrencyParentConstraintConverts.setVisibility(View.GONE);
                    addCurrencyPairEmpty.setVisibility(View.VISIBLE);
                    recyclerViewConverts.setVisibility(View.GONE);
                } else {
                    addCurrencyParentConstraintConverts.setVisibility(View.VISIBLE);
                    addCurrencyPairEmpty.setVisibility(View.GONE);
                    recyclerViewConverts.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerViewConverts.setAdapter(currencyAdapter);
    }

    @Override
    public void getConvertFromId(List<Integer> getConverFromId) {
        Constants.FromCurrencyConvert = getConverFromId;
    }

    private void setVisibilityOfAddCurrencyConstraint(boolean isActive) {
        if (isActive) {
            addCurrencyParentConstraintConverts.setVisibility(View.GONE);
            addCurrencyPairEmpty.setVisibility(View.VISIBLE);
            recyclerViewConverts.setVisibility(View.GONE);

        } else {
            addCurrencyParentConstraintConverts.setVisibility(View.GONE);
            addCurrencyPairEmpty.setVisibility(View.VISIBLE);
            recyclerViewConverts.setVisibility(View.VISIBLE);

        }
    }
}
