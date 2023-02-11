package com.example.appestoque.adapter;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    List<String> categorias = new ArrayList<String>();

    View viewOnCreate;

    ViewHolder viewHolderLocal;

    public RecyclerViewAdapter(Context contextRecebido, String[] categoriasRecebidas){
        context = contextRecebido;
        categorias.addAll(Arrays.asList(categoriasRecebidas));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtcategoria;
        public ImageView icone;

        public ViewHolder(View itemView) {
            super(itemView);

            txtcategoria = itemView.findViewById(R.id.txtCategoria);
            icone = itemView.findViewById(R.id.iconFoto);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.txtcategoria.setText(categorias.get(position));

        holder.icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaProduto(categorias.get(position));

                categorias.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, categorias.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }
}