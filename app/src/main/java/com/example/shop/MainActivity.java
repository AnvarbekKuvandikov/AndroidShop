package com.example.shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLog";
    private SearchView searchView;
    private ListView listView;
    private ListView listView2;
    private ArrayList<Product> list;
    private ArrayList<Item> list2;
    private ProductAdapter adapter;
    private ItemAdapter adapter2;
    private ImageView save;
    private ImageView calsel;
    private EditText price_product_count;
    private EditText price_inproduct_count;
    private  Product selectProduct;
    private  Item selectedItem;
    private static Integer selectItemSum=0;
    private static Integer sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save=(ImageView)findViewById(R.id.save);
        calsel=(ImageView)findViewById(R.id.calsel);

        price_product_count=(EditText)findViewById(R.id.price_product_count);
        price_inproduct_count=(EditText)findViewById(R.id.price_inproduct_count);


        searchView=(SearchView)findViewById(R.id.searchView);
        listView=(ListView)findViewById(R.id.listView);
        listView2=(ListView)findViewById(R.id.listView2);

        list = new ArrayList<>();
        list2 = new ArrayList<>();

//        list.add(new Product(1,"Дафтар",7000,700));
//        list.add(new Product(2,"Сигарет",9000,500));
//        list.add(new Product(3,"Сув",18000,1500));

//        list2.add(new Item(1,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(2,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(3,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(4,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(5,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(6,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(7,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(8,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(9,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(10,  "Ручка", 7, 1000, 7000));
//        list2.add(new Item(11,  "Ручка", 7, 1000, 7000));

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

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Product product=(Product)view.getTag();
                        setProduct(product);
                        Log.v(TAG,product.getName()+" "+selectProduct.name);
                    }
                }
        );

        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(selectProduct!=null){
                                            selectedItem=new Item();

                                            int price_product_count_int=Integer.parseInt(price_product_count.getText().toString());
                                            int price_inproduct_count_int=Integer.parseInt(price_inproduct_count.getText().toString());
                                            Log.v(TAG,price_inproduct_count_int+" "+price_product_count_int);
                                            if(price_product_count_int>0 && price_inproduct_count_int>0) {
                                                Log.v(TAG,"in 1:"+selectProduct.getName());
                                                selectedItem.setId(selectProduct.getId());
                                                selectedItem.setName(selectProduct.getName());
                                                selectedItem.setPrice(selectProduct.getPrice());
                                                selectedItem.setInprice(selectProduct.getInprice());

                                                selectedItem.setCount(price_product_count_int);
                                                selectedItem.setIncount(price_inproduct_count_int);

                                                selectItemSum=(selectProduct.getPrice()*selectedItem.getCount()+selectProduct.getInprice()*selectedItem.getIncount());
                                                sum+=selectItemSum;

                                                list2.add(selectedItem);

                                                setProduct(null);
                                                Log.v(TAG,"list2.size:"+list2.size());

                                            }
                                            else if (price_product_count_int>0) {
                                                selectedItem.setId(selectProduct.getId());
                                                selectedItem.setName(selectProduct.getName());
                                                selectedItem.setPrice(selectProduct.getPrice());
                                                selectedItem.setInprice(selectProduct.getInprice());

                                                selectedItem.setCount(price_product_count_int);
                                                selectedItem.setIncount(0);

                                                selectItemSum=selectProduct.getPrice()*selectedItem.getCount();
                                                sum+=selectItemSum;

                                                list2.add(selectedItem);

                                                setProduct(null);
                                                Log.v(TAG,"list2.size:"+list2.size());

                                            }
                                            else if(price_inproduct_count_int>0) {
                                                selectedItem.setId(selectProduct.getId());
                                                selectedItem.setName(selectProduct.getName());
                                                selectedItem.setPrice(selectProduct.getPrice());
                                                selectedItem.setInprice(selectProduct.getInprice());

                                                selectedItem.setIncount(price_product_count_int);
                                                selectedItem.setCount(0);
                                                selectItemSum=selectProduct.getInprice()*selectedItem.getIncount();
                                                sum+=selectItemSum;
                                                list2.add(selectedItem);
                                                setProduct(null);
                                                Log.v(TAG,"list2.size:"+list2.size());
                                            }
                                            else{
                                                Toast.makeText(MainActivity.this,"Сонини киритинг !!!",Toast.LENGTH_LONG);
                                            }
                                        }


                                    }
                                }
        );
        calsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                for (int i = 0; i <list2.size() ; i++) {
                                    Log.v(TAG,list2.get(i).toString());
                                    adapter.add(new Product(6,"Ha",23,23));
                                }
                                setProduct(null);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Сиз ростан хам бекор қилишни истайсизми?").setPositiveButton("Ха", dialogClickListener)
                        .setNegativeButton("Йўқ", dialogClickListener).show();
            }
        });




    }

    private void setProduct(Product product) {
        if(product!=null){
            ((TextView)findViewById(R.id.select_product)).setText(product.getName());
            selectProduct=product;
        }
        else{
            ((TextView)findViewById(R.id.select_product)).setText(R.string.product);
            selectProduct=product;
            selectedItem=null;
        }
        price_product_count.getText().clear();
        price_inproduct_count.getText().clear();

    }


}
