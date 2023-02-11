package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appestoque.adapter.RecyclerViewAdapter;
import com.example.appestoque.dao.Produto;
import com.example.appestoque.helper.DAO;

import java.util.ArrayList;
import java.util.List;

public class tela_inicial_itens extends AppCompatActivity {

    ImageView home, novo, relatorio;
    TextView homet, novot, relatoriot;
    Context context;
    RecyclerView listacateg;

    //layouts dinâmicos
    LinearLayout linearLayout;
    RecyclerView.Adapter recyclerviewadapter;
    RecyclerView.LayoutManager recyclerviewmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_itens);

        context = getApplicationContext();
        home = findViewById(R.id.iconHome);
        homet = findViewById(R.id.idhome);
        novo = findViewById(R.id.iconnovo);
        novot = findViewById(R.id.idnovo);
        relatorio = findViewById(R.id.iconrelatorio);
        relatoriot = findViewById(R.id.idrelatorio);
        listacateg = findViewById(R.id.lista_categorias);

        buscaNoBanco();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_itens.this, tela_inicial.class);
                startActivity(it);
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_itens.this, tela_inicial.class);
                startActivity(it);
            }
        });

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_itens.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        novot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_itens.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_itens.this, relatorio.class);
                startActivity(it);
            }
        });

        relatoriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(tela_inicial_itens.this, relatorio.class);
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

        for (Produto produtoBuscado : produtos){
            categoria.add(produtoBuscado.getCategoria());
        }

        dados_categoria = categoria.toArray(new String[0]);

        recyclerviewmanager = new LinearLayoutManager(context);
        listacateg.setLayoutManager(recyclerviewmanager);
        recyclerviewadapter = new RecyclerViewAdapter(context, dados_categoria);
        listacateg.setAdapter(recyclerviewadapter);
    }
}