package am.spaysapps.bibton.view.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.utils.Constants;


public class ProfitableExchangeFragment extends Fragment {
    private View main_view;
    private ConstraintLayout constraintLayout_next;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_view=inflater.inflate(R.layout.profitable_exchange_fragment,container,false);
        constraintLayout_next=(ConstraintLayout)main_view.findViewById(R.id.constraint_next_profitable);
       Constants.CURRENT_PAGE=3;
        loteAniamtion();
      //  animateButton();


        return main_view;
    }
//    public void animateButton(){
//        constraintLayout_next.animate()
//                .translationY(constraintLayout_next.getHeight())
//                .alpha(1.0f)
//                .setDuration(Constants.COINS_TRANSFERRING_DURABILITY)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        constraintLayout_next.setVisibility(View.VISIBLE);
//                    }
//                });
//    }

    public void loteAniamtion(){
        LottieAnimationView lottieAnimationView = main_view.findViewById(R.id.lottie_animation_profitable);
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
