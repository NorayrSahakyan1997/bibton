package am.bibton.model.rateModel;


public class RateResponse {

    private int pair_id;
    private int from_id;
    private String from_iso;
    private String from_name;
    private int to_id;
    private String to_iso;
    private String to_name;
    private float to_rate;

    public int getPair_id() {
        return pair_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public String getFrom_iso() {
        return from_iso;
    }

    public String getFrom_name() {
        return from_name;
    }

    public int getTo_id() {
        return to_id;
    }

    public String getTo_iso() {
        return to_iso;
    }

    public String getTo_name() {
        return to_name;
    }

    public float getTo_rate() {
        return to_rate;
    }
}
