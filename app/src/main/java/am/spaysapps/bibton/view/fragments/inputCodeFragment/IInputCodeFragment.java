package am.spaysapps.bibton.view.fragments.inputCodeFragment;


import am.spaysapps.bibton.view.activities.IBaseView;

public interface IInputCodeFragment extends IBaseView {
    void showPassCode(String passCode);
    void showUniqueId(String uniqueId);
    void isValidPassCode(boolean isValid);


}
