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

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mView;
        public TextView name;
        public TextView coordonate;
        public TextView temperature;
        public TextView spec;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView.findViewById(R.id.locationcardview);
            name=itemView.findViewById(R.id.cityTextView);
            coordonate=itemView.findViewById(R.id.coordonateTextView);
            temperature=itemView.findViewById(R.id.temperatureTextView);
            spec=itemView.findViewById(R.id.specTextView);
            img=itemView.findViewById(R.id.imgView);
        }

        public void bind(Location location) {
            name.setText(location.getName());
            coordonate.setText(location.getCoordonate());
            temperature.setText(location.getTemperature());
            spec.setText(location.getSpec());
            img.setImageResource(R.drawable.icon_2);
            Glide.with(mContext)
                    .load("https://i.imgur.com/wKSN3ik.png")
                    .into(img);

            Glide.with(mContext)
                    .load("https://i.imgur.com/MOmksUG.png")
                    .into(new CustomTarget<Drawable>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            itemView.findViewById(R.id.locationLayout).setBackground(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
            mView.setOnClickListener(v->{mListener.onMyItemClickListener();
            Intent intent=new Intent(mContext,DetailActivity.class);
            intent.putExtra("nume",location.getName());
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
