package com.example.microcompanylist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name= "banco.db";
    private static final  int version = 1;


    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table empresa (id integer primary key autoincrement, " +
                " nomeEmpresa varchar(50), tipoServico varchar(50), endereco varchar(250)," +
                " telefone varchar(50), proprietario varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
