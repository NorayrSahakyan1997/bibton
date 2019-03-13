package am.bibton.view.activities.welcomeActivity.welcomeFragments.phoneNumberFragment;



import am.bibton.view.activities.IBaseView;

public interface IPhoneNumberFragment extends IBaseView {
    void showCountryCode(String countryCode);
    void checkPhoneNumberValidation(Boolean message);
    void showUniqueID(String uniqueId);
}
