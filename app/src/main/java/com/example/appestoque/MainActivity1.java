package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.appestoque.helper.DAO;


public class MainActivity1 extends AppCompatActivity {

    ListView listViewProduto;
    DAO banco;
    Button button, voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        button = findViewById(R.id.btn_cadastrar);
        voltar = findViewById(R.id.voltar);

//        button = findViewById(R.id.btn_cadastrar);
        banco = new DAO(this);

        Log.e("tets", "wdewf");


        listViewProduto = findViewById(R.id.listViewProduto);
        listViewProduto.setEmptyView(findViewById(R.id.text_vazio));
        Cursor lista = banco.select_all();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_listar_produtos, lista,
                new String[]{"nome", "descricao", "categoria", "quantidade", "valor"},
                new int[]{R.id.text_nome, R.id.text_descricao, R.id.categoria, R.id.quantidade, R.id.valor}, 0);

        listViewProduto.setAdapter(adapter);
//        listViewProduto.setOnClickListener(new AdapterView.OnItemClickListener());

//        listViewProduto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Editar.class);
//                startActivity(intent);
//            }
//        });

        listViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity1.this, Editar.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1.this, Adicao_novos_produtos.class);
                startActivity(intent);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1.this, tela_inicial.class);
                startActivity(intent);
            }
        });

    }
}