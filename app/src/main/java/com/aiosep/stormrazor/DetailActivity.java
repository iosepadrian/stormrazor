package com.aiosep.stormrazor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    private RecyclerView mRecycleView;
    private DetailAdapter myAdapter;
    private RecyclerView.LayoutManager manager;


    private List<LocationDetails> locationList=new ArrayList<LocationDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name=getIntent().getStringExtra("nume");
        TextView nume=findViewById(R.id.locationTextView);
        nume.setText(name);

        addLocationDetail();

        mRecycleView=findViewById(R.id.detail_recyclerView);
        manager=new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(manager);
        myAdapter=new DetailAdapter(locationList, this);
        mRecycleView.setAdapter(myAdapter);
    }

    private void addLocationDetail() {
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
    }
}