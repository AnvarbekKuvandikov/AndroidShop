package com.example.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    private ListView listView2;
    private ArrayList<Product> list;
    private ArrayList<Item> list2;
    private ProductAdapter adapter;
    private ItemAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=(SearchView)findViewById(R.id.searchView);
        listView=(ListView)findViewById(R.id.listView);
        listView2=(ListView)findViewById(R.id.listView2);

        list = new ArrayList<>();
        list2 = new ArrayList<>();

        list.add(new Product(1,"Дафтар",7000,700));
        list.add(new Product(2,"Сигарет",9000,500));
        list.add(new Product(3,"Сув",18000,1500));

        list2.add(new Item(1,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(2,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(3,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(4,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(5,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(6,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(7,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(8,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(9,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(10,  "Ручка", 7, 1000, 7000));
        list2.add(new Item(11,  "Ручка", 7, 1000, 7000));

        adapter = new ProductAdapter(this,R.layout.products_item,list);
        adapter2 = new ItemAdapter(this, R.layout.list_item,list2);

        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query);
//                if (list.contains(query)) {
//                } else {
//                    Toast.makeText(MainActivity.this, "Topilmadi !!!", Toast.LENGTH_LONG).show();
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                    adapter.getFilter().filter(s);
//                if (list.contains(s)) {
//                    return  true;
//                }
                return false;
            }
        });
    }



}
