package am.bibton.view.activities.profileActivity;
import am.bibton.R;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.ChangePhoneNumberActivity;
import am.bibton.view.activities.profileActivity.editPersonalInfoActivities.EditCountryActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PersonalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
    }

    public void goToEditCountryActivity(View view) {
        Intent intent = new Intent(this, EditCountryActivity.class);
        startActivity(intent);
    }

    public void backToProfileActivity(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void changePhoneNumberActivity(View view) {
        Intent intent = new Intent(this, ChangePhoneNumberActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
