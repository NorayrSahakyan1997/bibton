package am.bibton.view.activities.ratesActivity.addSecondAlertPairActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.presenter.SecondAlertActivityPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.ratesActivity.RatesActivity;
import am.bibton.view.activities.ratesActivity.addAlertActivity.AddAlertActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class SecondAlertPairActivity extends BaseActivity implements ISecondAlertPairActivity {
    private ImageView fromAlertIcon;
    private TextView cratePriceAlertFor;
    private TextView toAlertIso;
    private ConstraintLayout parentConstraintSecondAlertActivity;
    private ConstraintLayout constraintSelectSecondCurrency;
    private TextView fromToAlertTextView;
    private EditText balanceAmountAlert;
    @Inject
    SecondAlertActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_alert_pair);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        init();
        getFirstAlertValue();
        closeKeyBoard();
        goToSelectSecondCurrencyActivity();
    }

    private void init() {
        fromAlertIcon = findViewById(R.id.fromAlertIcon);
        toAlertIso = findViewById(R.id.toAlertIso);
        cratePriceAlertFor = findViewById(R.id.cratePriceAlertFor);
        parentConstraintSecondAlertActivity = findViewById(R.id.parentConstraintSecondAlertActivity);
        constraintSelectSecondCurrency = findViewById(R.id.constraintSelectSecondCurrency);
        fromToAlertTextView = findViewById(R.id.fromToAlertTextView);
        toAlertIso.setText(getResources().getString(R.string.usd));
        balanceAmountAlert = findViewById(R.id.balanceAmountAlert);
        mPresenter.getExchange(Constants.FROM_AIERT_ID, Constants.TO_ALERT_ID, 1);
    }

    @SuppressLint("SetTextI18n")
    private void getFirstAlertValue() {
        cratePriceAlertFor.setText(getResources().getString(R.string.createAPriceAlert) + " " + Constants.FromAlertIso);
        if (!Constants.ToAlertIso.matches("")) {
            toAlertIso.setText(Constants.ToAlertIso);
        } else {
            toAlertIso.setText(getResources().getString(R.string.usd));
        }
        Picasso.get()
                .load(Constants.FromAlertIcon)
                .into(fromAlertIcon);
    }

    public void backToAddAlertActivity(View view) {
        Intent backToAddAlertActivity = new Intent(this, AddAlertActivity.class);
        startActivity(backToAddAlertActivity);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public void closeKeyBoard() {
        parentConstraintSecondAlertActivity.setOnClickListener(v -> KeyboardUtils.hideSoftInput(this));
    }

    public void goToSelectSecondCurrencyActivity() {
        constraintSelectSecondCurrency.setOnClickListener(v -> {
            Intent backToAddAlertActivity = new Intent(getApplicationContext(), AddAlertActivity.class);
            startActivity(backToAddAlertActivity);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getExchangeRate(ExchangeParentModel exchangeParentModel) {
        fromToAlertTextView.setText(exchangeParentModel.getCurrency().first_currency.getISO()
                + "=" + exchangeParentModel.getResult()
                + " " + exchangeParentModel.getCurrency().second_currency.getISO());

    }

    public void submitAlert(View view) {
        int alertAmount = Integer.parseInt(balanceAmountAlert.getText().toString());
        if (alertAmount == 0) {
            Toast.makeText(getBaseContext(), getResources().getString(R.string.pleaseFillAmountField), Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.addAlert(Constants.FROM_AIERT_ID, Constants.TO_ALERT_ID, alertAmount);
            goToRateActivity();
        }
    }

    public void goToRateActivity() {
        Intent goToRateActivity = new Intent(this, RatesActivity.class);
        goToRateActivity.putExtra("fragment", "AddAlertActivity");
        startActivity(goToRateActivity);
        Constants.FROM_AIERT_ID = 0;
        Constants.TO_ALERT_ID = 0;
        Constants.FromAlertIcon = "";
    }


}
