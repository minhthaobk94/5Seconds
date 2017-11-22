package com.thaontm.project.Seconds.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public String convertStringToDate(Date indate)
    {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        try{
            dateString = sdfr.format( indate );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }
}
