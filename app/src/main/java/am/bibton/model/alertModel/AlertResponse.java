package am.bibton.model.alertModel;

public class AlertResponse {
    private int id;
    private int from_id;
    private String from_iso;
    private String from_name;
    private String from_icon;
    private String to_id;
    private String to_iso;
    private String to_name;
    private String to_icon;
    private float amount;
    private String created_at;
    private float current_rate;
    private int status;

    public int getId() {
        return id;
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

    public String getFrom_icon() {
        return from_icon;
    }

    public String getTo_id() {
        return to_id;
    }

    public String getTo_iso() {
        return to_iso;
    }

    public String getTo_name() {
        return to_name;
    }

    public String getTo_icon() {
        return to_icon;
    }

    public float getAmount() {
        return amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public float getCurrent_rate() {
        return current_rate;
    }

    public int getStatus() {
        return status;
    }
}
