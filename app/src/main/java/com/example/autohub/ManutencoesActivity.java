package com.example.autohub;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManutencoesActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdicionar;

    ArrayList<String> listaManutencoes;

    ArrayList<Integer> listaIds;
    ArrayList<String> listaNomes;
    ArrayList<String> listaDatas;
    ArrayList<Double> listaCustos;
    ArrayList<Integer> listaKms;

    ArrayAdapter<String> adapter;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencoes);

        // Ligando elementos
        listView = findViewById(R.id.listViewManutencoes);
        btnAdicionar = findViewById(R.id.btnAdicionarManutencao);

        // Banco
        db = new DatabaseHelper(this);

        // Listas
        listaManutencoes = new ArrayList<>();

        listaIds = new ArrayList<>();
        listaNomes = new ArrayList<>();
        listaDatas = new ArrayList<>();
        listaCustos = new ArrayList<>();
        listaKms = new ArrayList<>();

        // Adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaManutencoes
        );

        listView.setAdapter(adapter);

        // Carregar dados
        carregarManutencoes();

        // Botão adicionar
        btnAdicionar.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ManutencoesActivity.this,
                    AddManutencaoActivity.class
            );

            startActivity(intent);
        });

        // DELETE segurando item
        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            int idManutencao = listaIds.get(position);

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);

            builder.setTitle("Excluir");

            builder.setMessage(
                    "Deseja excluir esta manutenção?"
            );

            builder.setPositiveButton("Sim", (dialog, which) -> {

                db.deletarManutencao(idManutencao);

                Toast.makeText(
                        this,
                        "Manutenção excluída!",
                        Toast.LENGTH_SHORT
                ).show();

                carregarManutencoes();
            });

            builder.setNegativeButton("Cancelar", null);

            builder.show();

            return true;
        });

        // EDITAR item
        listView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(
                    ManutencoesActivity.this,
                    AddManutencaoActivity.class
            );

            intent.putExtra("id",
                    listaIds.get(position));

            intent.putExtra("nome",
                    listaNomes.get(position));

            intent.putExtra("data",
                    listaDatas.get(position));

            intent.putExtra("custo",
                    listaCustos.get(position));

            intent.putExtra("km",
                    listaKms.get(position));

            startActivity(intent);
        });
    }

    // Atualiza ao voltar
    @Override
    protected void onResume() {
        super.onResume();

        carregarManutencoes();
    }

    // Carregar SQLite
    private void carregarManutencoes() {

        listaManutencoes.clear();

        listaIds.clear();
        listaNomes.clear();
        listaDatas.clear();
        listaCustos.clear();
        listaKms.clear();

        Cursor cursor = db.listarManutencoes();

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(0);

                String nome = cursor.getString(1);

                String data = cursor.getString(2);

                double custo = cursor.getDouble(3);

                int km = cursor.getInt(4);

                String item =
                        nome +
                                " - " +
                                data +
                                " - R$ " +
                                custo +
                                " - " +
                                km +
                                " km";

                // Lista visual
                listaManutencoes.add(item);

                // Listas auxiliares
                listaIds.add(id);
                listaNomes.add(nome);
                listaDatas.add(data);
                listaCustos.add(custo);
                listaKms.add(km);

            } while (cursor.moveToNext());
        }

        cursor.close();

        adapter.notifyDataSetChanged();
    }
}