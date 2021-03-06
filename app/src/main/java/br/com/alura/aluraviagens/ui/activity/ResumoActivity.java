package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

public class ResumoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do Pacote";
    private Pacote pacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        setTitle(TITULO_APPBAR);

        configuraPacote();

        mostraLocal(pacote);

        mostraImagem(pacote);

        mostraDias(pacote);

        mostraPreco(pacote);

        mostraData(pacote);

        Button pagamento = findViewById(R.id.resumo_pacote_botao_realiza_pagamento);
        pagamento.setOnClickListener(view -> {
            Intent intent = new Intent(this, PagamentoActivity.class);
            intent.putExtra("pacoteSelecionado", pacote);
            startActivity(intent);
        });

    }

    private void configuraPacote() {
        Intent dados = getIntent();
        if(dados.hasExtra("pacoteSelecionado")){
            pacote = (Pacote) dados.getSerializableExtra("pacoteSelecionado");
        } else {
            pacote = new Pacote("São Paulo", "sao_paulo_sp", 2, new BigDecimal("243.99"));
        }
    }

    private void mostraData(Pacote pacoteSaoPaulo) {
        TextView data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaDaViagem = DataUtil.periodoEmTexto(pacoteSaoPaulo.getDias());
        data.setText(dataFormatadaDaViagem);
    }



    private void mostraPreco(Pacote pacoteSaoPaulo) {
        TextView preco = findViewById(R.id.resumo_pacote_preco);
        preco.setText(MoedaUtil.formataParaBrasileiro(pacoteSaoPaulo.getPreco()));
    }

    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.resumo_pacote_dias);
        dias.setText(DiasUtil.formataEmTexto(pacote.getDias()));
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_pacote_imagem);
        imagem.setImageDrawable(ResourceUtil.devolveDrawable(this, pacote.getImagem()));
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.resumo_pacote_local);
        local.setText(pacote.getLocal());
    }
}