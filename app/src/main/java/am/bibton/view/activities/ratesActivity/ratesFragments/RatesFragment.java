package am.bibton.view.activities.ratesActivity.ratesFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.bibton.R;
import am.bibton.adapters.RatesAdapter;
import am.bibton.view.activities.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RatesFragment extends BaseFragment {
    private View mainView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.rates_fragment_layout, container, false);
        init();
        return mainView;

    }
    public void init(){
        RecyclerView ratesRecycle = mainView.findViewById(R.id.recycler_view_rates_fragment);
        ratesRecycle.setLayoutManager(new LinearLayoutManager(context));
        RatesAdapter ratesAdapter = new RatesAdapter(context);
        ratesRecycle.setAdapter(ratesAdapter);
    }

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }
}
