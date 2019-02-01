package am.spaysapps.bibton.view.fragments.inputCodeFragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.presenter.InputCodePresenter;
import am.spaysapps.bibton.view.fragments.createPassCodeFragment.CreatePassCodeFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class InputPhoneCodeFragment extends Fragment implements IInputCodeFragment, View.OnClickListener {

    private View main_View;
    @Inject
    InputCodePresenter mPresenter;
    private EditText mPasswordField;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_View = inflater.inflate(R.layout.input_phone_code_fragment, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.checkUserPassCodeResponse("25d98ee60998f9ed84b5", "123456");
        initViews();
        return main_View;

    }


    @Override
    public void showUniqueId(String uniqueId) {

    }

    @Override
    public void isValidPassCode(boolean isValid) {

    }

    @Override
    public void showPassCode(String passCode) {
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Bibton.getInstance().releaseAuthorizationComponent();
        mPresenter.stopSubscriptions();
    }

    private void initViews() {
        mPasswordField = main_View.findViewById(R.id.password_field);
        main_View.findViewById(R.id.t9_key_1).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_2).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_3).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_4).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_5).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_6).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_7).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_8).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_9).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_0).setOnClickListener(this);
        main_View.findViewById(R.id.t9_key_backspace).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Editable editable = mPasswordField.getText();
        int charCount = editable.length();
        if (v.getTag() != null && "number_button".equals(v.getTag())) {
            mPasswordField.append(((TextView) v).getText());
            if (charCount == 5) {

                Fragment newFragment = new CreatePassCodeFragment();
                FragmentTransaction transaction = ((FragmentActivity) main_View.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutWelcome, newFragment);
                transaction.commit();
            }
            return;
        }
        switch (v.getId()) {
            case R.id.t9_key_backspace: {
                if (charCount > 0) {
                    editable.delete(charCount - 1, charCount);
                }
            }
            break;
        }
    }
}
