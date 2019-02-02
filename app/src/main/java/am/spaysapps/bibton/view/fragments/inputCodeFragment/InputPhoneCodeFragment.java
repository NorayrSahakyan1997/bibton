package am.spaysapps.bibton.view.fragments.inputCodeFragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import javax.inject.Inject;
import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.presenter.InputCodePresenter;
import am.spaysapps.bibton.shared.utils.ChangeFragments;
import am.spaysapps.bibton.shared.utils.Constants;
import am.spaysapps.bibton.view.fragments.createPassCodeFragment.CreatePassCodeFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class InputPhoneCodeFragment extends Fragment implements IInputCodeFragment, View.OnClickListener {

    private View main_View;
    @Inject
    InputCodePresenter mPresenter;
    private EditText mPasswordField;
    private ChangeFragments changeFragments;
    private Context context;
    private ImageView highLight_1;
    private ImageView highLight_2;
    private ImageView highLight_3;
    private ImageView highLight_4;
    private ImageView highLight_5;
    private ImageView highLight_6;

//    private TextView pass_code_1;
//    private TextView pass_code_2;
//    private TextView pass_code_3;
//    private TextView pass_code_4;
//    private TextView pass_code_5;
//    private TextView pass_code_6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_View = inflater.inflate(R.layout.input_phone_code_fragment, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        initViews();
        return main_View;

    }


    @Override
    public void showUniqueId(String uniqueId) {

    }

    @Override
    public void isValidPassCode(boolean isValid) {
        if (isValid) {
            changeFragments.replaceFragment(new CreatePassCodeFragment(), false);
        } else {
            Toast.makeText(context, "Please write a valid passcode", Toast.LENGTH_SHORT).show();
        }
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
        this.context = context;
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
        changeFragments = new ChangeFragments(context, main_View, this);

        highLight_1 = main_View.findViewById(R.id.highlight_1);
        highLight_2 = main_View.findViewById(R.id.highlight_2);
        highLight_3 = main_View.findViewById(R.id.highlight_3);
        highLight_4 = main_View.findViewById(R.id.highlight_4);
        highLight_5 = main_View.findViewById(R.id.highlight_5);
        highLight_6 = main_View.findViewById(R.id.highlight_6);

//        pass_code_1 = main_View.findViewById(R.id.pass_code_1);
//        pass_code_2 = main_View.findViewById(R.id.pass_code_2);
//        pass_code_3 = main_View.findViewById(R.id.pass_code_3);
//        pass_code_4 = main_View.findViewById(R.id.pass_code_4);
//        pass_code_5 = main_View.findViewById(R.id.pass_code_5);
//        pass_code_6 = main_View.findViewById(R.id.pass_code_6);
    }

    private void changeHiglighColorsTextView(int highlightState) {
        if (highlightState == 0) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));

        }
        if (highlightState == 1) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));

        }
        if (highlightState == 2) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));

        }
        if (highlightState == 3) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));

        }
        if (highlightState == 4) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));

        }
        if (highlightState == 5) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_inactive));

        }
        if (highlightState == 6) {
            highLight_1.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_2.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_3.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_4.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_5.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
            highLight_6.setBackground(getResources().getDrawable(R.drawable.shape_pass_code_oval_active));
//
        }
    }

    @Override
    public void onClick(View v) {
        Editable editable = mPasswordField.getText();
        int charCount = editable.length();
        if (v.getTag() != null && "number_button".equals(v.getTag())) {

            if (charCount < 6) {
                changeHiglighColorsTextView(charCount + 1);
                //setPassCodesCorrectPlace(charCount - 1, pass_COdes);
                mPasswordField.append(((TextView) v).getText());
                if (charCount == 5) {

                    String pass_Code = editable.toString();
                    mPresenter.checkUserPassCodeResponse(Constants.UNIQUE_ID, pass_Code);
                }
            }

            return;
        }
        switch (v.getId()) {
            case R.id.t9_key_backspace: {
                if (charCount != -1) {

                   // pass_COdes.remove(charCount);
                   // setPassCodesCorrectPlace(charCount, pass_COdes);
                    changeHiglighColorsTextView(charCount - 1);
                    editable.delete(charCount - 1, charCount);


                }
            }
            break;
        }
    }

//    private void setPassCodesCorrectPlace(int placeId, List<String> codes) {
//        if (placeId == 0) {
//            pass_code_1.setText(codes.get(0));
//        }
//        if (placeId == 1) {
//            pass_code_2.setText(codes.get(1));
//        }
//        if (placeId == 2) {
//            pass_code_3.setText(codes.get(2));
//        }
//        if (placeId == 3) {
//            pass_code_4.setText(codes.get(3));
//        }
//        if (placeId == 4) {
//            pass_code_5.setText(codes.get(4));
//        }
//        if (placeId == 5) {
//            pass_code_6.setText(codes.get(5));
//        }
//    }
}
