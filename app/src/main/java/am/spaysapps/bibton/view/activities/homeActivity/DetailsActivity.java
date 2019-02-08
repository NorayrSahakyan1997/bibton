package am.spaysapps.bibton.view.activities.homeActivity;

import am.spaysapps.bibton.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class DetailsActivity extends AppCompatActivity {
    private ConstraintLayout constraint_details;
    private ConstraintLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        constraint_details = findViewById(R.id.constraint_details);
    }

    public void close_details_activity(View view) {
        Intent closeDetailsActivity = new Intent(this, HomeActivity.class);
        startActivity(closeDetailsActivity);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public void maxmize_constraint(View view) {
        layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        constraint_details.setLayoutParams(layoutParams);
    }
}
