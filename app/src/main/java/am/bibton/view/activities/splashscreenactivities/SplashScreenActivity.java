package am.bibton.view.activities.splashscreenactivities;

import android.content.Intent;
import android.os.Bundle;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.welcomeActivity.WelcomeActivity;

public class SplashScreenActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

}
