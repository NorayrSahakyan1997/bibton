package am.bibton.view.activities.ratesActivity.addAlertActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AddAlertAdapter;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.presenter.AddAlertListPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.ratesActivity.RatesActivity;
import am.bibton.view.activities.ratesActivity.addSecondAlertPairActivity.SecondAlertPairActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AddAlertActivity extends BaseActivity implements IAddAlertActivity {

    @Inject
    AddAlertListPresenter mPresenter;
    private SearchView searchView;
    private List<CurrencyResponse> filteredCurrencyList;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();

        init();


    }

    public void init() {
        searchView = findViewById(R.id.search_view_add_alert);
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
    public void getAlertList(List<CurrencyResponse> getAlertResponse) {
        filteredCurrencyList = getAlertResponse;

        recyclerView = findViewById(R.id.recycler_add_alert);
        AddAlertAdapter addAlertAdapter = new AddAlertAdapter(this, getAlertResponse, ((fromIsoName, fromIsoIcon) -> {
            check(fromIsoName, fromIsoIcon);
            goToSecondAlertPairActivity(fromIsoName, fromIsoIcon);
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addAlertAdapter);

    }

    private void searchItemsFromCountryList(String query) {
        List<CurrencyResponse> filteredList = new ArrayList<>();
        if (searchView != null) {
            for (CurrencyResponse item : filteredCurrencyList) {
                if (item.getName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        } else
            filteredList = filteredCurrencyList;
        AddAlertAdapter addAlertAdapter = new AddAlertAdapter(this, filteredList, (fromIsoName, fromIsoIcon) -> {
            check(fromIsoName, fromIsoIcon);
            goToSecondAlertPairActivity(fromIsoName, fromIsoIcon);

        });


        recyclerView.setAdapter(addAlertAdapter);

    }


    public void goToRatesActivity(View view) {
        Intent goToRatesActivity = new Intent(this, RatesActivity.class);
        goToRatesActivity.putExtra("fragment", "AddAlertActivity");
        startActivity(goToRatesActivity);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        Constants.SELECTSECONDALERT = false;
        Constants.FIRST_Alert_ID = 0;

    }

    public void goToSecondAlertPairActivity(String firstValueIso, String fromAlertIcon) {
        Intent goToSecondAlertPairActivity = new Intent(getApplicationContext(), SecondAlertPairActivity.class);
        goToSecondAlertPairActivity.putExtra(Constants.ALERT_FIRST_ISO, firstValueIso);
        goToSecondAlertPairActivity.putExtra(Constants.ALERT_FIRST_ICON, fromAlertIcon);
        startActivity(goToSecondAlertPairActivity);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private void check(String isoName, String fromIcon) {
        if (!Constants.SELECTSECONDALERT) {
            Constants.FromAlertIso = isoName;
            Constants.FromAlertIcon = fromIcon;
            Constants.SELECTSECONDALERT = true;
        } else {
            Constants.ToAlertIso = isoName;

        }
    }
}
