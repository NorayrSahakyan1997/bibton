package am.bibton.view.activities.profileActivity.editPersonalInfoActivities.changePhoneNumberActivity.counrtySearchActivity;

import java.util.List;

import am.bibton.model.countryModel.CountryModel;
import am.bibton.view.activities.IBaseView;

public interface ICountrySearchActivity extends IBaseView {
    void showCountryList(List<CountryModel> list);

}
