package am.bibton.view.activities.welcomeActivity.welcomeFragments.inputCodeFragment;


import am.bibton.view.activities.IBaseView;

public interface IInputCodeFragment extends IBaseView {
    void showPassCode(String passCode);
    void showUniqueId(String uniqueId);
    void isValidPassCode(boolean isValid);


}
