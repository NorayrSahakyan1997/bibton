package am.spaysapps.bibton.model.getTransactionList;

public class TransactionResponse {
    private int transaction_number;
    private String text;
    private String image;
    private String created_at;
    private float total_amount;

    public String getCreated_at() {

        return created_at;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public int getTransaction_number() {
        return transaction_number;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }
}
