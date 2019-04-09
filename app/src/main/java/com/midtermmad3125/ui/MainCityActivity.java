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

    public void weatherDetailsclick(View view){
        Intent iIntend = new Intent(MainCityActivity.this,WeatherListActivity.class);
        startActivity(iIntend);
    }

    public void ProcessJsonData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject Data = new JSONObject(JsonData);
            JSONObject cityObject = Data.getJSONObject("city");
            cityName.setText(cityObject.getString("name"));

            JSONObject cityCoordObject = cityObject.getJSONObject("coord");
            cityLong.setText("Longitude: "+cityCoordObject.getString("lon"));


            cityLat.setText("Latitude: "+cityCoordObject.getString("lat"));


            cityCode.setText("Country: "+cityObject.getString("country"));



            cityPopulation.setText("Population: "+cityObject.getString("population"));

            JSONArray List = Data.getJSONArray("list");
            for(int i = 0; i < List.length(); i++){
                JSONObject weatherDetail = List.getJSONObject(i);

                String Date = weatherDetail.getString("date");
                String pressure = weatherDetail.getString("pressure");
                String humidity = weatherDetail.getString("humidity");
                String speed = weatherDetail.getString("speed");
                String degree = weatherDetail.getString("degree");
                String clouds = weatherDetail.getString("cloudy");
                String rain = weatherDetail.getString("rainy");

//                weatherclassArray.add(new weatherList())
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}