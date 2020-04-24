package am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.presenter.ChangePhoneNumberPresenter;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.bibtonToBibtonActivity.sendMoneyActivityViaFingerprint.SendMoneyActivityViaFingerprint;
import am.bibton.view.activities.bibtonToBibtonActivity.writeCodeActivityForMoneyTranfer.WritePassCodeActivity;
import am.bibton.view.activities.profileActivity.PersonalDetailsActivity;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.counrtySearchActivity.CountrySearchActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

public class ChangePhoneNumberActivity extends BaseActivity implements IChangePhoneNumberActivity {

    @Inject
    ChangePhoneNumberPresenter mPresenter;

    private TextView countryShortPhoneCode;
    private View mainView;
    private Button receivePassCodeButton;
    private ImageView closeUserConstraintIcon;

    private LinearLayout passCodeLayout;
    private LinearLayout writePhoneNumberLayout;
    private LinearLayout parentLayoutChangePhone;
    private ConstraintLayout userPhoneNumberConstraint;

    private EditText inputReceivedCode;
    private EditText inputPhoneNumber;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCountryCode();
        init();
        receiveIntents();
        receivePassCode();
        closeUserPhoneConstraint();
        setTextChangeListenerToInputFields();
        closeKeyBoard();


    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        mainView = parent;
        return super.onCreateView(parent, name, context, attrs);
    }

    private void init() {
        writePhoneNumberLayout = findViewById(R.id.writePhoneNumberLayout);
        countryShortPhoneCode = findViewById(R.id.countryShortPhoneCodeButton);
        receivePassCodeButton = findViewById(R.id.receivePassCodeButton);
        passCodeLayout = findViewById(R.id.passCodeLayout);
        userPhoneNumberConstraint = findViewById(R.id.userPhoneNumberConstraint);
        closeUserConstraintIcon = findViewById(R.id.closeUserConstraintIcon);
        inputReceivedCode = findViewById(R.id.passCodeEditTextChangePhone);
        inputPhoneNumber = findViewById(R.id.phoneNumberInput);
        parentLayoutChangePhone = findViewById(R.id.parentLayoutChangePhone);

    }


    private void setTextChangeListenerToInputFields() {
        inputReceivedCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkActivenessOfPassCodeInput(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    receivePassCodeButton.setBackground(getResources().getDrawable(R.drawable.button_skip));
                    receivePassCodeButton.setEnabled(true);
                    receivePassCodeButton.setClickable(true);
                } else {
                    receivePassCodeButton.setBackground(getResources().getDrawable(R.drawable.inactive_button_send));
                    receivePassCodeButton.setEnabled(false);
                    receivePassCodeButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void closeKeyBoard() {
        parentLayoutChangePhone.setOnTouchListener((v, event) -> {
            KeyboardUtils.hideSoftInput(this);
            return false;
        });

    }


    @Override
    public void showCountryCode(String countryCode) {
        Intent intent = getIntent();
        if (intent.hasExtra("countryCode")) {
            String countryCodeReceived = intent.getStringExtra("countryCode");
            countryShortPhoneCode.setText(countryCodeReceived);
        } else {
            countryShortPhoneCode.setText(countryCode);
        }
    }

    public void goToChooseCountryActivity(View view) {
        Intent intent = new Intent(this, CountrySearchActivity.class);
        startActivity(intent);
    }


    public void backToPersonalDetailsActivity(View view) {
        Intent intent = new Intent(this, PersonalDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void receivePassCode() {


        receivePassCodeButton.setOnClickListener(v -> {
            if (receivePassCodeButton.isClickable() && receivePassCodeButton.getText().equals(getResources().getString(R.string.receiveCode))) {
                isFingerPrintDetected();
            }

        });


    }

    public void closeUserPhoneConstraint() {
        closeUserConstraintIcon.setOnClickListener(v -> {
            userPhoneNumberConstraint.setVisibility(View.GONE);
            passCodeLayout.setVisibility(View.GONE);
            receivePassCodeButton.setVisibility(View.VISIBLE);
            writePhoneNumberLayout.setVisibility(View.VISIBLE);
            receivePassCodeButton.setText(getResources().getString(R.string.receiveCode));
        });
    }


    private void isFingerPrintDetected() {
        String activity = "activityChangePhoneNumber";
        String activityname = "ChangePhoneNumber";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                Intent intent = new Intent(this, SendMoneyActivityViaFingerprint.class);
                intent.putExtra(activity, activityname);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, WritePassCodeActivity.class);
                intent.putExtra(activity, activityname);
                startActivity(intent);
            }
        }
    }

    private void receiveIntents() {
        Intent intent = getIntent();
        inputReceivedCode.setFocusable(true);
        if (intent.hasExtra("isSuccess")) {
            writePhoneNumberLayout.setVisibility(View.INVISIBLE);
            receivePassCodeButton.setVisibility(View.INVISIBLE);
            userPhoneNumberConstraint.setVisibility(View.VISIBLE);
            passCodeLayout.setVisibility(View.VISIBLE);
            inputReceivedCode.setEnabled(true);
            receivePassCodeButton.setText(getResources().getString(R.string.submit));

//            KeyboardUtils.showKeyboard(this);
            inputReceivedCode.requestFocus();
            Toast.makeText(this, "Change", Toast.LENGTH_SHORT).show();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    private void checkActivenessOfPassCodeInput(CharSequence s) {
        TextView textViewPassCode1 = findViewById(R.id.textViewPassCode1);
        TextView textViewPassCode2 = findViewById(R.id.textViewPassCode2);
        TextView textViewPassCode3 = findViewById(R.id.textViewPassCode3);
        TextView textViewPassCode4 = findViewById(R.id.textViewPassCode4);
        TextView textViewPassCode5 = findViewById(R.id.textViewPassCode5);
        TextView textViewPassCode6 = findViewById(R.id.textViewPassCode6);
        View highlighterl = findViewById(R.id.highlighter1);
        View highlighter2 = findViewById(R.id.highlighter2);
        View highlighter3 = findViewById(R.id.highlighter3);
        View highlighter4 = findViewById(R.id.highlighter4);
        View highlighter5 = findViewById(R.id.highlighter5);
        View highlighter6 = findViewById(R.id.highlighter6);

        TextView textView[] = new TextView[]{textViewPassCode1, textViewPassCode2, textViewPassCode3, textViewPassCode4, textViewPassCode5, textViewPassCode6};
        View highlighter[] = new View[]{highlighterl, highlighter2, highlighter3, highlighter4, highlighter5, highlighter6};

        for (int i = s.length(); i < textView.length; i++) {

            if (s.length() == 1 || s.length() == 0) {
                textView[0].setText(s);
            } else {
                textView[s.length() - 1].setText(s.subSequence(s.length() - 1, s.length()));
            }
            textView[i].setText("0");
            textView[i].setTextColor(getResources().getColor(R.color.grey));
            highlighter[i].setBackgroundColor(getResources().getColor(R.color.grey));

        }
        if (s.length() == 6) {
            textView[s.length() - 1].setText(s.subSequence(s.length() - 1, s.length()));
            inputReceivedCode.setEnabled(false);
            Toast.makeText(this, "PassWord has changed successfully", Toast.LENGTH_SHORT).show();
        }
        for (int i = 0; i < s.length(); i++) {
            textView[i].setTextColor(getResources().getColor(R.color.pass_code_active));
            highlighter[i].setBackgroundColor(getResources().getColor(R.color.pass_code_active));
        }
    }

}
