package com.yog.hp.fireapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hp on 28-11-2017.
 */

public class ViewBill extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    ListView lt;
    DatabaseReference myRef,databaseReference;
    ArrayList<Bill> bill;
    CustomAdapter listAdapter;
    SearchView searchView;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_bills);
        initiate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       // myRef = database.getReference("Count");
        databaseReference=database.getReference("Bill");
        bill=getList();
        searchBill("",bill);

        lt.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        Bill temp=(Bill)parent.getItemAtPosition(position);
        if(temp.getStatus().compareTo("unpaid")==0){
        intent=new Intent(this,PayByCheque.class);
            intent.putExtra("pBill",temp);
           // Log.d("hi","Bye");
            startActivity(intent);
            finish();
        }
        else{
            intent=new Intent(this,PaidDetails.class);
            intent.putExtra("pBill",temp);
           // Log.d("hi","Bye");
            startActivity(intent);
        }
    }



    public void initiate(){
        lt=(ListView) findViewById(R.id.listview_id);

    }

    public ArrayList<Bill> getList(){
        final ArrayList<Bill> temp=new ArrayList<Bill>();
        int i=0;
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve latest value
               // String message = dataSnapshot.getValue(String.class);
                for(DataSnapshot datasnap : dataSnapshot.getChildren()){
                    Bill b=datasnap.getValue(Bill.class);
                    temp.add(b);
                }
               listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error handling
            }
        });
        */
        databaseReference.orderByChild("agency").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Bill newBill = dataSnapshot.getValue(Bill.class);
                newBill.setFirebaseKey(dataSnapshot.getKey());
                temp.add(newBill);
                Collections.sort(temp, new Comparator<Bill>() {
                    @Override
                    public int compare(Bill lhs, Bill rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return lhs.getAgency().toLowerCase().compareTo(rhs.getAgency().toLowerCase());
                    }
                });
                //   System.out.println("Author: " + newPost.author);
                //  System.out.println("Title: " + newPost.title);
                // System.out.println("Previous Post ID: " + prevChildKey);
                listAdapter.notifyDataSetChanged();
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
        return temp;
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void searchBill(String query,ArrayList<Bill> out){
        ArrayList<Bill> output=out;
        ArrayList<Bill> filter=new ArrayList<Bill>();

        if(searchView!=null){
            for(Bill bl:output){
                if(bl.getAgency().toLowerCase().startsWith(query.toLowerCase())){
                    filter.add(bl);
                }
            }
        }
        else{
            filter=out;
        }
        listAdapter=new CustomAdapter(this,filter);
        lt.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_bill_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.app_bar_search);
        searchView =(SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search");
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchBill(newText,bill);
                return false;
            }
        });

       // return true;
       return super.onCreateOptionsMenu(menu);
    }
}
