package am.bibton.view.activities.homeActivity;

import am.bibton.R;
import am.bibton.adapters.NavigationMenuAdapter;
import am.bibton.shared.utils.ChangeColorsTabBar;
import am.bibton.shared.utils.ChangeFragments;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.homeActivity.homeFragments.BibtonCardFragment;
import am.bibton.view.activities.exchangeActivity.ExchangeActivity;
import am.bibton.view.activities.homeActivity.homeFragments.MoreFragment;
import am.bibton.view.activities.homeActivity.homeFragments.PaymentFragment;
import am.bibton.view.activities.homeActivity.homeFragments.homeFragment.HomeFragment;
import am.bibton.view.activities.homeActivity.homeFragments.statementFragment.StatementFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


public class HomeActivity extends BaseActivity {
    private DrawerLayout drawer_layout_home;
    private Fragment currentFragment;
    private ChangeFragments changeFragments;
    private View mainView;
    private Context context;
    private ImageView home_icon;
    private ImageView transfers_icon;
    private ImageView analytics_icon;
    private ImageView more_icon;
    private ImageView card_icon;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
        init();
        setFragment();
    }

    private void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        drawer_layout_home = findViewById(R.id.drawer_layout_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout_home, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationMenuAdapter navigationViewAdapter = new NavigationMenuAdapter(this);
        drawer_layout_home.addDrawerListener(toggle);
        RecyclerView recycler_view_navigation = findViewById(R.id.recycler_view_navigation);
        recycler_view_navigation.setLayoutManager(layoutManager);
        recycler_view_navigation.setAdapter(navigationViewAdapter);
        toggle.syncState();
        currentFragment = new HomeFragment();
        home_icon = findViewById(R.id.home_icon);
        transfers_icon = findViewById(R.id.transfers_icon);
        analytics_icon = findViewById(R.id.analytics_icon);
        more_icon = findViewById(R.id.more_icon);
        card_icon = findViewById(R.id.card_icon);
        changeFragments = new ChangeFragments(context, mainView, currentFragment);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        this.context = context;
        mainView = parent;
        return super.onCreateView(parent, name, context, attrs);
    }

    public void open_home_fragment(View view) {
        changeFragments.replaceHomeFragments(new HomeFragment(), true);
        ChangeColorsTabBar.changeColorsOfTabs(this, home_icon, analytics_icon, transfers_icon, card_icon, more_icon, 0);
    }

    public void open_analytics_fragment(View view) {
        changeFragments.replaceHomeFragments(new StatementFragment(), false);
        ChangeColorsTabBar.changeColorsOfTabs(this, home_icon, analytics_icon, transfers_icon, card_icon, more_icon, 1);
    }

    public void open_transfers_fragment(View view) {
        changeFragments.replaceHomeFragments(new PaymentFragment(), false);
        ChangeColorsTabBar.changeColorsOfTabs(this, home_icon, analytics_icon, transfers_icon, card_icon, more_icon, 2);
    }

    public void open_card_fragment(View view) {
        changeFragments.replaceHomeFragments(new BibtonCardFragment(), true);
        ChangeColorsTabBar.changeColorsOfTabs(this, home_icon, analytics_icon, transfers_icon, card_icon, more_icon, 3);
    }

    public void open_more_fragment(View view) {
        changeFragments.replaceHomeFragments(new MoreFragment(), false);
        ChangeColorsTabBar.changeColorsOfTabs(this, home_icon, analytics_icon, card_icon, transfers_icon, more_icon, 4);
    }

    private void setFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_home, currentFragment);
        fragmentTransaction.commit();
    }

    public void drawer(View view) {
        drawer_layout_home.openDrawer(GravityCompat.END);
    }

    @Override
    public void onBackPressed() {
    }

    public void open_exchange_fragment(View view) {
        Intent goToExchangeFragment = new Intent(this, ExchangeActivity.class);
        startActivity(goToExchangeFragment);
    }

    public void open_details_ectivity(View view) {
        Intent go_To_Details_Activity = new Intent(this, DetailsActivity.class);
        startActivity(go_To_Details_Activity);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }


}
