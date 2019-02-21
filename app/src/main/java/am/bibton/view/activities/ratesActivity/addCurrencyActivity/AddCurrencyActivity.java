package am.bibton.view.activities.ratesActivity.addCurrencyActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AddCurrencyPairAdapter;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.AddCurrencyPresenter;
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
    AddCurrencyPresenter mPresenter;
    private List<WalletCurrencyResponse> outputCountries;

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
    public void getCurrencyList(List<WalletCurrencyResponse> getWalletCurrencyList) {
        outputCountries = getWalletCurrencyList;
        AddCurrencyPairAdapter balanceHomeAdapter = new AddCurrencyPairAdapter(this, getWalletCurrencyList, position -> {

        });
        addCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addCurrencyRecyclerView.setAdapter(balanceHomeAdapter);
    }

    public void goToHomeActivity(View view) {
        onBackPressed();
        Constants.CURRENCY_SUM=0;
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
        List<WalletCurrencyResponse> filteredList = new ArrayList<>();

        if (searchView != null) {
            for (WalletCurrencyResponse item : outputCountries) {
                if (item.getCurrency_name().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        } else
            filteredList = outputCountries;
        AddCurrencyPairAdapter countryListAdapter = new AddCurrencyPairAdapter(this, filteredList, position ->{});
        addCurrencyRecyclerView.setAdapter(countryListAdapter);
    }
    //    public void selectTwoCurrency() {
//        Constants.CURRENCY_SUM++;
//        if (Constants.CURRENCY_SUM < 3) {
//            Constants.CURRENCY_SUM++;
//            Intent intent = getIntent();
//            startActivity(intent);
//            if (Constants.CURRENCY_SUM == 1) {
//                Constants.CURRENCY_ID_FIRST = currencyFirst;
//            }
//            if (Constants.CURRENCY_SUM == 2) {
//                Constants.CURRENCY_ID_SECOND = currencySecond;
//            }
//
//        } else if (Constants.CURRENCY_SUM ==3) {
//            Intent intent = new Intent(getApplicationContext(), RatesActivity.class);
//            startActivity(intent);
//            Toast.makeText(this,currencySecond+"",Toast.LENGTH_SHORT).show();
//            Constants.CURRENCY_SUM = 0;
//        }
//    }
}
