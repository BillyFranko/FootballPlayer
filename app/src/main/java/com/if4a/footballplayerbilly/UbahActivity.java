package com.if4a.footballplayerbilly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etKlub = findViewById(R.id.et_klub);
        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent varIntent = getIntent();
        String id = varIntent.getStringExtra("varId");
        String nama = varIntent.getStringExtra("varNama");
        String nomor = varIntent.getStringExtra("varNomor");
        String klub = varIntent.getStringExtra("varKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getKlub;
                getNama = etNama.getText().toString();
                getNomor = etNomor.getText().toString();
                getKlub = etKlub.getText().toString();

                if(nama.trim().equals(""))
                        {
                            etNama.setError("Nama Tidak Boleh Kosong!");
                        }
                        else if (nomor.trim().equals(""))
                        {
                            etNomor.setError("Nomor Tidak Boleh Kosong!");
                        }
                        else if (klub.trim().equals(""))
                        {
                            etKlub.setError("Klub Tidak Boleh Kosong!");
                        }
                        else {
                            long eks = myDB.ubahPlayer(id,getNama
                            ,getNomor,getKlub);
                            if(eks == -1){
                                Toast.makeText(UbahActivity.this, "Gagal Mengubah Data"
                                        , Toast.LENGTH_SHORT).show();
                                etNama.requestFocus();
                            }
                            else {
                                Toast.makeText(UbahActivity.this, "Berhasil Mengubah Data"
                                        , Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
            }
        });
    }
}