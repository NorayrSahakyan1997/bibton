package am.bibton.view.activities.homeActivity.homeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import am.bibton.R;
import am.bibton.view.activities.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PaymentFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payment_fragment_layout, container, false);
    }
}
