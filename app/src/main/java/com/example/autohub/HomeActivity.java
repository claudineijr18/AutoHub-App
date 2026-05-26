package com.example.autohub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    TextView tvTitulo, tvBoasVindas, tvPergunta, tvTotalModificacoes, tvTotalManutencoes, tvTotalGeral;
    Button btnModificacoes, btnManutencoes;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        tvBoasVindas = findViewById(R.id.tvBoasVindas);
        tvPergunta = findViewById(R.id.tvPergunta);
        tvTotalModificacoes = findViewById(R.id.tvTotalModificacoes);
        tvTotalManutencoes = findViewById(R.id.tvTotalManutencoes);
        tvTotalGeral = findViewById(R.id.tvTotalGeral);

        btnModificacoes = findViewById(R.id.btnModificacoes);
        btnManutencoes = findViewById(R.id.btnManutencoes);

        db = new DatabaseHelper(this);

        //Recuperar os dados salvos
        SharedPreferences prefs = getSharedPreferences("AutoHubPrefs", MODE_PRIVATE);
        String nome = prefs.getString("nome", "Usuario");
        String marca = prefs.getString("marca", "");
        String modelo = prefs.getString("modelo", "");

        //Exibir mensagens personalizadas
        tvBoasVindas.setText("Bem-vindo, " + nome + "!");

        if(modelo.isEmpty()){
            tvPergunta.setText("O que deseja fazer com o seu carro?");
        } else {
            tvPergunta.setText("O que deseja fazer com o seu " + marca + " " + modelo + "?");
        }

        atualizarTotais();

        //navegação
        btnModificacoes.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ModificacoesActivity.class);
            startActivity(intent);
        });

        btnManutencoes.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ManutencoesActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(db != null){
            atualizarTotais();
        }
    }

    private void atualizarTotais() {
        double totalModificacoes = db.calcularTotalModificacoes();
        double totalManutencoes = db.calcularTotalManutencoes();
        double totalGeral = totalModificacoes + totalManutencoes;

        tvTotalModificacoes.setText("Total de modificações: R$ " + String.format("%.2f", totalModificacoes));
        tvTotalManutencoes.setText("Total de manutenções: R$ " + String.format("%.2f", totalManutencoes));
        tvTotalGeral.setText("Total investido: R$ " + String.format("%.2f", totalGeral));
    }
}