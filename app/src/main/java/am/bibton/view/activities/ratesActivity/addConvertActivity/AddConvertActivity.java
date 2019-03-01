package am.bibton.view.activities.ratesActivity.addConvertActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AddConvertItemAdapter;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.presenter.AddConvertListPresenter;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.ratesActivity.RatesActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AddConvertActivity extends BaseActivity implements IAddConvertActivity {

    private RecyclerView addCurrencyRecyclerView;
    private SearchView searchView;
    @Inject
    AddConvertListPresenter mPresenter;
    private List<CurrencyResponse> outputCountries;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_convert);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();
        init();
        setSearchViewQueryListener();
    }

    public void init() {
        searchView = findViewById(R.id.searchViewAddConvert);
        searchView.setFocusable(true);
        ImageView backIconAddConvertActivity = findViewById(R.id.backIconAddConvertActivity);
        backIconAddConvertActivity.setOnClickListener(v -> goToRatesActivity());
    }

    public void setSearchViewQueryListener() {
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
        addCurrencyRecyclerView = findViewById(R.id.recyclerViewAddConvert);
        AddConvertItemAdapter addCurrencyPairAdapter = new AddConvertItemAdapter(this, getCurrencyList, position -> {
            mPresenter.addConvertItem(position);
            goToRatesActivity();
        });
        addCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addCurrencyRecyclerView.setAdapter(addCurrencyPairAdapter);
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
        AddConvertItemAdapter addCurrencyPairAdapter = new AddConvertItemAdapter(this, filteredList, position -> {
            mPresenter.addConvertItem(position);
            goToRatesActivity();
        });
        addCurrencyRecyclerView.setAdapter(addCurrencyPairAdapter);
    }

    public void goToRatesActivity() {
        Intent goToRatesActivity = new Intent(getApplicationContext(), RatesActivity.class);
        goToRatesActivity.putExtra("fragment", "convertFragment");
        startActivity(goToRatesActivity);
        overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToRatesActivity();
    }
}
