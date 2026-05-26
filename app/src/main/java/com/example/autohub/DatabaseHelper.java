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
    private static final int DATABASE_VERSION = 2;

    //criacao da tabela modificacoes
    public static final String TABLE_MODIFICACOES = "modificacoes";
    public static final String MOD_ID = "id";
    public static final String MOD_NOME = "nome";
    public static final String MOD_STATUS = "status";
    public static final String MOD_CUSTO = "custo";

    //criacao da tabela manutencoes
    public static final String TABLE_MANUTENCOES = "manutencoes";
    public static final String MAN_ID = "id";
    public static final String MAN_NOME = "nome";
    public static final String MAN_DATA = "data";
    public static final String MAN_CUSTO = "custo";
    public static final String MAN_KM = "quilometragem";


    //construtor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //criação do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {

        //tabela modificacoes
        String createTable = "CREATE TABLE " + TABLE_MODIFICACOES + "(" +
                MOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MOD_NOME + " TEXT," +
                MOD_STATUS + " TEXT," +
                MOD_CUSTO + " REAL" +
                ")";
        db.execSQL(createTable);

        //tabela manutencoes
        String createTable2 = "CREATE TABLE " + TABLE_MANUTENCOES + "(" +
                MAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MAN_NOME + " TEXT," +
                MAN_DATA + " TEXT," +
                MAN_CUSTO + " REAL," +
                MAN_KM + " INTEGER" +
                ")";
        db.execSQL(createTable2);

    }

    //atualizacao do banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODIFICACOES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUTENCOES);
        onCreate(db);
    }

    //=======================
    //CRUD DE MODIFICACOES
    //=======================

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

    //=======================
    //CRUD DE MANUTENCOES
    //=======================

    //insert
    public long insertManutencao(String nome, String data, double custo, int km) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAN_NOME, nome);
        values.put(MAN_DATA, data);
        values.put(MAN_CUSTO, custo);
        values.put(MAN_KM, km);

        return db.insert(TABLE_MANUTENCOES, null, values);
    }

    //listar
    public Cursor listarManutencoes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_MANUTENCOES, null);
    }

    //update
    public void atualizarManutencao(long id, String nome, String data, double custo, int km) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MAN_NOME, nome);
        values.put(MAN_DATA, data);
        values.put(MAN_CUSTO, custo);
        values.put(MAN_KM, km);
        db.update(
            TABLE_MANUTENCOES,
            values,
            MAN_ID + "=?",
            new String[]{String.valueOf(id)}
        );
    }

    //delete
    public void deletarManutencao(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
            TABLE_MANUTENCOES,
            MAN_ID + "=?",
            new String[]{String.valueOf(id)}
        );
    }

    //=======================
    //CALCULO DE GASTOS
    //=======================

    public double calcularTotalModificacoes(){
        SQLiteDatabase db = this.getReadableDatabase();
        double total = 0;

        Cursor cursor = db.rawQuery("SELECT SUM(" + MOD_CUSTO + ") FROM " + TABLE_MODIFICACOES + " WHERE " + MOD_STATUS + " = ?", new String[]{"Concluído"}  );
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }

    public double calcularTotalManutencoes(){
        SQLiteDatabase db = this.getReadableDatabase();
        double total = 0;

        Cursor cursor = db.rawQuery("SELECT SUM(" + MAN_CUSTO + ") FROM " + TABLE_MANUTENCOES, null);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }

}
