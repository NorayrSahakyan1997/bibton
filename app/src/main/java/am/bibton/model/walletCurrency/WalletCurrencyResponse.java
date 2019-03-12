package am.bibton.model.walletCurrency;

import lombok.Getter;
@Getter

public class WalletCurrencyResponse {
    private int currency_id;
    private String currency_icon;
    private String currency_iso;
    private String symbol;
    private String currency_name;
    private float balance;
    private int wallet_currency_id;


}
