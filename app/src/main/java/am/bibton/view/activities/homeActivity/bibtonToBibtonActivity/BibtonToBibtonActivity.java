package am.bibton.view.activities.homeActivity.bibtonToBibtonActivity;
import am.bibton.R;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.addAccountDetailsActivity.AddAccountDetailsActivity;
import am.bibton.view.activities.homeActivity.HomeActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

public class BibtonToBibtonActivity extends BaseActivity implements IBibtonToBibtonActivity {
    private ConstraintLayout goToAddAccountDetails;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibton_to_bibton);
        init();
        goToAddAccountDetails();

    }

    private void init() {
        goToAddAccountDetails = findViewById(R.id.goToAddAccountDetails);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void goToAddAccountDetails() {
        goToAddAccountDetails.setOnClickListener(v -> {
            Intent goToAddAccountDetails = new Intent(this, AddAccountDetailsActivity.class);
            startActivity(goToAddAccountDetails);
        });
    }

}
