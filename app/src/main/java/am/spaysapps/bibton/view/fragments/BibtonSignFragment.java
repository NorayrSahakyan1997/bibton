package am.spaysapps.bibton.view.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.utils.Constants;

public class BibtonSignFragment extends Fragment {
    private View main_view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        main_view = inflater.inflate(R.layout.bibton_sign_fragment, container, false);
      //  Constants.CURRENT_PAGE=1;
        return main_view;

    }
}
