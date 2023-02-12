package com.example.appestoque;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import com.example.appestoque.dao.Produto;
import com.example.appestoque.helper.DAO;

public class relatorio extends AppCompatActivity {

    Button pdf;
    ImageView home, novo, relatorio;
    TextView homet, novot, relatoriot;
    private static final int CREATEPDF = 1;
    int altura;
    double total=0;

    //aplicando redirecionamento correto para todas as funcionalidades necessárias
        DAO banco = new DAO(this);
        Produto produto = new Produto();

        Boolean busca = banco.verificarSeHaProduto();
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        pdf = findViewById(R.id.botao_pdf);

        home = findViewById(R.id.iconHome);
        homet = findViewById(R.id.idhome);
        novo = findViewById(R.id.iconnovo);
        novot = findViewById(R.id.idnovo);
        relatorio = findViewById(R.id.iconrelatorio);
        relatoriot = findViewById(R.id.idrelatorio);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (busca == true){
                    Intent it = new Intent(relatorio.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(relatorio.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerar_relatorio("Relatorio");
            }
        });

        homet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se há produtos cadastrados no banco, se sim abre tela do recyclerview, se não, vai pra tela_inicial
                if (busca == true){
                    Intent it = new Intent(relatorio.this, tela_inicial_categorias.class);
                    startActivity(it);
                } else {
                    Intent it = new Intent(relatorio.this, tela_inicial.class);
                    startActivity(it);
                }
            }
        });

        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        novot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, Adicao_novos_produtos.class);
                startActivity(it);
            }
        });

        relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, relatorio.class);
                startActivity(it);
            }
        });

        relatoriot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(relatorio.this, relatorio.class);
                startActivity(it);
            }
        });
    }


    public void gerar_relatorio(String titulo){
        Intent it = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        it.addCategory(Intent.CATEGORY_OPENABLE);
        it.setType("application/pdf");
        it.putExtra(Intent.EXTRA_TITLE, titulo);
        startActivityForResult(it, CREATEPDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        banco = new DAO(this);

        if(requestCode == CREATEPDF){
            if(data.getData()!=null){
                if(true) {
                    Uri caminhDoArquivo = data.getData();
                    PdfDocument pdfDocument = new PdfDocument();
                    Paint paint = new Paint();
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1240, 1754, 1).create();
                    PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                    Canvas canvas = page.getCanvas();
                    canvas.drawColor(0xFFE9F3FD);

                    paint.setTextAlign(Paint.Align.CENTER);
                    paint.setTextSize(40f);
                    paint.setFakeBoldText(true);
                    canvas.drawText("Relatório de Estoque", pageInfo.getPageWidth()/2, 145, paint);

                    paint.setTextAlign(Paint.Align.LEFT);
                    paint.setTextSize(32f);
                    paint.setFakeBoldText(false);
                    paint.setColor(Color.BLACK);

                    canvas.drawText("Nome", 50,200, paint);
                    canvas.drawText("Descrição", 250,200, paint);
                    canvas.drawText("Categoria", 450, 200, paint);
                    canvas.drawText("Quantidade", 650, 200, paint);
                    canvas.drawText("Valor", 850, 200, paint);
                    canvas.drawText("Total", 1000, 200, paint);
                    canvas.drawLine(48,210,pageInfo.getPageWidth()-100, 210, paint);

                    Cursor cursor = banco.selectProduto();
                    Produto produto = new Produto();
                    altura= 275;
                    String descricao = "";
                    String nome = "";
                    int quantidade = 0;
                    Double precoprod;
                    String categoria;
                    int cod;

                    if (cursor != null) {
                        if (cursor.moveToFirst()) {

                            do {

                                nome = cursor.getString(1);
                                descricao = cursor.getString(2);
                                categoria = cursor.getString(3);
                                quantidade = cursor.getInt(4);
                                int valor = cursor.getInt(5);

                                canvas.drawText(""+nome, 50, altura, paint);
                                canvas.drawText(""+descricao, 250, altura, paint);
                                canvas.drawText(""+categoria, 450, altura, paint);
                                canvas.drawText(""+quantidade, 650, altura, paint);
                                canvas.drawText(""+valor, 850, altura, paint);
                                canvas.drawText("R$ "+(valor*quantidade), 1000, altura, paint);
                                total = total + (valor*quantidade);
//                                canvas.drawLine(48,altura,pageInfo.getPageWidth()-100, altura, paint);
                                //listadeprod.add(listprod);
                                altura = altura + 100;
                            } while (cursor.moveToNext());
                            canvas.drawText("R$ "+total, 1000, altura, paint);
                            altura=altura-50;
                            canvas.drawLine(48,altura,pageInfo.getPageWidth()-100, altura, paint);
                        }
                    }

                    Date data_atual = new Date();
                    canvas.drawText("Data atual:  "+data_atual.toString(), 500, 1635, paint);


                    pdfDocument.finishPage(page);
                    gravarPdf(caminhDoArquivo, pdfDocument);
                }
            }
        }
    }

    private void gravarPdf(Uri caminhDoArquivo, PdfDocument pdfDocument) {
        try{
            BufferedOutputStream stream = new BufferedOutputStream(Objects.requireNonNull(getContentResolver().openOutputStream(caminhDoArquivo)));
            pdfDocument.writeTo(stream);
            pdfDocument.close();
            stream.flush();
            Toast.makeText(this, "PDF Gravado Com Sucesso", Toast.LENGTH_LONG).show();

        }catch (FileNotFoundException e){
            Toast.makeText(this, "Erro de arquivo não encontrado", Toast.LENGTH_LONG).show();
        }catch (IOException e){
            Toast.makeText(this, "Erro de entrada e saída", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this, "Erro desconhecido"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}