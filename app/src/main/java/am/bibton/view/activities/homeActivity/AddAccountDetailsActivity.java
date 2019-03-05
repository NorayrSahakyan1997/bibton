package am.bibton.view.activities.homeActivity;

import am.bibton.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddAccountDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }
}
