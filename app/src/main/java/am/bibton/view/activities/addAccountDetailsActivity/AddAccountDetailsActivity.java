package am.bibton.view.activities.addAccountDetailsActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.CurrencyAdapter;
import am.bibton.model.userInfoForTranferModel.UserInfoForTransferModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.AddAccountDetailsPresenter;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.homeActivity.bibtonToBibtonActivity.BibtonToBibtonActivity;
import am.bibton.view.activities.ratesActivity.addRateActivity.AddRateActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;

public class AddAccountDetailsActivity extends BaseActivity implements IAddAccountDetails {
    private LinearLayout myCurrencyLinear;
    private LinearLayout receivedCurrencyLinear;
    private ConstraintLayout currencyConstraint;
    private ConstraintLayout constraintCurrencyAddAccountDetails;
    private TextView currencyFromNameAddAccount;
    private ImageView iconFromCurrency;
    private TextView currencyToNameAddAccount;
    private ImageView toCurrencyIcon;
    private float balanceAmount;
    private EditText amountAccountDetails;
    private Button buttonSend;
    private ConstraintLayout userInfoConstraint;
    private ImageView closeUserConstraint;
    private EditText idAddAccountDetails;
    private TextView mobileOrIdTextView;
    private int userID;
    private TextView userNameText;
    private TextView userIDTextView;

    @Inject
    AddAccountDetailsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();
        init();
        openMyCurrency();
        openReceivedCurrency();
        closeMyCurrencyLayout();
        getReceivedItem();
        checkBalanceAmount();
        findUser();
        closeUserConstraint();
        getUserIdFromEditText();
    }

    @SuppressLint("CutPasteId")
    private void init() {
        amountAccountDetails = findViewById(R.id.amountAccountDetails);
        idAddAccountDetails = findViewById(R.id.idAddAccountDetails);
        myCurrencyLinear = findViewById(R.id.myCurrencyLinear);
        receivedCurrencyLinear = findViewById(R.id.receivedCurrencyLinear);
        currencyConstraint = findViewById(R.id.constraintCurrencyAddAccountDetails);
        constraintCurrencyAddAccountDetails = findViewById(R.id.constraintCurrencyAddAccountDetails);
        currencyFromNameAddAccount = findViewById(R.id.currencyFromNameAddAccount);
        iconFromCurrency = findViewById(R.id.iconFromCurrency);
        currencyToNameAddAccount = findViewById(R.id.currencyToNameAddAccount);
        toCurrencyIcon = findViewById(R.id.toCurrencyIcon);
        buttonSend = findViewById(R.id.buttonSend);
        userInfoConstraint = findViewById(R.id.userInfoConstraint);
        closeUserConstraint = findViewById(R.id.closeUserConstraint);
        userNameText = findViewById(R.id.userNameText);
        mobileOrIdTextView = findViewById(R.id.mobileOrIdTextView);
        userIDTextView = findViewById(R.id.userIDTextView);
    }

    private void openMyCurrency() {
        myCurrencyLinear.setOnClickListener(v -> {
            currencyConstraint.setVisibility(View.VISIBLE);
            KeyboardUtils.hideSoftInput(this);
        });
    }

    private void openReceivedCurrency() {
        receivedCurrencyLinear.setOnClickListener(v -> {
            Intent goToSelectReceivedCurrency = new Intent(this, AddRateActivity.class);
            goToSelectReceivedCurrency.putExtra("addRateActivity", "BibtonToBibton");
            startActivity(goToSelectReceivedCurrency);
        })
        ;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList) {
        currencyFromNameAddAccount.setText(getWalletCurrencyList.get(0).getCurrency_iso());
        setIconToImageView(iconFromCurrency, getWalletCurrencyList.get(0).getCurrency_icon());
        CurrencyAdapter balanceHomeAdapter = new CurrencyAdapter(this, getWalletCurrencyList, position -> {
            currencyConstraint.setVisibility(View.GONE);
            currencyFromNameAddAccount.setText(getWalletCurrencyList.get(position).getCurrency_iso());
            balanceAmount = getWalletCurrencyList.get(position).getBalance();
            setIconToImageView(iconFromCurrency, getWalletCurrencyList.get(position).getCurrency_icon());
        });
        RecyclerView recyclerView_balance_list = findViewById(R.id.recycle_AddAccountDetails);
        recyclerView_balance_list.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
        checkBalanceAmount();
    }


    private void closeMyCurrencyLayout() {
        constraintCurrencyAddAccountDetails.setOnClickListener(v -> currencyConstraint.setVisibility(View.GONE));
    }

    public void setIconToImageView(ImageView imageView, String url) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }

    private void getReceivedItem() {
        Intent intent = getIntent();
        String currentExtra;
        String currencyFlag;
        if (intent.hasExtra("currencyId") && intent.hasExtra("currencyFlag")) {
            currentExtra = intent.getStringExtra("currencyId");
            currencyFlag = intent.getStringExtra("currencyFlag");
            currencyToNameAddAccount.setText(currentExtra);
            setIconToImageView(toCurrencyIcon, currencyFlag);
        }
    }

    private void checkBalanceAmount() {
        amountAccountDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() != 0 && s.length()<15) {
                    float amount = Integer.parseInt(s.toString());

                    if (amount != 0 && amount > balanceAmount) {
                        amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_edittext_wrong));
                    } else {
                        amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_phone_number_edittext));
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getUserIdFromEditText() {
        idAddAccountDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    userID = Integer.parseInt(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BibtonToBibtonActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);

    }

    private void findUser() {
        buttonSend.setOnClickListener(v -> {
            mPresenter.getUserInfo(userID);

        });
    }

    private void closeUserConstraint() {
        closeUserConstraint.setOnClickListener(v -> {
            idAddAccountDetails.setVisibility(View.VISIBLE);
            userInfoConstraint.setVisibility(View.GONE);
            mobileOrIdTextView.setVisibility(View.VISIBLE);
            buttonSend.setText(getResources().getString(R.string.findUser));
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.stopSubscriptions();
        super.onDestroy();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getUserInfo(UserInfoForTransferModel userInfoForTransferModel, boolean isUser) {
        if (isUser) {
            idAddAccountDetails.setVisibility(View.GONE);
            userInfoConstraint.setVisibility(View.VISIBLE);
            mobileOrIdTextView.setVisibility(View.GONE);
            buttonSend.setText(getResources().getString(R.string.submit));
            userNameText.setText(userInfoForTransferModel.getName() + " " + userInfoForTransferModel.getSurname());
            idAddAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_phone_number_edittext));
            userIDTextView.setText("Id "+userInfoForTransferModel.getWallet_id() + "");

        } else {
            idAddAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_edittext_wrong));
        }

    }

}
