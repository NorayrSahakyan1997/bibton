package am.spaysapps.bibton.view.activities.homeActivity;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.NavigationViewAdapter;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer_layout_home;
    private ActionBarDrawerToggle toggle;
    private RecyclerView recycler_view_navigation;
    private NavigationViewAdapter navigationViewAdapter;
    private List<String> menu_names;
    private List<Drawable> menu_icons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setMenuNames();
        setMenuIcons();
        init();
        pendingTransaction();
    }

    private void init() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        navigationView = findViewById(R.id.navigation_view_home);
        drawer_layout_home = findViewById(R.id.drawer_layout_home);
        navigationView = findViewById(R.id.navigation_view_home);
        toggle = new ActionBarDrawerToggle(this, drawer_layout_home, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationViewAdapter = new NavigationViewAdapter(this, menu_names, menu_icons);
        drawer_layout_home.addDrawerListener(toggle);
        recycler_view_navigation = findViewById(R.id.recycler_view_navigation);
        recycler_view_navigation.setLayoutManager(layoutManager);
        recycler_view_navigation.setAdapter(navigationViewAdapter);
        toggle.syncState();
    }

    private void setMenuNames() {
        menu_names = new ArrayList<>();
        menu_names = Arrays.asList(getResources().getStringArray(R.array.menu_names_array));
    }

    private void setMenuIcons() {
        menu_icons = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            menu_icons.add(getResources().getDrawable(R.drawable.summary_icons));
        }

    }

    public void drawer(View view) {
        drawer_layout_home.openDrawer(GravityCompat.END);
    }

    private void pendingTransaction() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }
}
