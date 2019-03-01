package am.bibton.shared.utils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import am.bibton.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class ChangeFragments {
    private Fragment currentFragment;
    private View view;
    private Context context;


    public ChangeFragments(Context context, View view, Fragment currentFragment) {
        this.context = context;
        this.view = view;
        this.currentFragment = currentFragment;

    }

    public void replaceWelcomeFragments(Fragment fragment, boolean backAnim) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();

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
    public void replaceHomeFragments(Fragment fragment, boolean backAnim) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();

        if (backAnim) {
            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        } else {
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        transaction.remove(currentFragment);
        currentFragment = fragment;
        transaction.replace(R.id.frame_layout_home, currentFragment);
        transaction.commit();

    }


}
