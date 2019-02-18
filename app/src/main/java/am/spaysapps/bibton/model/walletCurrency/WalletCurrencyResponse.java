package am.spaysapps.bibton.model.walletCurrency;

public class WalletCurrencyResponse {
    private int currency_id;
    private String currency_icon;
    private String currency_iso;
    private String symbol;
    private String currency_name;
    private float balance;

    public float getBalance() {
        return balance;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public String getCurrency_icon() {
        return currency_icon;
    }

    public String getCurrency_iso() {
        return currency_iso;
    }
}
