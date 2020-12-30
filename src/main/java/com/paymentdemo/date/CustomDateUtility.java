package com.paymentdemo.date;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class CustomDateUtility {


    public static Date getSqlDate(int year, String monthString, int day)
    {
        monthString = monthString.toUpperCase(); // Allows for capitalisation to not be a concern
        MonthEnum month = MonthEnum.valueOf(monthString);
        // This is the "proper" way of deriving a date from a set of values.
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month.getMonthNumber());
        cal.set(Calendar.DAY_OF_MONTH, day);
        LocalDate localDate = LocalDate.ofInstant(cal.toInstant(), ZoneId.systemDefault());
        return Date.valueOf(localDate);
    }

    enum MonthEnum
    {
        // Expanded
        JANUARY(0),
        FEBRUARY(1),
        MARCH(2),
        APRIL(3),
        MAY(4),
        JUNE(5),
        JULY(6),
        AUGUST(7),
        SEPTEMBER(8),
        OCTOBER(9),
        NOVEMBER(10),
        DECEMBER(11),

        // Shorthand, allows for the user to just use 3 letters (note, May obviously doesn't need an extra entry)
        JAN(0),
        FEB(1),
        MAR(2),
        APR(3),
        JUN(5),
        JUL(6),
        AUG(7),
        SEP(8),
        OCT(9),
        NOV(10),
        DEC(11);

        private final Integer monthNumber;

        MonthEnum(int monthNumber)
        {
            this.monthNumber = monthNumber;
        }

        public Integer getMonthNumber()
        {
            return monthNumber;
        }
    }
}
