package com.example.shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IncomingWork extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_work);
        intent=getIntent();

    }
    public void setDownIntent(Intent nextIntent) {
        nextIntent.putExtra("user",intent.getSerializableExtra("user"));
        nextIntent.putExtra("ip",intent.getStringExtra("ip"));
    }


    public void clickSell(View view) {
        Intent nextInten=new Intent(IncomingWork.this,TypeChangeActivity.class);
        setDownIntent(nextInten);
        startActivity(nextInten);
        finish();
    }

    public void clickProducts(View view) {
        Intent nextInten=new Intent(IncomingWork.this,ProductsList.class);
        setDownIntent(nextInten);
        startActivity(nextInten);
        finish();
    }

    public void clickIncomings(View view) {
        Intent nextInten=new Intent(IncomingWork.this,IncomingProducts.class);
        setDownIntent(nextInten);
        startActivity(nextInten);
        finish();
    }
    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(IncomingWork.this);
        builder.setMessage("Дастурдан чикишни истайсизми?").setPositiveButton("Ха", dialogClickListener)
                .setNegativeButton("Йўқ", dialogClickListener).show();
    }
}
