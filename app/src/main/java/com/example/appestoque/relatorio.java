package com.example.appestoque;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.content.Intent;
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
                Intent it = new Intent(relatorio.this, tela_inicial.class);
                startActivity(it);
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
        if(requestCode == CREATEPDF){
            if(data.getData()!=null){
                if(true) {
                    Uri caminhDoArquivo = data.getData();

//                    String nomeVeiculo = campoNome.getText().toString();
//                    String valorVeiculo = campoValor.getText().toString();
//                    String descricaoVeiculo = campoDescricao.getText().toString();

                    String nomeVeiculo = "300 frutas";
                    String valorVeiculo = "5 carros";
                    String maior_preco = "R$ 55000";
                    String menor_preco = "R$ 40";

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

                    canvas.drawText("Categorias:  frustas, veículos, materiáis, ferramentas", 50,275, paint);
                    canvas.drawText("Maior quantidade em estoque:  "+nomeVeiculo, 50,375, paint);
                    canvas.drawText("Menor qauntidade em estoque:  "+valorVeiculo, 50, 475, paint);
                    canvas.drawText("Maior preço em estoque:  "+maior_preco, 50, 575, paint);
                    canvas.drawText("Menor preço em estoque:  "+menor_preco, 50, 675, paint);

                    Date data_atual = new Date();
                    canvas.drawText("Data atual:  "+data_atual.toString(), 500, 1635, paint);

                    canvas.drawLine(48,275,pageInfo.getPageWidth()-100, 275, paint);
                    canvas.drawLine(48,375,pageInfo.getPageWidth()-100, 375, paint);
                    canvas.drawLine(48,475,pageInfo.getPageWidth()-100, 475, paint);
                    canvas.drawLine(48,575,pageInfo.getPageWidth()-100, 575, paint);
                    canvas.drawLine(48,675,pageInfo.getPageWidth()-100, 675, paint);

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