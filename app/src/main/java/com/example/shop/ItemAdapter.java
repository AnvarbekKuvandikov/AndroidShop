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

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<AddItems> {
    private ArrayList<AddItems> items;
    private Context context;
    private int resource;
    public ItemAdapter(Context context, int resource,  ArrayList<AddItems> items) {
        super(context, resource, items);
        this.items=items;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        AddItems item=getItem(position);
        Log.v("MyLog2",item.getId().toString());
        Log.v("MyLog2",item.getName());
        LayoutInflater inflater =LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.list_item, parent, false);

        ((TextView)convertView.findViewById(R.id.item_name)).setText(item.getName());
        ((TextView)convertView.findViewById(R.id.item_count)).setText(item.getCount());
        ((TextView)convertView.findViewById(R.id.item_incount)).setText(item.getIncount());
        ((TextView)convertView.findViewById(R.id.item_sum)).setText(0);

        convertView.setTag(item);
        return convertView;

    }


}
