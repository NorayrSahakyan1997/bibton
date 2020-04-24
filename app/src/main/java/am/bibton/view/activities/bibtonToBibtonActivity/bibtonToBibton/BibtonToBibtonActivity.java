package am.bibton.view.activities.bibtonToBibtonActivity.bibtonToBibton;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.CurrencyAdapter;
import am.bibton.model.exchangeModel.ExchangeParentModel;
import am.bibton.model.userInfoForTranferModel.UserInfoForTransferModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.BibtonToBibtonActivityPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.bibtonToBibtonActivity.bibtonToBibtonList.BibtonToBibtonListActivity;
import am.bibton.view.activities.bibtonToBibtonActivity.sendMoneyActivityViaFingerprint.SendMoneyActivityViaFingerprint;
import am.bibton.view.activities.bibtonToBibtonActivity.writeCodeActivityForMoneyTranfer.WritePassCodeActivity;
import am.bibton.view.activities.ratesActivity.addRateActivity.AddRateActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
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

public class BibtonToBibtonActivity extends BaseActivity implements IBibtonToBibtonActivity {

    private LinearLayout myCurrencyLinear;
    private LinearLayout receivedCurrencyLinear;
    private ConstraintLayout currencyConstraint;
    private ConstraintLayout constraintCurrencyAddAccountDetails;
    private ConstraintLayout userInfoConstraint;

    private TextView currencyFromNameAddAccount;
    private TextView userNameText;
    private TextView userIDTextView;
    private TextView showConvertTwoCurrencies;
    private TextView currencyToNameAddAccount;
    private TextView mobileOrIdTextView;

    private ImageView iconFromCurrency;
    private ImageView toCurrencyIcon;
    private ImageView closeUserConstraint;
    private Button buttonSend;

    private EditText amountAccountDetails;
    private EditText idAddAccountDetails;

    private String userID;
    private String symbol;
    private String fromSymbol;

    private int fromCurrencyPosition;
    private int toCurrencyId;

    private float result;
    private float amount;


