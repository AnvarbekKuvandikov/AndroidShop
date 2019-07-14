
package com.example.shop;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class CustomFilter extends Filter {

    private ArrayList<Product> filterList;
    private ProductAdapter adapter;
    private FilterResults results;
    public CustomFilter(ArrayList<Product> filterList,ProductAdapter adapter) {
        this.filterList = filterList;
        this.adapter=adapter;
    }



    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        results=new FilterResults();

        if(charSequence != null && charSequence.length()>0) {

            charSequence=charSequence.toString().toUpperCase();

            String begin="";
            String end="";
            if(((String) charSequence).indexOf(' ')>0 && ((String) charSequence).indexOf(' ')<charSequence.length()-1){
                begin=((String) charSequence).substring(0,((String) charSequence).indexOf(' '));
                end=((String) charSequence).substring(((String) charSequence).lastIndexOf(' ')+1,charSequence.length());
            }
            ArrayList<Product> filteredMovies=new ArrayList<>();

            if(!begin.isEmpty() && !end.isEmpty() ) {
//                Log.v("MyLog", begin + " " + end);
                for(int i=0;i<filterList.size();i++) {
                    if(filterList.get(i).getName().toUpperCase().indexOf(begin)==0 && filterList.get(i).getName().toUpperCase().contains(end) ) {
//                    Log.v("MyLog", String.valueOf(filterList.get(i).getName().toUpperCase().contains(end)));
                        filteredMovies.add(filterList.get(i));
                    }
                }
            }

            else {
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getName().toUpperCase().indexOf((String) charSequence) == 0) {
                        filteredMovies.add(filterList.get(i));
                    }
                }
            }

            results.count=filteredMovies.size();
            results.values=filteredMovies;
        }
        else {
            results.count=adapter.originalList.size();
            results.values=adapter.originalList;
        }

        return results;
    }


    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.clear();
        adapter.addAll((ArrayList<Product>) filterResults.values);
        adapter.notifyDataSetChanged();
    }

}