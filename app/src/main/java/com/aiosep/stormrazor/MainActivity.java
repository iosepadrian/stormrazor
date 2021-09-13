package com.aiosep.stormrazor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnMyItemClickListener {

    public String tag="AdrianIosepTag";
    public int indexStart=0;
    private final List<Location> myLocations=new ArrayList<>();
    private  RecyclerView mRecycleView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager manager;

private LocationDatabase mDb;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(tag," on create ");
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        MyApiService service= retrofit.create(MyApiService.class);
        mDb=LocationDatabase.getInstance(getApplicationContext());

        mRecycleView=findViewById(R.id.main_recycler_view);

        manager=new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(manager);
        myAdapter=new MyAdapter(this,mDb.locationDao().getLocationList());
        mRecycleView.setAdapter(myAdapter);

        myAdapter.setOnMyItemClickListener(this::onMyItemClick);
        for(Location location:mDb.locationDao().getLocationList()){
            Call<LocationEntity> call=service.getLocationDetails(String.valueOf(location.getId()));
            call.enqueue(new Callback<LocationEntity>() {
                @Override
                public void onResponse(Call<LocationEntity> call, Response<LocationEntity> response) {
                    LocationEntity locationEntity=response.body();
                    String temp="";
                    if (locationEntity!=null){
                    for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).length();i++){
                        if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i)!='.'){
                            temp+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i);
                        }
                        else
                            break;
                    }
                    temp+="°";
                    location.setTemperature(temp);
                    location.setSpec(locationEntity.getConsolidatedWeather().get(0).getWeatherStateName());
                    location.setSpecabrev(locationEntity.getConsolidatedWeather().get(0).getWeatherStateAbbr());
                    mDb.locationDao().updateLocation(location);
                    myLocations.add(location);
                    myAdapter.setData(myLocations);
                }}

                @Override
                public void onFailure(Call<LocationEntity> call, Throwable t) {
                    Log.v(tag,t.getLocalizedMessage());
                }
            });
        }


        Call<LocationEntity> call=service.getLocationDetails("868274");
        call.enqueue(new Callback<LocationEntity>() {
            @Override
            public void onResponse(Call<LocationEntity> call, Response<LocationEntity> response) {
                LocationEntity locationEntity=response.body();
                String temp="";
                if(locationEntity!=null) {
                    for (int i = 0; i < String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).length(); i++) {
                        if (String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i) != '.') {
                            temp += String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i);
                        } else
                            break;
                    }
                    TextView myTemperature = findViewById(R.id.myLocationTemperatureTextView);
                    myTemperature.setText(temp);
                }
            }

            @Override
            public void onFailure(Call<LocationEntity> call, Throwable t) {

            }
        });



        ///////////////////////   MAKE THE CALL //////////////////////////////


        /*Call<LocationEntity> call1=service.getLocationDetails("2459115");
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
                mDb.locationDao().insertLocation(location);

            }

            @Override
            public void onFailure(Call<LocationEntity> call, Throwable t) {
                Log.v(tag,t.getLocalizedMessage());

            }
        });*/




        /////////////////////////////////////////////////////////////////////////////////////




        RadioGroup radioGroup=findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.Fradio) {
                    //change the color of the buttons
                    Log.v(tag, "Far checked");
                    RadioButton radioButtonC = findViewById(R.id.Cradio);
                    radioButtonC.setTextColor(Color.parseColor("#DCDCDC"));
                    RadioButton radioButtonF = findViewById(R.id.Fradio);
                    radioButtonF.setTextColor(Color.parseColor("#FFFFFFFF"));

                    /*for (int x = mRecycleView.getChildCount(), i = 0; i < x; ++i) {
                        RecyclerView.ViewHolder holder = mRecycleView.getChildViewHolder(mRecycleView.getChildAt(i));
                        TextView txtTemp      = holder.itemView.findViewById(R.id.temperatureTextView);
                        String t11=txtTemp.getText().toString();
                        int temp1=Integer.parseInt(t11.substring(0,t11.length()-1));
                        int newtemp1=(int)Math.round(temp1*1.8 +32);
                        String newtemp11=String.valueOf(newtemp1)+"°";
                        txtTemp.setText(newtemp11);
                    }*/
                    for (Location location:myLocations){
                        String t11=location.getTemperature();
                        int temp1=Integer.parseInt(t11.substring(0,t11.length()-1));
                        int newtemp1=(int)Math.round(temp1*1.8 +32);
                        location.setTemperature(String.valueOf(newtemp1)+"°");
                    }
                    TextView myTemperature = findViewById(R.id.myLocationTemperatureTextView);
                    String t=myTemperature.getText().toString();
                    int t1=Integer.parseInt(t);
                    int newt1=(int)Math.round(t1*1.8+32);
                    myTemperature.setText(String.valueOf(newt1));
                    myAdapter.setData(myLocations);

                }
                if(checkedId==R.id.Cradio) {

                    //cheange the color
                    Log.v(tag, "Celsius checked");
                    RadioButton radioButtonC = findViewById(R.id.Cradio);
                    radioButtonC.setTextColor(Color.parseColor("#FFFFFFFF"));
                    RadioButton radioButtonF = findViewById(R.id.Fradio);
                    radioButtonF.setTextColor(Color.parseColor("#DCDCDC"));

                    for (Location location:myLocations){
                        String t11=location.getTemperature();
                        int temp1=Integer.parseInt(t11.substring(0,t11.length()-1));
                        int newtemp1=(int)Math.round((temp1-32)*0.5556);
                        location.setTemperature(String.valueOf(newtemp1)+"°");
                    }
                    TextView myTemperature = findViewById(R.id.myLocationTemperatureTextView);
                    String t=myTemperature.getText().toString();
                    int t1=Integer.parseInt(t);
                    int newt1=(int)Math.round((t1-32)*0.5556);
                    myTemperature.setText(String.valueOf(newt1));
                    myAdapter.setData(myLocations);
                }
            }
        });

        FloatingActionButton myFAB=(FloatingActionButton) findViewById(R.id.myFAB);
        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SearchFragment searchFragment=SearchFragment.newInstance();
                searchFragment.show(getSupportFragmentManager(),"searchFragmentTag");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(tag," on start "+indexStart);
        indexStart+=1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(tag," on resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(tag," on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(tag," on destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(tag," on pause");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.v(tag," on restart");
    }

    public void onMyItemClick() {
        Log.v(tag,"click");
    }

    @Override
    public void onMyItemClickListener() {
        Log.v(tag,"click listener");
    }

    /*public void addCities(){
        Location l1=new Location(1,"Cluj-Napoca","46.72°N 26.62°E","30°","Clear");
        Location l2= new Location(2,"London","51.50°N 00.12°E","22°","Rain");
        Location l3=new Location(3,"New York","40.71°N 74.00°E","24°","Heavy Rain");
        Location l4=new Location(4,"Santa Cruz","36.96°N -122.02°E","30°","Clouds");
        Location l5=new Location(5,"Penzance","50.06°N -5.32°E","28°","Heavy Clouds");
        Location l6=new Location(6,"San Francisco","37.77°N -122.41°E","29°","Sunny");
        Location l7=new Location(7,"Bucharest","44.43°N 26.10°E","23°","Clear");
        Location l8=new Location(8,"Budapest","47.50°N 19.06°E","25°","Storm");
        Location l9=new Location(9,"Berlin","52.51°N 13.37°E","29°","Tsunami");
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);
        locationList.add(l6);
        locationList.add(l7);
        locationList.add(l8);
        locationList.add(l9);
    }*/


}