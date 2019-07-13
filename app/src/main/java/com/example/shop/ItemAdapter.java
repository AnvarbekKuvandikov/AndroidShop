package com.example.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, int resource,  ArrayList<Item> items) {
        super(context, resource, items);
    }


    @SuppressLint("StringFormatInvalid")
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Item item=getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name=(TextView) convertView.findViewById(R.id.name);
            viewHolder.count=(TextView) convertView.findViewById(R.id.count);
            viewHolder.price=(TextView) convertView.findViewById(R.id.price);
            viewHolder.sum=(TextView) convertView.findViewById(R.id.sum);
            viewHolder.delete=(ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        convertView.setTag(item);
        return convertView;



    }

}
