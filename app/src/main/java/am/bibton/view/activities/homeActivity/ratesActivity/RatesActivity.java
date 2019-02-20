package am.bibton.view.activities.homeActivity.ratesActivity;

import am.bibton.R;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.homeActivity.ratesActivity.ratesFragments.AlertsFragment;
import am.bibton.view.activities.homeActivity.ratesActivity.ratesFragments.ConverterFragment;
import am.bibton.view.activities.homeActivity.ratesActivity.ratesFragments.RatesFragment;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;


public class RatesActivity extends BaseActivity {
    private Fragment currentFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_rates);
        setFragments();

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
        currentFragment = new RatesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_rates_activity, currentFragment);
        fragmentTransaction.commit();
    }

    public void openRatesFragment(View view) {
        replaceFragment(new RatesFragment(), true);
    }

    public void openAlertFragment(View view) {
        replaceFragment(new AlertsFragment(), false);
    }

    public void openConverterFragment(View view) {
        replaceFragment(new ConverterFragment(), false);
    }


}
