package com.paymentdemo.date;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CustomDateUtilityTest {

    @Test
    public void testGetSqlDate()
    {
        Date myBirthDay = CustomDateUtility.getSqlDate(1991, "July", 4);
        assertEquals(678582000000L, myBirthDay.getTime());
    }
}