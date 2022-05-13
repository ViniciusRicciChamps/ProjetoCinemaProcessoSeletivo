package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.projetocinemaprocessoseletivo.Model.Filmes;

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
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaFilmes = new ArrayList<Filmes>();
        cardView = findViewById(R.id.cardViewFilmes);


        GetData getData = new GetData();
        getData.execute();
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
                    filmes.setLancamento_filme(jsonObject1.getString("2022-03-30"));
                    filmes.setNota_filme(jsonObject1.getString("vote_average"));
                    filmes.setResenha_filme(jsonObject1.getString("overview"));

                    listaFilmes.add(filmes);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            ColocarDadosCardView(listaFilmes);


        }
    }

    private void ColocarDadosCardView(List<Filmes> listaFilmes){



    }

}