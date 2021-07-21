package com.aiosep.stormrazor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.RecordingCanvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.ims.ImsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnMyItemClickListener {

    public String tag="AdrianIosepTag";
    public int indexStart=0;

    private RecyclerView mRecycleView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager manager;


    private List<Location> locationList=new ArrayList<Location>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(tag," on create ");

        addCities();
        mRecycleView=findViewById(R.id.main_recycler_view);

        manager=new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(manager);
        myAdapter=new MyAdapter(this,locationList);
        mRecycleView.setAdapter(myAdapter);

        myAdapter.setOnMyItemClickListener(this::onMyItemClick);



        RadioGroup radioGroup=findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.Fradio) {
                    //chenge the color of the buttons
                    Log.v(tag, "Far checked");
                    RadioButton radioButtonC = findViewById(R.id.Cradio);
                    radioButtonC.setTextColor(Color.parseColor("#808080"));
                    RadioButton radioButtonF = findViewById(R.id.Fradio);
                    radioButtonF.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
                if(checkedId==R.id.Cradio) {

                    //cheange the color
                    Log.v(tag, "Celsius checked");
                    RadioButton radioButtonC = findViewById(R.id.Cradio);
                    radioButtonC.setTextColor(Color.parseColor("#FFFFFFFF"));
                    RadioButton radioButtonF = findViewById(R.id.Fradio);
                    radioButtonF.setTextColor(Color.parseColor("#808080"));
                }
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

    public void addCities(){
        Location l1=new Location("Cluj-Napoca","46.72°N 26.62°E","30°","Clear");
        Location l2= new Location("London","51.50°N 00.12°E","22°","Rain");
        Location l3=new Location("New York","40.71°N 74.00°E","24°","Heavy Rain");
        Location l4=new Location("Santa Cruz","36.96°N -122.02°E","30°","Clouds");
        Location l5=new Location("Penzance","50.06°N -5.32°E","28°","Heavy Clouds");
        Location l6=new Location("San Francisco","37.77°N -122.41°E","29°","Sunny");
        Location l7=new Location("Bucharest","44.43°N 26.10°E","23°","Clear");
        Location l8=new Location("Budapest","47.50°N 19.06°E","25°","Storm");
        Location l9=new Location("Berlin","52.51°N 13.37°E","29°","Tsunami");
        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);
        locationList.add(l6);
        locationList.add(l7);
        locationList.add(l8);
        locationList.add(l9);
    }


}