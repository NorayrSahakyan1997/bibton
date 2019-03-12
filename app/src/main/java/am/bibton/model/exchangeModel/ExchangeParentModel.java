package am.bibton.model.exchangeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class ExchangeParentModel<T> {
    @Getter
    @Expose
    @SerializedName("currency")
    private CurrencyData currency;
    @Getter
    private float result;
    @Getter
    private int amount;


}
