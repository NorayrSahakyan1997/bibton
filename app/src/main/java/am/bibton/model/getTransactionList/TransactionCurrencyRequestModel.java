package am.bibton.model.getTransactionList;

import lombok.Getter;
import lombok.Setter;

public class TransactionCurrencyRequestModel {
    @Getter
    @Setter
    private int from_currency_id;
    @Getter
    @Setter
    private int currency_id;
}

