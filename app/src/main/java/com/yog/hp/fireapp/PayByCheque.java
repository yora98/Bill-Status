package com.yog.hp.fireapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hp on 11-12-2017.
 */

public class PayByCheque extends AppCompatActivity implements View.OnClickListener{
    EditText chequeNoUI,chequeAmtUI,chequeBankUI;
    DatabaseReference myRef,databaseReference;
    Button payByCheck,payByCash;
    Bill temp;
    SharedPreferences.Editor editor;
    TextView agency,amount;
    AutoCompleteTextView chequeDateUI;
 //   SharedPreferences sharedpreferences;
    ArrayAdapter<String> adapter;
    ArrayList<String> dateUI;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);
        temp= (Bill) getIntent().getExtras().getSerializable("pBill");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Bill").child(temp.getFirebaseKey());
        /*temp.setChequeAmt(chequeAmtUI.getText().toString());
        temp.setChequeNo(chequeNoUI.getText().toString());
        temp.setBankName(chequeAmtUI.getText().toString());
        */
        initiate();
        setupAutocompleteTextView();

      //  sharedpreferences = getSharedPreferences("dateRefer", Context.MODE_PRIVATE);
      //  editor = sharedpreferences.edit();

        agency.setText(Html.fromHtml("<b>Agency Name: </b> "+temp.getAgency()));
        amount.setText(Html.fromHtml("<b>Amount : </b>" +temp.getAmt()));

        payByCheck.setOnClickListener(this);
        payByCash.setOnClickListener(this);
    }

    private void setupAutocompleteTextView() {
        dateUI=new ArrayList<String>(3);
      //  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, );
        chequeDateUI.setAdapter(adapter);
    }

    void initiate(){
        amount=(TextView) findViewById(R.id.amount_ui_id_1);
        agency=(TextView) findViewById(R.id.agency_ui_id_1);
        chequeAmtUI=(EditText) findViewById(R.id.chequeAmtID);
        chequeDateUI=(AutoCompleteTextView) findViewById(R.id.chequeDateID);
        chequeNoUI=(EditText) findViewById(R.id.chequeNoID);
        chequeBankUI=(EditText) findViewById(R.id.bankNameID);
        payByCash =(Button) findViewById(R.id.payByCashID);
        payByCheck=(Button) findViewById(R.id.payByButtonID);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.payByButtonID:
                try {
                    temp.setChequeAmt(chequeAmtUI.getText().toString());
                    temp.setChequeNo(chequeNoUI.getText().toString());
                    temp.setBankName(chequeBankUI.getText().toString());
                    temp.setStatus("paid");
                    temp.setModeOfPay("cheque");
                    addDetails();
                    String[] a = chequeDateUI.getText().toString().split("/");
                    int d = Integer.parseInt(a[0]);
                    int m = Integer.parseInt(a[1]) - 1;
                    int y = Integer.parseInt(a[2]);

                    // DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    // Date dt=dateFormat.parse(dateFormat.format(billDateUI.getText().toString()));
                    if (d > 31 || y < 2000 || m > 12) {
                        throw new Exception();
                    }
                    Date dt = new Date(y-1900, m, d);
                    temp.setChequeDate(dt);
                    databaseReference.setValue(temp);
                    Toast.makeText(getApplicationContext(), "Status is upadated.", Toast.LENGTH_LONG).show();
                    intent = new Intent(this, ViewBill.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "UnSuccesfully Something is wrong.", Toast.LENGTH_LONG).show();
                    break;
                }
                break;

            case R.id.payByCashID:
                temp.setStatus("paid");
                temp.setModeOfPay("cash");
                databaseReference.setValue(temp);
                intent = new Intent(this, ViewBill.class);
                startActivity(intent);
                finish();
                break;

        }

    }

    private void addDetails() {

    }

    @Override
    public void finish() {
        super.finish();
    }
}
