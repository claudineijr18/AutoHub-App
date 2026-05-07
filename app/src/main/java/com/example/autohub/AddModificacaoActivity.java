package com.example.autohub;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddModificacaoActivity extends AppCompatActivity {

    EditText etNomeMod, etCusto;
    Spinner spStatus;
    Button btnSalvar;

    DatabaseHelper db;

    int idModificacao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modificacao);

        // Ligando elementos
        etNomeMod = findViewById(R.id.etNomeMod);
        etCusto = findViewById(R.id.etCusto);
        spStatus = findViewById(R.id.spStatus);
        btnSalvar = findViewById(R.id.btnSalvar);

        // Banco
        db = new DatabaseHelper(this);

        // Spinner status
        String[] status = {
                "Selecione o status",
                "Planejado",
                "Em andamento",
                "Concluído"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                status
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spStatus.setAdapter(adapter);

        // RECEBER DADOS PARA EDIÇÃO
        if (getIntent().hasExtra("id")) {

            idModificacao =
                    getIntent().getIntExtra("id", -1);

            String nome =
                    getIntent().getStringExtra("nome");

            String statusAtual =
                    getIntent().getStringExtra("status");

            double custo =
                    getIntent().getDoubleExtra("custo", 0);

            etNomeMod.setText(nome);

            etCusto.setText(String.valueOf(custo));

            int posicaoStatus =
                    adapter.getPosition(statusAtual);

            if (posicaoStatus >= 0) {
                spStatus.setSelection(posicaoStatus);
            }

            btnSalvar.setText("Atualizar");
        }

        // BOTÃO SALVAR
        btnSalvar.setOnClickListener(v -> {

            String nome =
                    etNomeMod.getText().toString().trim();

            String statusSelecionado =
                    spStatus.getSelectedItem().toString().trim();

            String custoTexto =
                    etCusto.getText().toString().trim();

            // Validação
            if (nome.isEmpty()
                    || custoTexto.isEmpty()
                    || spStatus.getSelectedItemPosition() == 0) {

                Toast.makeText(
                        this,
                        "Preencha todos os campos!",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            double custo =
                    Double.parseDouble(custoTexto);

            // UPDATE
            if (idModificacao != -1) {

                db.atualizarModificacao(
                        idModificacao,
                        nome,
                        statusSelecionado,
                        custo
                );

                Toast.makeText(
                        this,
                        "Modificação atualizada!",
                        Toast.LENGTH_SHORT
                ).show();
            }

            // INSERT
            else {

                long resultado =
                        db.insertModificacao(
                                nome,
                                statusSelecionado,
                                custo
                        );

                if (resultado != -1) {

                    Toast.makeText(
                            this,
                            "Modificação adicionada!",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    Toast.makeText(
                            this,
                            "Erro ao salvar!",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            finish();
        });
    }
}