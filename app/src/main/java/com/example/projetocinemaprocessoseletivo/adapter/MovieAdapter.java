package com.example.projetocinemaprocessoseletivo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        holder.id_filme.setText(filmeData.get(position).getId());
        holder.nome_filme.setText(filmeData.get(position).getNome_filme());
        holder.nota_filme.setText(filmeData.get(position).getNota_filme());
        holder.lancamento_filme.setText(filmeData.get(position).getLancamento_filme());
        holder.resenha_filme.setText(filmeData.get(position).getResenha_filme());


        Glide.with(contextFilme).load(filmeData.get(position).getPoster_filme()).into(holder.poster_filme);

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

        public MinhaView(@NonNull View itemView) {
            super(itemView);

            id_filme = itemView.findViewById(R.id.textoIdFilme);
            nome_filme = itemView.findViewById(R.id.textoTituloFilme);
            poster_filme = itemView.findViewById(R.id.posterFilme);
            nota_filme = itemView.findViewById(R.id.textoNotaFilme);
            lancamento_filme = itemView.findViewById(R.id.textoDataLancamentoFilme);
            resenha_filme = itemView.findViewById(R.id.textoResenhaFilme);

        }
    }




}
