package com.aiosep.stormrazor;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Location> mLocationList;
    private OnMyItemClickListener mListener;
    private final Context mContext;


    public MyAdapter(Context context,List<Location> locationList) {
        mContext=context;
        mLocationList = locationList;
    }

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.location_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Location location=mLocationList.get(position);
        holder.bind(location);

    }

    public void updateLocation(Location location){

        for (int i=0;i<=mLocationList.size();i++){
            if(mLocationList.get(i).getId()==location.getId()){
                mLocationList.add(i,location);
                notifyDataSetChanged();
            }
        }

    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    public void setData(List<Location> myLocations) {
        this.mLocationList=myLocations;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mView;
        public TextView name;
        public TextView coordonate;
        public TextView temperature;
        public TextView spec;
        public ImageView img;
        public ImageView backgroundimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView.findViewById(R.id.locationcardview);
            name=itemView.findViewById(R.id.cityTextView);
            coordonate=itemView.findViewById(R.id.coordonateTextView);
            temperature=itemView.findViewById(R.id.temperatureTextView);
            spec=itemView.findViewById(R.id.specTextView);
            img=itemView.findViewById(R.id.imgView);
            backgroundimg=itemView.findViewById(R.id.backgroundImage);
        }

        public void bind(Location location) {

            name.setText(location.getName());
            coordonate.setText(location.getCoordonate());
            temperature.setText(location.getTemperature());
            spec.setText(location.getSpec());
            img.setImageResource(R.drawable.icon_2);
            String url="https://www.metaweather.com/static/img/weather/png/";
            url+=location.getSpecabrev();
            url+=".png";
            
            Glide.with(mContext)
                    .load(url)
                    .into(img);

            switch (location.getSpecabrev()){
                case "s":
                    backgroundimg.setImageResource(R.drawable.s);
                    break;
                case "sn":
                    backgroundimg.setImageResource(R.drawable.sn);
                    break;
                case "sl":
                    backgroundimg.setImageResource(R.drawable.sl);
                    break;
                case "h":
                    backgroundimg.setImageResource(R.drawable.h);
                    break;
                case "t":
                    backgroundimg.setImageResource(R.drawable.t);
                    break;
                case "hr":
                    backgroundimg.setImageResource(R.drawable.hr);
                    break;
                case "lr":
                    backgroundimg.setImageResource(R.drawable.lr);
                    break;
                case "hc":
                    backgroundimg.setImageResource(R.drawable.hc);
                    break;
                case "lc":
                    backgroundimg.setImageResource(R.drawable.lc);
                    break;
                case "c":
                    backgroundimg.setImageResource(R.drawable.c);
                    break;
            }

            mView.setOnClickListener(v->{mListener.onMyItemClickListener();
            Intent intent=new Intent(mContext,DetailActivity.class);
            intent.putExtra("woeid",String.valueOf(location.getId()));
            intent.putExtra("name",location.getName());
            intent.putExtra("temperature",location.getTemperature());
            intent.putExtra("spec",location.getSpec());
            intent.putExtra("specabrev",location.getSpecabrev());
            mContext.startActivity(intent);
            });
        }
    }

    public interface OnMyItemClickListener {
        void onMyItemClickListener() ;
        }

        public void setOnMyItemClickListener(OnMyItemClickListener listener){
        mListener=listener;
        }


}
