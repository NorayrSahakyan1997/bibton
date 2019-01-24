package am.spaysapps.bibton.view.fragments.phoneNumberFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.model.countryModel.CountryModel;
import am.spaysapps.bibton.presenter.PhoneNumberPresenter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PhoneNumberFragment extends Fragment implements IPhoneNumberFragment {

    @Inject
    PhoneNumberPresenter mPresenter;
    private View mainView;
    private ArrayAdapter<String> adapter_phone_numbers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.phone_number_fragment, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getBalanceList();




        return mainView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Bibton.getInstance().releaseAuthorizationComponent();
        mPresenter.stopSubscriptions();
    }

    @Override
    public void showBalanceList(List<CountryModel> list) {

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
}
