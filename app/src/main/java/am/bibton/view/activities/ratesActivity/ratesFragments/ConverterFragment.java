package am.bibton.view.activities.ratesActivity.ratesFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.bibton.R;
import am.bibton.view.activities.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConverterFragment extends BaseFragment {
    private RecyclerView recyclerViewConverts;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.convert_fragment_layout, container, false);
        recyclerViewConverts = mainView.findViewById(R.id.recycler_view_converts);
        recyclerViewConverts.setLayoutManager(new LinearLayoutManager(context));
        return mainView;
    }

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }
}
