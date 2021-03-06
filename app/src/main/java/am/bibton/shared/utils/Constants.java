package am.bibton.shared.utils;

import android.support.v4.app.INotificationSideChannel;

import java.util.ArrayList;
import java.util.List;

import am.bibton.model.getTransactionList.TransactionResponse;
import am.bibton.model.rateModel.RateResponse;

public final class Constants {
    private Constants() {
    }

    public final static String ALERT_FIRST_ISO = "alertFirstIso";
    public final static String ALERT_FIRST_ICON = "alertFirstIcon";
    public final static String ALERT_SECOND_ISO = "alertSecondIso";


    public static boolean IS_REGISTERED;
    public static boolean IS_WALLET = false;
    public static boolean SELECTSECONDALERT = false;


    public static int CURRENCY_ID_FIRST;
    public static int CURRENCY_ID_SECOND = 0;
    public static int CURRENCY_SUM = 0;
    public static int FROM_AIERT_ID = 0;
    public static int TO_ALERT_ID = 0;
    public static Integer FROM_ALERT_ID_TRANSFER;
    public static Integer FROM_CURRENCY_POSITION;
    public static int FROM_CURRENCY_LONG_ID;

    public static String TOKEN = "Token";
    public static String COUNTRY_SHORT_NAME;
    public static String COUNTRY_CODE;
    public static String UNIQUE_ID;
    public static String SYMBOL = "";
    public static String FromAlertIso = "";
    public static String ToAlertIso = "";
    public static String FromAlertIcon = "";


    public static List<Integer> FROM_CURRENCY = new ArrayList<>();
    public static List<Integer> TO_CURRENCY = new ArrayList<>();
    public static List<Integer> FromCurrencyConvert = new ArrayList<>();
    public static List<TransactionResponse> DATELIST = new ArrayList<>();
    public static List<RateResponse> RATELIST = new ArrayList<>();


}
