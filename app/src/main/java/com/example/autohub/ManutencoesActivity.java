package com.example.autohub;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManutencoesActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdicionar;

    ArrayList<String> listaManutencoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manutencoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listViewManutencoes);
        btnAdicionar = findViewById(R.id.btnAdicionarManutencao);

        listaManutencoes = new ArrayList<>();

        //Dados de exemplo
        listaManutencoes.add("Troca de óleo - 10/03");
        listaManutencoes.add("Revisão completa - 01/04");
        listaManutencoes.add("Troca de filtro de ar - 15/04");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ManutencoesActivity.this,
                android.R.layout.simple_list_item_1,
                listaManutencoes
        );

        listView.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            listaManutencoes.add("Nova manutenção - Planejada");
            adapter.notifyDataSetChanged();
        });
    }
}