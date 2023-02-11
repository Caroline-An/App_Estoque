package com.example.appestoque;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appestoque.databinding.ActivityMainBinding;
import com.example.appestoque.helper.DAO;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    //Ana -- para login com google
        ActivityMainBinding binding;
        GoogleSignInClient googleSignInClient;
        private FirebaseAuth mAuth;
    //

    DAO banco;
    //XXX -- para login comum
        EditText nomeusuario, senha;
        Button entrar, cadastrar;
        DAO bd;
    //

    //-- para ver outras telas EXCLUIR DPS
    Button irtelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ana -- para login com google
            //vinculando com o arquivo de layout
            binding = ActivityMainBinding.inflate(getLayoutInflater());

        banco = new DAO(this);

                //crianndo view
            View view = binding.getRoot();

            setContentView(view);

                //conectando com o firebase NÃO SEI SE ESTOU USANDO
            mAuth = FirebaseAuth.getInstance();

                //objeto para opções do login com google
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("313453027242-3lvq3eqlr4h96g0rf8nc8e9ppmi7tha1.apps.googleusercontent.com")
                    .requestEmail()
                    .build();

            googleSignInClient = GoogleSignIn.getClient(this, gso);

            binding.sigingoogle.setOnClickListener(v -> {
                signIn();
            });

            nomeusuario = findViewById(R.id.edit_nome);
            senha = findViewById(R.id.edit_senha);
            entrar = findViewById(R.id.botao_entrar);
            cadastrar = findViewById(R.id.botao_cadastre_se);
            bd = new DAO(this);

            entrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String usuario = nomeusuario.getText().toString();
                    String senhaa = senha.getText().toString();

                    //veirifica se algum campo está vazio
                    if(TextUtils.isEmpty(usuario) || TextUtils.isEmpty(senhaa)){
                        Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    }else {
                        //chama o método de verificar senha no DBHelper
                        Boolean verifiSenha = bd.verificarSenha(usuario, senhaa);

                        //se os dados estiverem no banco ele retorna true, informa que efetuou o login e vai para a tela inicial
                        if (verifiSenha == true) {

                            Boolean insere = banco.verificarSeHaProduto();


                            if(insere == true){

                                Toast.makeText(MainActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(MainActivity.this, tela_inicial_itens.class);
                                startActivity(it);
                            }else {

                                Toast.makeText(MainActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(MainActivity.this, tela_inicial.class);
                                startActivity(it);
                            }


                        }else {
                            Toast.makeText(MainActivity.this, "Erro de autenticação! Tente novamente.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            //botão de ir à proxima activity
            cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(MainActivity.this, Cadastro.class);
                    startActivity(it);
                }
            });

        //

        //VER OUTRAS TELAS EXCLUIR DEPOIS
        irtelas = findViewById(R.id.botao_irtelas);

        irtelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, tela_inicial.class);
                startActivity(it);
            }
        });




    }

    //Ana -- para login com google
        private void signIn(){
            Intent i = googleSignInClient.getSignInIntent();
            //startActivityForResult(i, 1);
            abreActivity.launch(i);
        }

        ActivityResultLauncher<Intent> abreActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent it = result.getData();

                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(it);
                        try{
                            GoogleSignInAccount conta = task.getResult(ApiException.class);
                            loginComGoogle(conta.getIdToken());
                        }catch (ApiException exception){
                            Toast.makeText(getApplicationContext(), "Nenhum usuário Google logado", Toast.LENGTH_LONG).show();
                            //Log.d("Erro:", exception.toString());
                        }
                    }
                }
        );

        private void loginComGoogle(String token){
            AuthCredential credencial = GoogleAuthProvider.getCredential(token, null);
            mAuth.signInWithCredential(credencial).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login efetuado com sucesso", Toast.LENGTH_LONG).show();

                    Intent it = new Intent(MainActivity.this, tela_inicial.class);
                    startActivity(it);
                }else{
                    Toast.makeText(getApplicationContext(), "Erro ao efetuar o login com Google", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent){
            super.onActivityResult(requestCode, resultCode, intent);

            if(requestCode == 1){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                try{
                    GoogleSignInAccount conta = task.getResult(ApiException.class);
                    loginComGoogle(conta.getIdToken());
                }catch (ApiException exception){
                    Toast.makeText(getApplicationContext(), "Nenhum usuário Google logado", Toast.LENGTH_LONG).show();
                    //Log.d("Erro:", exception.toString());
                }
            }
        }
    //
}