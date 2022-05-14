package com.example.projetocinemaprocessoseletivo.Model;

public class Filmes {


    String id;
    String nome_filme;
    String poster_filme;
    String nota_filme;
    String lancamento_filme;
    String resenha_filme;
    String idioma_filme;

    public Filmes(String id, String nome_filme, String poster_filme, String genero_filme, String lancamento_filme, String resenha_filme, String idioma_filme) {
        this.id = id;
        this.nome_filme = nome_filme;
        this.poster_filme = poster_filme;
        this.nota_filme = genero_filme;
        this.lancamento_filme = lancamento_filme;
        this.resenha_filme = resenha_filme;
        this.idioma_filme = idioma_filme;
    }


    public Filmes() {
    }

    public String getIdioma_filme() {
        return idioma_filme;
    }

    public void setIdioma_filme(String idioma_filme) {
        this.idioma_filme = idioma_filme;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome_filme() {
        return nome_filme;
    }

    public void setNome_filme(String nome_filme) {
        this.nome_filme = nome_filme;
    }

    public String getPoster_filme() {
        return poster_filme;
    }

    public void setPoster_filme(String poster_filme) {
        this.poster_filme = poster_filme;
    }

    public String getNota_filme() {
        return nota_filme;
    }

    public void setNota_filme(String nota_filme) {
        this.nota_filme = nota_filme;
    }

    public String getLancamento_filme() {
        return lancamento_filme;
    }

    public void setLancamento_filme(String lancamento_filme) {
        this.lancamento_filme = lancamento_filme;
    }

    public String getResenha_filme() {
        return resenha_filme;
    }

    public void setResenha_filme(String resenha_filme) {
        this.resenha_filme = resenha_filme;
    }
}
