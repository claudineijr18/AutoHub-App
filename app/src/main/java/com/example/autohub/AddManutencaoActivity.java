package com.example.autohub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddManutencaoActivity extends AppCompatActivity {

    EditText etNomeMan, etData, etCusto, etKm;
    Button btnSalvarMan;

    DatabaseHelper db;

    int idManutencao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_manutencao);

        etNomeMan = findViewById(R.id.etNomeMan);
        etData = findViewById(R.id.etData);
        etCusto = findViewById(R.id.etCustoMan);
        etKm = findViewById(R.id.etKm);
        btnSalvarMan = findViewById(R.id.btnSalvarMan);

        //BANCO DE DADOS
        db = new DatabaseHelper(this);

        //EDITAR
        if(getIntent().hasExtra("id")){
            idManutencao = getIntent().getIntExtra("id", -1);
            String nome = getIntent().getStringExtra("nome");
            String data = getIntent().getStringExtra("data");
            double custo = getIntent().getDoubleExtra("custo", 0);
            int km = getIntent().getIntExtra("km", 0);

            etNomeMan.setText(nome);
            etData.setText(data);
            etCusto.setText(String.valueOf(custo));
            etKm.setText(String.valueOf(km));

            btnSalvarMan.setText("Atualizar");
        }

        //SALVAR
        btnSalvarMan.setOnClickListener(v -> {

            String nome = etNomeMan.getText().toString().trim();
            String data = etData.getText().toString().trim();
            String custoTexto = etCusto.getText().toString().trim();
            String kmTexto = etKm.getText().toString().trim();

            if (nome.isEmpty()) {
                etNomeMan.setError("Informe o nome da manutenção");
                etNomeMan.requestFocus();
                return;
            }

            if (nome.length() < 3) {
                etNomeMan.setError("O nome deve ter pelo menos 3 caracteres");
                etNomeMan.requestFocus();
                return;
            }

            if (data.isEmpty()) {
                etData.setError("Informe a data");
                etData.requestFocus();
                return;
            }

            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                etData.setError("Use o formato dd/mm/aaaa");
                etData.requestFocus();
                return;
            }

            if (custoTexto.isEmpty()) {
                etCusto.setError("Informe o custo");
                etCusto.requestFocus();
                return;
            }

            double custo;

            try {
                custo = Double.parseDouble(custoTexto);
            } catch (NumberFormatException e) {
                etCusto.setError("Informe um valor válido");
                etCusto.requestFocus();
                return;
            }

            if (custo <= 0) {
                etCusto.setError("O custo deve ser maior que zero");
                etCusto.requestFocus();
                return;
            }

            if (kmTexto.isEmpty()) {
                etKm.setError("Informe a quilometragem");
                etKm.requestFocus();
                return;
            }

            int km;

            try {
                km = Integer.parseInt(kmTexto);
            } catch (NumberFormatException e) {
                etKm.setError("Informe uma quilometragem válida");
                etKm.requestFocus();
                return;
            }

            if (km < 0) {
                etKm.setError("A quilometragem não pode ser negativa");
                etKm.requestFocus();
                return;
            }

            if (idManutencao != -1) {
                db.atualizarManutencao(idManutencao, nome, data, custo, km);
                Toast.makeText(this, "Manutenção atualizada!", Toast.LENGTH_SHORT).show();
            } else {
                long resultado = db.insertManutencao(nome, data, custo, km);

                if (resultado != -1) {
                    Toast.makeText(this, "Manutenção adicionada!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
                }
            }

            finish();
        });


    }
}