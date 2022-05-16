package DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projetocinemaprocessoseletivo.DetalhesFilme;
import com.example.projetocinemaprocessoseletivo.Model.Filmes;
import com.example.projetocinemaprocessoseletivo.adapter.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClasseSqLiteHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NOME = "FilmesFavoritosDataBase.bd";
    private static final int DATABASE_VERSION = 1;
    private static final String NOME_TABELA = "Meus_Filmes_Favoritos";
    private static final String COLUNA_ID = "ID_TABELA_FILME";
    private static final String COLUNA_NOME_FILME = "NOME_FILME";
    private static final String COLUNA_NOTA_FILME = "NOTA_FILME";
    private static final String COLUNA_ID_FILME_API = "ID_FILME_API";
    private static final String COLUNA_DATA_LANCAMENTO_FILME = "DATA_LANCAMENTO_FILME";
    private static final String COLUNA_RESENHA_FILME = "RESENHA_FILME";
    private static final String COLUNA_IDIOMA_FILME = "IDIOMA_FILME";
    private static final String COLUNA_POSTER_FILME = "POSTER_FILME";




    public ClasseSqLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + NOME_TABELA +
                        " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUNA_NOME_FILME + " TEXT, " +
                        COLUNA_NOTA_FILME + " TEXT, " +
                        COLUNA_ID_FILME_API + " TEXT, " +
                        COLUNA_DATA_LANCAMENTO_FILME + " TEXT, " +
                        COLUNA_RESENHA_FILME + " TEXT, " +
                        COLUNA_IDIOMA_FILME + " TEXT, " +
                        COLUNA_POSTER_FILME + " BLOB);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db .execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(db);
    }


    public void inserirDadosFilmeFavorito(com.example.projetocinemaprocessoseletivo.Model.Filmes filmeFavorito, byte[] poster){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put(COLUNA_POSTER_FILME, poster);
        dados.put(COLUNA_NOME_FILME, filmeFavorito.getNome_filme());
        dados.put(COLUNA_NOTA_FILME, filmeFavorito.getNota_filme());
        dados.put(COLUNA_ID_FILME_API , filmeFavorito.getId());
        dados.put(COLUNA_DATA_LANCAMENTO_FILME, filmeFavorito.getLancamento_filme());
        dados.put(COLUNA_RESENHA_FILME, filmeFavorito.getResenha_filme());
        dados.put(COLUNA_IDIOMA_FILME, filmeFavorito.getIdioma_filme());
        db.insert(NOME_TABELA, null, dados);

        Long resultado = db.insert(NOME_TABELA, null, dados);
        if(resultado == -1){
            Log.d("ERRO", "DEU ERRO");

        }else {
            Log.d("CERTO", "DEU CERTO");
           // Toast.makeText(context.getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("Range")
    public List<Filmes> buscarFilmesFavoritos(){

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + NOME_TABELA + " ;";

        Cursor cursor = db.rawQuery(sql, null);


        List<Filmes> filmesList = new ArrayList<>();
        //List<DetalhesFilme> detalhesFilmesArray = new ArrayList<DetalhesFilme>();

        while (cursor.moveToNext()){

           // DetalhesFilme filmes = new DetalhesFilme();
            Filmes filmes1 = new Filmes();
            filmes1.setId(cursor.getString(cursor.getColumnIndex(COLUNA_ID_FILME_API)));
            filmes1.setNome_filme(cursor.getString(cursor.getColumnIndex(COLUNA_NOME_FILME)));
            filmes1.setLancamento_filme(cursor.getString(cursor.getColumnIndex(COLUNA_DATA_LANCAMENTO_FILME)));
            filmes1.setResenha_filme(cursor.getString(cursor.getColumnIndex(COLUNA_RESENHA_FILME)));
            filmes1.setIdioma_filme(cursor.getString(cursor.getColumnIndex(COLUNA_IDIOMA_FILME)));
            filmesList.add(filmes1);
        }
        return filmesList;
    }
}
