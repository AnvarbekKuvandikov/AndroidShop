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

public class ItemAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> items;
    private Context context;
    private int resource;
    public ItemAdapter(Context context, int resource,  ArrayList<Product> items) {
        super(context, resource, items);
        this.items=items;
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Product product=getItem(position);
        LayoutInflater inflater =LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.list_item, parent, false);

        ((TextView)convertView.findViewById(R.id.item_name)).setText(product.getName());
        ((TextView)convertView.findViewById(R.id.item_count)).setText(product.getCount());
        ((TextView)convertView.findViewById(R.id.item_incount)).setText(product.getIncount());
        ((TextView)convertView.findViewById(R.id.item_sum)).setText(product.getCount()*product.getPrice()+ product.getCount()*product.getInprice());

        convertView.setTag(product);
        return convertView;

    }


}
