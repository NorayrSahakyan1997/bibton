package am.bibton.model.transferMoneyModel;

import lombok.Setter;

public class TransferMoneyModel {
    @Setter
    private int from_wallet_currency;
    @Setter
    private int to_currency;
    @Setter
    private float amount;
    @Setter
    private int fingerprint;
    @Setter
    private String unique_id;
    @Setter
    private String to_user_unique;
    @Setter
    private int passcode;



}
