package com.example.appestoque;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

import com.example.appestoque.dao.Produto;
import com.example.appestoque.helper.DAO;


public class Adicao_novos_produtos extends AppCompatActivity {

    //king -- variáveis do produto

    EditText nomep, descrp, qntp, categp, valorp;
    //talvez não use o image view
    ImageView camera;
    Button addfoto, cadprod;
    //

    //aplicando redirecionamento correto para todas as funcionalidades necessárias
    DAO banco = new DAO(this);
    Produto produto = new Produto();

    Boolean insere = banco.insereProduto(produto);
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
        camera = findViewById(R.id.iconFoto);
        addfoto = findViewById(R.id.botao_foto);
        cadprod = findViewById(R.id.botao_cadastProd);
        //

        //king -- cadastrando produto

        cadprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(nomep.getText().toString().equals("") || descrp.getText().toString().equals("") || qntp.getText().toString().equals("") || categp.getText().toString().equals("") || qntp.getText().toString().equals("") || categp.getText().toString().equals("") || valorp.getText().toString().equals(""))){

                    produto.setNome(nomep.getText().toString());
                    produto.setDescricao(descrp.getText().toString());
                    produto.setCategoria(categp.getText().toString());
                    produto.setQuantidade(Integer.parseInt(qntp.getText().toString()));
                    produto.setValor(Double.valueOf(valorp.getText().toString()));

                    Boolean insere = banco.insereProduto(produto);

                    Log.e("produto","nome: "+produto.getNome()+" desc: "
                            +produto.getDescricao()+" categ: "+produto.getCategoria()+
                            " quanti: "+produto.getQuantidade()+" valor: "+produto.getValor());

                    if(insere == true){
                        Toast.makeText(Adicao_novos_produtos.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                        //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                        if (insere == true){
                            Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial_itens.class);
                            startActivity(it);
                        } else {
                            Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial.class);
                            startActivity(it);
                        }

                    }else {
                        Toast.makeText(Adicao_novos_produtos.this, "Falha ao tentar cadastrar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Adicao_novos_produtos.this, "Existem campos vazios, preencha-os e tente novamente!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //

        //Ana -- usando a câmera
        addfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder selecionaFoto = new AlertDialog.Builder(Adicao_novos_produtos.this);
                selecionaFoto.setTitle("Origem da foto");
                selecionaFoto.setMessage("Por favor, selecione a origem da foto");
                selecionaFoto.setPositiveButton("Câmera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent abre_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(abre_camera, 100);
                    }
                });
                selecionaFoto.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent abre_galeria = new Intent(Intent.ACTION_GET_CONTENT);
                        abre_galeria.setType("image/*");
                        startActivityForResult(abre_galeria, 2);
                    }
                });
                selecionaFoto.create().show();
            }
        });

        //

        //king -- barra inferior
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (insere == true){
                    Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial_itens.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (insere == true){
                    Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial_itens.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(Adicao_novos_produtos.this, tela_inicial.class);
                    startActivity(it);
                }
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