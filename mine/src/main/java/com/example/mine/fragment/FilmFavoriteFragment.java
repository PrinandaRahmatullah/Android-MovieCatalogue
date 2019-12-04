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

import com.example.mine.Detail;
import com.example.mine.R;
import com.example.mine.adapter.FilmAdapter;
import com.example.mine.model.Film;
import com.example.mine.viewmodel.FilmViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFavoriteFragment extends Fragment {

    public FilmFavoriteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressBar progressBar = view.findViewById(R.id.progressBar3);
        RecyclerView recyclerView= view.findViewById(R.id.fv_rv_film);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final FilmAdapter filmAdapter = new FilmAdapter();
        recyclerView.setAdapter(filmAdapter);

        progressBar.setVisibility(View.VISIBLE);

        FilmViewModel filmViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FilmViewModel.class);
        filmViewModel.setFilmFav(getContext());
        filmViewModel.getFilmFav().observe(getViewLifecycleOwner(), new Observer<ArrayList<Film>>() {
            @Override
            public void onChanged(ArrayList<Film> films) {
                if (films!=null){
                    filmAdapter.setData(films);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        filmAdapter.setOnItemClickCallback(new FilmAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Film data) {
                Intent i= new Intent(getContext(), Detail.class);
                i.putExtra(Detail.ExtraString, data);
                i.putExtra(Detail.putFrom, Detail.movieFav);
                startActivity(i);
            }
        });
        filmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
