package am.spaysapps.bibton.view.activities.homeActivity;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.NavigationViewAdapter;
import am.spaysapps.bibton.shared.utils.ChangeFragments;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.BibtonCardFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.MoreFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.PaymentFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.ServiceFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.StatementFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawer_layout_home;
    private ConstraintLayout wallet_layout;
    private Fragment currentFragment;
    private ChangeFragments changeFragments;
    private View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setFragment();
        pendingTransaction();
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



    }

    private void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        drawer_layout_home = findViewById(R.id.drawer_layout_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout_home, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationViewAdapter navigationViewAdapter = new NavigationViewAdapter(this);
        drawer_layout_home.addDrawerListener(toggle);
        RecyclerView recycler_view_navigation = findViewById(R.id.recycler_view_navigation);
        recycler_view_navigation.setLayoutManager(layoutManager);
        recycler_view_navigation.setAdapter(navigationViewAdapter);
        toggle.syncState();

        wallet_layout = findViewById(R.id.constrait_wallet);
        changeFragments= new ChangeFragments(this,mainView,currentFragment);


    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        mainView=parent;
        return super.onCreateView(parent, name, context, attrs);
    }

    public void open_payment_fragment(View view) {
        changeFragments.replaceFragment(new PaymentFragment(),false);
    }

    public void open_statement_fragment(View view) {
        changeFragments.replaceFragment(new StatementFragment(), false);
    }

    public void open_home_fragment(View view) {
        changeFragments.replaceFragment(new ServiceFragment(), true);
    }

    public void open_card_fragment(View view) {
        changeFragments.replaceFragment(new BibtonCardFragment(), true);
    }

    public void open_more_fragment(View view) {
        changeFragments.replaceFragment(new MoreFragment(), true);
    }


//    public void replaceFragment(Fragment fragment, boolean backAnim) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        if (backAnim) {
//            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
//        } else {
//            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
//        }
//        transaction.remove(currentFragment);
//        currentFragment = fragment;
//        transaction.replace(R.id.frame_layout_home, currentFragment);
//        transaction.commit();
//
//    }

    private void setWalletLayout() {
        Animation animation_down_to_up = AnimationUtils.loadAnimation(getApplication(), R.anim.down_to_up);
        wallet_layout.setAnimation(animation_down_to_up);
        wallet_layout.setVisibility(View.VISIBLE);
    }


    private void setFragment() {
        currentFragment = new ServiceFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_home, currentFragment);
        fragmentTransaction.commit();
    }


    public void drawer(View view) {
        drawer_layout_home.openDrawer(GravityCompat.END);
    }

    private void pendingTransaction() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    @Override
    public void onBackPressed() {

    }

    public void open_wallet_layout(View view) {
        setWalletLayout();

    }

    public void close_wallet_layout(View view) {
        Animation animation_up_to_down = AnimationUtils.loadAnimation(getApplication(), R.anim.up_to_down);
        wallet_layout.setAnimation(animation_up_to_down);
        wallet_layout.setVisibility(View.GONE);
    }
}