    @Inject
    BibtonToBibtonActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibton_to_bibton);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();
        init();
        receiveIntents();
        getReceivedItem();
        openMyCurrency();
        openReceivedCurrency();
        closeMyCurrencyLayout();
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
        showConvertTwoCurrencies = findViewById(R.id.showConvertTwoCurrencies);
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
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList) {
        checkFromCurrencyId(getWalletCurrencyList);
        checkBalanceAmount(getWalletCurrencyList);
        mPresenter.getExchange(getWalletCurrencyList.get(0).getCurrency_id(), toCurrencyId, 1);
        Constants.FROM_CURRENCY_LONG_ID = getWalletCurrencyList.get(0).getWallet_currency_id();
        CurrencyAdapter balanceHomeAdapter = new CurrencyAdapter(this, getWalletCurrencyList, position -> {
            currencyConstraint.setVisibility(View.GONE);
            Constants.FROM_CURRENCY_POSITION = position;
            Constants.FROM_ALERT_ID_TRANSFER = getWalletCurrencyList.get(position).getCurrency_id();
            currencyFromNameAddAccount.setText(getWalletCurrencyList.get(position).getCurrency_iso());
            setIconToImageView(iconFromCurrency, getWalletCurrencyList.get(position).getCurrency_icon());
            symbol = getWalletCurrencyList.get(position).getSymbol();
            checkFromCurrencyId(getWalletCurrencyList);
            checkBalanceAmount(getWalletCurrencyList);
            getEditTextAmount(getWalletCurrencyList.get(position).getBalance());
            Constants.FROM_CURRENCY_LONG_ID = getWalletCurrencyList.get(position).getWallet_currency_id();
            convertMoneyWhileEditing(amount);
            mPresenter.getExchange(getWalletCurrencyList.get(position).getCurrency_id(), toCurrencyId, 1);
        });
        RecyclerView recyclerView_balance_list = findViewById(R.id.recycle_AddAccountDetails);
        recyclerView_balance_list.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
    }


    public void checkFromCurrencyId(List<WalletCurrencyResponse> getWalletCurrencyList) {

        if (Constants.FROM_ALERT_ID_TRANSFER != null) {
            mPresenter.getExchange(Constants.FROM_ALERT_ID_TRANSFER, toCurrencyId, 1);
            symbol = getWalletCurrencyList.get(Constants.FROM_CURRENCY_POSITION).getSymbol();
        } else {
            mPresenter.getExchange(0, toCurrencyId, 1);
            symbol = getWalletCurrencyList.get(0).getSymbol();
        }
        if (Constants.FROM_CURRENCY_POSITION != null) {
            fromCurrencyPosition = Constants.FROM_CURRENCY_POSITION;
            currencyFromNameAddAccount.setText(getWalletCurrencyList.get(fromCurrencyPosition).getCurrency_iso());
            setIconToImageView(iconFromCurrency, getWalletCurrencyList.get(fromCurrencyPosition).getCurrency_icon());
        } else {
            fromCurrencyPosition = 0;
            currencyFromNameAddAccount.setText(getWalletCurrencyList.get(0).getCurrency_iso());
            setIconToImageView(iconFromCurrency, getWalletCurrencyList.get(0).getCurrency_icon());
        }
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
            currentExtra = intent.getStringExtra("currencyName");
            currencyFlag = intent.getStringExtra("currencyFlag");
            fromSymbol = intent.getStringExtra("symbol");
            toCurrencyId = intent.getIntExtra("currencyId", 0);
            currencyToNameAddAccount.setText(currentExtra);
            setIconToImageView(toCurrencyIcon, currencyFlag);
            mPresenter.getExchange(fromCurrencyPosition, toCurrencyId, 1);
        } else {
            mPresenter.getExchange(fromCurrencyPosition, fromCurrencyPosition, 1);
            fromSymbol = "";
        }
    }


    private void checkBalanceAmount(List<WalletCurrencyResponse> walletCurrencyResponses) {
        float balanceAmount;
        if (Constants.FROM_ALERT_ID_TRANSFER != null) {
            balanceAmount = walletCurrencyResponses.get(Constants.FROM_CURRENCY_POSITION).getBalance();
        } else {
            balanceAmount = walletCurrencyResponses.get(0).getBalance();
        }
        amountAccountDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_edittext_wrong));
                    buttonSend.setBackground(getResources().getDrawable(R.drawable.inactive_button_send));
                    buttonSend.setClickable(false);
                    buttonSend.setFocusable(false);
                    buttonSend.setEnabled(false);

                    convertMoneyWhileEditing(1);
                } else {
                    buttonSend.setClickable(true);
                    buttonSend.setFocusable(true);
                    buttonSend.setEnabled(true);

                }

                if (s.length() != 0 && s.length() < 15) {
                    amount = Float.parseFloat(s.toString());
                    convertMoneyWhileEditing(amount);

                    if (amount > balanceAmount) {
                        amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_edittext_wrong));
                        buttonSend.setBackground(getResources().getDrawable(R.drawable.inactive_button_send));
                        buttonSend.setClickable(false);
                        buttonSend.setFocusable(false);
                        buttonSend.setEnabled(false);
                    } else {
                        buttonSend.setBackground(getResources().getDrawable(R.drawable.button_skip));
                        amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_phone_number_edittext));
                        buttonSend.setClickable(true);
                        buttonSend.setFocusable(true);
                        buttonSend.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void convertMoneyWhileEditing(float amount) {
        showConvertTwoCurrencies.setText(amount + symbol + "=" + fromSymbol + " " + amount * result);
    }

    public void getEditTextAmount(float balanceAmount) {
        if (!amountAccountDetails.getText().toString().matches("")) {
            float amoount = Float.parseFloat(amountAccountDetails.getText().toString());
            if (amoount != 0 && amoount > balanceAmount) {
                amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_edittext_wrong));
                buttonSend.setBackground(getResources().getDrawable(R.drawable.inactive_button_send));
                buttonSend.setClickable(false);
                buttonSend.setFocusable(false);
                buttonSend.setEnabled(false);
            } else {
                buttonSend.setBackground(getResources().getDrawable(R.drawable.button_skip));
                amountAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_phone_number_edittext));
                buttonSend.setClickable(true);
                buttonSend.setFocusable(true);
                buttonSend.setEnabled(true);
           }
        }
    }

    public void getUserIdFromEditText() {
        idAddAccountDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    userID = s.toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void findUser() {
        buttonSend.setOnClickListener(v -> {
            if (buttonSend.getText().toString().equals(getResources().getString(R.string.findUser))) {
                mPresenter.getUserInfo(userID);
            } else {
                isFindgerPrintDetected();
            }

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
            userIDTextView.setText("Id " + userInfoForTransferModel.getWallet_id() + "");
        } else {
            idAddAccountDetails.setBackground(getResources().getDrawable(R.drawable.shape_edittext_wrong));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getExchangeRate(ExchangeParentModel exchangeParentModel) {
        result = exchangeParentModel.getResult();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, BibtonToBibtonListActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        Constants.FROM_ALERT_ID_TRANSFER = 0;
        Constants.FROM_CURRENCY_POSITION = 0;
    }

    private void isFindgerPrintDetected() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Get an instance of KeyguardManager and FingerprintManager//
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                float amount = Float.parseFloat(amountAccountDetails.getText().toString());
                Intent intent = new Intent(this, SendMoneyActivityViaFingerprint.class);
                intent.putExtra("fromCurrencyPosition", Constants.FROM_CURRENCY_LONG_ID);
                intent.putExtra("toCurrencyId", toCurrencyId);
                intent.putExtra("amount", amount);
                intent.putExtra("uniqueId", userID);
                startActivity(intent);
            } else {
                float amount = Float.parseFloat(amountAccountDetails.getText().toString());
                Intent intent = new Intent(this, WritePassCodeActivity.class);
                intent.putExtra("fromCurrencyPosition", Constants.FROM_CURRENCY_LONG_ID);
                intent.putExtra("toCurrencyId", toCurrencyId);
                intent.putExtra("amount", amount);
                intent.putExtra("uniqueId", userID);
                startActivity(intent);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void receiveIntents() {
        Intent intent = getIntent();
        int userUniqueId;
        String phoneNumber;
        String userName;
        String userSurName;
        if (intent.hasExtra("userUniqueId")) {
            idAddAccountDetails.setVisibility(View.GONE);
            userInfoConstraint.setVisibility(View.VISIBLE);
            mobileOrIdTextView.setVisibility(View.GONE);
            userUniqueId = intent.getIntExtra("userUniqueId", 0);
            phoneNumber = intent.getStringExtra("phoneNumber");
            userName = intent.getStringExtra("userName");
            userSurName = intent.getStringExtra("userSurname");

            if (userName != null) {
                userNameText.setText(userName.concat(" ").concat(userSurName));
            } else {
                userNameText.setText(phoneNumber);
            }
            userIDTextView.setText("ID :" + userUniqueId);
            buttonSend.setText(getResources().getString(R.string.submit));
        }

    }

}
