package com.example.shop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        intent=getIntent();
        saveProduct=findViewById(R.id.product_add_save_product_for_add);
        barcodescan=findViewById(R.id.product_add_barcodescan);

        barcode1=findViewById(R.id.product_add_barcode1);
        barcode2=findViewById(R.id.product_add_barcode2);
        barcode3=findViewById(R.id.product_add_barcode3);

        barcodescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ZxingOrient(ProductAdd.this).setIcon(R.mipmap.ic_launcher).initiateScan();
            }
        });
        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductAdd.this,"Ura",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        ZxingOrientResult scanResult =
                ZxingOrient.parseActivityResult(requestCode, resultCode, intent);

        if (scanResult != null) {
            // handle the result
           if(tryParse(barcode1.getText().toString())<0){
               CharSequence c=scanResult.getContents();
               barcode1.setText("ha", TextView.BufferType.EDITABLE);
           }

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