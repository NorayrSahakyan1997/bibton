package am.spaysapps.bibton.shared.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import am.spaysapps.bibton.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class ChangeFragments {
    private Context context;
    private Fragment currentFragment;
    private View view;
    private ImageView home_icon;
    private ImageView payment_icon;
    private ImageView statement_icon;
    private ImageView more_icon;
    private ImageView card_icon;

    public ChangeFragments(Context context, View view, Fragment currentFragment) {
        this.context = context;
        this.view = view;
        this.currentFragment = currentFragment;
      //  init();

    }
    private void init(){
        home_icon=view.findViewById(R.id.home_icon);
        payment_icon=view.findViewById(R.id.payment_icon);
        statement_icon=view.findViewById(R.id.statement_icon);
        more_icon=view.findViewById(R.id.more_icon);
        card_icon=view.findViewById(R.id.card_icon);
    }

    public void replaceFragment(Fragment fragment, boolean backAnim) {
        FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();

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

//    public void replaceFragmentWithId(Fragment fragment,boolean backAnim,int id){
//        replaceFragment(fragment,backAnim);
//        if(id==1){
//            home_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.pass_code_active));
//            payment_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//            statement_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//            more_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//            card_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//
//
//
//        }
//        if(id==2){
//            home_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//            payment_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.pass_code_active));
//            statement_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//            more_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//            card_icon.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorBlack));
//
//
//
//        }
//    }
}
