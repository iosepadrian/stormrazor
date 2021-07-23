package com.aiosep.stormrazor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {



    private List<LocationDetails> mLocationDetailsList;
    private final Context mcontext;

    public DetailAdapter(Context context) {
        this.mcontext = context;
    }

    public DetailAdapter(List<LocationDetails> mLocationDetailsList, Context mcontext) {
        this.mLocationDetailsList = mLocationDetailsList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_details,parent,false);
        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        LocationDetails location=mLocationDetailsList.get(position);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return mLocationDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public CardView mView;
        public TextView day;
        public TextView percentage;
        public TextView maxTemp;
        public TextView minTemp;
        public ImageView img;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            mView=itemView.findViewById(R.id.dailydetailsCardView);
            day=itemView.findViewById(R.id.dayTextView);
            percentage=itemView.findViewById(R.id.rainPercentageTextView);
            maxTemp=itemView.findViewById(R.id.maxTemp);
            minTemp=itemView.findViewById(R.id.minTemp);
            img=itemView.findViewById(R.id.imgViewDailyDetails);
        }

        public void bind(LocationDetails location) {
            day.setText(location.getDay());
            percentage.setText(location.getPercentage());
            maxTemp.setText(location.getMaxTemp());
            minTemp.setText(location.getMinTemp());
            img.setImageResource(R.drawable.icon_2);
        }
    }
}
