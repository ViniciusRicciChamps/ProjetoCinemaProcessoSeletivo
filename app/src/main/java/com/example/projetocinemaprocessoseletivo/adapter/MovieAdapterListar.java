package com.example.projetocinemaprocessoseletivo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetocinemaprocessoseletivo.DetalhesFilme;
import com.example.projetocinemaprocessoseletivo.Model.Filmes;
import com.example.projetocinemaprocessoseletivo.R;

import java.util.List;

public class MovieAdapterListar extends RecyclerView.Adapter<MovieAdapterListar.MinhaView> {


    private Context contextFilme;
    private List<Filmes> filmeData;



    public MovieAdapterListar(Context contextFilme, List<Filmes> filmeData) {
        this.contextFilme = contextFilme;
        this.filmeData =  filmeData;
    }

    @NonNull
    @Override
    public MinhaView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(contextFilme);
        v = inflater.inflate(R.layout.view_carrega_lista_filmes, parent, false);
        return new MinhaView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaView holder, @SuppressLint("RecyclerView") int position) {

        holder.nome_filme.setText(filmeData.get(position).getNome_filme());
        holder.nota_filme.setText(filmeData.get(position).getNota_filme());
        holder.lancamento_filme.setText(filmeData.get(position).getLancamento_filme());
        Glide.with(contextFilme)
                .load("https://image.tmdb.org/t/p/w500"+filmeData.get(position).getPoster_filme())
                .into(holder.poster_filme);


        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextFilme, DetalhesFilme.class);


                Bundle bundle = new Bundle();
                bundle.putString("id", filmeData.get(position).getId());
                bundle.putString("original_title", filmeData.get(position).getNome_filme());
                bundle.putString("vote_average", filmeData.get(position).getNota_filme());
                bundle.putString("release_date", filmeData.get(position).getLancamento_filme());
                bundle.putString("overview", filmeData.get(position).getResenha_filme());
                bundle.putString("poster_path", filmeData.get(position).getPoster_filme());
                bundle.putString("original_language", filmeData.get(position).getIdioma_filme());
                intent.putExtras(bundle);
                contextFilme.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return filmeData.size();
    }

    public static class MinhaView extends RecyclerView.ViewHolder{

        TextView id_filme;
        TextView nome_filme;
        ImageView poster_filme;
        TextView nota_filme;
        TextView lancamento_filme;
        TextView resenha_filme;
        ConstraintLayout constraintLayout;

        public MinhaView(@NonNull View itemView) {
            super(itemView);

           // id_filme = itemView.findViewById(R.id.textoIdFilme);
            nome_filme = itemView.findViewById(R.id.textoTituloFilme);
            poster_filme = itemView.findViewById(R.id.posterFilme);
            nota_filme = itemView.findViewById(R.id.textoNotaFilme);
            lancamento_filme = itemView.findViewById(R.id.textoDataLancamentoFilme);
            //resenha_filme = itemView.findViewById(R.id.textoResenhaFilme);
            constraintLayout = itemView.findViewById(R.id.layout_info_filme);

        }
    }




}
