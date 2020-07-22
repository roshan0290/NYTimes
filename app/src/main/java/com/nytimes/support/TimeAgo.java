package com.nytimes.support;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.net.ParseException;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class TimeAgo {
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;

    public static String getDate(long time,String formate) {
        Date date = new Date(time*1000L); // *1000 is to convert seconds to milliseconds
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(formate); // the format of your date
        //  sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        return sdf.format(date);
    }
    @SuppressLint("NewApi")
    public static String getWeekDate() {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
        Calendar currentCal = Calendar.getInstance();
        //String currentdate=dateFormat.format(currentCal.getTime());
        currentCal.add(Calendar.DATE, 6);

            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, 7);
           // String day = sdf.format(calendar.getTime());


        String toDate=dateFormat.format(calendar.getTime());
        Timber.d("To Date : "+toDate );

        return toDate;
    }
    //formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy")
    public static String getFormatedDate(String date) {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }

    //formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy")
    public static String getDatebyDay(String date) {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("EEE, dd MMM");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }
    //formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy")
    public static String getDatebyDayMonth(String date) {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("dd MMM");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }







    @SuppressLint("NewApi")
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
        Calendar currentCal = Calendar.getInstance();
        Calendar calendar = new GregorianCalendar();

        String currentdate=dateFormat.format(calendar.getTime());
        //currentCal.add(Calendar.DATE, 7);
       // String toDate=dateFormat.format(currentCal.getTime());
        Timber.d("Current Date : "+currentdate );
        return currentdate;
    }



    public static String GetDateToMonthDay(String date)
    {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("MMM dd");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }

    public static String GetMonthDay(String date)
    {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("dd MMM");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }

    public static String GetDay(String date)
    {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("EEE");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }


    public static String GetDayNumeric(String date)
    {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("dd");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }


    public static String GetTime(String date)
    {
        String fTime="";

        try {

            java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            Date df = sm.parse(date);
            java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("HH:mm a");
            fTime = time.format(df);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fTime;
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String covertTimeToText(String dataDate) {
        String convTime = null;
        String prefix = "";
        String suffix = "Ago";
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            Date pasTime = null;
            try {
                dateFormat.setTimeZone(TimeZone.GMT_ZONE);
                pasTime = dateFormat.parse(dataDate);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
           // Date serverdate = new Date(String.valueOf(pasTime));

            Date nowTime = new Date();

            long dateDiff = nowTime.getTime() - pasTime.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                convTime = second + " Seconds " + suffix;
            } else if (minute < 60) {
                convTime = minute + " Minutes " + suffix;
            } else if (hour < 24) {
                convTime = hour + " Hours " + suffix;
            } else if (day >= 7) {
                if (day > 360) {
                    convTime = (day / 360) + " Years " + suffix;
                } else if (day > 30) {
                    convTime = (day / 30) + " Months " + suffix;
                } else {

                    if((day / 7)==1)
                    {
                        convTime = (day / 7) + " Week " + suffix;

                    }
                    else
                    {
                        convTime = (day / 7) + " Weeks " + suffix;

                    }
                }
            } else if (day < 7) {
                convTime = day + " Days " + suffix;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ConvTimeE", e.getMessage());
        }
        return convTime;
    }
}