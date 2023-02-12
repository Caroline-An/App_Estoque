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
                    String nomeudosuario = nomeusuario.getText().toString();
                    String senhaqvai = senha.getText().toString();
                    String confsenha = confirmasenha.getText().toString();
                    Usuario usuario = new Usuario();

                    usuario.setNome(nomeudosuario);
                    usuario.setSenha(senhaqvai);

                    //verifica campos vazios, se não houver ele vai verificar se as senhas são iguais
                    if(!(TextUtils.isEmpty(nomeudosuario) || TextUtils.isEmpty(senhaqvai) || TextUtils.isEmpty(confsenha))){

                        //verifica se as senhas são iguais
                        if(senhaqvai.equals(confsenha)){

                            //verifica se o nome de usuário já existe no banco
                            Boolean veriusuario = banco.verificarUsuario(nomeudosuario);

                            //se não tiver registro do nome informado, ele insere no banco
                            if (veriusuario == false){

                                Boolean insere = banco.insereUser(usuario);

                                //se der tudo certo, mostra mensagem de sucesso e vai para a tela de login
                                if(insere == true){
                                    Toast.makeText(Cadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                                    Intent it = new Intent(Cadastro.this, MainActivity.class);
                                    startActivity(it);

                                    //se não der certo, mostra mensagem de falha
                                }else {
                                    Toast.makeText(Cadastro.this, "Falha ao tentar cadastrar! Tente novamente.", Toast.LENGTH_LONG).show();
                                }

                                //se houver, exibe mensagem de usuário já existente
                            } else {
                                Toast.makeText(Cadastro.this, "Usuário já existe, por favor tente outro!", Toast.LENGTH_LONG).show();
                            }

                            //se não forem iguais, mostra mensagem de erro
                        } else {
                            Toast.makeText(Cadastro.this, "As senhas não combinam, tente novamente", Toast.LENGTH_LONG).show();
                        }

                        //se houver campos vazios, mostra mensagem de erro
                    } else {
                        Toast.makeText(Cadastro.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    }

                }
            });

        //
    }
}