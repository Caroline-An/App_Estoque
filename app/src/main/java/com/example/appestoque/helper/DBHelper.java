package com.example.appestoque.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String NOMEBD = "organizer.db";

    public DBHelper(Context context) {
        super(context, "organizer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //criando tabela de usuários
        db.execSQL("CREATE TABLE usuarios(nomeusuario VARCHAR primary key, senha VARCHAR)");

        //criando tabela de produtos
        String sql1 = ("CREATE TABLE produto (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeprod VARCHAR(100), descricao VARCHAR(100), categoria VARCHAR(100), quantidade INT, valor DECIMAL, foto VARCHAR(100))");

        //executando a criação da tabela
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //remove as informações de usuário
        db.execSQL("DROP TABLE IF EXISTS usuarios");
    }

    public Boolean insereDados(String nomeusuario, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nomeusuario", nomeusuario);
        valores.put("senha", senha);

        long result = db.insert("nomeusuario", null, valores);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean verificarUsuario(String nomeusuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE nomeusuario=?", new String[] {nomeusuario});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean verificarSenha(String nomeusuario, String senha){
        SQLiteDatabase db = this.getWritableDatabase();

        //o cursor vai buscar na tabela do banco se existe algum usuário com o nome e a senha informados
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE nomeusuario=? AND senha=?", new String[] {nomeusuario, senha});

        //o cursor conta o resultado da busca, se for maior que 0 ele retorna true, se não, retorna false
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

}
