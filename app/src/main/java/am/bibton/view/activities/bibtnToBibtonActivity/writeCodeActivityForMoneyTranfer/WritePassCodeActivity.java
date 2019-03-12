package am.bibton.view.activities.bibtnToBibtonActivity.writeCodeActivityForMoneyTranfer;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.model.transferMoneyModel.TransferMoneyModel;
import am.bibton.presenter.SendMoneyActivityPresenter;
import am.bibton.shared.utils.CheckActivenessOvalIcons;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.bibtnToBibtonActivity.transferWasDoneActivity.TransferWasDoneActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import javax.inject.Inject;

public class WritePassCodeActivity extends BaseActivity implements IWritePassCodeActivity {
    private EditText passCodeEditText;
    private CheckActivenessOvalIcons checkActivenessOvalIcons;
    private View mainView;
    private TransferMoneyModel transferMoneyModel;

    private int fromCurrencyPosition;
    private int toCurrencyId;
    private float amount;
    private String uniqueId;

    @Inject
    SendMoneyActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_pass_code);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        init();


    }

    public void init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        checkActivenessOvalIcons = new CheckActivenessOvalIcons(getApplicationContext(), mainView);
        passCodeEditText = findViewById(R.id.passCodeEditText);
        setTextChangeListener();
        getIntents();
        transferMoneyModel = new TransferMoneyModel();
    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        mainView = parent;
        return super.onCreateView(parent, name, context, attrs);
    }

    public void setTextChangeListener() {
        passCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() < 7) {
                    int length = s.length();
                    checkActivenessOvalIcons.checkActivenessOvalButtons(length);
                    if (s.length() != 0) {
                        int passCode = Integer.parseInt(s.toString());
                        if (s.length() == 6) {
                            transferMoneyModel.setFrom_wallet_currency(fromCurrencyPosition);
                            transferMoneyModel.setTo_currency(toCurrencyId);
                            transferMoneyModel.setAmount(amount);
                            transferMoneyModel.setFingerprint(0);
                            transferMoneyModel.setTo_user_unique(uniqueId);
                            transferMoneyModel.setPasscode(passCode);
                            mPresenter.sendMoney(transferMoneyModel);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void getIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra("fromCurrencyPosition")) {
            fromCurrencyPosition = intent.getIntExtra("fromCurrencyPosition", 0);
            toCurrencyId = intent.getIntExtra("toCurrencyId", 0);
            amount = intent.getFloatExtra("amount", 0);
            uniqueId = intent.getStringExtra("uniqueId");
            Log.d("N_TAG",fromCurrencyPosition+toCurrencyId+amount+uniqueId);

        }

    }

    @Override
    public void getMessage(boolean success) {
        if (success) {
            Intent intent = new Intent(this, TransferWasDoneActivity.class);
            intent.putExtra("userId", uniqueId);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
