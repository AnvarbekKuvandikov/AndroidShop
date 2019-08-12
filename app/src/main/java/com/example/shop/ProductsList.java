package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

public class ProductsList extends AppCompatActivity {
    Intent intent;
    ImageView barcodescan;
    ListView listView;
    SearchView searchView;
    List<String> stringList;
    List<String> barcodeList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        barcodescan=findViewById(R.id.products_list_barcodescan);
        listView=findViewById(R.id.products_list_list_view);
        searchView=findViewById(R.id.searchView);
        intent=getIntent();
        stringList=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, stringList);
        stringList.add("Дафтар");
        stringList.add("Дафтар");
        stringList.add("Дафтар");
        stringList.add("Дафтар");
        stringList.add("Дафтар");
        listView.setAdapter(adapter);


        barcodescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ZxingOrient(ProductsList.this).setIcon(R.mipmap.ic_launcher).initiateScan();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ZxingOrientResult scanResult =
                ZxingOrient.parseActivityResult(requestCode, resultCode, intent);

        if (scanResult != null) {
            // handle the result
            Log.v("MyTag",scanResult.getContents());

        }
    }

}
