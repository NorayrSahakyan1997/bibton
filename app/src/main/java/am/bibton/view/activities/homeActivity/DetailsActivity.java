package am.bibton.view.activities.homeActivity;

import am.bibton.R;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.ratesActivity.RatesActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DetailsActivity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

    }

    public void close_details_activity(View view) {
        Intent closeDetailsActivity = new Intent(this, HomeActivity.class);
        startActivity(closeDetailsActivity);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    public void goToRatesActivity(View view) {
        Intent goToRatesActivity = new Intent(this, RatesActivity.class);
        startActivity(goToRatesActivity);
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
