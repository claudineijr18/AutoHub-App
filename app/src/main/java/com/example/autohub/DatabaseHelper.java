package com.example.autohub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;



public class DatabaseHelper extends SQLiteOpenHelper {
    //Definindo o nome do banco
    private static final String DATABASE_NAME = "autohub.db";

    //Definindo a versão do banco
    private static final int DATABASE_VERSION = 1;

    //criacao da tabela modificacoes
    public static final String TABLE_MODIFICACOES = "modificacoes";
    public static final String MOD_ID = "id";
    public static final String MOD_NOME = "nome";
    public static final String MOD_STATUS = "status";
    public static final String MOD_CUSTO = "custo";

    //construtor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //criação do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MODIFICACOES + "(" +
                MOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MOD_NOME + " TEXT," +
                MOD_STATUS + " TEXT," +
                MOD_CUSTO + " REAL" +
                ")";
        db.execSQL(createTable);

    }

    //atualizacao do banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODIFICACOES);
        onCreate(db);
    }

    //insert
    public long insertModificacao(String nome, String status, double custo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOD_NOME, nome);
        values.put(MOD_STATUS, status);
        values.put(MOD_CUSTO, custo);

        return db.insert(TABLE_MODIFICACOES, null, values);
    }

    //listar
    public Cursor listarModificacoes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MODIFICACOES, null);
    }

    //update
    public void atualizarModificacao(long id, String nome, String status, double custo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOD_NOME, nome);
        values.put(MOD_STATUS, status);
        values.put(MOD_CUSTO, custo);
        db.update(
            TABLE_MODIFICACOES,
            values,
            MOD_ID + "=?",
            new String[]{String.valueOf(id)}
        );
    }

    //delete
    public void deletarModificacao(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
            TABLE_MODIFICACOES,
            MOD_ID + "=?",
            new String[]{String.valueOf(id)}
        );
    }

}
