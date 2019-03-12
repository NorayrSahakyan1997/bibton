package am.bibton.model.exchangeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
@Getter

public class ExchangeParentModel<T> {
    @Expose
    @SerializedName("currency")
    private CurrencyData currency;
    private float result;
    private int amount;
}
