package com.midtermmad3125.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainCityActivity extends AppCompatActivity
{

    private TextView cityName;
    private TextView cityLat;
    private TextView cityLong;
    private TextView cityCode;
    private TextView cityPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = findViewById(R.id.CityName);
        cityLat = findViewById(R.id.CityLat);
        cityLong = findViewById(R.id.CityLong);
        cityCode = findViewById(R.id.CityCode);
        cityPopulation = findViewById(R.id.CityPopulation);

        ProcessJsonData();

    }

    public void weatherDetailsclick(View v){
        Intent iIntend = new Intent(MainCityActivity.this,WeatherListActivity.class);
        startActivity(iIntend);
    }

    public void ProcessJsonData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject Data = new JSONObject(JsonData);
            JSONObject cityObject= Data.getJSONObject("city");
            cityName.setText(cityObject.getString("name"));

            JSONObject cityCoordObject= cityObject.getJSONObject("coord");
            cityLong.setText("Longitude: "+cityCoordObject.getString("long"));
            cityLat.setText("Latitude: "+cityCoordObject.getString("lat"));

            cityCode.setText("Country: "+cityObject.getString("country"));
            cityPopulation.setText("Population: "+cityObject.getString("population"));

            JSONArray wList = Data.getJSONArray("list");
            for(int y2 = 0; y2 < wList.length(); y2++){
                JSONObject weatherDetail = wList.getJSONObject(y2);
                String weatherdate = weatherDetail.getString("dt");


                String weatherDate = weatherDetail.getString("dt");
                String weatherpressure = weatherDetail.getString("pressure");
                String weatherhumidity = weatherDetail.getString("humidity");
                String weatherspeed = weatherDetail.getString("speed");
                String weatherdeg = weatherDetail.getString("degree");
                String weatherclouds = weatherDetail.getString("cloudy");
                String weatherrain = weatherDetail.getString("rainy");

//                weatherclassArray.add(new weatherList())
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}