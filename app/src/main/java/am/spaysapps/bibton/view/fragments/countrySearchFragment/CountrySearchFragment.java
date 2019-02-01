package am.spaysapps.bibton.view.fragments.countrySearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import javax.inject.Inject;
import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.CountryListAdapter;
import am.spaysapps.bibton.model.countryModel.CountryModel;
import am.spaysapps.bibton.presenter.CountrySearchPresenter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountrySearchFragment extends Fragment implements ICountrySearchFragment {
    private View mainView;
    private RecyclerView recycler_view_country_flags;

    @Inject
    CountrySearchPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.country_search_fragment, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCountryList();
        init();
        return mainView;
    }

    private void init() {
        recycler_view_country_flags = mainView.findViewById(R.id.recycler_view_country_flags);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_view_country_flags.setLayoutManager(layoutManager);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Bibton.getInstance().releaseAuthorizationComponent();
        mPresenter.stopSubscriptions();

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
    public void showCountryList(List<CountryModel> list) {
        CountryListAdapter countryListAdapter = new CountryListAdapter(getContext(), list);
        recycler_view_country_flags.setAdapter(countryListAdapter);
    }

}
