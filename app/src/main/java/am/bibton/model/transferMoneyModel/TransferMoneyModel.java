package am.bibton.model.transferMoneyModel;

public class TransferMoneyModel {
    private int from_wallet_currency;
    private int to_currency;
    private float amount;
    private int fingerprint;
    private String unique_id;
    private String to_user_unique;
    private int passcode;

    public void setFrom_wallet_currency(int from_wallet_currency) {
        this.from_wallet_currency = from_wallet_currency;
    }

    public void to_currency(int to_currency) {
        this.to_currency = to_currency;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setFingerprint(int fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public void setTo_user_unique(String to_user_unique) {
        this.to_user_unique = to_user_unique;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }
}
