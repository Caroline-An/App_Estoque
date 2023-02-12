package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appestoque.adapter.RecyclerViewAdapter;
import com.example.appestoque.dao.Produto;
import com.example.appestoque.helper.DAO;

import java.util.ArrayList;
import java.util.List;

public class tela_itens extends AppCompatActivity {

    ImageView home, novo, relatorio;
    TextView homet, novot, relatoriot;
    Context context;
    DAO banco;
    RecyclerView listaitens;

    //layouts dinâmicos
    LinearLayout linearLayout;
    RecyclerView.Adapter recyclerviewadapter;
    RecyclerView.LayoutManager recyclerviewmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_categorias);

        context = getApplicationContext();
        home = findViewById(R.id.iconHome);
        homet = findViewById(R.id.idhome);
        novo = findViewById(R.id.iconnovo);
        novot = findViewById(R.id.idnovo);
        relatorio = findViewById(R.id.iconrelatorio);
        relatoriot = findViewById(R.id.idrelatorio);
        listaitens = findViewById(R.id.lista_categorias);

        banco = new DAO(this);

        buscaNoBanco();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean busca = banco.verificarSeHaProduto();
                if (busca == true){
                    Intent it = new Intent(tela_itens.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else{
                    Intent it = new Intent(tela_itens.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean busca = banco.verificarSeHaProduto();
                if (busca == true){
                    Intent it = new Intent(tela_itens.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else{
                    Intent it = new Intent(tela_itens.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_itens.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        novot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_itens.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_itens.this, relatorio.class);
                startActivity(it);
            }
        });

        relatoriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_itens.this, relatorio.class);
                startActivity(it);
            }
        });
    }

    private void buscaNoBanco(){
        DAO dao = new DAO(getApplicationContext());

        //lista de objetos produtos do banco
        List<Produto> produtos = dao.buscaCategoriaProduto(); //retorna as categorias

        dao.buscaItemProduto(produtos); //retorna os itens

        //lista de objetos itens do banco
        List<String> itens = new ArrayList<String>();

        String[] dados_itens = new String[]{};

        for (Produto itembuscado : produtos){
            itens.add(itembuscado.getNome());
            itens.add(itembuscado.getDescricao());
            itens.add(itembuscado.getCategoria());
            itens.add(String.valueOf(itembuscado.getQuantidade()));
            itens.add(String.valueOf(itembuscado.getValor()));
        }

        dados_itens = itens.toArray(new String[0]);

        recyclerviewmanager = new LinearLayoutManager(context);
        listaitens.setLayoutManager(recyclerviewmanager);
        recyclerviewadapter = new RecyclerViewAdapter(context, dados_itens);
        listaitens.setAdapter(recyclerviewadapter);
    }
}