package com.example.covidapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Country extends AppCompatActivity {

    ProgressBar progressBar;
    ArrayList<CovidCountry> covidCountries;
    private final static String TAG= Country.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
//        progressBar=findViewById(R.id.progress_circular_country);
        getDataFromServer();
    }
    private void showRecyclerView(){
        CovidCountryAdapter covidCountryAdapter=new CovidCountryAdapter(covidCountries);
        rvCovidCountry.setAdapter(covidCountryAdapter);
    }
    private void getDataFromServer(){
        String url="https://corona.lmao.ninja/v2/countries";
        covidCountries= new ArrayList<>();
        StringRequest stringRequest=new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "on Response"+response);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            covidCountries.add(new CovidCountry(jsonObject.getString("country"),jsonObject.getString("cases")));
                        }
                        showRecyclerView();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
                Log.e(TAG,"onResponse" + error);
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
