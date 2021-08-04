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

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchDetailsAdapter extends RecyclerView.Adapter<SearchDetailsAdapter.ViewHolder>{
    private List<LocationDetails> mLocationDetailsList;
    private final Context mcontext;

    public SearchDetailsAdapter(List<LocationDetails> mLocationDetailsList, Context mcontext) {
        this.mLocationDetailsList = mLocationDetailsList;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_details,parent,false);
        return new SearchDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchDetailsAdapter.ViewHolder holder, int position) {
        LocationDetails location=mLocationDetailsList.get(position);
        try {
            holder.bind(location);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return mLocationDetailsList.size();
    }

        public CardView mView;
        public TextView day;
        public TextView percentage;
        public TextView maxTemp;
        public TextView minTemp;
        public ImageView img;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView.findViewById(R.id.dailydetailsCardView);
            day=itemView.findViewById(R.id.dayTextView);
            percentage=itemView.findViewById(R.id.rainPercentageTextView);
            maxTemp=itemView.findViewById(R.id.maxTemp);
            minTemp=itemView.findViewById(R.id.minTemp);
            img=itemView.findViewById(R.id.imgViewDailyDetails);
        }

        public void bind(LocationDetails location) throws ParseException {
            String input_date=location.getDay();
            String date="";
            String[] arrOfStrDate = input_date.split("-", 3);
            date+=arrOfStrDate[2];
            date+="/";
            date+=arrOfStrDate[1];
            date+="/";
            date+=arrOfStrDate[0];
            SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
            Date dt1=format1.parse(date);
            DateFormat format2=new SimpleDateFormat("EEEE");
            String finalDay=format2.format(dt1);


            day.setText(finalDay);
            percentage.setText(location.getPercentage());
            maxTemp.setText(location.getMaxTemp());
            minTemp.setText(location.getMinTemp());
            String url="https://www.metaweather.com/static/img/weather/png/";
            url+=location.getSpecAbrev();
            url+=".png";
            Glide.with(mcontext)
                    .load(url)
                    .into(img);
        }
    }
}
