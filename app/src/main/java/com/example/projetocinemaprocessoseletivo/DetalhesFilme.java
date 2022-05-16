package com.example.projetocinemaprocessoseletivo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projetocinemaprocessoseletivo.Model.Filmes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import DAO.ClasseSqLiteHelper;

public class DetalhesFilme extends AppCompatActivity {

    private String nome_Filme;
    private String id_Filme;
    private String nota_Filme;
    private String linguagem_Filme;
    private String dataLancamento_Filme;
    private String resenha_Filme;
    private String poster_Filme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        Button botaoAdiconarFavoritos = findViewById(R.id.botao_Favoritar);
        botaoAdiconarFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    salvarDadosBd();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });



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

    public void salvarDadosBd() throws IOException {

        InputStream fileInputStream = new URL("https://image.tmdb.org/t/p/w500" + poster_Filme).openStream();
        byte[] imagem= new byte[fileInputStream.available()];
        fileInputStream.read(imagem);
        ContentValues values = new ContentValues();
        values.put("a",imagem);
        fileInputStream.close();
        Toast.makeText(this,"Feito", Toast.LENGTH_SHORT).show();


        ClasseSqLiteHelper classeSqLiteHelper = new ClasseSqLiteHelper(getApplicationContext());
        Filmes filmes = new Filmes();

        filmes.setId(getId_Filme());
        filmes.setNome_filme(getNome_Filme());
        filmes.setNota_filme(getNota_Filme());
        filmes.setIdioma_filme(getLinguagem_Filme());
        filmes.setLancamento_filme(getDataLancamento_Filme());
        filmes.setResenha_filme(getResenha_Filme());
        classeSqLiteHelper.inserirDadosFilmeFavorito(filmes, imagem);
        classeSqLiteHelper.close();




    }


    public String getNome_Filme() {
        return nome_Filme;
    }

    public String getId_Filme() {
        return id_Filme;
    }

    public String getNota_Filme() {
        return nota_Filme;
    }

    public String getLinguagem_Filme() {
        return linguagem_Filme;
    }

    public String getDataLancamento_Filme() {
        return dataLancamento_Filme;
    }

    public String getResenha_Filme() {
        return resenha_Filme;
    }

    public String getPoster_Filme() {
        return poster_Filme;
    }

    public void setNome_Filme(String nome_Filme) {
        this.nome_Filme = nome_Filme;
    }

    public void setId_Filme(String id_Filme) {
        this.id_Filme = id_Filme;
    }

    public void setNota_Filme(String nota_Filme) {
        this.nota_Filme = nota_Filme;
    }

    public void setLinguagem_Filme(String linguagem_Filme) {
        this.linguagem_Filme = linguagem_Filme;
    }

    public void setDataLancamento_Filme(String dataLancamento_Filme) {
        this.dataLancamento_Filme = dataLancamento_Filme;
    }

    public void setResenha_Filme(String resenha_Filme) {
        this.resenha_Filme = resenha_Filme;
    }

    public void setPoster_Filme(String poster_Filme) {
        this.poster_Filme = poster_Filme;
    }
}