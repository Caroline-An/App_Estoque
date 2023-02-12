package com.example.appestoque.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appestoque.R;
public class RecyclerViewAdapter_Itens extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements RecyclerViewAdapter_Itens2 {
    Context context;
    String[] nome;
    String[] descricao;
    String[] categoria;
    String[] quantidade;
    String[] valor;
    View viewOnCreate;
    RecyclerView.ViewHolder viewHolderLocal;
    public RecyclerViewAdapter_Itens(Context contextRecebido, String[] nomeRecebido, String[] descricaoRecebida, String[] categoriaRecebida, String[] quantidadeRecebida, String[] valorRecebido){
        context = contextRecebido;
        nome = nomeRecebido;
        descricao = descricaoRecebida;
        categoria = categoriaRecebida;
        quantidade = quantidadeRecebida;
        valor = valorRecebido;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtnome;
        public TextView txtdescricao;
        public TextView txtcategoria;
        public TextView txtquantidade;
        public TextView txtvalor;
        public ImageView icone;
        public ViewHolder( View itemView) {
            super(itemView);
            txtnome = itemView.findViewById(R.id.txtNome);
            txtdescricao = itemView.findViewById(R.id.txtDescricao);
            txtcategoria = itemView.findViewById(R.id.txtCategoria);
            txtquantidade = itemView.findViewById(R.id.txtQuantidade);
            txtvalor = itemView.findViewById(R.id.txtValor);
            icone = itemView.findViewById(R.id.iconFoto);
        }
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.recyclerview_itens, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);

        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtnome.setText(nome[position]);
        holder.txtdescricao.setText(descricao[position]);
        holder.txtcategoria.setText(categoria[position]);
        holder.txtquantidade.setText(quantidade[position]);
        holder.txtvalor.setText(valor[position]);

    }
    @Override
    public int getItemCount() {
        return 0;
    }
}
