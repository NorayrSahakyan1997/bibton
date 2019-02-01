package am.spaysapps.bibton.shared.utils;

import android.content.Context;
import android.view.View;

import am.spaysapps.bibton.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class ChangeFragments {
    private FragmentTransaction transaction;
    private Context context;
    private Fragment currentFragment;
    private View view;

    public ChangeFragments(Context context, View view, Fragment currentFragment) {
        this.context = context;
        this.view = view;
        this.currentFragment = currentFragment;
    }

    public  void replaceFragment(Fragment fragment, boolean backAnim) {
        transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

        if (backAnim) {
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        } else {
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        transaction.remove(currentFragment);
        currentFragment = fragment;
        transaction.replace(R.id.frameLayoutWelcome, currentFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
