package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalhesFilme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        String nome_Filme;
        String id_Filme;
        String nota_Filme;
        String linguagem_Filme;
        String dataLancamento_Filme;
        String resenha_Filme;
        String poster_Filme;

        TextView id_filme_Detalhe;
        TextView nome_filme_Detalhe;
        ImageView poster_filme_Detalhe;
        TextView nota_filme_Detalhe;
        TextView linguagem_filme_Detalhe;
        TextView lancamento_filme_Detalhe;
        TextView resenha_filme_Detalhe;

        id_filme_Detalhe = findViewById(R.id.id_Filme_Detalhe);
        nome_filme_Detalhe = findViewById(R.id.nome_filme_Detalhe);
        poster_filme_Detalhe = findViewById(R.id.poster_filme_Detalhes);
        nota_filme_Detalhe = findViewById(R.id.nota_Filme_Detalhes);
        lancamento_filme_Detalhe = findViewById(R.id.data_lancamento_Detalhes);
        resenha_filme_Detalhe = findViewById(R.id.resenha_Filme_Detalhes);
        linguagem_filme_Detalhe = findViewById(R.id.linguagem_Filme_Detalhes);

        Bundle bundle = getIntent().getExtras();

        nome_Filme = bundle.getString("original_title");
        id_Filme = bundle.getString("id");
        nota_Filme = bundle.getString("vote_average");
        linguagem_Filme = bundle.getString("original_language");
        dataLancamento_Filme = bundle.getString("release_date");
        resenha_Filme = bundle.getString("overview");
        poster_Filme = bundle.getString("poster_path");


        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+poster_Filme).into(poster_filme_Detalhe);
        id_filme_Detalhe.setText(id_Filme);
        nome_filme_Detalhe.setText(nome_Filme);
        nota_filme_Detalhe.setText(nota_Filme);
        lancamento_filme_Detalhe.setText(dataLancamento_Filme);
        resenha_filme_Detalhe.setText(resenha_Filme);
        linguagem_filme_Detalhe.setText(linguagem_Filme);


    }
}