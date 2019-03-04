package am.bibton.view.activities.homeActivity;

import am.bibton.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

public class BibtonToBibtonActivity extends AppCompatActivity {
    private ConstraintLayout goToAddAccountDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibton_to_bibton);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        init();
        goToAddAccountDetails();
    }

    private void init() {
        goToAddAccountDetails = findViewById(R.id.goToAddAccountDetails);
    }

    private void goToAddAccountDetails() {
        goToAddAccountDetails.setOnClickListener(v -> {
            Intent goToAddAccountDetails = new Intent(this, AddAccountDetailsActivity.class);
            startActivity(goToAddAccountDetails);
        });
    }
}
