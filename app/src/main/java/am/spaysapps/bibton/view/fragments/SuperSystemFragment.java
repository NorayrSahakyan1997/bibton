package am.spaysapps.bibton.view.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;

import am.spaysapps.bibton.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class SuperSystemFragment extends Fragment {
    private View mainView;
    private ConstraintLayout constraintLayout_next;
    private Animation slideUpAnimation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.super_system_fragment,container,false);
        init();
        loteAniamtion();
        return mainView;
    }
    private void init(){
        constraintLayout_next = (ConstraintLayout) mainView.findViewById(R.id.constraint_super_system);
        slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.down_to_up);
    }

    public void loteAniamtion() {
        LottieAnimationView lottieAnimationView = mainView.findViewById(R.id.lottie_animation_super_system);
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
