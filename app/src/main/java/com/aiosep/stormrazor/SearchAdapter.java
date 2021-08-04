package com.aiosep.stormrazor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{


    private List<SearchLocation> mLocationList;
    private SearchAdapter.OnMyItemClickListener mListener;
    private final Context mContext;

    public SearchAdapter(Context context,List<SearchLocation> locationList) {
        mContext=context;
        mLocationList = locationList;
    }


    public SearchAdapter(Context mContext) {
        this.mContext = mContext;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        SearchLocation location=mLocationList.get(position);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView searchmView;
        public TextView searchName;
        public TextView searchCoordonate;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            searchmView=itemView.findViewById(R.id.searchcardView);
            searchName=itemView.findViewById(R.id.searchcityTextView);
            searchCoordonate=itemView.findViewById(R.id.searchcoordonateTextView);
            img=itemView.findViewById(R.id.imgSearch);
        }

        public void bind(SearchLocation location) {
            searchName.setText(location.getName());
            searchCoordonate.setText(location.getCoordonates());
            img.setImageResource(R.drawable.search_cards_asset);
            searchmView.setOnClickListener(v->{mListener.onMyItemClickListener();
                Intent intent=new Intent(mContext,SearchDetailsActivity.class);
                intent.putExtra("woeid",String.valueOf(location.getWoeid()));

                mContext.startActivity(intent);
            });
        }
    }
    public interface OnMyItemClickListener {
        void onMyItemClickListener() ;
    }

    public void setOnMyItemClickListener(SearchAdapter.OnMyItemClickListener listener){
        mListener=listener;
    }
}
