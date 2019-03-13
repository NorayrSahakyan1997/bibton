package am.bibton.view.activities.homeActivity.homeFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.bibton.R;
import am.bibton.view.activities.BaseFragment;
import am.bibton.view.activities.profileActivity.UserProfileActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MoreFragment extends BaseFragment {
    private Context context;
    private ConstraintLayout goToProfilePage;
    private View mainView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.more_fragment_layout, container, false);
        init();
        goToProfilePage();
        return mainView;

    }

    private void init() {
        goToProfilePage = mainView.findViewById(R.id.goToProfilePageConstraint);

    }

    private void goToProfilePage() {
        goToProfilePage.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserProfileActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
