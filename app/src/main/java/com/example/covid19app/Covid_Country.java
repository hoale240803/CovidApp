package com.example.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Covid_Country extends AppCompatActivity {

    EditText editText;
    ListView listView;
    SimpleArcLoader loader;
    protected static List<CountryModel> countries= new ArrayList<>();

    CountryModel countryModel;
    CountryAdapter countryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid__country);

        editText=findViewById(R.id.editSearch);
        listView=findViewById(R.id.listView);
        loader=findViewById(R.id.loader);
        //support action bar
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fetchData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailCountry.class).putExtra("position",position));
            }
        });

        //search changed
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ontextchanged
                countryAdapter.getFilter().filter(s);
                countryAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/countries";
        loader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String countryName=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String deaths=jsonObject.getString("deaths");
                        String active=jsonObject.getString("active");
                        String recovered=jsonObject.getString("recovered");
                        JSONObject object= jsonObject.getJSONObject("countryInfo");
                        String flag = object.getString("flag");
                        countryModel=new CountryModel(flag,countryName,cases,"",deaths,"",recovered,active);
                        countries.add(countryModel);
                    }
                    countryAdapter=new CountryAdapter(Covid_Country.this,countries);
                    listView.setAdapter(countryAdapter);
                    loader.stop();
                    loader.setVisibility(View.GONE);
                } catch (JSONException e) {
                    loader.stop();
                    loader.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.stop();
                loader.setVisibility(View.GONE);
                Toast.makeText(Covid_Country.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    protected void onResume() {
        countries.clear();
        super.onResume();
    }
}
