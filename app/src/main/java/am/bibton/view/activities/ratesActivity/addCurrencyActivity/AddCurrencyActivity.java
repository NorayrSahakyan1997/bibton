package am.bibton.view.activities.ratesActivity.addCurrencyActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AddCurrencyPairAdapter;
import am.bibton.model.ResponseModel;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.CurrencyListPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.ratesActivity.RatesActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AddCurrencyActivity extends BaseActivity implements IAddCurrencyActivity {
    private RecyclerView addCurrencyRecyclerView;
    private ConstraintLayout constraintParent;
    private SearchView searchView;
    @Inject
    CurrencyListPresenter mPresenter;
    private List<CurrencyResponse> outputCountries;
    private boolean ratesAdded;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_currency);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();

        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        init();
        closeKeyBoard();
    }

    public void init() {
        addCurrencyRecyclerView = findViewById(R.id.recycler_add_currency);
        constraintParent = findViewById(R.id.constraint_Parent_Add_Currency);
        searchView = findViewById(R.id.search_view_editText);
        searchView.setFocusable(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchItemsFromCountryList(searchView.getQuery().toString());
                return false;
            }
        });

    }

    @Override
    public void getCurrencyList(List<CurrencyResponse> getCurrencyList) {
        outputCountries = getCurrencyList;
        AddCurrencyPairAdapter balanceHomeAdapter = new AddCurrencyPairAdapter(this, getCurrencyList, position -> {
            selectTwoCurrency(position);
        });
        addCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addCurrencyRecyclerView.setAdapter(balanceHomeAdapter);

    }


    public void goToHomeActivity(View view) {
        onBackPressed();
        Constants.CURRENCY_SUM = 0;
    }

    private void closeKeyBoard() {
        constraintParent.setOnClickListener(v -> KeyboardUtils.hideSoftInput(AddCurrencyActivity.this));
    }

    @Override
    public void onBackPressed() {
        Intent goToRateActivity = new Intent(this, RatesActivity.class);
        startActivity(goToRateActivity);
    }

    private void searchItemsFromCountryList(String query) {
        List<CurrencyResponse> filteredList = new ArrayList<>();

        if (searchView != null) {
            for (CurrencyResponse item : outputCountries) {
                if (item.getName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        } else
            filteredList = outputCountries;
        AddCurrencyPairAdapter countryListAdapter = new AddCurrencyPairAdapter(this, filteredList, position -> {
            selectTwoCurrency(position);
        });
        addCurrencyRecyclerView.setAdapter(countryListAdapter);
    }

    @Override
    public void addCurrencyPair(boolean isAdded) {
        ratesAdded = isAdded;
    }



    public void selectTwoCurrency(int currency) {

        Constants.CURRENCY_SUM++;
        if (Constants.CURRENCY_SUM < 3) {
            Intent intent = getIntent();
            startActivity(intent);
            if (Constants.CURRENCY_SUM == 1) {
                Constants.CURRENCY_ID_FIRST = currency;
            }
            if (Constants.CURRENCY_SUM == 2) {
                Constants.CURRENCY_ID_SECOND = currency;
                mPresenter.addCurrencyPair(Constants.CURRENCY_ID_FIRST, Constants.CURRENCY_ID_SECOND);
                Intent backToHomeActivity = new Intent(getApplicationContext(), RatesActivity.class);
                startActivity(backToHomeActivity);
                Constants.CURRENCY_SUM = 0;
            }
        }

    }
}
