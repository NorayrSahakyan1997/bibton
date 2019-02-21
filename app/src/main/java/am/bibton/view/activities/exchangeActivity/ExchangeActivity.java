package am.bibton.view.activities.exchangeActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.BalanceHomeAdapter;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.ExchangeActivityPresenter;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.homeActivity.HomeActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

public class ExchangeActivity extends BaseActivity implements IExchangeActivity {
    private EditText convert_first_value;
    private EditText conver_second_valuel;
    private ScrollView scrollView;
    private ImageView constraintCircleShape;
    private ImageView exchangeIcon;
    private TextView textCurrencyFrom;
    private TextView tectCurrencyTo;
    private TextView textBalanceFrom;
    private TextView textBalanceTo;
    private TextView currencyResultFor1;
    private LinearLayout parent_layout_exchange;
    private ConstraintLayout constraintBalance;
    private Boolean setFrom;

    @Inject
    ExchangeActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();
        pendingTransaction();
        init();
        setFusibilityEditTexts();
        hideSoftKeyboard();
        rotateImageViewExchange();
        closeKeyBoard();
        convertFirstValuesExchange();
        //convertSecondValue();
    }

    private void init() {
        convert_first_value = findViewById(R.id.convert_from_value);
        conver_second_valuel = findViewById(R.id.convert_to_value);
        scrollView = findViewById(R.id.scroll_view_exchange);
        constraintCircleShape = findViewById(R.id.constraint_circle_shape);
        exchangeIcon = findViewById(R.id.change_icon);
        parent_layout_exchange = findViewById(R.id.parent_layout_exchange);
        constraintBalance = findViewById(R.id.constraint_balance);
        textCurrencyFrom = findViewById(R.id.text_convert_from);
        tectCurrencyTo = findViewById(R.id.convert_to);
        textBalanceFrom = findViewById(R.id.text_balance_from);
        textBalanceTo = findViewById(R.id.text_balance_to);
        currencyResultFor1 = findViewById(R.id.currency_result_for_1);
    }

    private void closeKeyBoard() {
        parent_layout_exchange.setOnClickListener(v -> KeyboardUtils.hideSoftInput(this));
    }

    private void rotateImageViewExchange() {

        constraintCircleShape.setOnClickListener(v -> {
            RotateAnimation rotate = new RotateAnimation(
                    0, 180,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            rotate.setDuration(500);
            rotate.setRepeatCount(0);
            exchangeIcon.startAnimation(rotate);

        });

    }

    public void close_balance_list(View view) {
        constraintBalance.setVisibility(View.GONE);
    }

    private void pendingTransaction() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void close_exchange_activity(View view) {
        Intent closeExchangeActivity = new Intent(this, HomeActivity.class);
        startActivity(closeExchangeActivity);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

    }

    public void scroll_activity(View view) {
        scrollView.scrollTo(20, 40);
    }

    private void setFusibilityEditTexts() {
        convert_first_value.setFocusable(true);
        conver_second_valuel.setFocusable(true);
    }

    public void openCurrencyFrom(View view) {
        KeyboardUtils.hideSoftInput(this);
        setFrom = true;
        constraintBalance.setVisibility(View.VISIBLE);
    }

    public void openCurrencyTo(View view) {
        KeyboardUtils.hideSoftInput(this);
        setFrom = false;
        constraintBalance.setVisibility(View.VISIBLE);
    }

    private int from_currency_id;
    private int to_currency_id;
    private float result;
    private String fromSymbol;
    private String toSymbol;

    @SuppressLint("SetTextI18n")
    @Override
    public void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList) {
        from_currency_id = getWalletCurrencyList.get(0).getCurrency_id();
        to_currency_id = getWalletCurrencyList.get(1).getCurrency_id();
        textCurrencyFrom.setText(getWalletCurrencyList.get(0).getCurrency_iso());
        tectCurrencyTo.setText(getWalletCurrencyList.get(1).getCurrency_iso());
        textBalanceFrom.setText(getWalletCurrencyList.get(0).getSymbol() + getWalletCurrencyList.get(0).getBalance());
        textBalanceTo.setText(getWalletCurrencyList.get(1).getSymbol() + getWalletCurrencyList.get(1).getBalance());
        fromSymbol = getWalletCurrencyList.get(0).getSymbol();
        toSymbol = getWalletCurrencyList.get(1).getSymbol();

        mPresenter.getExchange(from_currency_id, to_currency_id, 1);
        BalanceHomeAdapter balanceHomeAdapter = new BalanceHomeAdapter(this, getWalletCurrencyList, position -> {
            constraintBalance.setVisibility(View.GONE);
            if (setFrom) {
                textCurrencyFrom.setText(getWalletCurrencyList.get(position).getCurrency_iso());
                textBalanceFrom.setText(getWalletCurrencyList.get(position).getSymbol() + getWalletCurrencyList.get(position).getBalance());
                from_currency_id = getWalletCurrencyList.get(position).getCurrency_id();
                mPresenter.getExchange(from_currency_id, to_currency_id, 1);
                fromSymbol = getWalletCurrencyList.get(position).getSymbol();
            } else {
                tectCurrencyTo.setText(getWalletCurrencyList.get(position).getCurrency_iso());
                textBalanceTo.setText(getWalletCurrencyList.get(position).getSymbol() + getWalletCurrencyList.get(position).getBalance());
                to_currency_id = getWalletCurrencyList.get(position).getCurrency_id();
                mPresenter.getExchange(from_currency_id, to_currency_id, 1);
                toSymbol = getWalletCurrencyList.get(position).getSymbol();
            }
        });
        RecyclerView recyclerView_balance_list = findViewById(R.id.recycle_balance);
        recyclerView_balance_list.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getExchangeRate(ExchangeParentModel exchangeParentModel) {
        result = exchangeParentModel.getResult();
        currencyResultFor1.setText(fromSymbol + exchangeParentModel.getAmount() + "=" + exchangeParentModel.getResult() + toSymbol);
    }


    private void convertFirstValuesExchange() {
        convert_first_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && s.length() < 10) {
                    float amount = Integer.parseInt(s.toString());
                    conver_second_valuel.setText((int) (result * amount) + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

//    private void convertSecondValue() {
//        conver_second_valuel.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() != 0) {
//                    float amount = Integer.parseInt(s.toString());
//                    convert_first_value.setText((int) (result * amount) + "");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }


}
