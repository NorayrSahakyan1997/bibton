package am.spaysapps.bibton.view.activities;

import am.spaysapps.bibton.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExchangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        pendingTransaction();
    }

    private void pendingTransaction() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

}
