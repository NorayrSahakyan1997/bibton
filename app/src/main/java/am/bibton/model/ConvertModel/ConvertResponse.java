package am.bibton.model.ConvertModel;

public class ConvertResponse {
    private int compare_id;
    private int currency_id;
    private String to_iso;
    private String to_icon;
    private String to_name;
    private String to_symbol;
    private String to_numeric_code;
    private float rate;

    public int getCompare_id() {
        return compare_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public String getTo_iso() {
        return to_iso;
    }

    public String getTo_icon() {
        return to_icon;
    }

    public String getTo_name() {
        return to_name;
    }

    public String getTo_symbol() {
        return to_symbol;
    }

    public String getTo_numeric_code() {
        return to_numeric_code;
    }

    public float getRate() {
        return rate;
    }
}
