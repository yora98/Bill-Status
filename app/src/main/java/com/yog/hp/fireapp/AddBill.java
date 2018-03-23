package com.yog.hp.fireapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hp on 28-11-2017.
 */

public class AddBill extends AppCompatActivity implements View.OnClickListener{
    Button submitbt;
    DatabaseReference myRef,databaseReference,databaseReference2;
    EditText amtEdit,billnoEdit,billDateUI;
    AutoCompleteTextView agencyEdit;
    int count;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill);
        initiate();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
       // databaseReference=database.getReference("Count");

        databaseReference2=database.getReference("Agencies");
        // myRef = database.getReference("Count");
       // String[] countries = getResources().getStringArray(R.array.countries_array);
// Create the adapter and set it to the AutoCompleteTextView
        ArrayList<String> countries=getString();
        //String[] agencies=(String[]) countries.toArray();
               // new ArrayAdapter<ArrayList<String>>(this, android.R.layout.simple_list_item_1, agencies);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        agencyEdit.setAdapter(adapter);
    //    databaseReference=database.getReference("Count");

     //   databaseReference2=database.getReference("Agencies");

        submitbt.setOnClickListener(this);
    }

    public void initiate(){
        submitbt= (Button) findViewById(R.id.submit_button_id);
        agencyEdit=(AutoCompleteTextView) findViewById(R.id.agency_ui_id);
        amtEdit=(EditText) findViewById(R.id.amount_ui_id);
        billnoEdit =(EditText) findViewById(R.id.bill_no_ui_id);
        billDateUI=(EditText) findViewById(R.id.date_ui_id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button_id:
                try{
                    String[] a=billDateUI.getText().toString().split("/");
                    int d=Integer.parseInt(a[0]);
                    int m=Integer.parseInt(a[1])-1;
                    int y=Integer.parseInt(a[2]);

                   // DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                   // Date dt=dateFormat.parse(dateFormat.format(billDateUI.getText().toString()));
                    if(d>31||y<2000||m>=12){
                        throw new Exception();
                    }
                    Date dt=new Date(y-1900,m,d);
                    Bill bill=new Bill(agencyEdit.getText().toString(),
                        billnoEdit.getText().toString(),
                        Integer.parseInt(amtEdit.getText().toString()),dt);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Bill").child(new Date().toString());
                myRef.setValue(bill);
                }catch (Exception e){
                  //  Log.d("Exception","in addView class");
                    Toast.makeText(getApplicationContext(), "UnSuccesfully Something is wrong.", Toast.LENGTH_LONG).show();
                    break;
                }

                Toast.makeText(getApplicationContext(), "Your Bill added Succesfully", Toast.LENGTH_LONG).show();
              //  Intent intent =new Intent(this,MainActivity.class);
                finish();
              //  startActivity(intent);

                break;
        }
    }


    @Override
    public void finish() {
        super.finish();
    }

 /*   public void datePickerOn(View view) {
        DatePickerFragment df=new DatePickerFragment();
        DialogFragment newFragment =df;
        //newFragment.show(getFragmentManager(),"datePicker");
      //  while (df.getDisplayStatus()){}
        Log.d("date",""+df.getChosenDate().getMonth());
      //  billDateUI.setText((String)df.getDate().toString());

    }*/

  /*  public void getCount(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve latest value
                // String message = dataSnapshot.getValue(String.class);
               count=dataSnapshot.getValue(Integer.class);
               // listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error handling
            }
        });
    }*/

    public ArrayList<String> getString(){
        final ArrayList<String> te=new ArrayList<String>();
        databaseReference2.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String agen = dataSnapshot.getValue(String.class);
              //  Log.d("stragenn",agen);
                //newBill.setFirebaseKey(dataSnapshot.getKey());
                te.add(agen);
              //  Log.d("stragen",agen);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return te;
    }
}

