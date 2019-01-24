package am.spaysapps.bibton.view.activities;


import android.os.Bundle;
import android.os.Handler;

import am.spaysapps.bibton.view.fragments.CountrySearchFragment;
import am.spaysapps.bibton.view.fragments.PhoneNumberFragment;
import am.spaysapps.bibton.view.fragments.SuperSystemFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.view.fragments.BibtonSignFragment;
import am.spaysapps.bibton.view.fragments.FlexibleTransferingFragment;
import am.spaysapps.bibton.view.fragments.ModenrUserFragment;
import am.spaysapps.bibton.view.fragments.ProfitableExchangeFragment;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler;
    private int waitTime = 2000;

    FrameLayout frameLayout_welcome;
    private Fragment currentFragment = null;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private int current_fragment_number;
    private FrameLayout frameLayout;
    private ImageButton skip_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
        setFragments();
    }

    public void init() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutWelcome);
        skip_button = (ImageButton) findViewById(R.id.skip_button);
        handler = new Handler();
    }


    public void setFragments() {
        fragmentManager = getSupportFragmentManager();
        currentFragment = new BibtonSignFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutWelcome, currentFragment);
        fragmentTransaction.commit();
    }


    private FragmentTransaction transaction;

    public void replaceFragment(Fragment fragment, boolean backAnim) {
        transaction = getSupportFragmentManager().beginTransaction();

        if (backAnim) {
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        } else {
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        transaction.remove(currentFragment);
        currentFragment = fragment;
        transaction.replace(R.id.frameLayoutWelcome, currentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goToProfitableExchangeFragment(View view) {
        replaceFragment(new ProfitableExchangeFragment(), false);
    }

    public void goToFlexibleExchangeFragment(View view) {
        replaceFragment(new FlexibleTransferingFragment(), false);
    }

    public void goToModerUserFragment(View view) {
        replaceFragment(new ModenrUserFragment(), false);
    }

    public void goToSuperSystemFragment(View view) {
        replaceFragment(new SuperSystemFragment(), false);
    }

    public void goToPhoneNumberFragment(View view) {
        replaceFragment(new PhoneNumberFragment(), false);
    }
    public void openCountrySearchFragment(View view){
        replaceFragment(new CountrySearchFragment(),true);
    }


    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        super.onBackPressed();
    }
}
