package am.bibton.model.currencyModel;

public class CurrencyResponse {
    private String iso;
    private String name;
    private String flag;
    private String symbol;
    private String numeric_code;
    private int currency_id;

    public int getCurrency_id() {
        return currency_id;
    }

    public String getIso() {
        return iso;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getNumeric_code() {
        return numeric_code;
    }
}
