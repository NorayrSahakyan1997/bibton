package am.bibton.view.activities.ratesActivity;

import am.bibton.R;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.homeActivity.HomeActivity;
import am.bibton.view.activities.ratesActivity.alertFragment.AlertFragment;
import am.bibton.view.activities.ratesActivity.ratesFragments.convertFragment.ConverterFragment;
import am.bibton.view.activities.ratesActivity.ratesFragments.rateFragment.RatesFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressLint("Registered")
public class RatesActivity extends BaseActivity {
    private Fragment currentFragment;

    private ImageView imageRateFrame;
    private ImageView imageConvertFrame;
    private ImageView imageAlertFrame;

    private ImageView rateIcon;
    private ImageView convertIcon;
    private ImageView alertIcon;

    private TextView rateText;
    private TextView convertText;
    private TextView alertText;
    private ImageView goToHomeActivity;
    private String currentExtra = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        init();
        setFragments();
        goToHomeActivity();
    }

    public void init() {
        imageRateFrame = findViewById(R.id.rate_frame_image);
        imageConvertFrame = findViewById(R.id.convert_frame_image);
        imageAlertFrame = findViewById(R.id.alert_frame_image);

        rateIcon = findViewById(R.id.rate_Icon);
        convertIcon = findViewById(R.id.convert_Icon);
        alertIcon = findViewById(R.id.alert_Icon);
        rateIcon.getDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        rateText = findViewById(R.id.text_Rate);
        convertText = findViewById(R.id.text_Convert);
        alertText = findViewById(R.id.text_Alert);

        goToHomeActivity = findViewById(R.id.back_toHomeActivity);
        changeTabBarColors(0);
    }

    public void replaceFragment(Fragment fragment, boolean backAnim) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (backAnim) {
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        } else {
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        transaction.remove(currentFragment);
        currentFragment = fragment;
        transaction.replace(R.id.frame_layout_rates_activity, currentFragment);
        transaction.commit();
    }

    public void setFragments() {
        Intent intent = getIntent();

        if (intent.hasExtra("fragment")) {
            currentExtra = intent.getStringExtra("fragment");
        }
        if (currentExtra.matches("")) {
            currentFragment = new RatesFragment();
        }
        if (currentExtra.equals("convertFragment")) {
            currentFragment = new ConverterFragment();
            changeTabBarColors(1);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_rates_activity, currentFragment);
        fragmentTransaction.commit();
    }

    public void openRatesFragment(View view) {
        replaceFragment(new RatesFragment(), true);
        changeTabBarColors(0);
    }

    public void openAlertFragment(View view) {
        replaceFragment(new AlertFragment(), false);
        changeTabBarColors(2);
    }

    public void openConverterFragment(View view) {
        replaceFragment(new ConverterFragment(), false);
        changeTabBarColors(1);
    }

    public void changeTabBarColors(int currentPosition) {
        if (currentPosition == 0) {
            imageRateFrame.setBackground(getDrawable(R.drawable.rectangle_shape_rates_activity));
            rateText.setTextColor(getResources().getColor(R.color.white));
            rateIcon.getDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

            imageConvertFrame.setBackground(getResources().getDrawable(R.drawable.rectangle_shape_rates_activity_transparent));
            convertText.setTextColor(getResources().getColor(R.color.pass_code_active));
            convertIcon.getDrawable().setColorFilter(getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);

            imageAlertFrame.setBackground(getResources().getDrawable(R.drawable.rectangle_shape_rates_activity_transparent));
            alertText.setTextColor(getResources().getColor(R.color.pass_code_active));
            alertIcon.getDrawable().setColorFilter(getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);


        } else if (currentPosition == 1) {
            imageRateFrame.setBackground(getResources().getDrawable(R.drawable.rectangle_shape_rates_activity_transparent));
            rateText.setTextColor(getResources().getColor(R.color.pass_code_active));
            rateIcon.getDrawable().setColorFilter(getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);

            imageConvertFrame.setBackground(getDrawable(R.drawable.rectangle_shape_rates_activity));
            convertText.setTextColor(getResources().getColor(R.color.white));
            convertIcon.getDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

            imageAlertFrame.setBackground(getResources().getDrawable(R.drawable.rectangle_shape_rates_activity_transparent));
            alertText.setTextColor(getResources().getColor(R.color.pass_code_active));
            alertIcon.getDrawable().setColorFilter(getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);


        } else if (currentPosition == 2) {
            imageRateFrame.setBackground(getResources().getDrawable(R.drawable.rectangle_shape_rates_activity_transparent));
            rateText.setTextColor(getResources().getColor(R.color.pass_code_active));
            rateIcon.getDrawable().setColorFilter(getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);

            imageConvertFrame.setBackground(getResources().getDrawable(R.drawable.rectangle_shape_rates_activity_transparent));
            convertText.setTextColor(getResources().getColor(R.color.pass_code_active));
            convertIcon.getDrawable().setColorFilter(getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);

            imageAlertFrame.setBackground(getDrawable(R.drawable.rectangle_shape_rates_activity));
            alertText.setTextColor(getResources().getColor(R.color.white));
            alertIcon.getDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        }

    }

    public void goToHomeActivity() {
        goToHomeActivity.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        Intent returnHomeActivity = new Intent(this, HomeActivity.class);
        startActivity(returnHomeActivity);
    }
}
