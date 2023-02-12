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

public class tela_inicial_categorias extends AppCompatActivity {

    ImageView home, novo, relatorio;
    TextView homet, novot, relatoriot;
    Context context;

    //layouts dinâmicos
    RecyclerView listacateg;
    LinearLayout linearLayout;
    RecyclerView.Adapter recyclerviewadapter;
    RecyclerView.LayoutManager recyclerviewmanager;

    //aplicando redirecionamento correto para todas as funcionalidades necessárias
        DAO banco = new DAO(this);
        Produto produto = new Produto();

        Boolean busca = banco.verificarSeHaProduto();
    //

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
        listacateg = findViewById(R.id.lista_categorias);

        buscaNoBanco();

        Boolean insere = banco.insereProduto(produto);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (busca == true){
                    Intent it = new Intent(tela_inicial_categorias.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(tela_inicial_categorias.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (busca == true){
                    Intent it = new Intent(tela_inicial_categorias.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(tela_inicial_categorias.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_categorias.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        novot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_categorias.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_categorias.this, relatorio.class);
                startActivity(it);
            }
        });

        relatoriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_categorias.this, relatorio.class);
                startActivity(it);
            }
        });
    }

    private void buscaNoBanco(){
        DAO dao = new DAO(getApplicationContext());

        //lista de objetos produtos do banco
        List<Produto> produtos = dao.buscaCategoriaProduto();

        //lista de objetos categoria do banco
        List<String> categoria = new ArrayList<String>();

        String[] dados_categoria = new String[]{};

        for (Produto categoriabuscada : produtos){
            categoria.add(categoriabuscada.getCategoria());
        }

        dados_categoria = categoria.toArray(new String[0]);

        recyclerviewmanager = new LinearLayoutManager(context);
        listacateg.setLayoutManager(recyclerviewmanager);
        recyclerviewadapter = new RecyclerViewAdapter(context, dados_categoria);
        listacateg.setAdapter(recyclerviewadapter);
    }
}