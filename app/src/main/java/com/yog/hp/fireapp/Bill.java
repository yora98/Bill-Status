package com.yog.hp.fireapp;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.Date;

/**
 * Created by hp on 28-11-2017.
 */

public class Bill implements Serializable {
    String agency,modeOfPay;
    String key,firebaseKey;
    String bill_no,chequeNo,bankName,chequeAmt;
    Date billDate=null,chequeDate=null;
    int amt;
    String status="unpaid";
    public Bill() {}
    public Bill(String age, String bl_no, int amt1, Date dt1){
        agency=age;
        bill_no=bl_no;
        amt=amt1;
        billDate=dt1;
    }
    public void setModeOfPay(String t){modeOfPay=t;}
    public void setBankName(String t){bankName=t;}
    public void setChequeNo(String t){chequeNo=t;}
    public void setChequeAmt(String t){chequeAmt=t;}
    public void setFirebaseKey(String t){firebaseKey=t;}
    public void setStatus(String t){status=t;}
    public void setChequeDate(Date t){chequeDate=t;}
    public void setBillDate(Date t){billDate=t;}
    public String getChequeNo(){return chequeNo;}
    public String getBankName(){return bankName;}
    public String getChequeAmt(){return chequeAmt;}
    public Date getChequeDate(){return chequeDate;}
    public String getModeOfPay(){return modeOfPay;}
    public String getFirebaseKey(){return firebaseKey;}
    public String getAgency(){
        return agency;
    }
    public String getBill_no(){
        return bill_no;
    }
    public Date getBillDate(){return billDate;}
    public int getAmt(){
        return amt;
    }
    public String getStatus(){return status;}
    public String getKey(){return key;}
}
