package am.bibton.model.rateModel;


import lombok.Getter;
@Getter
public class RateResponse {
    private int pair_id;
    private int from_id;
    private String from_iso;
    private String from_name;
    private int to_id;
    private String to_iso;
    private String to_name;
    private float to_rate;
}
