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
import com.example.mine.model.Film;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.RvViewHolder> {

    private ArrayList<Film> mData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void setData(ArrayList<Film> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Film data);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_movies, parent, false);
        return new RvViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RvViewHolder holder, int position) {
        Film film = mData.get(position);
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185"+film.getPhoto())
                .apply(new RequestOptions().override(120, 180))
                .into(holder.imgPhoto);
        holder.tvTitle.setText(film.getTitle());
        holder.tvRating.setText(film.getUserScore());
        holder.tvDesc.setText(film.getDescription());

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

    class RvViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDesc, tvRating;
        ImageView imgPhoto;

        RvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDesc = itemView.findViewById(R.id.tv_item_description);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }

    }
}

