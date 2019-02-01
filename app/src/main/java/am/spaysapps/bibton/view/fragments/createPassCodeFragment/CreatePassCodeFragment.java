package am.spaysapps.bibton.view.fragments.createPassCodeFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.shared.utils.ChangeFragments;
import am.spaysapps.bibton.shared.utils.CheckActivenessOvalIcons;
import am.spaysapps.bibton.view.fragments.YouAreDoneFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreatePassCodeFragment extends Fragment implements View.OnClickListener {

    private View mainView;
    private Context context;
    private Fragment currentFragment;
    private CheckActivenessOvalIcons checkActivenessOvalButtons;
    private ChangeFragments changeFragments;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.create_pass_code_fragmen, container, false);
        currentFragment = this;
        initViews();
        return mainView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void initViews() {
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
            changeFragments.replaceFragment(new YouAreDoneFragment(), false);
        }

    }


}
