package am.bibton.model.alertModel;

import lombok.Getter;
@Getter
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
}
