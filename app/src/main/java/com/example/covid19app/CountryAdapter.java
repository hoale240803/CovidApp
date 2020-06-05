package com.example.covid19app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class CountryAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> countryModels;
    private List<CountryModel> countryModelsFiltered;

    public CountryAdapter(@NonNull Context context, List<CountryModel> listCountry) {
        super(context, R.layout.list_country_item, listCountry);
        this.context=context;
        this.countryModels=listCountry;
        this.countryModelsFiltered=listCountry;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country_item,null, true);
        TextView tvCountry=view.findViewById(R.id.countryName);
        ImageView imgCountry=view.findViewById(R.id.imageFlag);
        tvCountry.setText(countryModelsFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelsFiltered.get(position).getFlag()).into(imgCountry);
        return view;
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelsFiltered.get(position);
    }

    @Override
    public int getCount() {
        return countryModelsFiltered.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint==null|| constraint.length()==0){
                    filterResults.count=countryModels.size();
                    filterResults.values=countryModels;
                }else{
                    List<CountryModel> resultCountry= new ArrayList<>();
                    String searchString= constraint.toString().toLowerCase();
                    for(CountryModel country: countryModels){
                        if(country.getCountry().toLowerCase().contains(searchString)){
                            resultCountry.add(country);
                        }
                        filterResults.count=resultCountry.size();
                        filterResults.values=resultCountry;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelsFiltered =(List<CountryModel>) results.values;
                Covid_Country.countries=(List<CountryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
