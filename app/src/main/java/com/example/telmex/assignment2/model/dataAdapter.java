package com.example.telmex.assignment2.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.telmex.assignment2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

//import firstproject2.mcs.com.mvpproject.R;


public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder> {

        private List<DataModel> mObjectList;

        public dataAdapter(List<DataModel> mObjectList) {
            this.mObjectList = mObjectList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.mTvName.setText(mObjectList.get(position).getArtistName());
            holder.mTvPrice.setText(mObjectList.get(position).getTrackPrice().toString());
            holder.mTvInstru.setText(mObjectList.get(position).getCollectionName());
            String url = "http://services.hanselandpetal.com/photos/" + mObjectList.get(position).getArtworkUrl60();

            Picasso.get().load(url).resize(50, 50).centerCrop().into(holder.mTvCake);

        }



        @Override
        public int getItemCount() {
            return mObjectList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView mTvName;
            private TextView mTvPrice;
            private TextView mTvInstru;
            private ImageView mTvCake;
            public ViewHolder(View view) {
                super(view);

                mTvName = view.findViewById(R.id.as_text_artist_name);
                mTvInstru = view.findViewById(R.id.as_text_collection_name);
                mTvPrice = view.findViewById(R.id.as_text_track_price);
                mTvCake = view.findViewById(R.id.as_artworkUrl60);
            }
        }
    }

