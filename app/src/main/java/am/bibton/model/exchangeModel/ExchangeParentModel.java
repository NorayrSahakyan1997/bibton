package am.bibton.model.exchangeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeParentModel<T> {
    @Expose
    @SerializedName("currency")
    private CurrencyData currency;

    private float result;
    private int amount;


    public float getResult() {
        return result;
    }
    public int getAmount() {
        return amount;
    }

    public CurrencyData getCurrency() {
        return currency;
    }
}
