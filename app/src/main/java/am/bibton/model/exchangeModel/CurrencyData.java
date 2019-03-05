package am.bibton.model.exchangeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyData {
    @Expose
    @SerializedName("first_currency")
    public FirstCurrencyResponse first_currency;
    @Expose
    @SerializedName("second_currency")
    public SecondCurrencyResponse second_currency;

}
