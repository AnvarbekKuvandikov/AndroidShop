package com.example.shop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

public class ProductAdd extends AppCompatActivity {

    Intent intent;
    Button saveProduct;
    EditText name;
    EditText name_short;
    EditText in_count;
    EditText barcode1;
    EditText barcode2;
    EditText barcode3;
    EditText type1;
    EditText type2;
    EditText type3;
    EditText type4;
    EditText type5;
    EditText type6;
    EditText for_count;
    EditText for_incount;
    EditText incomingprice;
    ImageView barcodescan;
    STovar sTovar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        intent=getIntent();
        saveProduct=findViewById(R.id.product_add_save_product_for_add);
        barcodescan=findViewById(R.id.product_add_barcodescan);
        name=findViewById(R.id.product_add_name);
        name_short=findViewById(R.id.product_add_name_short);
        in_count=findViewById(R.id.product_add_in_count);
        barcode1=findViewById(R.id.product_add_barcode1);
        barcode2=findViewById(R.id.product_add_barcode2);
        barcode3=findViewById(R.id.product_add_barcode3);
        type1=findViewById(R.id.product_add_type1);
        type2=findViewById(R.id.product_add_type2);
        type3=findViewById(R.id.product_add_type3);
        type4=findViewById(R.id.product_add_type4);
        type5=findViewById(R.id.product_add_type5);
        type6=findViewById(R.id.product_add_type6);
        for_count=findViewById(R.id.product_add_for_count);
        for_incount=findViewById(R.id.product_add_for_incount);
        incomingprice=findViewById(R.id.product_add_incomingprice);
        sTovar=new STovar();

        barcodescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ZxingOrient(ProductAdd.this).setIcon(R.mipmap.ic_launcher).initiateScan();
            }
        });
        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void copyPraporty(STovar sTovar) {
        sTovar.setNom(name.getText().toString());
        sTovar.setNom_sh(name_short.getText().toString());
        sTovar.setKol_in(tryParse(in_count.getText().toString()));
        sTovar.setShtrix(barcode1.getText().toString());
        sTovar.setShtrix1(barcode2.getText().toString());
        sTovar.setShtrix2(barcode3.getText().toString());
        sTovar.setSotish(Double.parseDouble(type1.getText().toString()) );
        sTovar.setUlg1(Double.parseDouble(type2.getText().toString()) );
        sTovar.setUlg2(Double.parseDouble(type3.getText().toString()) );
        sTovar.setUlg1_pl(Double.parseDouble(type4.getText().toString()) );
        sTovar.setUlg2_pl(Double.parseDouble(type5.getText().toString()) );
        sTovar.setBank(Double.parseDouble(type6.getText().toString()) );
        sTovar.setShtrixkod(1);
        sTovar.setSena(Double.parseDouble(incomingprice.getText().toString()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        ZxingOrientResult scanResult =
                ZxingOrient.parseActivityResult(requestCode, resultCode, intent);

        if (scanResult != null) {
            // handle the result
            CharSequence c=scanResult.getContents();
            Log.v("MyTag",""+c);
            setText(c);
        }

    }


    public void setText(CharSequence sequence){
        if(tryParse(barcode1.getText().toString())==0){
            barcode1.setText(sequence, TextView.BufferType.EDITABLE);
        }
        else if(tryParse(barcode2.getText().toString())==0){
            barcode2.setText(sequence, TextView.BufferType.EDITABLE);
        }
        else  if(tryParse(barcode3.getText().toString())==0){
            barcode3.setText(sequence, TextView.BufferType.EDITABLE);
        }
        else {
            Toast.makeText(this,"Барча штрих қодлар банд",Toast.LENGTH_LONG).show();
        }
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

    private  class AddNewProduct extends AsyncTask<Void,Void,Void>{

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(ProductAdd.this);
            progressDialog.setMessage("Сақлаyмоқда !!!");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler=new HttpHandler();
            httpHandler.makeServiceAddNewProducts("",sTovar);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            Intent nextIntent = new Intent(ProductAdd.this, TypeChangeActivity.class);
            nextIntent.putExtra("user",intent.getSerializableExtra("user"));
            nextIntent.putExtra("ip",intent.getStringExtra("ip"));
            nextIntent.putExtra("asosId",intent.getIntExtra("asosId",0));
            nextIntent.putExtra("type",intent.getIntExtra("type",0));
            startActivity(nextIntent);
        }
    }

}
