package am.spaysapps.bibton.view.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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


public class ModerUserFragment extends Fragment {
    private View mainVew;
    private ConstraintLayout constraintLayout_next;
    private Animation slideUpAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainVew = inflater.inflate(R.layout.modern_user_fragment, container, false);
        init();
        loteAniamtion();
        return mainVew;
    }

    private void init(){

        constraintLayout_next = (ConstraintLayout) mainVew.findViewById(R.id.constraint_next_modern_user);
        slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.down_to_up);


    }

    public void loteAniamtion() {
        LottieAnimationView lottieAnimationView = mainVew.findViewById(R.id.lottie_animation_modern_user);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                constraintLayout_next.startAnimation(slideUpAnimation);
                constraintLayout_next.setVisibility(View.VISIBLE);            }

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
