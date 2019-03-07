package am.bibton.view.activities.addAccountDetailsActivity.writeCodeActivityForMoneyTranfer;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.model.transferMoneyModel.TransferMoneyModel;
import am.bibton.presenter.SendMoneyActivityPresenter;
import am.bibton.shared.utils.CheckActivenessOvalIcons;
import am.bibton.view.activities.BaseActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
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

    @Inject
    SendMoneyActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_pass_code);
        passCodeEditText = findViewById(R.id.passCodeEditText);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        init();


    }

    public void init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        checkActivenessOvalIcons = new CheckActivenessOvalIcons(getApplicationContext(), mainView);
        setTextChangeListener();
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
                            transferMoneyModel.setFrom_wallet_currency(153);
                            transferMoneyModel.to_currency(1);
                            transferMoneyModel.setAmount(750);
                            transferMoneyModel.setFingerprint(0);
                            transferMoneyModel.setTo_user_unique("+37491106116");
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

    @Override
    public void getMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
