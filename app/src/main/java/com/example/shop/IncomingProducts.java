package com.example.shop;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomingProducts extends AppCompatActivity {
    private DatePickerDialog picker;
    private EditText incomingDate;
    private EditText incomingNum;
    private AutoCompleteTextView incomingdiller;
    private CheckBox incomingDollar;
    private Spinner listView;
    private List<CharSequence> list;
    private List<Diller> dillerList;

    private List<AsosModell> modellList;
    private List<String> listAsos;
    private ArrayAdapter<String> adapterdillers;
    private ArrayAdapter<CharSequence> adapter;
    private List<String> dillerNames;
    private ProgressDialog progressDialog;
    private User thisUser;
    private String ip;
    private Intent intent;
    private AsosModell asos;
    private AsosModell inserAsos;
    private Integer dillerId;
    private ImageView save;
    private ImageView create;
    private ImageView clear;
    private Integer newAsosCheck;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomingproducts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        incomingDate=findViewById(R.id.incoming_date);
        incomingDate.setInputType(InputType.TYPE_NULL);
        incomingNum=findViewById(R.id.incoming_num);
        incomingdiller=findViewById(R.id.incoming_diller);
        incomingDollar=findViewById(R.id.incoming_dollar);
        listView=findViewById(R.id.incoming_ac_list);
        save=findViewById(R.id.incoming_save);
        create=findViewById(R.id.incoming_new);
        clear=findViewById(R.id.incoming_clear);
        next=findViewById(R.id.incoming_add_product_next);
        intent=getIntent();
        thisUser=(User)intent.getSerializableExtra("user");
        ip=intent.getStringExtra("ip");

        newAsosCheck=0;

        asos=new AsosModell();
        asos.setClientId(thisUser.getClientId());
        asos.setUserId(thisUser.getId());
        asos.setDel_flag(1);
        asos.setTurOper(2);
        asos.setXodimId(thisUser.getId());
        asos.setHaridorId(0);
        asos.setSana("");
        asos.setDilerId(0);
        asos.setSumma(0.0);
        asos.setSotuv_turi(1);
        asos.setNomer("");
        asos.setDollar(1);
        asos.setKurs(0.0);
        asos.setSum_d(0.0);
        asos.setKol(0);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAsosCheck=0;
                setSeleection(modellList.size()-1);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAsosCheck=1;
                incomingDate.getText().clear();
                incomingNum.getText().clear();
                incomingdiller.getText().clear();
                incomingDollar.setChecked(false);
                listView.setSelected(false);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserAsos=new AsosModell();
                asos.setNomer(incomingNum.getText().toString());
//                asos.set
                asos.setSana(incomingDate.getText().toString());
                asos.setDilerId(dillerId);
                Integer check=0;
                if(incomingDollar.isChecked()) {
                    check=1;
                }
                asos.setDollar(check);
                copyProperties(inserAsos,asos);
                new getDiller().execute();

            }
        });
        incomingdiller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection=(String)adapterView.getItemAtPosition(i);
                Integer selectionIndex=dillerNames.indexOf(selection);
                dillerId=dillerList.get(selectionIndex).getId();
               /*Log.v("MyTag","dillerList:"+dillerList.get(selectionIndex).getName()+" "+dillerList.get(selectionIndex).getId()+", dillerNames:"+dillerNames.get(selectionIndex)+", :"+dillerId);
                Log.v("MyTag","selection:"+selection+", "+selectionIndex); */

            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                  Log.v("MyTag",list.get(position).toString());
                  newAsosCheck=0;
                  setSeleection(position);

                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent=new Intent(IncomingProducts.this,IncomingAdd.class);
                setDownIntent(nextIntent);
                nextIntent.putExtra("asos",asos);
                startActivity(nextIntent);
            }
        });

        modellList=new ArrayList<>();
        list=new ArrayList<>();
        dillerList=new ArrayList<>();
        dillerNames=new ArrayList<>();

        listAsos=new ArrayList<>();

        adapterdillers=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, dillerNames  );
        incomingdiller.setAdapter(adapterdillers);
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, list);
        listView.setAdapter(adapter);

        new getDiller().execute();

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
                                incomingDate.setText(  year+ "-" + (monthOfYear + 1) + "-" +dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }

    private void setSeleection(Integer position) {
        asos=modellList.get(position);
        listView.setSelection(position);
        incomingDate.setText(asos.getSana(), TextView.BufferType.EDITABLE);
        incomingNum.setText(asos.getNomer(), TextView.BufferType.EDITABLE);
        if(asos.getDilerId()>0){
            incomingdiller.setText((CharSequence) asos.getDiller().getName(),TextView.BufferType.EDITABLE);
        }
        if (asos.getDollar().equals(1)){
           incomingDollar.setChecked(true);
        }
        else {
           incomingDollar.setChecked(false);
        }
    }

    private void copyProperties(AsosModell asosBefore,AsosModell asosLast) {
        if (newAsosCheck==0){
            asosBefore.setId(asosLast.getId());
        }
        asosBefore.setUserId(asosLast.getUserId());
        asosBefore.setDollar(asosLast.getDollar());
        asosBefore.setClientId(asosLast.getClientId());
        asosBefore.setDel_flag(asosLast.getDel_flag());
        asosBefore.setTurOper(asosLast.getTurOper());
        asosBefore.setXodimId(asosLast.getXodimId());
        asosBefore.setHaridorId(asosLast.getHaridorId());
        asosBefore.setSana(asosLast.getSana());
        asosBefore.setDilerId(asosLast.getDilerId());
        asosBefore.setSumma(asosLast.getSumma());
        asosBefore.setSotuv_turi(asosLast.getSotuv_turi());
        asosBefore.setNomer(asosLast.getNomer());
        asosBefore.setKurs(asosLast.getKurs());
        asosBefore.setSum_d(asosLast.getSum_d());
        asosBefore.setKol(asosLast.getKol());

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

    public Integer tryParse(Object obj) {
        Integer retVal;
        try {
            retVal = Integer.parseInt((String) obj);
        } catch (NumberFormatException nfe) {
            retVal = 0; // or null if that is your preference
        }
        return retVal;
    }
    public Double tryParseDouble(Object obj) {
        Double retVal;
        try {
            retVal = Double.parseDouble((String) obj);
        } catch (NumberFormatException nfe) {
            retVal = 0.0; // or null if that is your preference
        }
        return retVal;
    }
    private String getModny(Double DoubleValue){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedValue = decimalFormat.format(DoubleValue);
        return formattedValue;
    }
    public void setDownIntent(Intent nextIntent) {
        nextIntent.putExtra("user",intent.getSerializableExtra("user"));
        nextIntent.putExtra("ip",intent.getStringExtra("ip"));
        nextIntent.putExtra("asosId",intent.getIntExtra("asosId",0));
        nextIntent.putExtra("type",intent.getIntExtra("type",0));
        nextIntent.putExtra("sumprice",intent.getStringExtra("sumprice"));
        nextIntent.putExtra("stovar",intent.getSerializableExtra("stovar"));
    }



    class getDiller extends AsyncTask<Void,Void,Void> {


        HttpHandler httpHandler=new HttpHandler();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(IncomingProducts.this);
            progressDialog.setMessage("Малумот юкланяпти");
            progressDialog.setCancelable(false);
            progressDialog.show();
            list.clear();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if (thisUser.getId()!=null) {
//                http://localhost:8080/application/json/dillerid=4/harodors
                String urlGetDillers = "http://" + ip + ":8080/application/json/clientid="+thisUser.getClientId()+"/dillers";
//                String urlGetAsoss = "http://" + ip + ":8080/application/json/clientid="+thisUser.getClientId()+"/asoss";
                String urlGetAsoss = "http://" + ip + ":8080/application/json/asoss";
                String urlNewAsos = "http://" + ip + ":8080/application/json/newasos";

                if (asos.getDiller() != null){
                    Log.v("MyTag",asos.toString());
                }
                String jsonDillersStr=httpHandler.makeServiceCall(urlGetDillers);
                String jsonAsosStr;
                AsosModell asosModell=new AsosModell();
                asosModell.setClientId(asos.getClientId());
                asosModell.setUserId(asos.getUserId());
                asosModell.setDollar(asos.getDollar());
                asosModell.setDilerId(asos.getDilerId());
                asosModell.setSana(asos.getSana());

                if (newAsosCheck==0){
                    jsonAsosStr=httpHandler.makeServiceCreate(urlGetAsoss,asos);
                }
                else {
                       jsonAsosStr=httpHandler.makeServiceCreate(urlNewAsos,inserAsos);
                }

                if(jsonDillersStr != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(jsonDillersStr);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Diller diller=new Diller();
                            diller.setId(object.getInt("id"));
                            diller.setName(object.getString("nom"));
                            diller.setClientId(object.getInt("client_id"));
                            diller.setPhone(object.getString("tel"));
                            dillerList.add(diller);
                            dillerNames.add(object.getString("nom"));
                            /* {
                           "id": 1,
                           "nom": "01.Artel dileri",
                           "tel": "+998999664660",
                           "client_id": 3
                           },*/

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

                if (jsonAsosStr != null){
                    try {
                        JSONArray jsonArray = new JSONArray(jsonAsosStr);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            AsosModell modell=new AsosModell();
                            modell.setId(object.getInt("id"));
                            modell.setClientId(object.getInt("clientId"));
                            modell.setXodimId(object.getInt("xodimId"));
                            modell.setHaridorId(object.getInt("haridorId"));
                            modell.setSana(object.getString("sana"));
                            modell.setDilerId(object.getInt("dilerId"));
                            modell.setTurOper(object.getInt("turOper"));
                            modell.setSumma(tryParseDouble(object.getString("summa")));
                            modell.setSotuv_turi(object.getInt("sotuvTuri"));
                            modell.setNomer(object.getString("nomer"));
                            modell.setDel_flag(object.getInt("del_flag"));
                            modell.setDollar(object.getInt("dollar"));
                            modell.setKurs(tryParseDouble(object.getString("kurs")));
                            modell.setSum_d(tryParseDouble(object.getString("sum_d")));
//                            modell.setKol(object.getInt("kol"));
                            Log.v("MyTag2",modell.getId()+"");
                            /*{
                                "id": 1782,
                                    "clientId": 4,
                                    "userId": 30,
                                    "xodimId": 99,
                                    "haridorId": 1,
                                    "sana": "2019-09-18",
                                    "dilerId": 0,
                                    "turOper": 2,
                                    "summa": 4500,
                                    "sotuvTuri": 1,
                                    "nomer": null,
                                    "del_flag": 1,
                                    "dollar": null,
                                    "kurs": null,
                                    "sum_d": null,
                                    "kol": null
                            }*/
                            modellList.add(modell);
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



            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i=0;i<modellList.size();i++){
                Integer id=modellList.get(i).getDilerId();
                Log.v("MyTag3","id:"+id);
                CharSequence x;
                if (id>0){
                    modellList.get(i).setDiller(getDiller(id));
                    x="Таминотчи:"+modellList.get(i).getDiller().getName()+ ", Сумма: "+modellList.get(i).getSumma();
//                    CharSequence x="id"+id+","+modellList.get(i).getDiller().getId()+" : "+modellList.get(i).getId();
                }
                else {
                    x="Таминотчи: Мавжуд емас, Сумма: "+modellList.get(i).getSumma();
                }
                list.add(x);
                adapter.notifyDataSetChanged();
            }
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            newAsosCheck=0;
            setSeleection(modellList.size()-1);
        }
        public Diller getDiller(Integer dillerId){
            for (int i=0;i<dillerList.size();i++){
                if (dillerList.get(i).getId().equals(dillerId))
                    return dillerList.get(i);
            }
            return null;
        }

    }




}
