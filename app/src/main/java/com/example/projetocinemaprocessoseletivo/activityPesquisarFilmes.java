package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.projetocinemaprocessoseletivo.Model.Filmes;
import com.example.projetocinemaprocessoseletivo.adapter.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class activityPesquisarFilmes extends AppCompatActivity {

    public  String queryParametro;
    private String JSON_URL_PESQUISAR = "";


    List<Filmes> listarFilmesPesquisa;
    RecyclerView recyclerViewPesquisarFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_filmes);

        listarFilmesPesquisa = new ArrayList<Filmes>();
        recyclerViewPesquisarFilme = findViewById(R.id.recyclerViewPesquisarFilme);

        ProcurarFilme();
    }

    public void ProcurarFilme(){
        final SearchView  searchView = findViewById(R.id.barraPesquisaFilme);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                    listarFilmesPesquisa.clear();
                    queryParametro = query;
                    JSON_URL_PESQUISAR = "https://api.themoviedb.org/3/search/movie?api_key=f321a808e68611f41312aa8408531476&query=" + queryParametro;
                    Log.d("NEW TEXT", queryParametro);
                    query = "";

                    GetData getData = new GetData();
                    getData.execute();




                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               /* JSON_URL_PESQUISAR = "";
                JSON_URL_PESQUISAR = "https://api.themoviedb.org/3/search/movie?api_key=f321a808e68611f41312aa8408531476&query=" + newText;

                GetData getData = new GetData();
                getData.execute();
                Log.d("NEW TEXT", newText);*/
                return false;
            }
        });


    }

    public class GetData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String atualPesquisar = "";

            try {
                URL url;
                HttpURLConnection httpURLConnection = null;

                try {
                    url = new URL(JSON_URL_PESQUISAR);
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();
                    while (data != -1){
                        atualPesquisar += (char) data;
                        data = inputStreamReader.read();
                    }
                    return atualPesquisar;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(httpURLConnection != null){
                        httpURLConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return atualPesquisar;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Filmes filmes = new Filmes();

                    filmes.setId(jsonObject1.getString("id"));
                    filmes.setNome_filme(jsonObject1.getString("original_title"));
                    filmes.setLancamento_filme(jsonObject1.getString("release_date"));
                    filmes.setNota_filme(jsonObject1.getString("vote_average"));
                    filmes.setResenha_filme(jsonObject1.getString("overview"));
                    filmes.setPoster_filme(jsonObject1.getString("poster_path"));
                    filmes.setIdioma_filme(jsonObject1.getString("original_language"));
                    listarFilmesPesquisa.add(filmes);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            ColocarDadosCardViewPesquisa(listarFilmesPesquisa);
        }
    }
    private void ColocarDadosCardViewPesquisa(List<Filmes> listaFilmesPesquisa){


        MovieAdapter movieAdapter = new MovieAdapter(this, listaFilmesPesquisa);
        recyclerViewPesquisarFilme.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPesquisarFilme.setAdapter(movieAdapter);


    }



}