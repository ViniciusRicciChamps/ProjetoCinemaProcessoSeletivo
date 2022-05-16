package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projetocinemaprocessoseletivo.Model.Filmes;
import com.example.projetocinemaprocessoseletivo.adapter.MovieAdapterListar;

import java.util.ArrayList;
import java.util.List;

import DAO.ClasseSqLiteHelper;

public class listaFavoritos extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Filmes> listaFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_favoritos);
        recyclerView = findViewById(R.id.listaFavoritos);
        ClasseSqLiteHelper classeSqLiteHelper = new ClasseSqLiteHelper(getApplicationContext());

        List<Filmes> filmesList = classeSqLiteHelper.buscarFilmesFavoritos();
        listaFilmes = new ArrayList<Filmes>();


        for(Filmes filmeBuscado : filmesList){
            Filmes filmes = new Filmes();
            filmes.setNome_filme(filmeBuscado.getNome_filme()) ;
            filmes.setId(filmeBuscado.getId());
            filmes.setLancamento_filme(filmeBuscado.getLancamento_filme());
            filmes.setNota_filme(filmeBuscado.getNota_filme()); ;
            filmes.setResenha_filme(filmeBuscado.getResenha_filme());
            filmes.setIdioma_filme(filmeBuscado.getIdioma_filme());
            listaFilmes.add(filmes);

        }
        ColocarDadosCardView(listaFilmes);

    }

    private void ColocarDadosCardView(List<Filmes> filmes){

        MovieAdapterListar movieAdapterListar = new MovieAdapterListar(this, filmes);
        recyclerView .setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapterListar);

    }
}