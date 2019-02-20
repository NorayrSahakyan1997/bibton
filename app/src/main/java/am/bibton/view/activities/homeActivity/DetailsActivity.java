package am.bibton.view.activities.homeActivity;

import am.bibton.R;
import am.bibton.view.activities.BaseActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

public class DetailsActivity extends BaseActivity {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_details);
//        ConstraintLayout constraint_details = findViewById(R.id.constraint_details);
    }

    public void close_details_activity(View view) {
        Intent closeDetailsActivity = new Intent(this, HomeActivity.class);
        startActivity(closeDetailsActivity);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

//    public void maxmize_constraint(View view) {
//        ValueAnimator anim = ValueAnimator.ofInt(constraint_details.getMeasuredHeight(), 50);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int val = (Integer) valueAnimator.getAnimatedValue();
//                ViewGroup.LayoutParams layoutParams = constraint_details.getLayoutParams();
//                layoutParams.height = val;
//                constraint_details.setLayoutParams(layoutParams);
//            }
//        });
//        anim.setDuration(500);
//        anim.start();
//    }
}
