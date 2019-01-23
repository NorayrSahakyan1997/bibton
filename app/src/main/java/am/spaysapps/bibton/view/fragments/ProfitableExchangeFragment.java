package am.spaysapps.bibton.view.fragments;

import android.animation.Animator;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.utils.Constants;


public class ProfitableExchangeFragment extends Fragment {
    private View main_view;
    private ConstraintLayout constraintLayout_next;
    private Animation slideUpAnimation;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_view = inflater.inflate(R.layout.profitable_exchange_fragment, container, false);
        constraintLayout_next = (ConstraintLayout) main_view.findViewById(R.id.constraint_next_profitable);
        slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.down_to_up);

        //Constants.CURRENT_PAGE = 3;
        loteAniamtion();


        return main_view;
    }


    public void loteAniamtion() {
        LottieAnimationView lottieAnimationView = main_view.findViewById(R.id.lottie_animation_profitable);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                constraintLayout_next.startAnimation(slideUpAnimation);
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
