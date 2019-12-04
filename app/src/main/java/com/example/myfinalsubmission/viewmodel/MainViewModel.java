package com.example.myfinalsubmission.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfinalsubmission.BuildConfig;
import com.example.myfinalsubmission.model.Film;
import com.example.myfinalsubmission.model.Tv;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Film>> listFilmMut = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Tv>> listTvMut = new MutableLiveData<>();

    public void setMovie(String language) {

        String url1 = "https://api.themoviedb.org/3/discover/movie?api_key=" + BuildConfig.API_KEY + "&language=" + language;
        String url2 = "https://api.themoviedb.org/3/discover/tv?api_key=" + BuildConfig.API_KEY + "&language=" + language;

        final ArrayList<Film> listFilm = new ArrayList<>();
        final ArrayList<Tv> listTv = new ArrayList<>();

        AsyncHttpClient client1 = new AsyncHttpClient();
        AsyncHttpClient client2 = new AsyncHttpClient();

        client1.get(url1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody);
                    JSONObject responseObject = new JSONObject(json);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);
                        Film filmmodel = new Film();
                        filmmodel.setTitle(film.getString("title"));
                        filmmodel.setDescription(film.getString("overview"));
                        filmmodel.setPhoto(film.getString("poster_path"));
                        filmmodel.setUserScore(film.getString("vote_average"));
                        listFilm.add(filmmodel);
                    }
                    listFilmMut.postValue(listFilm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error client1", "onFailure: " + error.getMessage());
            }
        });

        client2.get(url2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String json = new String(responseBody);
                    JSONObject responseObject = new JSONObject(json);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        Tv tvmodel = new Tv();
                        tvmodel.setName(tv.getString("name"));
                        tvmodel.setDescription(tv.getString("overview"));
                        tvmodel.setPhoto(tv.getString("poster_path"));
                        tvmodel.setUserScore(tv.getString("vote_average"));
                        listTv.add(tvmodel);
                    }
                    listTvMut.postValue(listTv);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error", "onFailure: " + error.getMessage());
            }
        });
    }


    public void SearchMovie(String name, String language, final String movieOrTv) {
        final String movie = "movie";
        final String tv = "tv";
        String namec = name.replace(" ", "%20");
        String url = "https://api.themoviedb.org/3/search/" + movieOrTv + "?api_key="
                + BuildConfig.API_KEY + "&language=" + language + "&query=" + namec;

        final ArrayList<Film> listFilm = new ArrayList<>();
        final ArrayList<Tv> listTv = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String json = new String(responseBody);
                    JSONObject responseObject = new JSONObject(json);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        if (movieOrTv.equals(movie)) {
                            JSONObject film = list.getJSONObject(i);
                            Film filmmodel = new Film();
                            filmmodel.setTitle(film.getString("title"));
                            filmmodel.setDescription(film.getString("overview"));
                            filmmodel.setPhoto(film.getString("poster_path"));
                            filmmodel.setUserScore(film.getString("vote_average"));
                            listFilm.add(filmmodel);
                        }
                        if (movieOrTv.equals(tv)) {
                            JSONObject tv = list.getJSONObject(i);
                            Tv tvmodel = new Tv();
                            tvmodel.setName(tv.getString("name"));
                            tvmodel.setDescription(tv.getString("overview"));
                            tvmodel.setPhoto(tv.getString("poster_path"));
                            tvmodel.setUserScore(tv.getString("vote_average"));
                            listTv.add(tvmodel);
                        }
                    }

                    if (movieOrTv.equals(movie)) {
                        listFilmMut.postValue(listFilm);
                    }
                    if (movieOrTv.equals(tv)) {
                        listTvMut.postValue(listTv);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error om disearch", "onSuccess: search error" + e.getMessage());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("fail get", "onFailure: " + error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Film>> getFILM() {
        return listFilmMut;
    }

    public LiveData<ArrayList<Tv>> getTV() {
        return listTvMut;
    }
}
