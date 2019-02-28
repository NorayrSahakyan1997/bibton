package am.bibton.shared.utils;
import java.util.ArrayList;
import java.util.List;
import am.bibton.model.getTransactionList.TransactionResponse;
import am.bibton.model.rateModel.RateResponse;

public final class Constants {
    private Constants() {
    }
    public static boolean IS_REGISTERED;
    public static boolean IS_WALLET=false;

    public static int CURRENCY_ID_FIRST;
    public static int CURRENCY_ID_SECOND=0;
    public static int CURRENCY_SUM=0;

    public static String TOKEN  ;
    public static String COUNTRY_SHORT_NAME;
    public static String COUNTRY_CODE;
    public static String UNIQUE_ID;
    public static String SYMBOL="";

    public static List<Integer> FROM_CURRENCY= new ArrayList<>();
    public static List<Integer> TO_CURRENCY= new ArrayList<>();
    public static List<Integer> FromCurrencyConvert= new ArrayList<>();
    public static List<TransactionResponse> DATELIST= new ArrayList<>();
    public static List<RateResponse> RATELIST= new ArrayList<>();



}
