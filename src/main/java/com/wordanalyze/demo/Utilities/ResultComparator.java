package com.wordanalyze.demo.Utilities;

import com.wordanalyze.demo.POJO.Result;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ResultComparator implements Comparator<Result> {

    @Override
    public int compare(Result r1, Result r2){
        // get the name w/o extension (.txt)
        String date1inString = r1.getNewFileName();
        date1inString = date1inString.substring(0, date1inString.length() - 4);
        String date2inString = r2.getNewFileName();
        date2inString = date2inString.substring(0, date2inString.length() - 4);

        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date1, date2;
        try{
            date1 = format.parse(date1inString);
            date2 = format.parse(date2inString);
        } catch (java.text.ParseException e){
            e.printStackTrace();
            return 0;
        }

        return date2.compareTo(date1);
    }
}
