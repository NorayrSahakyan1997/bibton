package am.spaysapps.bibton.view.fragments.createPassCodeFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import javax.inject.Inject;
import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.presenter.CreatePassCodePresenter;
import am.spaysapps.bibton.shared.utils.ChangeFragments;
import am.spaysapps.bibton.shared.utils.CheckActivenessOvalIcons;
import am.spaysapps.bibton.shared.utils.Constants;
import am.spaysapps.bibton.view.fragments.YouAreDoneFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreatePassCodeFragment extends Fragment implements ICreatePassCode, View.OnClickListener {

    private View mainView;
    private Context context;
    private Fragment currentFragment;
    private CheckActivenessOvalIcons checkActivenessOvalButtons;
    private ChangeFragments changeFragments;
    private TextView forget_text_view;
    @Inject
    CreatePassCodePresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.create_pass_code_fragmen, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        currentFragment = this;
        mPresenter.onViewCreated(this);

        initViews();
        checkIfUserWasRegistered();
        openForgetPassWordDialog();
        return mainView;
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

    private void openForgetPassWordDialog() {
        forget_text_view.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(getResources().getString(R.string.warning))
                    .setMessage(getResources().getString(R.string.writeNewPassword))
                    .setCancelable(false)
                    .setNegativeButton(getResources().getString(R.string.ok),
                            (dialog, id) -> {
                                dialog.cancel();
                                forget_text_view.setVisibility(View.INVISIBLE);
                            });
            AlertDialog alert = builder.create();
            alert.show();
        });


    }

    @Override
    public void getUserToken(String token) {
        if (token != null) {
            changeFragments.replaceFragment(new YouAreDoneFragment(), false);
            Toast.makeText(context, token, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void showServerError() {

    }

    @Override
    public void showNetworkError() {

    }
}
