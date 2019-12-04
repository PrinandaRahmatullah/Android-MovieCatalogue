package com.example.mine.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mine.Detail;
import com.example.mine.R;
import com.example.mine.adapter.TvAdapter;
import com.example.mine.model.Tv;
import com.example.mine.viewmodel.TvViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavoriteFragment extends Fragment {


    public TvFavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar4);
        final RecyclerView recyclerView = view.findViewById(R.id.fv_rv_tv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final TvAdapter tvAdapter = new TvAdapter();

        recyclerView.setAdapter(tvAdapter);

        progressBar.setVisibility(View.VISIBLE);

        TvViewModel tvViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvViewModel.class);
        tvViewModel.setTvFav(getContext());
        tvViewModel.getTvFav().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tv>>() {
            @Override
            public void onChanged(ArrayList<Tv> tvs) {
                tvAdapter.setData(tvs);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        tvAdapter.setOnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Tv data) {
                Intent i = new Intent(getContext(), Detail.class);
                i.putExtra(Detail.ExtraString, data);
                i.putExtra(Detail.putFrom, Detail.tvFav);
                startActivity(i);
            }
        });
        tvAdapter.notifyDataSetChanged();
    }
}
