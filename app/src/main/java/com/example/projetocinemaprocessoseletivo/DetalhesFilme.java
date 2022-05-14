package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalhesFilme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

       // TextView id_filme_Detalhe;
        TextView nome_filme_Detalhe;
        ImageView poster_filme_Detalhe;
        TextView nota_filme_Detalhe;
        TextView linguagem_filme_Detalhe;
        TextView lancamento_filme_Detalhe;
        TextView resenha_filme_Detalhe;

       // id_filme_Detalhe = findViewById(R.id.textoIdFilm);
        nome_filme_Detalhe = findViewById(R.id.nome_filme_Detalhe);
        poster_filme_Detalhe = findViewById(R.id.poster_filme_Detalhes);
        nota_filme_Detalhe = findViewById(R.id.nota_Filme_Detalhes);
        lancamento_filme_Detalhe = findViewById(R.id.data_lancamento_Detalhes);
        resenha_filme_Detalhe = findViewById(R.id.resenha_Filme_Detalhes);

        Bundle bundle = getIntent().getExtras();

    }
}