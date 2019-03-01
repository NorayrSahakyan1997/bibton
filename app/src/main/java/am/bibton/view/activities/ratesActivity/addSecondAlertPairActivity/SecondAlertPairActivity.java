package am.bibton.view.activities.ratesActivity.addSecondAlertPairActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.presenter.SecondAlertActivityPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.ratesActivity.addAlertActivity.AddAlertActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class SecondAlertPairActivity extends BaseActivity implements ISecondAlertPairActivity {
    private ImageView fromAlertIcon;
    private TextView cratePriceAlertFor;
    private TextView toAlertIso;
    private ConstraintLayout parentConstraintSecondAlertActivity;
    private ConstraintLayout constraintSelectSecondCurrency;
    private TextView fromToAlertTextView;
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
        mPresenter.getExchange(1, 2, 1);
    }

    private void init() {
        fromAlertIcon = findViewById(R.id.fromAlertIcon);
        toAlertIso = findViewById(R.id.toAlertIso);
        cratePriceAlertFor = findViewById(R.id.cratePriceAlertFor);
        parentConstraintSecondAlertActivity = findViewById(R.id.parentConstraintSecondAlertActivity);
        constraintSelectSecondCurrency = findViewById(R.id.constraintSelectSecondCurrency);
        fromToAlertTextView = findViewById(R.id.fromToAlertTextView);

    }

    @SuppressLint("SetTextI18n")
    private void getFirstAlertValue() {
        cratePriceAlertFor.setText(getResources().getString(R.string.createAPriceAlert) + " " + Constants.FromAlertIso);
        toAlertIso.setText(Constants.ToAlertIso);
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
        fromToAlertTextView.setText(exchangeParentModel.getCurrency().first_currency.getISO() + "=" + exchangeParentModel.getAmount() + " " + exchangeParentModel.getCurrency().second_currency.getISO());

    }

    public void submitAlert(View view) {
        mPresenter.addAlert(1, 5, 100);
    }

}
