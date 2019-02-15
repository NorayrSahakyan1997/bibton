package am.spaysapps.bibton.model.getTransactionList;

import java.util.List;

public class TransactionParentModel {
    private List<TransactionResponse> data;
    private int current_page;

    public int getCurrent_page() {
        return current_page;
    }

    public List<TransactionResponse> getData() {
        return data;
    }
}
