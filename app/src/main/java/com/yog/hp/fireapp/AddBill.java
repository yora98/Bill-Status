package com.yog.hp.fireapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Created by hp on 28-11-2017.
 */

public class AddBill extends AppCompatActivity implements View.OnClickListener{
    Button submitbt;
    EditText agencyEdit,amtEdit,billnoEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill);
        initiate();



        submitbt.setOnClickListener(this);
    }

    public void initiate(){
        submitbt= (Button) findViewById(R.id.submit_button_id);
        agencyEdit=(EditText) findViewById(R.id.agency_ui_id);
        amtEdit=(EditText) findViewById(R.id.amount_ui_id);
        billnoEdit =(EditText) findViewById(R.id.bill_no_ui_id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button_id:
                try{
                Date dt=new Date();
                Bill bill=new Bill(agencyEdit.getText().toString(),
                        billnoEdit.getText().toString(),
                        Integer.parseInt(amtEdit.getText().toString()),dt);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Bill").child(new Date().toString());
                myRef.setValue(bill);
                }catch (Exception e){
                    Log.d("Exception","in addView class");
                    break;
                }
                Dialog d=new Dialog(this);
                d.setTitle("Your Entry is added");
                TextView tv=new TextView(this);
                tv.setText("Succesfully added");
                d.setContentView(tv);
                d.show();
                Intent intent =new Intent(this,AddBill.class);
                finish();
                startActivity(intent);

                break;
        }
    }

    public void firebaseSetup(){

    }

    @Override
    public void finish() {
        super.finish();
    }
}
