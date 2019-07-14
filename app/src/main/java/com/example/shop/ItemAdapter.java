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
    private ArrayList<Item> items;
    public ItemAdapter(Context context, int resource,  ArrayList<Item> items) {
        super(context, resource, items);
        this.items=items;
    }


    @SuppressLint("StringFormatInvalid")
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Item item=getItem(position);
        ViewHolder viewHolder;
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.list_item, parent, false);

        if(convertView==null){
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.name);
            viewHolder.count=(TextView) convertView.findViewById(R.id.count);
            viewHolder.incount=(TextView) convertView.findViewById(R.id.incount);
            viewHolder.sum=(TextView) convertView.findViewById(R.id.sum);
            viewHolder.delete=(ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
            ((TextView)convertView.findViewById(R.id.name)).setText(item.getName());
            ((TextView)convertView.findViewById(R.id.count)).setText(item.getCount());
            ((TextView)convertView.findViewById(R.id.incount)).setText(item.getIncount());
            ((TextView)convertView.findViewById(R.id.sum)).setText(item.getPrice()+item.getInprice());

        }

        convertView.setTag(item);
        return convertView;



    }

}
