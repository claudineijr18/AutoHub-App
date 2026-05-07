package com.example.autohub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;


public class MainActivity extends AppCompatActivity {

    EditText etNome;
    Spinner spMarca, spModelo;
    Button btnEntrar;

    String marcaSelecionada = "";
    String modeloSelecionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //ligando os elementos
        etNome = findViewById(R.id.etNome);
        spMarca = findViewById(R.id.spMarca);
        spModelo = findViewById(R.id.spModelo);
        btnEntrar = findViewById(R.id.btnEntrar);

        //Listas utilizando o spinner
        String[] marcas = {"Selecione a marca", "Audi", "BMW", "Chevrolet", "Citroen", "Fiat", "Ford", "Honda", "Hyundai", "Jeep", "Kia", "Mazda",
        "Mercedes-Benz", "Mitsubishi", "Nissan", "Peugeot", "Renault", "Subaru", "Toyota", "Volkswagen", "Volvo"};

        String[] audi = {"Selecione o modelo", "A1", "A3", "A4", "A5", "A6", "A7", "A8", "Q3", "Q5", "Q7", "Q8", "R8", "TT"};
        String[] bmw = {"Selecione o modelo", "1 Series", "2 Series", "3 Series", "4 Series", "5 Series", "6 Series", "7 Series", "8 Series", "M2", "M3", "M4", "M5", "M6"};
        String[] chevrolet = {"Selecione o modelo", "Aveo", "Camaro", "Captiva", "Captiva Sport", "Celta", "Corsa", "Cruze", "Cruze Sport", "Malibu", "S10", "S10 Sport", "S10 Blazer"};
        String[] citroen = {"Selecione o modelo", "C1", "C2", "C3", "C4", "C5", "C6", "C8", "DS3", "DS4", "DS5", "DS7", "DS8", "Nemo", "Xsara", "Xsara Picasso"};
        String[] fiat = {"Selecione o modelo", "124 Spider", "500", "50", "500L", "500X", "500C", "50", "Bravo", "Doblo", "Doblo Cargo", "Doblo Com", "Ducato", "Fiorino", "Fiorino Pick", "Uno"};
        String[] ford = {"Selecione o modelo", "Fiesta", "Focus", "Ka", "Kuga", "Mondeo", "Mustang", "Ranger", "Transit"};
        String[] honda = {"Selecione o modelo", "Accord", "City", "Civic", "Civic Type R", "CR-V", "HR-V", "Odyssey"};
        String[] hyundai = {"Selecione o modelo", "Accent", "Elantra", "Genesis", "Getz", "i30", "i40", "iX35", "Santa Fe", "Sonata", "Tucson"};
        String[] jeep = {"Selecione o modelo", "Cherokee", "Compass", "Grand Cherokee", "Renegade", "Wrangler"};
        String[] kia = {"Selecione o modelo", "Cerato", "Ceed", "Cerato", "Soul"};
        String[] mazda = {"Selecione o modelo", "2", "3", "6", "CX-3", "CX-5", "CX-9", "MX-5", "MX-6"};
        String[] mercedes = {"Selecione o modelo", "A-Class", "B-Class", "C-Class", "CLA", "E-Class", "G-Class", "GLA", "GLC", "GLE", "S-Class", "SLK"};
        String[] mitsubishi = {"Selecione o modelo", "ASX", "Lancer", "Outlander", "Pajero", "Pajero Sport", "Pajero TR4"};
        String[] nissan = {"Selecione o modelo", "370Z", "Altima", "Armada", "Frontier", "GT-R", "Kicks", "Leaf", "Maxima", "Murano", "NV200", "Pathfinder", "Rogue", "Sentra", "Titan"};
        String[] peugeot = {"Selecione o modelo", "206", "207", "208", "3008", "307", "308", "4008"};
        String[] renault = {"Selecione o modelo", "Clio", "Duster", "Fluence", "Fluence Sport", "Kangoo", "Laguna", "Logan", "Sandero", "Sandero Stepway", "Symbol"};
        String[] subaru = {"Selecione o modelo", "Forester", "Impreza", "Legacy", "Outback", "WRX"};
        String[] toyota = {"Selecione o modelo", "Auris", "Corolla", "Hilux", "Prius", "RAV4", "Yaris"};
        String[] volkswagen = {"Selecione o modelo", "Amarok", "Bora", "Fox", "Gol", "Golf", "Jetta", "New Beetle", "Passatt", "Polo", "Saveiro", "Spacefox", "Tiguan"};
        String[] volvo = {"Selecione o modelo", "C30", "C70", "S40", "S60", "S80", "V40", "V60", "V90"};

        //Utilizando o adapter em marcas
        ArrayAdapter<String> adapterMarca = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, marcas);
        spMarca.setAdapter(adapterMarca);

        //Spinner de forma dependente
        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String[] modelos;

                switch (position) {
                    case 1:
                        modelos = audi;
                        break;
                    case 2:
                        modelos = bmw;
                        break;
                    case 3:
                        modelos = chevrolet;
                        break;
                    case 4:
                        modelos = citroen;
                        break;
                    case 5:
                        modelos = fiat;
                        break;
                    case 6:
                        modelos = ford;
                        break;
                    case 7:
                        modelos = honda;
                        break;
                    case 8:
                        modelos = hyundai;
                        break;
                    case 9:
                        modelos = jeep;
                        break;
                    case 10:
                        modelos = kia;
                        break;
                    case 11:
                        modelos = mazda;
                        break;
                    case 12:
                        modelos = mercedes;
                        break;
                    case 13:
                        modelos = mitsubishi;
                        break;
                    case 14:
                        modelos = nissan;
                        break;
                    case 15:
                        modelos = peugeot;
                        break;
                    case 16:
                        modelos = renault;
                        break;
                    case 17:
                        modelos = subaru;
                        break;
                    case 18:
                        modelos = toyota;
                        break;
                    case 19:
                        modelos = volkswagen;
                        break;
                    case 20:
                        modelos = volvo;
                        break;
                    default:
                        modelos = new String[]{"Selecione o modelo"};
                }

                ArrayAdapter<String> adapterModelo = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_spinner_item,
                        modelos
                );

                adapterModelo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spModelo.setAdapter(adapterModelo);

                marcaSelecionada = marcas[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //aqui será feito a recuperação dos dados

        SharedPreferences prefs = getSharedPreferences("AutoHubPrefs", MODE_PRIVATE);

        String nomeSalvo = prefs.getString("nome", "");
        String marcaSalva = prefs.getString("marca", "");
        String modeloSalvo = prefs.getString("modelo", "");

        etNome.setText(nomeSalvo);

        int posMarca = adapterMarca.getPosition(marcaSalva);
        if(posMarca >= 0) spMarca.setSelection(posMarca);

        spMarca.post(() -> {
           ArrayAdapter<String> adapterModelo = (ArrayAdapter<String>) spModelo.getAdapter();
           if(adapterModelo != null){
               int posModelo = adapterModelo.getPosition(modeloSalvo);
               if(posModelo >= 0) spModelo.setSelection(posModelo);
           }
        });

        //Botão de entrar
        btnEntrar.setOnClickListener(v -> {
            String nome = etNome.getText().toString().trim();
            String marca = spMarca.getSelectedItem().toString().trim();
            String modelo = spModelo.getSelectedItem().toString().trim();

            if(nome.isEmpty() || spMarca.getSelectedItemPosition() == 0 || spModelo.getSelectedItemPosition() == 0){
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;

            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nome", nome);
            editor.putString("marca", marca);
            editor.putString("modelo", modelo);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

    }
}