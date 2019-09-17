package com.example.shop;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomingProducts extends AppCompatActivity {
    DatePickerDialog picker;
    EditText incomingDate;
    EditText incomingNum;
    AutoCompleteTextView incomingClient;
    RadioButton incomingDollar;
    Spinner listView;
    List<CharSequence> list;
    List<String> listAsos;
    ArrayAdapter<CharSequence> adapter;
    ProgressDialog progressDialog;
    User thisUser;
    String ip;
    Intent intent;
    Integer asosId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomingproducts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        incomingDate=findViewById(R.id.incoming_date);
        incomingDate.setInputType(InputType.TYPE_NULL);
        incomingNum=findViewById(R.id.incoming_num);
        incomingClient=findViewById(R.id.incoming_client);
        incomingDollar=findViewById(R.id.incoming_dollar);
        listView=findViewById(R.id.incoming_ac_list);
        intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        ip=intent.getStringExtra("ip");
        asosId=intent.getIntExtra("asosId",0);
        list=new ArrayList<>();
        list.add("Абдулла, 84 000, 8");
        list.add("Файзулла, 45 000, 4");
        list.add("Шодлик, 145 000, 42");
        listAsos=new ArrayList<>();

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, list);
        listView.setAdapter(adapter);
        incomingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(IncomingProducts.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                incomingDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
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
//           new Finish().execute();
            Intent intent=new Intent(IncomingProducts.this,TypeChangeActivity.class);
            setDownIntent(intent);
            startActivity(intent);
            finish();
        }
        if (id == R.id.item2){
            Intent intent=new Intent(IncomingProducts.this,MainActivity.class);
            setDownIntent(intent);
            startActivity(intent);
            finish();
        }
        if (id == R.id.item3){
            Intent intent=new Intent(IncomingProducts.this,ProductsList.class);
            setDownIntent(intent);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void setDownIntent(Intent nextIntent) {
        nextIntent.putExtra("user",intent.getSerializableExtra("user"));
        nextIntent.putExtra("ip",intent.getStringExtra("ip"));
        nextIntent.putExtra("asosId",intent.getIntExtra("asosId",0));
        nextIntent.putExtra("type",intent.getIntExtra("type",0));
        nextIntent.putExtra("sumprice",intent.getStringExtra("sumprice"));
        nextIntent.putExtra("stovar",intent.getSerializableExtra("stovar"));
    }

    private class getDiller extends AsyncTask<Void,Void,Void> {


        HttpHandler httpHandler=new HttpHandler();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(IncomingProducts.this);
            progressDialog.setMessage("Малумот юкланяпти");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if (thisUser.getId()!=null) {
//                http://localhost:8080/application/json/clientid=4/harodors
                String urlGetHaridor = "http://" + ip + ":8080/application/json/clientid="+thisUser.getClientId()+"/harodors";

                String jsonHaridorsStr=httpHandler.makeServiceCall(urlGetHaridor);
                if(jsonHaridorsStr != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonHaridorsStr);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Product product = new Product();
                            JSONObject object = jsonArray.getJSONObject(i);

                           /* {
                                    "id": 1,
                                    "clientId": 4,
                                    "nom": "Нуржамол опа (Нурлан )",
                                    "adress": "Беруний",
                                    "manzil": null
                            },*/
//                            listId.add(object.getInt("id"));
                            list.add(object.getString("nom"));
                            adapter.notifyDataSetChanged();

                        }
                    } catch (final JSONException e) {
                        Log.v("MyTag2", e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(IncomingProducts.this, "Хатолик юз берди", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }
                else{
                    Log.v("MyTag2", "serverdan galmadi");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(IncomingProducts.this,"Сервер билан муамо бор",Toast.LENGTH_LONG).show();
                        }
                    });
                }



            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

}
