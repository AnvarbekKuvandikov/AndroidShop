package com.example.shop;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;
/*
* Shuni ishlatish garak
* */
public class CustomFilter extends Filter {

    private ArrayList<Product> filterList;
    private ProductAdapter adapter;

    public CustomFilter(ArrayList<Product> filterList,ProductAdapter adapter) {
        this.filterList = filterList;
        this.adapter=adapter;
    }



    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results=new FilterResults();

        if(charSequence != null && charSequence.length()>0) {

            charSequence=charSequence.toString().toUpperCase();

            ArrayList<Product> filteredMovies=new ArrayList<>();

            for(int i=0;i<filterList.size();i++) {
                if(filterList.get(i).getName().toUpperCase().contains(charSequence)) {
                    filteredMovies.add(filterList.get(i));
                }
            }

            results.count=filteredMovies.size();
            results.values=filteredMovies;
        }
        else {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }


    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.list= (ArrayList<Product>) filterResults.values;
        adapter.notifyDataSetChanged();
    }

}