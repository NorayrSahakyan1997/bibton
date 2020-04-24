package am.bibton.view.activities.bibtonToBibtonActivity.transferWasDoneActivity;

import am.bibton.R;
import am.bibton.view.activities.homeActivity.HomeActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class TransferWasDoneActivity extends AppCompatActivity {
    private TextView userNameIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_was_done);
        setAnimation();
        init();
        receivedIntents();
    }

    private void init() {
        userNameIdText = findViewById(R.id.userNameIdText);
    }

    private void setAnimation() {
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_animation_splash);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        lottieAnimationView.playAnimation();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public void transferWasDone(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    @SuppressLint("SetTextI18n")
    private void receivedIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra("userId")) {
            int userId = intent.getIntExtra("userId", 0);
            userNameIdText.setText(getResources().getString(R.string.transactionWasCreatedSuccessfully) + " " + userId);
        } else {
            userNameIdText.setText(getResources().getString(R.string.transactionWasCreatedSuccessfully));
        }
    }
}
