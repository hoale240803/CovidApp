package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.Controller.Country;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTotalConfirmed,tvTotalDeaths,tvTotalRecovered;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTotalConfirmed=findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths=findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered=findViewById(R.id.tvTotalRecovered);
        progressBar=findViewById(R.id.progress_circular_home);
        // call by Volley
        getData();

    }

    private void getData(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvTotalConfirmed.setText(jsonObject.getString("cases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTotalRecovered.setText(jsonObject.getString("recovered"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response", error.toString());
            }
        });
        queue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTotalConfirmed:
            case R.id.tvTotalDeaths:
            case R.id.tvTotalRecovered:
                Intent intent=new Intent(MainActivity.this, Country.class);
                startActivity(intent);
                break;
        }
    }
}
