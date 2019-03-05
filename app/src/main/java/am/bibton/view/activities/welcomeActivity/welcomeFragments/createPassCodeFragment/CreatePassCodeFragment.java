package am.bibton.view.activities.welcomeActivity.welcomeFragments.createPassCodeFragment;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import javax.inject.Inject;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.presenter.CreatePassCodePresenter;
import am.bibton.shared.helpers.SharedPreferencesHelper;
import am.bibton.shared.utils.ChangeFragments;
import am.bibton.shared.utils.CheckActivenessOvalIcons;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.BaseFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.forgetPassCodeFragment.ForgetPassCodeFragment;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.YouAreDoneFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreatePassCodeFragment extends BaseFragment implements ICreatePassCode, View.OnClickListener {

    private View mainView;
    private Context context;
    private Fragment currentFragment;
    private CheckActivenessOvalIcons checkActivenessOvalButtons;
    private ChangeFragments changeFragments;
    private TextView forget_text_view;
    @Inject
    CreatePassCodePresenter mPresenter;
    private SharedPreferencesHelper sharedPreferencesHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.create_pass_code_fragmen, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        currentFragment = this;
        mPresenter.onViewCreated(this);

        initViews();
        checkIfUserWasRegistered();
        goToForgetPassCodeFragment();

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setAnimation();
        super.onActivityCreated(savedInstanceState);
    }

    private void setAnimation() {
        LottieAnimationView lottieAnimationView = mainView.findViewById(R.id.lottie_animation_splash);
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
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stopSubscriptions();
    }

    private void initViews() {
        sharedPreferencesHelper = new SharedPreferencesHelper(context);
        forget_text_view = mainView.findViewById(R.id.forget_text_view);
        changeFragments = new ChangeFragments(context, mainView, currentFragment);
        mainView.findViewById(R.id.t9_key_1).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_2).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_3).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_4).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_5).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_6).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_7).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_8).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_9).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_0).setOnClickListener(this);
        mainView.findViewById(R.id.t9_key_backspace).setOnClickListener(this);
        checkActivenessOvalButtons = new CheckActivenessOvalIcons(context, mainView);
    }

    private void checkIfUserWasRegistered() {
        boolean is_Registered = Constants.IS_REGISTERED;
        if (is_Registered) {
            forget_text_view.setVisibility(View.VISIBLE);
        } else {
            forget_text_view.setVisibility(View.INVISIBLE);
        }
    }

    private int char_count = 0;

    @Override
    public void onClick(View v) {
        v.getId();

        if (v.getTag() != null && "number_button".equals(v.getTag()) && char_count != 6) {
            char_count++;

            goToYouAreDoneFragment(char_count);
            checkActivenessOvalButtons.checkActivenessOvalButtons(char_count);
            return;

        }
        switch (v.getId()) {
            case R.id.t9_key_backspace: {
                if (char_count != 0) {
                    char_count--;
                    checkActivenessOvalButtons.checkActivenessOvalButtons(char_count);
                }
            }
            break;
        }
    }

    private void goToYouAreDoneFragment(int passCodeCount) {
        if (passCodeCount == 6) {
            mPresenter.createAccountResponse(Constants.UNIQUE_ID, "123456", "123456");

        }

    }

    @Override
    public void getUserToken(String token) {
        if (token != null) {
            changeFragments.replaceWelcomeFragments(new YouAreDoneFragment(), false);
            sharedPreferencesHelper.setStringSharedPreferences(Constants.TOKEN, token);

        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToForgetPassCodeFragment() {
        forget_text_view.setOnClickListener(v -> changeFragments.replaceWelcomeFragments(new ForgetPassCodeFragment(), false));
    }
}
