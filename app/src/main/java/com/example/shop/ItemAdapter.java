package com.example.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<AddItems> {
    private ArrayList<AddItems> items;
    private Context context;
    private int resource;

    public ItemAdapter(Context context, int resource, ArrayList<AddItems> items) {
        super(context, resource,items);
        this.items = items;
        this.context = context;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        AddItems item=getItem(position);
        Log.v("MyLog2",item.getId().toString());
        Log.v("MyLog2",item.getName());
        if(convertView==null){
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.list_item, parent, false);
        }

       if(item!=null) {
        Log.v("MyLog2",((TextView)convertView.findViewById(R.id.item_name)).getText().toString());
           TextView itemName=(TextView) convertView.findViewById(R.id.item_name);
           TextView itemCount=(TextView) convertView.findViewById(R.id.item_count);
           TextView  itemIncount=(TextView) convertView.findViewById(R.id.item_incount);
           TextView  itemSum=(TextView) convertView.findViewById(R.id.item_sum);
           if(itemName!=null)
               itemName.setText("anvar");
           if(itemCount!=null)
               itemCount.setText(3);
           if (itemIncount!=null)
               itemIncount.setText(15);
           if(itemSum!=null)
               itemSum.setText(1223);

       }

       convertView.setTag(item);
       return convertView;

    }


}
