package com.lifesaver.shortnote.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static String getCurrentTimeStamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
            String currentDateTime = dateFormat.format(new Date());
            return currentDateTime;
        } catch (Exception e) {
            return null;
        }
    }
    //convert the month number to a readable string abbreviation like "Jan"
    public static String getMonthFromNumber(String monthNumber) {
        switch (monthNumber) {
            case "01": {
                return "Jan";
            }
            case "02": {
                return "Feb";
            }
            case "03": {
                return "March";
            }
            case "04": {
                return "April";
            }
            case "05": {
                return "May";
            }
            case "06": {
                return "Jun";
            }
            case "07": {
                return "July";
            }
            case "08": {
                return "Aug";
            }
            case "09": {
                return "Sept";
            }
            case "10": {
                return "Oct";
            }
            case "11": {
                return "Nov";
            }
            case "12": {
                return "Dec";
            }
            default: {
                return "Error";
            }
        }

    }
}
