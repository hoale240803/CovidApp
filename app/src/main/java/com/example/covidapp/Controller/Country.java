package com.example.covidapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Country extends AppCompatActivity {
    private ListView countryListView;
    List<CovidCountry> covidCountries;
    private final static String TAG= Country.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        countryListView=findViewById(R.id.country_listview);
//        getDataFromServer();
        covidCountries=new ArrayList<>();
        covidCountries.add( new CovidCountry("Viet Nam","123",R.drawable.ic_dashboard_black_24dp));
        covidCountries.add( new CovidCountry("Viet Nam 1","124",R.drawable.ic_dashboard_black_24dp));
        covidCountries.add( new CovidCountry("Viet Nam 2","125",R.drawable.ic_dashboard_black_24dp));
        CovidCountryAdapter adapter=new CovidCountryAdapter(this, R.layout.country_list_item,covidCountries);
        countryListView.setAdapter(adapter);
        countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CovidCountry temp=(CovidCountry) countryListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Selected Country "+temp.getCovidCountry(),Toast.LENGTH_SHORT).show();
            }
        });
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
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"onResponse" + error);
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
