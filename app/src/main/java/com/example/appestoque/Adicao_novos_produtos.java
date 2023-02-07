package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appestoque.dao.ProdutoDAO;

public class Adicao_novos_produtos extends AppCompatActivity {

    //king -- variáveis do produto
    EditText nomep, descrp, qntp, categp, valorp;
    //talvez não use o image view
    ImageView camera;
    Button addfoto, cadprod;
    //

    //king -- barra inferior
    ImageView home, novo, relatorio;
    TextView homet, novot, relatoriot;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicao_novos_produtos);

        //king -- barra inferior
        home = findViewById(R.id.iconHome);
        homet = findViewById(R.id.idhome);
        novo = findViewById(R.id.iconnovo);
        novot = findViewById(R.id.idnovo);
        relatorio = findViewById(R.id.iconrelatorio);
        relatoriot = findViewById(R.id.idrelatorio);
        //

        //king -- atributos do produto
        nomep = findViewById(R.id.edit_nomeProd);
        descrp = findViewById(R.id.edit_descricaoProd);
        qntp = findViewById(R.id.edit_qntProd);
        categp = findViewById(R.id.edit_categoriaProd);
        valorp = findViewById(R.id.edit_valorProd);
        camera = findViewById(R.id.iconFoto);
        addfoto = findViewById(R.id.botao_foto);
        cadprod = findViewById(R.id.botao_cadastProd);

        //king -- cadastrando produto
        cadprod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //inserção de dados
                ProdutoDAO p = new ProdutoDAO(null, nomep.getText().toString(), descrp.getText().toString(), categp.getText().toString(), qntp.getText().toString(), valorp.getText().toString(), addfoto.getText().toString(), Adicao_novos_produtos.this);

                if(p.inserirProdutos()){
                    Intent main = new Intent(Adicao_novos_produtos.this, tela_inicial.class);

                    startActivity(main);
                }
            }
        });
        //

        //king -- barra inferior
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial.class);
                startActivity(it);
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial.class);
                startActivity(it);
            }
        });

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicao_novos_produtos.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        novot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicao_novos_produtos.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicao_novos_produtos.this, relatorio.class);
                startActivity(it);
            }
        });

        relatoriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Adicao_novos_produtos.this, relatorio.class);
                startActivity(it);
            }
        });
        //
    }


}