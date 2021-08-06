package com.aiosep.stormrazor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchDetailsActivity extends AppCompatActivity {
    public String tag="AdrianIosepTag";

    private RecyclerView mRecycleView;
    private SearchDetailsAdapter myAdapter;
    private RecyclerView.LayoutManager manager;


    private LocationDatabase mDb;
    private List<LocationDetails> locationList=new ArrayList<LocationDetails>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        String woeid=getIntent().getStringExtra("woeid");

        final ProgressBar simpleProgressBar = findViewById(R.id.searchDetailProgressBar);
        simpleProgressBar.setVisibility(View.VISIBLE);
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
                Log.v(tag,locationEntity.toString());


                View layout=findViewById(R.id.topConstraintSearchDetail);
                layout.setBackgroundResource(R.drawable.c);

                switch (locationEntity.getConsolidatedWeather().get(0).getWeatherStateAbbr()){
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

                TextView currentDate=findViewById(R.id.dateSearchDetailTextView);
                String date="";
                String[] arrOfStrDate = String.valueOf(locationEntity.getConsolidatedWeather().get(0).getApplicableDate()).split("-", 3);
                date+=arrOfStrDate[2];
                date+=".";
                date+=arrOfStrDate[1];
                date+=".";
                date+=arrOfStrDate[0];
                currentDate.setText(date);

                //set the temperature
                String temp="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i)!='.'){
                        temp+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i);
                    }
                    else
                        break;
                }
                temp+="°";

                TextView temperature=findViewById(R.id.temperatureTextViewSearchDetail);
                temperature.setText(temp);

                ///SET THE NAME OF LOCATION
                TextView nume=findViewById(R.id.locationSearchDetailTextView);
                nume.setText(locationEntity.getTitle());

                TextView spec=findViewById(R.id.specTextViewSearchDetails);
                spec.setText(locationEntity.getConsolidatedWeather().get(0).getWeatherStateName());

                //SET MIN TEMP
                TextView minTemp=findViewById(R.id.minTemp1TextViewSearchDetail);
                String mintemp="L:";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMinTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMinTemp()).charAt(i)!='.'){
                        mintemp+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMinTemp()).charAt(i);
                    }
                    else
                        break;
                }
                mintemp+="°";
                minTemp.setText(mintemp);


                //SET MAX TEMP
                TextView macTemp=findViewById(R.id.maxTemp1TextViewSearchDetail);
                String maxtemp="H:";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMaxTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMaxTemp()).charAt(i)!='.'){
                        maxtemp+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getMaxTemp()).charAt(i);
                    }
                    else
                        break;
                }
                maxtemp+="°";
                macTemp.setText(maxtemp);

                ImageView img=findViewById(R.id.locationSearchDetailImageView);
                String url="https://www.metaweather.com/static/img/weather/png/";
                url+=locationEntity.getConsolidatedWeather().get(0).getWeatherStateAbbr();
                url+=".png";
                Glide.with(SearchDetailsActivity.this)
                        .load(url)
                        .into(img);


                TextView windSpeed=findViewById(R.id.windSpeedValueSearchDetailTextView);
                String speed="";
                String[] arrOfStr = String.valueOf(locationEntity.getConsolidatedWeather().get(0).getWindSpeed()).split("\\.", 2);
                speed+=arrOfStr[0];
                speed+=".";
                if(arrOfStr[1].length()>1)
                    speed+=arrOfStr[1].substring(0,2);
                else speed+=arrOfStr[1];
                speed+=" km/hr";
                windSpeed.setText(speed);

                TextView windDirection=findViewById(R.id.windDirectionValueSearchDetailTextView);
                String direction="";
                String[] arrOfStr2= String.valueOf(locationEntity.getConsolidatedWeather().get(0).getWindDirection()).split("\\.",2);
                direction+=arrOfStr2[0];
                direction+=".";
                if(arrOfStr2[1].length()>1)
                    direction+=arrOfStr2[1].substring(0,2);
                else direction+=arrOfStr2[1];
                direction+="°";
                windDirection.setText(direction);

                TextView airPressure=findViewById(R.id.airPressureValueSearchDetailTextView);
                airPressure.setText(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getAirPressure())+" hPa");

                TextView humidity=findViewById(R.id.HumidityValueSearchDetailTextView);
                humidity.setText(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getHumidity())+"%");

                TextView visibility=findViewById(R.id.visibilityValueSearchDetailTextView);
                String visib="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getVisibility()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getVisibility()).charAt(i)!='.'){
                        visib+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getVisibility()).charAt(i);
                    }
                    else
                        break;
                }
                visib+=" km";
                visibility.setText(visib);

                TextView predictability=findViewById(R.id.predictabilityValueSearchDetailTextView);
                predictability.setText(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getPredictability())+"%");






                //////// add prediction for 5 days

                String maxtemp1="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMaxTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMaxTemp()).charAt(i)!='.'){
                        maxtemp1+=String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMaxTemp()).charAt(i);
                    }
                    else
                        break;
                }
                String mintemp1="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMinTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMinTemp()).charAt(i)!='.'){
                        mintemp1+=String.valueOf(locationEntity.getConsolidatedWeather().get(1).getMinTemp()).charAt(i);
                    }
                    else
                        break;
                }
                locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(1).getApplicableDate(),
                        String.valueOf(locationEntity.getConsolidatedWeather().get(1).getPredictability())+"%",
                        maxtemp1,
                        mintemp1,
                        locationEntity.getConsolidatedWeather().get(1).getWeatherStateAbbr()));
                String maxtemp2="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMaxTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMaxTemp()).charAt(i)!='.'){
                        maxtemp2+=String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMaxTemp()).charAt(i);
                    }
                    else
                        break;
                }
                String mintemp2="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMinTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMinTemp()).charAt(i)!='.'){
                        mintemp2+=String.valueOf(locationEntity.getConsolidatedWeather().get(2).getMinTemp()).charAt(i);
                    }
                    else
                        break;
                }
                locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(2).getApplicableDate(),
                        String.valueOf(locationEntity.getConsolidatedWeather().get(2).getPredictability())+"%",
                        maxtemp2,
                        mintemp2,
                        locationEntity.getConsolidatedWeather().get(2).getWeatherStateAbbr()));

                String maxtemp3="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMaxTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMaxTemp()).charAt(i)!='.'){
                        maxtemp3+=String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMaxTemp()).charAt(i);
                    }
                    else
                        break;
                }
                String mintemp3="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMinTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMinTemp()).charAt(i)!='.'){
                        mintemp3+=String.valueOf(locationEntity.getConsolidatedWeather().get(3).getMinTemp()).charAt(i);
                    }
                    else
                        break;
                }
                locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(3).getApplicableDate(),
                        String.valueOf(locationEntity.getConsolidatedWeather().get(3).getPredictability())+"%",
                        maxtemp3,
                        mintemp3,
                        locationEntity.getConsolidatedWeather().get(3).getWeatherStateAbbr()));

                String maxtemp4="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMaxTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMaxTemp()).charAt(i)!='.'){
                        maxtemp4+=String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMaxTemp()).charAt(i);
                    }
                    else
                        break;
                }
                String mintemp4="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMinTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMinTemp()).charAt(i)!='.'){
                        mintemp4+=String.valueOf(locationEntity.getConsolidatedWeather().get(4).getMinTemp()).charAt(i);
                    }
                    else
                        break;
                }
                locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(4).getApplicableDate(),
                        String.valueOf(locationEntity.getConsolidatedWeather().get(4).getPredictability())+"%",
                        maxtemp4,
                        mintemp4,
                        locationEntity.getConsolidatedWeather().get(4).getWeatherStateAbbr()));

                String maxtemp5="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMaxTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMaxTemp()).charAt(i)!='.'){
                        maxtemp5+=String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMaxTemp()).charAt(i);
                    }
                    else
                        break;
                }
                String mintemp5="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMinTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMinTemp()).charAt(i)!='.'){
                        mintemp5+=String.valueOf(locationEntity.getConsolidatedWeather().get(5).getMinTemp()).charAt(i);
                    }
                    else
                        break;
                }
                locationList.add(new LocationDetails(locationEntity.getConsolidatedWeather().get(5).getApplicableDate(),
                        String.valueOf(locationEntity.getConsolidatedWeather().get(5).getPredictability())+"%",
                        maxtemp5,
                        mintemp5,
                        locationEntity.getConsolidatedWeather().get(5).getWeatherStateAbbr()));

                mRecycleView=findViewById(R.id.SearchDetail_recyclerView);
                manager=new LinearLayoutManager(SearchDetailsActivity.this);
                mRecycleView.setLayoutManager(manager);
                myAdapter=new SearchDetailsAdapter(locationList, SearchDetailsActivity.this);
                mRecycleView.setAdapter(myAdapter);

                simpleProgressBar.setVisibility(View.INVISIBLE);
                currentDate.setVisibility(View.VISIBLE);
                nume.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);
                spec.setVisibility(View.VISIBLE);
                temperature.setVisibility(View.VISIBLE);
                minTemp.setVisibility(View.VISIBLE);
                macTemp.setVisibility(View.VISIBLE);
                View divider1=findViewById(R.id.dividerSearchDetail);
                divider1.setVisibility(View.VISIBLE);
                View divider2=findViewById(R.id.divider2SearchDetail);
                divider2.setVisibility(View.VISIBLE);
                TextView windSpeed2=findViewById(R.id.windspeedSearchDetailTextView);
                windSpeed.setVisibility(View.VISIBLE);
                windSpeed2.setVisibility(View.VISIBLE);
                TextView windDirection2=findViewById(R.id.winddirectionSearchDetailTextView);
                windDirection.setVisibility(View.VISIBLE);
                windDirection2.setVisibility(View.VISIBLE);
                View divider3=findViewById(R.id.divider3SearchDetail);
                divider3.setVisibility(View.VISIBLE);
                TextView airpressure2=findViewById(R.id.airpressureSearchDetailTextView);
                airPressure.setVisibility(View.VISIBLE);
                airpressure2.setVisibility(View.VISIBLE);
                TextView humidity2=findViewById(R.id.HumiditySearchDetailTextView);
                humidity.setVisibility(View.VISIBLE);
                humidity2.setVisibility(View.VISIBLE);
                View divider4=findViewById(R.id.divider4SearchDetail);
                divider4.setVisibility(View.VISIBLE);
                TextView visibility2=findViewById(R.id.visibilitySearchDetailTextView);
                visibility.setVisibility(View.VISIBLE);
                visibility2.setVisibility(View.VISIBLE);
                TextView predictability2=findViewById(R.id.PredictabilitySearchDetailTextView);
                predictability.setVisibility(View.VISIBLE);
                predictability2.setVisibility(View.VISIBLE);
                View divider5=findViewById(R.id.divider5SearchDetail);
                divider5.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<LocationEntity> call, Throwable t) {
                Log.v(tag,t.getLocalizedMessage());

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSearchDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView button=findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(tag,"Add button clicked");

                mDb=LocationDatabase.getInstance(getApplicationContext());
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
                        Log.v(tag,locationEntity.toString());



                        //set the temperature
                        String temp="";
                        for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).length();i++){
                            if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i)!='.'){
                                temp+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i);
                            }
                            else
                                break;
                        }
                        temp+="°";

                        //set the coordinates

                        String coordonate="";
                        if(locationEntity.getLattLong().length()>4)
                            coordonate+=locationEntity.getLattLong().substring(0,5);
                        else coordonate+=locationEntity.getLattLong();
                        coordonate+="°N ";
                        String[] arrOfStr = locationEntity.getLattLong().split(",", 2);
                        String longit=arrOfStr[1];
                        String[] arrOfStr2= longit.split("\\.",2);
                        coordonate+=arrOfStr2[0];
                        coordonate+=".";
                        if(arrOfStr2[1].length()>1)
                            coordonate+=arrOfStr2[1].substring(0,2);
                        else coordonate+=arrOfStr2[1];
                        coordonate+="°E";

                        Location location=new Location(locationEntity.getWoeid(),locationEntity.getTitle(),coordonate,
                                temp,
                                locationEntity.getConsolidatedWeather().get(0).getWeatherStateName(),locationEntity.getConsolidatedWeather().get(0).getWeatherStateAbbr());

                        Log.v(tag,location.toString());
                        int ok=1;
                        for (Location location1:mDb.locationDao().getLocationList()){
                            if (location1.getId()==location.getId())
                                ok=0;
                        }
                        if(ok==1) {
                            mDb.locationDao().insertLocation(location);
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    // if you are redirecting from a fragment then use getActivity() as the context.
                                    Intent intent=new Intent(SearchDetailsActivity.this,MainActivity.class);
                                    SearchDetailsActivity.this.startActivity(intent);
                                    // To close the CurrentActitity, r.g. SpalshActivity
                                    finish();
                                }
                            };
                            Handler h = new Handler();
                            h.postDelayed(r, 1500);
                        }
                        else {
                            AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Error");
                            builder.setMessage("This location is alredy in the list");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    // if you are redirecting from a fragment then use getActivity() as the context.
                                    Intent intent=new Intent(SearchDetailsActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    // To close the CurrentActitity, r.g. SpalshActivity

                                }
                            };
                            Handler h = new Handler();
                            h.postDelayed(r, 4000);
                        }

                    }

                    @Override
                    public void onFailure(Call<LocationEntity> call, Throwable t) {
                        Log.v(tag,t.getLocalizedMessage());

                    }
                });


            }
        });


    }
}