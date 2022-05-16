package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetocinemaprocessoseletivo.Model.Filmes;
import com.example.projetocinemaprocessoseletivo.adapter.MovieAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=f321a808e68611f41312aa8408531476&language=en-US&page=1";

    List<Filmes> listaFilmes;
    RecyclerView recyclerView;
    ImageView cabecalho;


    FloatingActionButton botaoFlutuantePesquisar, botaoFlutuanteFavoritos;
    ExtendedFloatingActionButton botaoFlutuantePrincipal;
    TextView textViewFlutuantePesquisar, getTextViewFlutuanteFavoritos;

    Boolean botoesFlutuantesVisivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metodoBotaoFlutuante();

        listaFilmes = new ArrayList<Filmes>();
        recyclerView = findViewById(R.id.listRecyclerView);
        GetData getData = new GetData();
        getData.execute();


    }
    
    public void metodoBotaoFlutuante(){
        botaoFlutuantePrincipal = findViewById(R.id.botaoFlutuantePrincipal);
        botaoFlutuantePesquisar = findViewById(R.id.botao_Flutuante_Pesquisa);
        botaoFlutuanteFavoritos = findViewById(R.id.botao_Flutuante_Favoritos);
        textViewFlutuantePesquisar =
                findViewById(R.id.textViewFlutuantePesquisar);
        getTextViewFlutuanteFavoritos =
                findViewById(R.id.textViewFlutuanteFavoritos);

        botaoFlutuantePesquisar.setVisibility(View.GONE);
        botaoFlutuanteFavoritos.setVisibility(View.GONE);
        textViewFlutuantePesquisar.setVisibility(View.GONE);
        getTextViewFlutuanteFavoritos.setVisibility(View.GONE);

        botoesFlutuantesVisivel = false;

        botaoFlutuantePrincipal.shrink();

        botaoFlutuantePrincipal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!botoesFlutuantesVisivel) {

                            botaoFlutuantePesquisar.show();
                            botaoFlutuanteFavoritos.show();
                            textViewFlutuantePesquisar
                                    .setVisibility(View.VISIBLE);
                            getTextViewFlutuanteFavoritos
                                    .setVisibility(View.VISIBLE);

                            botaoFlutuantePrincipal.extend();

                            botoesFlutuantesVisivel = true;
                        } else {

                            botaoFlutuantePesquisar.hide();
                            botaoFlutuanteFavoritos.hide();
                            textViewFlutuantePesquisar
                                    .setVisibility(View.GONE);
                            getTextViewFlutuanteFavoritos
                                    .setVisibility(View.GONE);

                            botaoFlutuantePrincipal.shrink();

                            botoesFlutuantesVisivel = false;
                        }
                    }
                });

        botaoFlutuanteFavoritos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), listaFavoritos.class);
                        startActivity(intent);
                    }
                });

        botaoFlutuantePesquisar.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), activityPesquisarFilmes.class);
                        startActivity(intent1);
                    }
                });

    }

    public class GetData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            String atual = "";

            try {
                URL url;
                HttpURLConnection httpURLConnection = null;

                try {
                    url = new URL(JSON_URL);
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();
                    while (data != -1){
                        atual += (char) data;
                        data = inputStreamReader.read();
                    }
                    return atual;

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

            return atual;
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
                    listaFilmes.add(filmes);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            ColocarDadosCardView(listaFilmes);


        }
    }


    private void ColocarDadosCardView(List<Filmes> listaFilmes){

        MovieAdapter movieAdapter = new MovieAdapter(this, listaFilmes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

    }

}