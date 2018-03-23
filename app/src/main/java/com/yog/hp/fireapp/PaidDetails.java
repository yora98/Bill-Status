package com.yog.hp.fireapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hp on 14-12-2017.
 */

public class PaidDetails extends AppCompatActivity {
    TextView paidDetails;
    Bill temp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paid_details);

        paidDetails=(TextView) findViewById(R.id.paid_details_id);
        temp= (Bill) getIntent().getExtras().getSerializable("pBill");
        String te="Agency Name: "+temp.getAgency()+"\n"+
                            "Bill No."+temp.getBill_no()+"\n"+
                            "Bill Amount: "+temp.getAmt()+"\n"+
                "Bill Date: "+temp.getBillDate().toString()+"\n"+
                            "Paid By: "+temp.getModeOfPay()+"\n";

                if(temp.getModeOfPay().compareTo("cheque")==0) {
                    te+="Bank Name: " + temp.getBankName() + "\n" +
                            "Cheque No.: " + temp.getChequeNo() + "\n" + "Cheuqe Amount" + temp.getChequeAmt() + "\n"
                            + "Cheque Date: " + temp.getBillDate().toString() + "\n";
                }
        paidDetails.setText(te);

    }
}
