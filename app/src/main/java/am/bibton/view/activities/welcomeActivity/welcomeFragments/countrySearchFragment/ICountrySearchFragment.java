package am.bibton.view.activities.welcomeActivity.welcomeFragments.countrySearchFragment;

import java.util.List;

import am.bibton.model.countryModel.CountryModel;
import am.bibton.view.activities.IBaseView;

public interface ICountrySearchFragment extends IBaseView {
    void showCountryList(List<CountryModel> list);

}
