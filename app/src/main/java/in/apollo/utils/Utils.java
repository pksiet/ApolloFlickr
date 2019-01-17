package in.apollo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    public static final String DATE_FORMAT_TAKEN_ON = "yyyy-MM-dd'T'HH:mm:ssZ";//2019-01-16T19:11:23-08:00

    public static long getLongFromDate(String date){
        long time = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_TAKEN_ON);
        try {
            time = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }
}
