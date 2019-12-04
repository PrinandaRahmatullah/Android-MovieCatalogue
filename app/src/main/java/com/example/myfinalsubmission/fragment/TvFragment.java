package com.example.myfinalsubmission.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalsubmission.DetailActivity;
import com.example.myfinalsubmission.R;
import com.example.myfinalsubmission.adapter.TvAdapter;
import com.example.myfinalsubmission.model.Tv;
import com.example.myfinalsubmission.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private ProgressBar progressBar;
    private TvAdapter tvAdapter;
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private String thing;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.rv_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tvAdapter = new TvAdapter();
        tvAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(tvAdapter);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.getTV().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tv>>() {
            @Override
            public void onChanged(ArrayList<Tv> tvs) {
                if (tvs != null) {
                    tvAdapter.setData(tvs);
                    progressBar.setVisibility(View.INVISIBLE);
                    tvAdapter.notifyDataSetChanged();
                }

            }
        });

        String x = getResources().getString(R.string.language);
        mainViewModel.setMovie(x);

        tvAdapter.setOnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Tv data) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ExtraString, data);
                intent.putExtra(DetailActivity.putFrom, DetailActivity.tv);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    tvAdapter.clearData();
                    progressBar.setVisibility(View.VISIBLE);
                    mainViewModel.SearchMovie(query, thing, "tv");
                    tvAdapter.notifyDataSetChanged();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }
            });
        }
    }
}
