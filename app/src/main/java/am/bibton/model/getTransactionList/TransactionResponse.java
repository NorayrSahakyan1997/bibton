package am.bibton.model.getTransactionList;

import lombok.Getter;

public class TransactionResponse {
    @Getter
    private int transaction_number;
    @Getter
    private String text;
    @Getter
    private String image;
    @Getter
    private String created_at;
    @Getter
    private float total_amount;
    @Getter
    private int transfer_type;
    @Getter
    private int transaction_id;


}
