package am.bibton.model.rateModel;


import lombok.Getter;

public class RateResponse {
    @Getter
    private int pair_id;
    @Getter
    private int from_id;
    @Getter
    private String from_iso;
    @Getter
    private String from_name;
    @Getter
    private int to_id;
    @Getter
    private String to_iso;
    @Getter
    private String to_name;
    @Getter
    private float to_rate;


}
