package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        barcodescan=findViewById(R.id.products_list_barcodescan);
        listView=findViewById(R.id.products_list_list_view);
        searchView=findViewById(R.id.searchView);
        intent=getIntent();
        stringList=new ArrayList<>();
        barcodeList=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, stringList);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        ZxingOrientResult scanResult =
                ZxingOrient.parseActivityResult(requestCode, resultCode, intent);

        if (scanResult != null) {
            // handle the result
            Log.v("MyTag",scanResult.getContents());
            String s=stringList.get(barcodeList.indexOf(scanResult.getContents()));
            stringList.clear();
            barcodeList.clear();
            barcodeList.add(scanResult.getContents());
            stringList.add(s);
            adapter.notifyDataSetChanged();
        }
    }


    public void setDownIntent(Intent nextIntent) {
        nextIntent.putExtra("user",intent.getSerializableExtra("user"));
        nextIntent.putExtra("ip",intent.getStringExtra("ip"));
        nextIntent.putExtra("asosId",intent.getIntExtra("asosId",0));
        nextIntent.putExtra("type",intent.getIntExtra("type",0));
        nextIntent.putExtra("sumprice",intent.getStringExtra("sumprice"));
        nextIntent.putExtra("stovar",intent.getSerializableExtra("stovar"));
    }

    void myFilter(String search){

        for (int i = 0; i <barcodeList.size() ; i++) {
            if (barcodeList.get(i).equals(search)){

            }
            else{

            }

        }
    }

}
