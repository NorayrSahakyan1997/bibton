package am.bibton.view.activities.ratesActivity.addRateActivity;
import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AddCurrencyPairAdapter;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.presenter.AddRateListPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.addAccountDetailsActivity.AddAccountDetailsActivity;
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

public class AddRateActivity extends BaseActivity implements IAddRateActivity {
    private RecyclerView addCurrencyRecyclerView;
    private ConstraintLayout constraintParent;
    private SearchView searchView;
    @Inject
    AddRateListPresenter mPresenter;
    private List<CurrencyResponse> outputCountries;


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
        AddCurrencyPairAdapter addCurrencyPairAdapter = new AddCurrencyPairAdapter(this, getCurrencyList, Constants.FromCurrencyConvert, new AddCurrencyPairAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                selectTwoCurrency(id);
            }

            @Override
            public void setPosition(int position) {
                goToBibtonToBibtonActivity(outputCountries.get(position).getIso(), outputCountries.get(position).getFlag());
            }
        });
        addCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addCurrencyRecyclerView.setAdapter(addCurrencyPairAdapter);
    }

    public void goToHomeActivity(View view) {
        onBackPressed();
        Constants.CURRENCY_ID_FIRST = 0;
        Constants.CURRENCY_SUM = 0;
    }

    private void closeKeyBoard() {
        constraintParent.setOnClickListener(v -> KeyboardUtils.hideSoftInput(AddRateActivity.this));
    }

    @Override
    public void onBackPressed() {
        Intent goToRateActivity = new Intent(this, RatesActivity.class);
        startActivity(goToRateActivity);
    }

    List<CurrencyResponse> filteredList;

    private void searchItemsFromCountryList(String query) {
        filteredList = new ArrayList<>();
        if (searchView != null) {
            for (CurrencyResponse item : outputCountries) {
                if (item.getName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        } else
            filteredList = outputCountries;
        AddCurrencyPairAdapter addCurrencyPairAdapter = new AddCurrencyPairAdapter(this, filteredList, Constants.FromCurrencyConvert, new AddCurrencyPairAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                selectTwoCurrency(id);
            }

            @Override
            public void setPosition(int position) {
                goToBibtonToBibtonActivity(filteredList.get(position).getIso(), filteredList.get(position).getFlag());
            }
        });
        addCurrencyRecyclerView.setAdapter(addCurrencyPairAdapter);
    }

    @Override
    public void addCurrencyPair(boolean isAdded) {

    }

    private void selectTwoCurrency(int currency) {
        Intent intent = getIntent();

        Constants.CURRENCY_SUM++;
        if (Constants.CURRENCY_SUM < 3) {
            startActivity(intent);
            if (Constants.CURRENCY_SUM == 1) {
                Constants.CURRENCY_ID_FIRST = currency;

            }
            if (Constants.CURRENCY_SUM == 2) {
                Constants.CURRENCY_ID_SECOND = currency;
                mPresenter.addCurrencyPair(Constants.CURRENCY_ID_FIRST, Constants.CURRENCY_ID_SECOND);
                Intent backToHomeActivity = new Intent(getApplicationContext(), RatesActivity.class);
                startActivity(backToHomeActivity);
                backToHomeActivity.putExtra("fragment", "rates");
                Constants.CURRENCY_SUM = 0;
                Constants.CURRENCY_ID_FIRST = 0;
            }
        }
    }

    private void goToBibtonToBibtonActivity(String name, String icon) {
        Intent intent = getIntent();

        if (intent.hasExtra("addRateActivity")) {
            Intent returnToBibtonToBibtonActivity = new Intent(this, AddAccountDetailsActivity.class);
            returnToBibtonToBibtonActivity.putExtra("currencyId", name);
            returnToBibtonToBibtonActivity.putExtra("currencyFlag", icon);
            startActivity(returnToBibtonToBibtonActivity);
            Constants.CURRENCY_SUM = 0;

        }
    }
}
