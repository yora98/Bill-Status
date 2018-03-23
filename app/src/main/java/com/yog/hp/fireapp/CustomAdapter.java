package com.yog.hp.fireapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 28-11-2017.
 */

public class CustomAdapter extends ArrayAdapter {

    TextView agen,bill_no,amt,stat,pay;

    ArrayList<Bill> arr;
    public CustomAdapter( Context context, ArrayList<Bill> objects) {
        super(context,R.layout.custom_adapter_bil, objects);
        Log.d("hui","buhu");
        arr=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater yogsInflator=LayoutInflater.from(getContext());
        View custView=yogsInflator.inflate(R.layout.custom_adapter_bil,parent,false);

        agen=(TextView) custView.findViewById(R.id.text1);
        bill_no=(TextView) custView.findViewById(R.id.text2);
        stat=(TextView) custView.findViewById(R.id.status_id);
        amt=(TextView) custView.findViewById(R.id.amt_id);
        pay=(TextView) custView.findViewById(R.id.pay_id);

        Bill temp=(Bill) getItem(position);
        Log.d("Debug CustomAdapter",temp.getAgency());
        agen.setText("Agency Name: "+temp.getAgency());
        amt.setText("Amount: "+temp.getAmt());//pass String
        bill_no.setText("Bill No: "+temp.getBill_no());
        stat.setText("Status: "+temp.getStatus());
        pay.setText("Paid By:"+temp.getModeOfPay());

        if(temp.getStatus().compareTo("paid")!=0){
            custView.setBackgroundColor(Color.parseColor("#fcfc88"));
        }

        return custView;
    }



}
