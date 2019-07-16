package com.example.shop;

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

public class ProductAdapter extends ArrayAdapter<Product> {

    public ArrayList<Product> list;
    public ArrayList<Product> originalList = new ArrayList<>();
    private CustomFilter filter;


    public ProductAdapter( Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        list=products;
        originalList.addAll(products);
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        Product product=getItem(position);
        LayoutInflater inflater =LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.products_item, parent, false);

        ((TextView)convertView.findViewById(R.id.product_name)).setText(product.getName());
        ((TextView)convertView.findViewById(R.id.product_prices)).setText("("+product.getInprice()+", "+product.getPrice()+")");

        convertView.setTag(product);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(originalList,this);
        }

        return filter;
    }
}
