package am.bibton.model.getTransactionList;

import java.util.List;

import lombok.Getter;

public class TransactionDateResponse {
    @Getter
    private String date;
    @Getter
    private List<TransactionResponse> list;


}
