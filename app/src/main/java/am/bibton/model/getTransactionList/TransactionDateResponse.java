package am.bibton.model.getTransactionList;

import java.util.List;

import lombok.Getter;
@Getter
public class TransactionDateResponse {
    private String date;
    private List<TransactionResponse> list;
}
