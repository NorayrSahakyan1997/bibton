package am.bibton.model.getTransactionList;

import lombok.Getter;
@Getter

public class TransactionResponse {
    private int transaction_number;
    private String text;
    private String image;
    private String created_at;
    private float total_amount;
    private int transfer_type;
    private int transaction_id;
}
