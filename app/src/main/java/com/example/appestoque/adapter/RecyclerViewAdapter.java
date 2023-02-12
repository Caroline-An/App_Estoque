package com.example.appestoque.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appestoque.Cadastro;
import com.example.appestoque.R;
import com.example.appestoque.tela_itens;

import kotlin.text.UStringsKt;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtcategoria.setText(categorias[position]);
        viewOnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, tela_itens.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(it);
            }
        });
    }
    public int getItemCount() {
        return categorias.length;
    }
    public String getItemlenght() {
        return "length";
    }
}