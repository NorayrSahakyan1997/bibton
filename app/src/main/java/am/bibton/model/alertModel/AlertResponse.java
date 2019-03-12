package am.bibton.model.alertModel;

import lombok.Getter;

public class AlertResponse {

    @Getter
    private int id;
    @Getter
    private int from_id;
    @Getter
    private String from_iso;
    @Getter
    private String from_name;
    @Getter
    private String from_icon;
    @Getter
    private String to_id;
    @Getter
    private String to_iso;
    @Getter
    private String to_name;
    @Getter
    private String to_icon;
    @Getter
    private float amount;
    @Getter
    private String created_at;
    @Getter
    private float current_rate;
    @Getter
    private int status;


}
