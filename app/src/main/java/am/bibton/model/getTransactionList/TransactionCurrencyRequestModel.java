package am.bibton.model.getTransactionList;

public class TransactionCurrencyRequestModel {

    private int from_currency_id;
    private int currency_id;

    public int getFrom_currency_id() {
        return from_currency_id;
    }

    public void setFrom_currency_id(int from_currency_id) {
        this.from_currency_id = from_currency_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}
