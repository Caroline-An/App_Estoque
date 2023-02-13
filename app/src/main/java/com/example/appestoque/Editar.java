package com.example.appestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appestoque.helper.DAO;

public class Editar extends AppCompatActivity {

    Button btn_excluir, btn_editar;
    DAO banco;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        btn_excluir = findViewById(R.id.btn_excluir);
        btn_editar = findViewById(R.id.btn_editar);
        banco = new DAO(this);

        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banco.delete();
                Toast.makeText(Editar.this, "Produto deletado com sucesso!", Toast.LENGTH_LONG).show();
            }
        });


        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Editar.this, "Produto Editado com sucesso!", Toast.LENGTH_LONG).show();
            }
        });

    }
}