package com.example.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void Cektiket(View v) {
        Intent i = new Intent(this, CekTiket.class);
        startActivity(i);
    }
    public void Pesantiket(View v) {
        Intent i = new Intent(this, Pesantiket.class);
        startActivity(i);
    }

    public void Riwayattiket(View v) {
        Intent i = new Intent(this, History.class);
        startActivity(i);
    }
    public void BtnKeluarApps(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}


