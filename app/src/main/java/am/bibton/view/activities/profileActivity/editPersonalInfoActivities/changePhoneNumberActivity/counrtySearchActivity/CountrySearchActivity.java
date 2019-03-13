package am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.counrtySearchActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.CountryListAdapter;
import am.bibton.model.countryModel.CountryModel;
import am.bibton.presenter.SelectCountryPresenter;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.ChangePhoneNumberActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CountrySearchActivity extends BaseActivity implements ICountrySearchActivity {
    private RecyclerView recycler_view_country_flags;
    private SearchView search_view_countries;
    @Inject
    SelectCountryPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCountryList();
        init();
    }

    private void init() {
        recycler_view_country_flags = findViewById(R.id.recycler_view_searchCountryActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_country_flags.setLayoutManager(layoutManager);
        search_view_countries = findViewById(R.id.search_view_countriesActivity);
        search_view_countries.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchItemsFromCountryList(search_view_countries.getQuery().toString());
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stopSubscriptions();
    }

    private List<CountryModel> outputCountries;

    @Override
    public void showCountryList(List<CountryModel> list) {
        outputCountries = new ArrayList<>();
        outputCountries = list;
        CountryListAdapter countryListAdapter = new CountryListAdapter(this, outputCountries, false, this::goToChangePhoneNumberActivity);
        recycler_view_country_flags.setAdapter(countryListAdapter);
    }

    private void searchItemsFromCountryList(String query) {
        List<CountryModel> filteredList = new ArrayList<>();

        if (search_view_countries != null) {
            for (CountryModel item : outputCountries) {
                if (item.getName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        } else
            filteredList = outputCountries;
        CountryListAdapter countryListAdapter = new CountryListAdapter(this, filteredList, false, this::goToChangePhoneNumberActivity);
        recycler_view_country_flags.setAdapter(countryListAdapter);
    }

    private void goToChangePhoneNumberActivity(String countryCode) {
        Intent intent = new Intent(this, ChangePhoneNumberActivity.class);
        intent.putExtra("countryCode", countryCode);
        startActivity(intent);

    }

}
