package am.bibton.model.walletCurrency;

import lombok.Getter;

public class WalletCurrencyResponse {
    @Getter
    private int currency_id;
    @Getter
    private String currency_icon;
    @Getter
    private String currency_iso;
    @Getter
    private String symbol;
    @Getter
    private String currency_name;
    @Getter
    private float balance;
    @Getter
    private int wallet_currency_id;


}
