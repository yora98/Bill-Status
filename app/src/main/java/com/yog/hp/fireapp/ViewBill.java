package com.yog.hp.fireapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hp on 28-11-2017.
 */

public class ViewBill extends AppCompatActivity {
    ListView lt;
    DatabaseReference myRef,databaseReference;
    ArrayList<Bill> bill;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Count");
        databaseReference=database.getReference("Bill");
        bill=getList();


    }

    public void initiate(){
        lt=(ListView) findViewById(R.id.listview_id);

    }

    public ArrayList<Bill> getList(){
        final ArrayList<Bill> temp=new ArrayList<Bill>();
        int i=0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve latest value
               // String message = dataSnapshot.getValue(String.class);
                for(DataSnapshot datasnap:dataSnapshot.getChildren()){
                    Bill b=datasnap.getValue(Bill.class);
                    temp.add(b);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error handling
            }
        });
        return temp;
    }
}
