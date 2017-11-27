package com.thaontm.project.Seconds.utils;

import com.thaontm.project.Seconds.model.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public String convertDateToString(Date indate)
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

    public String cutProductDescription(Product product) {
        String des = product.getDescription();
        if (des.length() > Constants.DESCRIPTION_LENGTH) {
            product.setDescription(des.substring(0, Constants.DESCRIPTION_LENGTH) + " ...");
        }
        return des;
    }
}
