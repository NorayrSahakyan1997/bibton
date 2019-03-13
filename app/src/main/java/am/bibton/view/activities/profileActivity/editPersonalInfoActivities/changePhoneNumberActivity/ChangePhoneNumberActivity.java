package am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.presenter.ChangePhoneNumberPresenter;
import am.bibton.shared.utils.KeyboardUtils;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.profileActivity.PersonalDetailsActivity;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.counrtySearchActivity.CountrySearchActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import javax.inject.Inject;

public class ChangePhoneNumberActivity extends BaseActivity implements IChangePhoneNumberActivity {
    private TextView countryShortPhoneCode;
    @Inject
    ChangePhoneNumberPresenter mPresenter;
    private View mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCountryCode();
        setupUI(mainView);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        mainView = parent;
        return super.onCreateView(parent, name, context, attrs);
    }

    private void init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        countryShortPhoneCode = findViewById(R.id.countryShortPhoneCodeButton);
        EditText passCodeEditText = findViewById(R.id.passCodeEditText);
        passCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
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

    @SuppressLint("ClickableViewAccessibility")
    private void setupUI(View view) {

        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                KeyboardUtils.hideSoftInput(this);
                return false;
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void backToPersonalDetailsActivity(View view) {
        Intent intent = new Intent(this, PersonalDetailsActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
