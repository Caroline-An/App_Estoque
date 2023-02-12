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

import com.example.appestoque.R;
import com.example.appestoque.helper.DAO;
import com.example.appestoque.tela_inicial_categorias;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    String[] categorias;

    View viewOnCreate;

    ViewHolder viewHolderLocal;

    public RecyclerViewAdapter(Context contextRecebido, String[] categoriasRecebidas){
        context = contextRecebido;
        categorias = categoriasRecebidas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtcategoria;
        public ImageView icone;

        public ViewHolder( View itemView) {
            super(itemView);

            txtcategoria = itemView.findViewById(R.id.txtCategoria);
            icone = itemView.findViewById(R.id.iconFoto);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);

        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtcategoria.setText(categorias[position]);

        holder.icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.buscaProdutos(categorias[position]);


            }
        });

    }

    @Override
    public int getItemCount() {
        return categorias.length;
    }
}