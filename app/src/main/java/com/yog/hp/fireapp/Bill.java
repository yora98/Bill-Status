package com.yog.hp.fireapp;

import java.util.Date;

/**
 * Created by hp on 28-11-2017.
 */

public class Bill {
    String agency;
    String bill_no;
    Date dt;
    int amt;
    public Bill() {}
    public Bill(String age, String bl_no, int amt1, Date dt1){
        agency=age;
        bill_no=bl_no;
        amt=amt1;
        dt=dt1;

    }
    public String getAgency(){
        return agency;
    }
    public String getBill_no(){
        return bill_no;
    }
    public Date getDate(){
        return dt;
    }
    public int getAmt(){
        return amt;
    }
}
