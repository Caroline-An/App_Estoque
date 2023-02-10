package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appestoque.dao.Usuario;
import com.example.appestoque.helper.DAO;

public class Cadastro extends AppCompatActivity {

    //XX -- para cadastrar
        EditText nomeusuario, senha, confirmasenha;
        Button cadastrar;

        DAO banco;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //XX -- para cadastrar
            nomeusuario = findViewById(R.id.edit_nome_cadastro);
            senha = findViewById(R.id.edit_senha_cadastro);
            confirmasenha = findViewById(R.id.edit_confsenha_cadastro);
            cadastrar = findViewById(R.id.botao_cadastrar);
            banco = new DAO(this);

            cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String usuario = nomeusuario.getText().toString();
                    String senhaqvai = senha.getText().toString();
                    String confsenha = confirmasenha.getText().toString();

                    //verifica se há algum campo vazio
                    if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senhaqvai) || TextUtils.isEmpty(confsenha)){
                        Toast.makeText(Cadastro.this, "Existem campos em branco, por favor preencha-os", Toast.LENGTH_SHORT).show();
                    }else{

                        //verifica se as senhas são iguais
                        if (senhaqvai.equals(confsenha)){

                            //verifica se o nome de usuário já existe no banco
                            Boolean veriusuario = banco.verificarUsuario(usuario);

                            //se não tiver registro do nome informado, ele insere no banco
                            if (veriusuario == false){
                                Boolean insere = banco.insereUser(usuario);

                                Log.e("mensagem", senhaqvai+usuario);

                                if(insere == true){
                                    Toast.makeText(Cadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                                    Intent it = new Intent(Cadastro.this, tela_inicial.class);
                                    startActivity(it);
                                }else {
                                    Toast.makeText(Cadastro.this, "Falha ao tentar cadastrar! Tente novamente.", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Cadastro.this, "Usuário já existe! Escolha outro nome e tente novamente.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Cadastro.this, "As senhas não combinam, tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        //
    }
}