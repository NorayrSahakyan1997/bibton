package am.spaysapps.bibton.view.activities;



import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;


import am.spaysapps.bibton.R;
import am.spaysapps.bibton.utils.Constants;
import am.spaysapps.bibton.view.fragments.BibtonSignFragment;
import am.spaysapps.bibton.view.fragments.FlexibleTransferingFragment;
import am.spaysapps.bibton.view.fragments.ModerUserFragment;
import am.spaysapps.bibton.view.fragments.ProfitableExchangeFragment;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler;
    private int waitTime = 2000;

    // @BindView(R.id.frame_layout_welcome)
    FrameLayout frameLayout_welcome;
    private Fragment currentFragment = null;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private int current_fragment_number;
    private FrameLayout frameLayout;
    private ImageButton skip_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_welcome);

        init();
        setFragments();
       // skipp();
//        changeFragment();


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

      Constants.CURRENT_PAGE = 1;

    }

    private void ableSkipButton() {
        handler.postDelayed(new Runnable() {
            public void run() {
                skip_button.setEnabled(true);
            }
        }, Constants.COINS_TRANSFERRING_DURABILITY);
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
        transaction.replace(R.id.frameLayoutWelcome, currentFragment);
        transaction.commit();

    }
    public void goToProfitableExchangeFragment(View view){
        replaceFragment(new ProfitableExchangeFragment(),false);
    }

    public void click(View view) {

        replaceFragment(new FlexibleTransferingFragment(), false);


    }
    public void nextToModerUserFragment(View view){
        replaceFragment(new ModerUserFragment(),false);
//        Toast.makeText(getApplicationContext(),"Asad",Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onBackPressed() {
        if (Constants.CURRENT_PAGE == 2) {
            replaceFragment(new BibtonSignFragment(), true);

        }
        else if(Constants.CURRENT_PAGE==3){
            replaceFragment(new FlexibleTransferingFragment(),true);
        }
        else if(Constants.CURRENT_PAGE==4){
            replaceFragment(new ProfitableExchangeFragment(),true);

        }
    }
}
