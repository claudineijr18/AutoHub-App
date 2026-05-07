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

public class ModificacoesActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdicionar;

    ArrayList<String> listaModificacoes;

    ArrayList<Integer> listaIds;
    ArrayList<String> listaNomes;
    ArrayList<String> listaStatus;
    ArrayList<Double> listaCustos;

    ArrayAdapter<String> adapter;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacoes);

        // Ligando elementos
        listView = findViewById(R.id.listViewModificacoes);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        // Banco
        db = new DatabaseHelper(this);

        // Listas
        listaModificacoes = new ArrayList<>();

        listaIds = new ArrayList<>();
        listaNomes = new ArrayList<>();
        listaStatus = new ArrayList<>();
        listaCustos = new ArrayList<>();

        // Adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaModificacoes
        );

        listView.setAdapter(adapter);

        // Carregar dados
        carregarModificacoes();

        // Botão adicionar
        btnAdicionar.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ModificacoesActivity.this,
                    AddModificacaoActivity.class
            );

            startActivity(intent);
        });

        // DELETE segurando item
        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            int idModificacao = listaIds.get(position);

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);

            builder.setTitle("Excluir");
            builder.setMessage("Deseja excluir esta modificação?");

            builder.setPositiveButton("Sim", (dialog, which) -> {

                db.deletarModificacao(idModificacao);

                Toast.makeText(
                        this,
                        "Modificação excluída!",
                        Toast.LENGTH_SHORT
                ).show();

                carregarModificacoes();
            });

            builder.setNegativeButton("Cancelar", null);

            builder.show();

            return true;
        });

        // EDITAR item
        listView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(
                    ModificacoesActivity.this,
                    AddModificacaoActivity.class
            );

            intent.putExtra("id", listaIds.get(position));

            intent.putExtra("nome",
                    listaNomes.get(position));

            intent.putExtra("status",
                    listaStatus.get(position));

            intent.putExtra("custo",
                    listaCustos.get(position));

            startActivity(intent);
        });
    }

    // Atualiza a lista ao voltar
    @Override
    protected void onResume() {
        super.onResume();

        carregarModificacoes();
    }

    // Carregar dados do SQLite
    private void carregarModificacoes() {

        listaModificacoes.clear();

        listaIds.clear();
        listaNomes.clear();
        listaStatus.clear();
        listaCustos.clear();

        Cursor cursor = db.listarModificacoes();

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(0);

                String nome = cursor.getString(1);

                String status = cursor.getString(2);

                double custo = cursor.getDouble(3);

                String item =
                        nome +
                                " - " +
                                status +
                                " - R$ " +
                                custo;

                // Lista visual
                listaModificacoes.add(item);

                // Listas auxiliares
                listaIds.add(id);
                listaNomes.add(nome);
                listaStatus.add(status);
                listaCustos.add(custo);

            } while (cursor.moveToNext());
        }

        cursor.close();

        adapter.notifyDataSetChanged();
    }
}