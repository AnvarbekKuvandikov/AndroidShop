package com.example.shop;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;

public class IncomingProducts extends AppCompatActivity {
    DatePickerDialog picker;
    EditText incomingDate;
    EditText incomingNum;
    AutoCompleteTextView incomingClient;
    RadioButton incomingDollar;
    Button incomingBack;
    Button incomingSave;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomingproducts);
        incomingDate=findViewById(R.id.incoming_date);
        incomingDate.setInputType(InputType.TYPE_NULL);
        incomingNum=findViewById(R.id.incoming_num);
        incomingClient=findViewById(R.id.incoming_client);
        incomingDollar=findViewById(R.id.incoming_dollar);
        incomingBack=findViewById(R.id.incoming_back);
        incomingSave=findViewById(R.id.incoming_save);

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
}
