package com.example.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mine.R;
import com.example.mine.model.Tv;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private ArrayList<Tv> mData = new ArrayList<>();

    public void setData(ArrayList<Tv> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Tv data);
    }
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_tvshows, parent, false);
        return new TvAdapter.TvViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {

        Tv tv =mData.get(position);
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185"+tv.getPhoto())
                .apply(new RequestOptions().override(120, 180))
                .into(holder.imgPhoto);
        holder.tvTitle.setText(tv.getName());
        holder.tvRating.setText(tv.getUserScore());
        holder.tvDesc.setText(tv.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class TvViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDesc, tvRating;
        ImageView imgPhoto;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDesc = itemView.findViewById(R.id.tv_item_description);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }

    }
}
