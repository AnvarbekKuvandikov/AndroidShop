package com.example.shop;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyLog";
    private SearchView searchView;
    private ListView listView;
    private ListView listView2;
    private ArrayList<Product> list;
    private ArrayList<AddItems> list2;
    private ProductAdapter adapter;
    private ItemAdapter adapter2;
    private ImageView save;
    private ImageView calsel;
    private EditText price_product_count;
    private EditText price_inproduct_count;
    private Product selectProduct;
    private AddItems selectedItem;
    private static Integer selectItemSum=0;
    private static Integer sum=0;

    private ProgressDialog progressDialog;
    private static String urlProducts="http://192.168.43.52:8080/application/json/products";

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

        new GetProducts().execute();
//        list.add(new Product(1,"Дафтар",7000,700));
//        list.add(new Product(2,"Сигарет",9000,500));
//        list.add(new Product(3,"Сув",18000,1500));

//        list2.add(new AddItems(1, "Ручка", 7, 0, 14000,700 ));
//        list2.add(new AddItems(2,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(3,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(4,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(5,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(6,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(7,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(8,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(9,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(10,  "Ручка", 7, 1000, 7000));
//        list2.add(new AddItems(11,  "Ручка", 7, 1000, 7000));


//        list2.add(new AddItems(452, 1,"anvar",5000,750,5,40));
//        adapter2 = new ItemAdapter(this, R.layout.list_item,list2);
//        listView2.setAdapter(adapter2);

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
                        Log.v(TAG,product.getName()+" "+selectProduct.getName());
                    }
                }
        );

        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(selectProduct!=null){
                                            selectedItem=new AddItems();


                                            int price_product_count_int=tryParse(price_product_count.getText().toString());
                                            int price_inproduct_count_int=tryParse(price_inproduct_count.getText().toString());

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
                                                Toast.makeText(MainActivity.this,"Сонини киритинг !!!",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this,"Махсулотни танланг",Toast.LENGTH_LONG).show();
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
//                                    adapter2.add(new AddItems(452, 1,"anvar",5000,750,5,40));
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

    public Integer tryParse(Object obj) {
        Integer retVal;
        try {
            retVal = Integer.parseInt((String) obj);
        } catch (NumberFormatException nfe) {
            retVal = 0; // or null if that is your preference
        }
        return retVal;
    }


    private class GetProducts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Малумот юкланяпти");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler=new HttpHandler();
            String jsonStr=httpHandler.makeServiceCall(urlProducts);
            Log.v(TAG,"URL:"+urlProducts);
            if(jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Product product = new Product();
                        JSONObject object = jsonArray.getJSONObject(i);

                                /*
                                "id": 1,
                                "productId": 2,
                                "nameShort": "anvar",
                                "count": 4,
                                "incount": 5,
                                "price": 6,
                                "inprice": 7
                                */
                        product.setPutId(object.getInt("id"));
                        product.setId(object.getInt("productId"));
                        product.setName(object.getString("nameShort"));
                        product.setCount(object.getInt("count"));
                        product.setIncount(object.getInt("incount"));
                        product.setPrice(object.getInt("price"));
                        product.setInprice(object.getInt("inprice"));
                        list.add(product);

                    }
                } catch (final JSONException e) {
                    Log.v("MyTag2", e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Хатолик юз берди", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
            else{
                Log.v("MyTag2", "serverdan galmadi");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"Сервер билан муамо бор",Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            adapter = new ProductAdapter(MainActivity.this,R.layout.products_item,list);
            listView.setAdapter(adapter);


        }
    }
}
