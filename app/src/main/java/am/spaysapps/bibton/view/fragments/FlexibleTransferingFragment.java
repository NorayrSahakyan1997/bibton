package am.spaysapps.bibton.view.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import am.spaysapps.bibton.utils.Constants;


import com.airbnb.lottie.LottieAnimationView;

import am.spaysapps.bibton.R;

public class FlexibleTransferingFragment extends Fragment {
    private View main_view;
    private Handler handler;
    private ImageButton button_skip;
    private ConstraintLayout constraintLayout_next;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        main_view = inflater.inflate(R.layout.flexible_transfer_fragment, container, false);
        Constants.CURRENT_PAGE=2;
        handler= new Handler();
        button_skip=main_view.findViewById(R.id.skip_button);
        constraintLayout_next=(ConstraintLayout)main_view.findViewById(R.id.constraint_next_flexible);
        button_skip.setEnabled(true);
        ableSkipButton();
        loteAniamtion();
        //animateButton();
        return main_view;

    }
//    public void animateButton(){
//        constraintLayout_next.animate()
//                .translationY(constraintLayout_next.getHeight())
//                .alpha(1.0f)
//                .setDuration(300)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        constraintLayout_next.setVisibility(View.VISIBLE);
//                    }
//                });
//    }
    private void ableSkipButton(){
        handler.postDelayed(new Runnable() {
            public void run() {
                button_skip.setEnabled(true);

            }
        },Constants.COINS_TRANSFERRING_DURABILITY);
    }

    public void loteAniamtion(){
        LottieAnimationView lottieAnimationView = main_view.findViewById(R.id.lottie_animation_flexible);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                constraintLayout_next.setVisibility(View.VISIBLE);
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


}
