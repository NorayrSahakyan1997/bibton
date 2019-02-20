package am.bibton.view.activities;

public interface IBaseView {
    void onError(String errorMessage);

    void showServerError();

    void showNetworkError();
}