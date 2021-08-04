package com.aiosep.stormrazor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnMyItemClickListener {
    public String tag="AdrianIosepTag";
    public int indexStart=0;

    private RecyclerView mRecycleView;
    private SearchAdapter myAdapter;
    private RecyclerView.LayoutManager manager;

    private List<SearchLocation> locationList=new ArrayList<SearchLocation>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://www.metaweather.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        MyApiService service= retrofit.create(MyApiService.class);

        Bundle bundle=getIntent().getBundleExtra("bundle");
        String name=bundle.getString("name");
        String latit=bundle.getString("latit");
        String longit=bundle.getString("longit");
        Log.v(tag,name);
        Log.v(tag,latit);
        Log.v(tag,longit);
        Call<LocationEntity> calltemp=service.getLocationDetails("868274");
        calltemp.enqueue(new Callback<LocationEntity>() {
            @Override
            public void onResponse(Call<LocationEntity> calltemp, Response<LocationEntity> response) {
                LocationEntity locationEntity=response.body();
                String temp="";
                for (int i=0;i<String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).length();i++){
                    if(String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i)!='.'){
                        temp+=String.valueOf(locationEntity.getConsolidatedWeather().get(0).getTheTemp()).charAt(i);
                    }
                    else
                        break;
                }
                temp+="°";
                TextView myTemperature=findViewById(R.id.myLocationSearchTemperatureTextView);
                myTemperature.setText(temp);
            }

            @Override
            public void onFailure(Call<LocationEntity> calltemp, Throwable t) {

            }
        });

        if(name.length()>0){

            if(latit.length()==0&& longit.length()==0){
            Call<List<LocationEntity>> call=service.getLocation(name);
            call.enqueue(new Callback<List<LocationEntity>>() {
                @Override
                public void onResponse(Call<List<LocationEntity>> call, Response<List<LocationEntity>> response) {
                    List<LocationEntity> list=response.body();
                    if (list!=null) {
                        for (LocationEntity location : list) {
                            String coordonate = "";
                            if (location.getLattLong().length() > 4)
                                coordonate += location.getLattLong().substring(0, 5);
                            else coordonate += location.getLattLong();
                            coordonate += "°N ";
                            String[] arrOfStr = location.getLattLong().split(",", 2);
                            String longit = arrOfStr[1];
                            String[] arrOfStr2 = longit.split("\\.", 2);
                            coordonate += arrOfStr2[0];
                            coordonate += ".";
                            if (arrOfStr2[1].length() > 1)
                                coordonate += arrOfStr2[1].substring(0, 2);
                            else coordonate += arrOfStr2[1];
                            coordonate += "°E";
                            locationList.add(new SearchLocation(String.valueOf(location.getWoeid()),location.getTitle(), coordonate));
                        }
                    }
                    mRecycleView=findViewById(R.id.search_recycler_view);

                    manager=new LinearLayoutManager(SearchActivity.this);
                    mRecycleView.setLayoutManager(manager);
                    myAdapter=new SearchAdapter(SearchActivity.this,locationList);
                    mRecycleView.setAdapter(myAdapter);


                    myAdapter.setOnMyItemClickListener(this::onMyItemClick);

                }

                private void onMyItemClick() {
                    Log.v(tag,"search tag");
                }


                @Override
                public void onFailure(Call<List<LocationEntity>> call, Throwable t) {
                    Log.v(tag,t.getLocalizedMessage());
                }
            });
        }
            else {
                List<SearchLocation> locationList1=new ArrayList<SearchLocation>();
                Call<List<LocationEntity>> call=service.getLocation(name);
                call.enqueue(new Callback<List<LocationEntity>>() {
                    @Override
                    public void onResponse(Call<List<LocationEntity>> call, Response<List<LocationEntity>> response) {
                        List<LocationEntity> list=response.body();
                        if(list!=null) {
                            for (LocationEntity location : list) {
                                String coordonate = "";
                                if (location.getLattLong().length() > 4)
                                    coordonate += location.getLattLong().substring(0, 5);
                                else coordonate += location.getLattLong();
                                coordonate += "°N ";
                                String[] arrOfStr = location.getLattLong().split(",", 2);
                                String longit = arrOfStr[1];
                                String[] arrOfStr2 = longit.split("\\.", 2);
                                coordonate += arrOfStr2[0];
                                coordonate += ".";
                                if (arrOfStr2[1].length() > 1)
                                    coordonate += arrOfStr2[1].substring(0, 2);
                                else coordonate += arrOfStr2[1];
                                coordonate += "°E";
                                locationList1.add(new SearchLocation(String.valueOf(location.getWoeid()),location.getTitle(), coordonate));
                            }
                        }

                        List<SearchLocation> locationList2=new ArrayList<SearchLocation>();
                        Call<List<LocationEntity>> call2=service.getLocationByLatLong(latit+","+longit);
                        call2.enqueue(new Callback<List<LocationEntity>>() {
                            @Override
                            public void onResponse(Call<List<LocationEntity>> call, Response<List<LocationEntity>> response) {
                                List<LocationEntity> list=response.body();
                                if(list!=null) {
                                    for (LocationEntity location : list) {
                                        String coordonate = "";
                                        if (location.getLattLong().length() > 4)
                                            coordonate += location.getLattLong().substring(0, 5);
                                        else coordonate += location.getLattLong();
                                        coordonate += "°N ";
                                        String[] arrOfStr = location.getLattLong().split(",", 2);
                                        String longit = arrOfStr[1];
                                        String[] arrOfStr2 = longit.split("\\.", 2);
                                        coordonate += arrOfStr2[0];
                                        coordonate += ".";
                                        if (arrOfStr2[1].length() > 1)
                                            coordonate += arrOfStr2[1].substring(0, 2);
                                        else coordonate += arrOfStr2[1];
                                        coordonate += "°E";
                                        locationList2.add(new SearchLocation(String.valueOf(location.getWoeid()),location.getTitle(), coordonate));
                                    }
                                }
                                for (SearchLocation searchLocation:locationList1){
                                    for(SearchLocation searchLocation1:locationList2){
                                        if (searchLocation.getName().equals(searchLocation1.getName()))
                                            locationList.add(searchLocation);
                                    }
                                }
                                mRecycleView=findViewById(R.id.search_recycler_view);

                                manager=new LinearLayoutManager(SearchActivity.this);
                                mRecycleView.setLayoutManager(manager);
                                myAdapter=new SearchAdapter(SearchActivity.this,locationList);
                                mRecycleView.setAdapter(myAdapter);


                                myAdapter.setOnMyItemClickListener(this::onMyItemClick);
                            }

                            private void onMyItemClick() {
                                Log.v(tag,"search tag");
                            }

                            @Override
                            public void onFailure(Call<List<LocationEntity>> call, Throwable t) {

                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<List<LocationEntity>> call, Throwable t) {
                        Log.v(tag,t.getLocalizedMessage());
                    }
                });
            }



        }
        else {
            Call<List<LocationEntity>> call2=service.getLocationByLatLong(latit+","+longit);
            call2.enqueue(new Callback<List<LocationEntity>>() {
                              @Override
                              public void onResponse(Call<List<LocationEntity>> call, Response<List<LocationEntity>> response) {

                                  List<LocationEntity> list=response.body();
                                    if(list!=null){
                                  for (LocationEntity location:list){
                                      String coordonate="";
                                      if(location.getLattLong().length()>4)
                                          coordonate+=location.getLattLong().substring(0,5);
                                      else coordonate+=location.getLattLong();
                                      coordonate+="°N ";
                                      String[] arrOfStr = location.getLattLong().split(",", 2);
                                      String longit=arrOfStr[1];
                                      String[] arrOfStr2= longit.split("\\.",2);
                                      coordonate+=arrOfStr2[0];
                                      coordonate+=".";
                                      if(arrOfStr2[1].length()>1)
                                          coordonate+=arrOfStr2[1].substring(0,2);
                                      else coordonate+=arrOfStr2[1];
                                      coordonate+="°E";
                                      locationList.add(new SearchLocation(String.valueOf(location.getWoeid()),location.getTitle(),coordonate));
                                  }}

                                  mRecycleView=findViewById(R.id.search_recycler_view);

                                  manager=new LinearLayoutManager(SearchActivity.this);
                                  mRecycleView.setLayoutManager(manager);
                                  myAdapter=new SearchAdapter(SearchActivity.this,locationList);
                                  mRecycleView.setAdapter(myAdapter);


                                  myAdapter.setOnMyItemClickListener(this::onMyItemClick);
                              }

                              private void onMyItemClick() {
                                  Log.v(tag,"search tag");
                              }

                              @Override
                              public void onFailure(Call<List<LocationEntity>> call, Throwable t) {

                              }
            });
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.searchtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



    @Override
    public void onMyItemClickListener() {
            Log.v(tag,"search tag");
    }
}