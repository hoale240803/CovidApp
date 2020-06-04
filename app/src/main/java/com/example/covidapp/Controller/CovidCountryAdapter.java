package com.example.covidapp.Controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covidapp.R;

import java.util.ArrayList;
import java.util.List;

public class CovidCountryAdapter extends ArrayAdapter<CovidCountry> {
    private  Context context;
    private int  resource;
    private List<CovidCountry> listCountry;

    public CovidCountryAdapter(@NonNull Context context1, int resource, @NonNull List<CovidCountry> list) {
        super(context1, resource, list);
        this.context = context1;
        this.resource = resource;
        this.listCountry = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(resource,null);
        TextView textView_country= view.findViewById(R.id.textView_country);
        TextView textView_totalCases= view.findViewById(R.id.textView_totalcases);
        ImageView imageView=view.findViewById(R.id.imageView);

        CovidCountry covidCountry=listCountry.get(position);
        textView_country.setText(covidCountry.getCovidCountry());
        textView_totalCases.setText(covidCountry.getCases());
        imageView.setImageDrawable(context.getResources().getDrawable(covidCountry.getImage()));
        return  view;
    }
}
