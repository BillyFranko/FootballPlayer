package com.if4a.footballplayerbilly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper myDB;
    private RecyclerView rvPlayer;
    private FloatingActionButton fabTambah;
    private AdapterFootballPlayer adPlayer;
    private ArrayList<String> arrID,arrNama, arrNomor, arrklub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlayer =findViewById(R.id.rv_player);

        myDB = new MyDatabaseHelper(MainActivity.this);
        fabTambah = findViewById(R.id.fab_tambah);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilPlayer();
    }

    private void SQLiteToArrayList(){
        Cursor varCursor = myDB.bacaDataPlayer();
        if(varCursor.getCount()==0){
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while(varCursor.moveToNext())
            {
                arrID.add(varCursor.getString(0));
                arrNama.add(varCursor.getString(1));
                arrNomor.add(varCursor.getString(2));
                arrklub.add(varCursor.getString(3));
            }
        }
    }

    private void tampilPlayer(){
        arrID = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrNomor = new ArrayList<>();
        arrklub = new ArrayList<>();
        SQLiteToArrayList();
        adPlayer = new AdapterFootballPlayer(MainActivity.this, arrID,arrNama,arrNomor,arrklub);
        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPlayer.setAdapter(adPlayer);
    }
}