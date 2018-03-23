package com.yog.hp.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt;
    private TextView tv;
    Button addbt;
    Button viewbt;
    Button delbt;
    //private Firebase mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //below line is very important line add it when using firebase.
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
       // myRef=database.getReference();
        //myRef.child("User").setValue("yog123");
        bt = (Button) findViewById(R.id.button);
        tv=(TextView) findViewById(R.id.text1);
        String message;
      //  mRef=new Firebase("https://fireapp-7563d.firebaseio.com/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve latest value
                String message = dataSnapshot.getValue(String.class);
                tv.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error handling
            }
        });*/




        initiate();
//        bt.setOnClickListener(this);
        addbt.setOnClickListener(this);
        viewbt.setOnClickListener(this);
       // delbt.setOnClickListener(this);
    }

    public void initiate(){
        addbt=(Button) findViewById(R.id.add);
        viewbt=(Button) findViewById(R.id.view);
       // delbt=(Button) findViewById(R.id.del);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
            switch (v.getId()) {
                /*case R.id.button :

                    break;*/

                case R.id.add:
                    intent= new Intent(this,AddBill.class);
                    startActivity(intent);
                    break;

         /*       case R.id.del:

                    break;
            */
                case R.id.view:
                    intent= new Intent(this,ViewBill.class);
                    startActivity(intent);
                    break;
                }

            }
}
