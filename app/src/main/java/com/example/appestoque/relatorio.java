package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appestoque.dao.Produto;
import com.example.appestoque.helper.DAO;

public class relatorio extends AppCompatActivity {

    Button pdf;
    ImageView home, novo, relatorio;
    TextView homet, novot, relatoriot;

    //aplicando redirecionamento correto para todas as funcionalidades necessárias
        DAO banco = new DAO(this);
        Produto produto = new Produto();

        Boolean insere = banco.insereProduto(produto);
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        pdf = findViewById(R.id.botao_pdf);

        home = findViewById(R.id.iconHome);
        homet = findViewById(R.id.idhome);
        novo = findViewById(R.id.iconnovo);
        novot = findViewById(R.id.idnovo);
        relatorio = findViewById(R.id.iconrelatorio);
        relatoriot = findViewById(R.id.idrelatorio);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (insere == true){
                    Intent it = new Intent(relatorio.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(relatorio.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (insere == true){
                    Intent it = new Intent(relatorio.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(relatorio.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        novot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, relatorio.class);
                startActivity(it);
            }
        });

        relatoriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, relatorio.class);
                startActivity(it);
            }
        });
    }
}