package am.spaysapps.bibton.view.activities.homeActivity;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.shared.utils.CloseKeyBoard;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ExchangeActivity extends AppCompatActivity {
    private EditText convert_first_value;
    private EditText conver_second_valuel;
    private ScrollView scrollView;
    private ImageView constraintCircleShape;
    private ImageView changeIcon;
    private LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        pendingTransaction();
        init();
        setFocusibilityEditTexts();
        hideSoftKeyboard();
        rotateImageView();
        closeKeyBoard();
    }

    private void init() {
        convert_first_value = findViewById(R.id.convert_first_value);
        conver_second_valuel = findViewById(R.id.convert_second_value);
        scrollView = findViewById(R.id.scroll_view_exchange);
        constraintCircleShape = findViewById(R.id.constraint_circle_shape);
        changeIcon = findViewById(R.id.change_icon);
        parentLayout=findViewById(R.id.parent_layout_exchange);
    }

    private void closeKeyBoard(){
        parentLayout.setOnClickListener(v -> CloseKeyBoard.hideKeyboard(this));
    }
    private void setFocusibilityEditTexts() {
        convert_first_value.setFocusable(true);
        conver_second_valuel.setFocusable(true);
    }

    private void rotateImageView() {

        constraintCircleShape.setOnClickListener(v -> {
            RotateAnimation rotate = new RotateAnimation(
                    0, 180,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            rotate.setDuration(500);
            rotate.setRepeatCount(0);
            changeIcon.startAnimation(rotate);

        });

    }

    private void pendingTransaction() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void close_exchange_activity(View view) {
        Intent closeExchangeActivity = new Intent(this, HomeActivity.class);
        startActivity(closeExchangeActivity);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

    }

    public void scroll_activity(View view) {
        scrollView.scrollTo(20, 40);
    }


}
