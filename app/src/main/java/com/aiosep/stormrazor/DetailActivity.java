package com.aiosep.stormrazor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    public String tag="AdrianIosepTag";

    private RecyclerView mRecycleView;
    private DetailAdapter myAdapter;
    private RecyclerView.LayoutManager manager;


    private final List<LocationDetails> locationList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final ProgressBar simpleProgressBar = findViewById(R.id.simpleProgressBar);
        simpleProgressBar.setVisibility(View.VISIBLE);

        String woeid=getIntent().getStringExtra("woeid");
        String name=getIntent().getStringExtra("name");
        String temperature=getIntent().getStringExtra("temperature");
        String spec=getIntent().getStringExtra("spec");
        String specabrev=getIntent().getStringExtra("specabrev");

        View layout=findViewById(R.id.topConstraintDetail);
        layout.setBackgroundResource(R.drawable.c);

        switch (specabrev) {
            case "s":
                layout.setBackgroundResource(R.drawable.s);
                break;
            case "sn":
                layout.setBackgroundResource(R.drawable.sn);
                break;
            case "sl":
                layout.setBackgroundResource(R.drawable.sl);
                break;
            case "h":
                layout.setBackgroundResource(R.drawable.h);
                break;
            case "t":
                layout.setBackgroundResource(R.drawable.t);
                break;
            case "hr":
                layout.setBackgroundResource(R.drawable.hr);
                break;
            case "lr":
                layout.setBackgroundResource(R.drawable.lr);
                break;
            case "hc":
                layout.setBackgroundResource(R.drawable.hc);
                break;
            case "lc":
                layout.setBackgroundResource(R.drawable.lc);
                break;
            case "c":
                layout.setBackgroundResource(R.drawable.c);
                break;
        }
        TextView temp = findViewById(R.id.temperatureTextViewDetalis);
        temp.setText(temperature);

        ///SET THE NAME OF LOCATION
        TextView nume = findViewById(R.id.locationTextView);
        nume.setText(name);

        TextView specs = findViewById(R.id.specTextViewDetails);
        specs.setText(spec);

        ImageView img = findViewById(R.id.locationImageView);
        String url = "https://www.metaweather.com/static/img/weather/png/";
        url += specabrev;
        url += ".png";
        Glide.with(DetailActivity.this)
                .load(url)
                .into(img);



        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        MyApiService service= retrofit.create(MyApiService.class);

        Call<LocationEntity> call1=service.getLocationDetails(woeid);
        call1.enqueue(new Callback<LocationEntity>() {
            @Override
            public void onResponse(Call<LocationEntity> call, Response<LocationEntity> response) {
                LocationEntity locationEntity=response.body();




                    TextView currentDate = findViewById(R.id.dateTextView);
                    String date = "";
                    String[] arrOfStrDate = String.valueOf(locationEntity.getConsolidatedWeather().get(0).getApplicableDate()).split("-", 3);
                    date += arrOfStrDate[2];
                    date += ".";
                    date += arrOfStrDate[1];
                    date += ".";
                    date += arrOfStrDate[0];
                    currentDate.setText(date);







                    //SET MIN TEMP
                    TextView minTemp = findViewById(R.id.minTemp1TextViewDetails);
                    String mintemp = "L:";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMinTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMinTemp()).charAt(i) != '.') {
                            mintemp += String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMinTemp()).charAt(i);
                        } else
                            break;
                    }
                    mintemp += "°";
                    minTemp.setText(mintemp);


                    //SET MAX TEMP
                    TextView macTemp = findViewById(R.id.maxTemp1TextViewDetails);
                    String maxtemp = "H:";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMaxTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMaxTemp()).charAt(i) != '.') {
                            maxtemp += String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMaxTemp()).charAt(i);
                        } else
                            break;
                    }
                    maxtemp += "°";
                    macTemp.setText(maxtemp);




                    TextView windSpeed = findViewById(R.id.windSpeedValueTextView);
                    String speed = "";
                    String[] arrOfStr = String.valueOf(locationEntity.getConsolidatedWeather().get(0).getWindSpeed()).split("\\.", 2);
                    speed += arrOfStr[0];
                    speed += ".";
                    if (arrOfStr[1].length() > 1)
                        speed += arrOfStr[1].substring(0, 2);
                    else speed += arrOfStr[1];
                    speed += " km/hr";
                    windSpeed.setText(speed);

                    TextView windDirection = findViewById(R.id.windDirectionValueTextView);
                    String direction = "";
                    String[] arrOfStr2 = String.valueOf(locationEntity.getConsolidatedWeather().get(0).getWindDirection()).split("\\.", 2);
                    direction += arrOfStr2[0];
                    direction += ".";
                    if (arrOfStr2[1].length() > 1)
                        direction += arrOfStr2[1].substring(0, 2);
                    else direction += arrOfStr2[1];
                    direction += "°";
                    windDirection.setText(direction);

                    TextView airPressure = findViewById(R.id.airPressureValueTextView);
                    airPressure.setText(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getAirPressure()) + " hPa");

                    TextView humidity = findViewById(R.id.HumidityValueTextView);
                    humidity.setText(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getHumidity()) + "%");

                    TextView visibility = findViewById(R.id.visibilityValueTextView);
                    String visib = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(0).getVisibility()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(0).getVisibility()).charAt(i) != '.') {
                            visib += String.valueOf(locationEntity.getConsolidatedWeather().get(0).getVisibility()).charAt(i);
                        } else
                            break;
                    }
                    visib += " km";
                    visibility.setText(visib);

                    TextView predictability = findViewById(R.id.predictabilityValueTextView);
                    predictability.setText(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getPredictability()) + "%");


                    //////// add prediction for 5 days

                    String maxtemp1 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMaxTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMaxTemp()).charAt(i) != '.') {
                            maxtemp1 += String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMaxTemp()).charAt(i);
                        } else
                            break;
                    }
                    String mintemp1 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMinTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMinTemp()).charAt(i) != '.') {
                            mintemp1 += String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMinTemp()).charAt(i);
                        } else
                            break;
                    }
                    locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(1).getApplicableDate(),
                            String.valueOf(locationEntity.getConsolidatedWeather().get(1).getPredictability()) + "%",
                            maxtemp1,
                            mintemp1,
                            locationEntity.getConsolidatedWeather().get(1).getWeatherStateAbbr()));
                    String maxtemp2 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMaxTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMaxTemp()).charAt(i) != '.') {
                            maxtemp2 += String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMaxTemp()).charAt(i);
                        } else
                            break;
                    }
                    String mintemp2 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMinTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMinTemp()).charAt(i) != '.') {
                            mintemp2 += String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMinTemp()).charAt(i);
                        } else
                            break;
                    }
                    locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(2).getApplicableDate(),
                            String.valueOf(locationEntity.getConsolidatedWeather().get(2).getPredictability()) + "%",
                            maxtemp2,
                            mintemp2,
                            locationEntity.getConsolidatedWeather().get(2).getWeatherStateAbbr()));

                    String maxtemp3 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMaxTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMaxTemp()).charAt(i) != '.') {
                            maxtemp3 += String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMaxTemp()).charAt(i);
                        } else
                            break;
                    }
                    String mintemp3 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMinTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMinTemp()).charAt(i) != '.') {
                            mintemp3 += String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMinTemp()).charAt(i);
                        } else
                            break;
                    }
                    locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(3).getApplicableDate(),
                            String.valueOf(locationEntity.getConsolidatedWeather().get(3).getPredictability()) + "%",
                            maxtemp3,
                            mintemp3,
                            locationEntity.getConsolidatedWeather().get(3).getWeatherStateAbbr()));

                    String maxtemp4 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMaxTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMaxTemp()).charAt(i) != '.') {
                            maxtemp4 += String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMaxTemp()).charAt(i);
                        } else
                            break;
                    }
                    String mintemp4 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMinTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMinTemp()).charAt(i) != '.') {
                            mintemp4 += String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMinTemp()).charAt(i);
                        } else
                            break;
                    }
                    locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(4).getApplicableDate(),
                            String.valueOf(locationEntity.getConsolidatedWeather().get(4).getPredictability()) + "%",
                            maxtemp4,
                            mintemp4,
                            locationEntity.getConsolidatedWeather().get(4).getWeatherStateAbbr()));

                    String maxtemp5 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMaxTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMaxTemp()).charAt(i) != '.') {
                            maxtemp5 += String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMaxTemp()).charAt(i);
                        } else
                            break;
                    }
                    String mintemp5 = "";
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMinTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMinTemp()).charAt(i) != '.') {
                            mintemp5 += String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMinTemp()).charAt(i);
                        } else
                            break;
                    }
                    locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(5).getApplicableDate(),
                            String.valueOf(locationEntity.getConsolidatedWeather().get(5).getPredictability()) + "%",
                            maxtemp5,
                            mintemp5,
                            locationEntity.getConsolidatedWeather().get(5).getWeatherStateAbbr()));

                    mRecycleView = findViewById(R.id.detail_recyclerView);
                    manager = new LinearLayoutManager(DetailActivity.this);
                    mRecycleView.setLayoutManager(manager);
                    myAdapter = new DetailAdapter(locationList, DetailActivity.this);
                    mRecycleView.setAdapter(myAdapter);

                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    TextView windSpeed2 = findViewById(R.id.windspeedTextView);
                    windSpeed.setVisibility(View.VISIBLE);
                    windSpeed2.setVisibility(View.VISIBLE);
                    TextView windDirection2=findViewById(R.id.winddirectionTextView);
                    windDirection.setVisibility(View.VISIBLE);
                    windDirection2.setVisibility(View.VISIBLE);
                    View divider2=findViewById(R.id.divider2);
                    View divider3=findViewById(R.id.divider3);
                    divider2.setVisibility(View.VISIBLE);
                    divider3.setVisibility(View.VISIBLE);
                    TextView airpressure2=findViewById(R.id.airpressureTextView);
                    airPressure.setVisibility(View.VISIBLE);
                    airpressure2.setVisibility(View.VISIBLE);
                    TextView humidity2=findViewById(R.id.HumidityTextView);
                    humidity.setVisibility(View.VISIBLE);
                    humidity2.setVisibility(View.VISIBLE);
                    View divider4=findViewById(R.id.divider4);
                    divider4.setVisibility(View.VISIBLE);
                    TextView visibility2=findViewById(R.id.visibilityTextView);
                    visibility.setVisibility(View.VISIBLE);
                    visibility2.setVisibility(View.VISIBLE);
                    TextView predictability2=findViewById(R.id.PredictabilityTextView);
                    predictability.setVisibility(View.VISIBLE);
                    predictability2.setVisibility(View.VISIBLE);
                    View divider5=findViewById(R.id.divider5);
                    divider5.setVisibility(View.VISIBLE);
                }



            @Override
            public void onFailure(Call<LocationEntity> call, Throwable t) {
                Log.v(tag,t.getLocalizedMessage());

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /////////////////////////////////////////////////////////////////////////////////////




    }

 /*   private void addLocationDetail() {
        LocationDetails l1=new LocationDetails("Friday","70%","30","18");
        LocationDetails l2=new LocationDetails("Saturday","","32","17");
        LocationDetails l3=new LocationDetails("Sunday","","32","18");
        LocationDetails l4=new LocationDetails("Monday","70%","28","18");
        LocationDetails l5=new LocationDetails("Tuesday","70%","25","17");
        LocationDetails l6=new LocationDetails("Wednesday","70%","24","15");
        LocationDetails l7=new LocationDetails("Thursday","","27","13");
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);
        locationList.add(l6);
        locationList.add(l7);
    }*/
}