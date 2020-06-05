package com.example.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailCountry extends AppCompatActivity {

    private int positionCountry;
    private TextView tvCountry,tvCases,tvDeaths,tvRecovered,tvActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
        // get position of country item
        positionCountry = getIntent().getIntExtra("position",0);
        getSupportActionBar().setTitle("Details of "+ Covid_Country.countries.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //mapping item layout
        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.tvCases);
        tvDeaths=findViewById(R.id.tvDeaths);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvActive=findViewById(R.id.tvActive);
        //coding
        tvCountry.setText(Covid_Country.countries.get(positionCountry).getCountry());
        tvCases.setText(Covid_Country.countries.get(positionCountry).getCases());
        tvDeaths.setText(Covid_Country.countries.get(positionCountry).getDeaths());
        tvRecovered.setText(Covid_Country.countries.get(positionCountry).getRecovered());
        tvActive.setText(Covid_Country.countries.get(positionCountry).getActive());
    }
}
