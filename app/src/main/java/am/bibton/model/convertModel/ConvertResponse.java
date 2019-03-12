package am.bibton.model.convertModel;

import lombok.Getter;

public class ConvertResponse {
    @Getter
    private int compare_id;
    @Getter
    private int currency_id;
    @Getter
    private String to_iso;
    @Getter
    private String to_icon;
    @Getter
    private String to_name;
    @Getter
    private String to_symbol;
    @Getter
    private String to_numeric_code;
    @Getter
    private float rate;



}
