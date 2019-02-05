package am.spaysapps.bibton.view.activities.homeActivity.homeFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.ServiceAdapterHorizontal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceFragment extends Fragment {
    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.service_fragment_layout, container, false);
        RecyclerView recyclerView_service_horizontal = mainView.findViewById(R.id.recyclerView_service_horizontal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_service_horizontal.setLayoutManager(layoutManager);
        ServiceAdapterHorizontal serviceAdapterHorizontal = new ServiceAdapterHorizontal(context);
        recyclerView_service_horizontal.setAdapter(serviceAdapterHorizontal);


        return mainView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }
}
