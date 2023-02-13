package com.example.appestoque.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appestoque.dao.Produto;
import com.example.appestoque.dao.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {
    SQLiteDatabase db = getWritableDatabase();

    public DAO (Context context){
        super (context, "banco", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //criando tabela
        String sql1 = ("CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(100), senha VARCHAR(10))");

        //executando a criação da tabela
        db.execSQL(sql1);

        String sql2 = ("CREATE TABLE produto (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(100), descricao VARCHAR(100), categoria VARCHAR(100), quantidade INT, valor DECIMAL)");

        //executando a criação da tabela
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //remove as informações de usuário
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS produto");
        onCreate(db);
    }

    public Boolean verificarUsuario(String nomeusuario){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE nome=?", new String[] {nomeusuario});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean verificarSeHaProduto(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produto", null);
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean delete(){
        int if_delete = db.delete("produto", null, null);

        return true;
    }
    public boolean insereUser(Usuario user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", user.getNome());
        dados.put("senha", user.getSenha());

        if(db.insert("usuario", null, dados) != -1){
            return true;
        }else{
            return false;
        }
    }

    public Boolean verificarSenha(String nomeusuario, String senha){
        SQLiteDatabase db = this.getWritableDatabase();

        //o cursor vai buscar na tabela do banco se existe algum usuário com o nome e a senha informados
        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE nome=? AND senha=?", new String[] {nomeusuario, senha});

        //o cursor conta o resultado da busca, se for maior que 0 ele retorna true, se não, retorna false
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean insereProduto(Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", produto.getNome());
        dados.put("descricao", produto.getDescricao());
        dados.put("categoria", produto.getCategoria());
        dados.put("quantidade", produto.getQuantidade());
        dados.put("valor", produto.getValor());

        if(db.insert("produto", null, dados) != -1){
            return true;
        }else{
            return false;
        }
    }

    public Cursor selectProduto(){
        SQLiteDatabase db = getReadableDatabase();

        String sql_select = "SELECT * FROM produto;";
        Cursor lista = db.rawQuery(sql_select, null);

        return lista;
    }

    public Cursor select(){
        SQLiteDatabase db = getReadableDatabase();
        String sql_select = "SELECT * FROM produto;";
        Cursor lista = db.rawQuery(sql_select, null);

        return lista;
    }

    public Cursor select_all(){
        SQLiteDatabase db = getReadableDatabase();
        String sql_select = "SELECT 'id' as '_id', nome, descricao, categoria, quantidade, valor FROM produto;";
        Cursor lista = db.rawQuery(sql_select, null);

        if (lista != null){
            lista.moveToFirst();
        }
        return lista;
    }
    public List<Produto> buscaCategoriaProduto(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT DISTINCT categoria FROM produto ;";

        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();

        while (c.moveToNext()){
            Produto produto = new Produto();

            produto.setCategoria(c.getString(c.getColumnIndexOrThrow("categoria")));

            Log.e("buscandoCategoria", "coisa" +String.valueOf(produto));

            produtos.add(produto);
        }

        return produtos;
    }

    public List<Produto> buscaItemProduto(String categ){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM produto WHERE categoria = '" + categ +"';";

        Cursor c = db.rawQuery(sql, null);

        List<Produto> itens = new ArrayList<Produto>();

        while (c.moveToNext()){
            Produto produto = new Produto();

            produto.setNome(c.getString(c.getColumnIndexOrThrow("nome")));
            produto.setDescricao(c.getString(c.getColumnIndexOrThrow("descricao")));
            produto.setCategoria(c.getString(c.getColumnIndexOrThrow("categoria")));
            produto.setQuantidade(Integer.parseInt(c.getString(c.getColumnIndexOrThrow("quantidade"))));
            produto.setValor(Double.valueOf(c.getString(c.getColumnIndexOrThrow("valor"))));

            itens.add(produto);
        }

        return itens;
    }
}
