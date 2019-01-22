package am.spaysapps.bibton.view.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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


public class ModerUserFragment extends Fragment {
    private View mainVew;
    private ConstraintLayout constraintLayout_next;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainVew = inflater.inflate(R.layout.modern_user_fragment, container, false);
        Constants.CURRENT_PAGE = 4;
        constraintLayout_next = (ConstraintLayout) mainVew.findViewById(R.id.constraint_next_modern_user);
        loteAniamtion();
       // animateButton();

        return mainVew;
    }

    public void animateButton() {
        constraintLayout_next.animate()
                .translationY(constraintLayout_next.getHeight())
                .alpha(1.0f)
                .setDuration(Constants.COINS_TRANSFERRING_DURABILITY)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        constraintLayout_next.setVisibility(View.VISIBLE);
                    }
                });
    }

    public void loteAniamtion() {
        LottieAnimationView lottieAnimationView = mainVew.findViewById(R.id.lottie_animation_modern_user);
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
