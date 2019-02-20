package am.bibton.shared.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import am.bibton.R;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ChangeRateTabBars {
    private Context context;

    private ImageView imageRateFrame;
    private ImageView imageConvertFrame;
    private ImageView imageAlertFrame;

    private ImageView rateIcon;
    private ImageView convertIcon;
    private ImageView alertIcon;

    private TextView rateText;
    private TextView convertText;
    private TextView alertText;


    public ChangeRateTabBars(Context context,
                             ImageView imageRateFrame,
                             ImageView imageConvertFrame,
                             ImageView imageAlertFrame,
                             ImageView rateIcon,
                             ImageView convertIcon,
                             ImageView alertIcon,
                             TextView rateText,
                             TextView convertText,
                             TextView alertText) {
        this.context = context;

        this.rateIcon = rateIcon;
        this.convertIcon = convertIcon;
        this.alertIcon = alertIcon;

        this.imageRateFrame = imageRateFrame;
        this.imageConvertFrame = imageConvertFrame;
        this.imageAlertFrame = imageAlertFrame;

        this.rateText = rateText;
        this.convertText = convertText;
        this.alertText = alertText;
    }

    public void changeTabBarColors(int currentPosition) {
        if (currentPosition == 0) {
//            imageRateFrame.getDrawable().setColorFilter(context.getResources().getColor(R.color.pass_code_active), PorterDuff.Mode.SRC_ATOP);
            rateText.setTextColor(context.getResources().getColor(R.color.white));
            rateIcon.getDrawable().setColorFilter(context.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        }
    }
}
