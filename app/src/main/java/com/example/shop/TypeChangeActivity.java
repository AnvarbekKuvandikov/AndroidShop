package com.example.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class TypeChangeActivity extends AppCompatActivity {

    AutoCompleteTextView client;
    List<String> mCats;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_change);
        mCats=new ArrayList<>();
        mCats.add("Sanajar");
        mCats.add("Anvar");
        mCats.add("Bobur");
        mCats.add("Sator");
        mCats.add("Qahor");
        mCats.add("muhtor");
        client = (AutoCompleteTextView) findViewById(R.id.client);
        client.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, mCats));


    }
}
