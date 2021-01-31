package cl.mineduc.came.apoyo_mejora_continua.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {
    
    public static Long convertToNumber(String number) {
        Long result = null;
        
        result = number == null || number.isEmpty() ?  null : Long.parseLong(number);

        return result;
    }
    
    public static Date convertToDate(String date) {
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    } 

    public static Date convertToDateHour(String date) {
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static Date convertToDateMDY(String date) {
        Date result = null;
        try {
            result = new SimpleDateFormat("MM-dd-yyyy").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    

    public static Date convertToDateDMY(String date) {
        Date result = null;
        try {
            result = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
