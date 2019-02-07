package am.spaysapps.bibton.view.activities.homeActivity;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.BalanceHomeAdapter;
import am.spaysapps.bibton.adapters.NavigationViewAdapter;
import am.spaysapps.bibton.shared.utils.ChangeFragments;
import am.spaysapps.bibton.view.activities.ExchangeActivity;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.BibtonCardFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.MoreFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.PaymentFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.ServiceFragment;
import am.spaysapps.bibton.view.activities.homeActivity.homeFragments.StatementFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawer_layout_home;
    private ConstraintLayout wallet_layout;
    private Fragment currentFragment;
    private ChangeFragments changeFragments;
    private View mainView;
    private ImageView home_icon;
    private Context context;
    private List<String> country_names;
    private ConstraintLayout constraint_balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setCountryNamesList();

        init();
        setFragment();
        pendingTransaction();


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
        BalanceHomeAdapter balanceHomeAdapter = new BalanceHomeAdapter(this, country_names);
        RecyclerView recyclerView_balance_list = findViewById(R.id.recycle_balance);
        RecyclerView.LayoutManager recycler_Manager = new LinearLayoutManager(this);
        recyclerView_balance_list.setLayoutManager(recycler_Manager);
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
        wallet_layout = findViewById(R.id.constrait_wallet);
        currentFragment = new ServiceFragment();
        home_icon = findViewById(R.id.home_icon);
        ImageView payment_icon = findViewById(R.id.payment_icon);
        ImageView statement_icon = findViewById(R.id.statement_icon);
        ImageView more_icon = findViewById(R.id.more_icon);
        ImageView card_icon = findViewById(R.id.card_icon);
        constraint_balance = findViewById(R.id.constraint_balance);
        changeFragments = new ChangeFragments(context, mainView, currentFragment);

    }

    private void setCountryNamesList() {
        country_names = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            country_names.add("AMD");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        this.context = context;
        mainView = parent;
        return super.onCreateView(parent, name, context, attrs);
    }

    public void open_payment_fragment(View view) {
        changeFragments.replaceFragment(new PaymentFragment(), false);
        home_icon.getDrawable().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
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


    private void setWalletLayout() {
        Animation animation_down_to_up = AnimationUtils.loadAnimation(getApplication(), R.anim.down_to_up);
        wallet_layout.setAnimation(animation_down_to_up);
        wallet_layout.setVisibility(View.VISIBLE);
    }


    private void setFragment() {
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
        wallet_layout.setFocusable(true);
        wallet_layout.setVisibility(View.GONE);
    }

    public void close_balance_list(View view) {
        Animation animation_up_to_down = AnimationUtils.loadAnimation(getApplication(), R.anim.up_to_down);
        constraint_balance.setAnimation(animation_up_to_down);
        constraint_balance.setVisibility(View.GONE);
    }

    public void open_balance_list(View view) {
        Animation animation_down_to_up = AnimationUtils.loadAnimation(getApplication(), R.anim.down_to_up);
        constraint_balance.setAnimation(animation_down_to_up);
        constraint_balance.setFocusable(true);
        constraint_balance.setVisibility(View.VISIBLE);
    }

    public void open_exchange_fragment(View view) {
        Intent goToExchangeFragment = new Intent(this, ExchangeActivity.class);
        startActivity(goToExchangeFragment);
    }
}
