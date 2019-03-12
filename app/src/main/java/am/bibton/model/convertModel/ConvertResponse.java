package am.bibton.model.convertModel;

import lombok.Getter;
@Getter
public class ConvertResponse {
    private int compare_id;
    private int currency_id;
    private String to_iso;
    private String to_icon;
    private String to_name;
    private String to_symbol;
    private String to_numeric_code;
    private float rate;
}
