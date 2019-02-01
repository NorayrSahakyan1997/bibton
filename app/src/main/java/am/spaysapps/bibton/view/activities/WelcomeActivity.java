package am.spaysapps.bibton.view.activities;


import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;

import am.spaysapps.bibton.view.fragments.BibtonSignFragment;
import am.spaysapps.bibton.view.fragments.FlexibleTransferringFragment;
import am.spaysapps.bibton.view.fragments.inputCodeFragment.InputPhoneCodeFragment;
import am.spaysapps.bibton.view.fragments.countrySearchFragment.CountrySearchFragment;
import am.spaysapps.bibton.view.fragments.phoneNumberFragment.PhoneNumberFragment;
import am.spaysapps.bibton.view.fragments.SuperSystemFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.view.fragments.ModernUserFragment;
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
    private PhoneNumberFragment phoneNumberFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //init();
        setFragments();
        //loteAnimation();
    }

    public void init() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutWelcome);
        // skip_button = (ImageButton) findViewById(R.id.skip_button);
        phoneNumberFragment = new PhoneNumberFragment();
        handler = new Handler();
    }


    public void setFragments() {
        fragmentManager = getSupportFragmentManager();
         //currentFragment = new BibtonSignFragment();
        currentFragment = new PhoneNumberFragment();
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
        transaction.commit();
    }


    public void loteAnimation() {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie_animation_splash);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                replaceFragment(new FlexibleTransferringFragment(), false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        lottieAnimationView.playAnimation();
    }



    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
