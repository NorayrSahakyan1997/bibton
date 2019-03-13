package am.bibton.view.activities.profileActivity;

import am.bibton.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    public void backToMoreFragment(View view) {
        onBackPressed();
    }
    public void goToPersonalDitalisActivity(View view){
        Intent intent = new Intent(this, PersonalDetailsActivity.class);
        startActivity(intent);
    }

}
