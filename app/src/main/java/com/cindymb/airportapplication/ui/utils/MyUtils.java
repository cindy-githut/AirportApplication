package com.cindymb.airportapplication.ui.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyUtils {

    /*  Runtime permissions */
    public static final int PERMISSION_CODE = 100;
    public static boolean isFirstTimeLogin = false;

    public static String formatTime(String receivedDateTime) throws ParseException {
        if(TextUtils.isEmpty(receivedDateTime)) return "";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-dd-mm'T'HH:mm:ss", Locale.ENGLISH);
        Date date = simpleDateFormat1.parse(receivedDateTime);
        System.out.println(date);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        return simpleDateFormat2.format(date);
    }

    public static String sentenceCase(String myString){
        return TextUtils.isEmpty(myString) ? "" : myString.substring(0,1).toUpperCase() + myString.substring(1);
    }
}
