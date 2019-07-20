package com.example.shop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Product> {

    private  ItemAdapter adapter;
    public ItemAdapter(Context context, int resource, ArrayList<Product> items) {
        super(context,resource, items);
        this.adapter=this;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        final Product item=getItem(position);
        if (convertView == null) {
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.list_item, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.item_name)).setText(item.getName());
        ((TextView)convertView.findViewById(R.id.item_count)).setText(item.getCount().toString());
        ((TextView)convertView.findViewById(R.id.item_incount)).setText(item.getIncount().toString());
        ((TextView)convertView.findViewById(R.id.item_sum)).setText(String.valueOf(item.getCount()*item.getPrice()+item.getIncount()*item.getInprice() ));
        ((ImageView)convertView.findViewById(R.id.item_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                adapter.remove(item);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Сиз ростан хам ўчиришни истайсизми?").setPositiveButton("Ха", dialogClickListener)
                        .setNegativeButton("Йўқ", dialogClickListener).show();
            }
        });
       convertView.setTag(item);
       return convertView;
    }




}
