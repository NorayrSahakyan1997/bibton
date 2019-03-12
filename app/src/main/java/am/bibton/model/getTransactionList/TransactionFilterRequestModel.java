package am.bibton.model.getTransactionList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionFilterRequestModel {
    private String start_date;
    private String end_date;
}
