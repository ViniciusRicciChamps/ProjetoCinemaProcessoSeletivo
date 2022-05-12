package com.example.projetocinemaprocessoseletivo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetocinemaprocessoseletivo.Model.Filmes;
import com.example.projetocinemaprocessoseletivo.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MinhaView> {


    private Context contextFilme;
    private List<Filmes> filmeData;

    public MovieAdapter(Context contextFilme, List<Filmes> filmeData) {
        this.contextFilme = contextFilme;
        this.filmeData = filmeData;
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
    public void onBindViewHolder(@NonNull MinhaView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MinhaView extends RecyclerView.ViewHolder{

        TextView id;
        TextView nome_filme;
        ImageView poster_filme;
        TextView genero_filme;
        TextView lancamento_filme;
        TextView resenha_filme;

        public MinhaView(@NonNull View itemView) {
            super(itemView);
        }
    }




}
