package com.example.appestoque.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appestoque.helper.DBHelper;

public class ProdutoDAO {

    //atributos relacionados ao modelo de dados
    private Integer id;
    private String nomeprod, descricao, categoria, foto, quantidade, valor;

    //atributos relacionados ao bd
    private DBHelper dbHelper;
    private SQLiteDatabase banco;

    public ProdutoDAO(Integer id, String nomeprod, String descricao, String categoria, String quantidade, String valor, String foto, Context ctx){
        this.id = id;
        this.nomeprod = nomeprod;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.valor = valor;
        this.foto = foto;

        //variável que instacia, dá acesso ao banco de dados
        dbHelper = new DBHelper(ctx);

        banco = dbHelper.getWritableDatabase();
    }

    public ProdutoDAO(Integer id, String nomeprod, String descricao, String categoria, String quantidade, String valor, String foto){
        this.id = id;
        this.nomeprod = nomeprod;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.valor = valor;
        this.foto = foto;
    }

    public ProdutoDAO(Context ctx){
        dbHelper = new DBHelper(ctx);

        banco = dbHelper.getWritableDatabase();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeprod() {
        return nomeprod;
    }

    public void setNomeprod(String nomeprod) {
        this.nomeprod = nomeprod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    //em classes do tipo dao usa-se métodos para fazer o CRUD
    public boolean insert(){
        return false;
    }

    public boolean update(){
        return false;
    }

    public boolean delete(){
        return false;
    }

    public Cursor listaProdutos(){
        //já que sempre haverá um campo chamado _id no SimpleCursorAdapter precisamos que no código sql algum dado seja lido como _id
        String sql = "SELECT id as _id, nomeprod FROM produto;";

        //conexões e comandos precisam da variável banco, o rawQuery faz consultas no sql, não passa outros argumentos (null) porque a instrução "SELECT..." não oferece nenhuma restrição
        Cursor c = banco.rawQuery(sql, null);

        //verificar se houve retorno na consulta
        if(c != null){
            //o cursor se movimenta para o primeiro elemento da lista retornada pelo rawQuery
            c.moveToFirst();
        }
        return c;
    }

    public Boolean inserirProdutos(){
        ContentValues cv = new ContentValues();
        cv.put("nomeprod", this.nomeprod);
        cv.put("descricao", this.descricao);
        cv.put("categoria", this.categoria);
        cv.put("quantidade", this.quantidade);
        cv.put("valor", this.valor);
        cv.put("foto", this.foto);

        long ret = banco.insert("produto", null, cv);

        if (ret > 0 ){
            return true;
        }
        else {
            return false;
        }
    }

    public void obterProdutoById(int i){}
}

