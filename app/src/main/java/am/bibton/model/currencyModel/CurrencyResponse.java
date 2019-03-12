package am.bibton.model.currencyModel;

import lombok.Getter;

public class CurrencyResponse {
    @Getter
    private String iso;
    @Getter
    private String name;
    @Getter
    private String flag;
    @Getter
    private String symbol;
    @Getter
    private String numeric_code;
    @Getter
    private int currency_id;


}
