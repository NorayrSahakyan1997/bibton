package am.bibton.model.transferMoneyModel;
import lombok.Setter;
@Setter
public class TransferMoneyModel {
    private int from_wallet_currency;
    private int to_currency;
    private float amount;
    private int fingerprint;
    private String unique_id;
    private String to_user_unique;
    private int passcode;
}
