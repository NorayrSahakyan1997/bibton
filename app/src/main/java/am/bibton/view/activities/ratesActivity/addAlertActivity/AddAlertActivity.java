package am.bibton.view.activities.ratesActivity.addAlertActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.AddAlertAdapter;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.presenter.AddAlertListPresenter;
import am.bibton.presenter.AddRateListPresenter;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.homeActivity.HomeActivity;
import am.bibton.view.activities.ratesActivity.RatesActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

public class AddAlertActivity extends BaseActivity implements IAddAlertActivity {

    @Inject
    AddAlertListPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();


    }

    @Override
    public void getAlertList(List<CurrencyResponse> getAlertResponse) {
        RecyclerView recyclerView = findViewById(R.id.recycler_add_alert);
        AddAlertAdapter addAlertAdapter = new AddAlertAdapter(this, getAlertResponse, new AddAlertAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addAlertAdapter);

    }

    public void goToRatesActivity(View view) {
        Intent goToRatesActivity = new Intent(this, RatesActivity.class);
        goToRatesActivity.putExtra("fragment", "AddAlertActivity");
        startActivity(goToRatesActivity);
    }
}
