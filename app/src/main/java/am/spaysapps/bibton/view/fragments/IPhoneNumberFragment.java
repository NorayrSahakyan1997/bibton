package am.spaysapps.bibton.view.fragments;

import java.util.List;

import am.spaysapps.bibton.model.countryModel.CountryModel;
import am.spaysapps.bibton.view.activities.IBaseView;

public interface IPhoneNumberFragment extends IBaseView {
    void showBalanceList(List<CountryModel> list);
}
