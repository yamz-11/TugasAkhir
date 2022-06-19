package com.example.tugasakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class LihatTiket extends AppCompatActivity {

    private TextView nama, nik, alamat, jenisk, asal,nokursi, tujuan, harga;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_tiket);

        //Memanggil activity lihat tiket
        nama = findViewById(R.id.ltNama);
        nik = findViewById(R.id.ltNIK);
        alamat = findViewById(R.id.ltAlamat);
        jenisk = findViewById(R.id.ltJK);
        asal = findViewById(R.id.ltAsal);
        nokursi = findViewById(R.id.ltNoKursi);
        tujuan = findViewById(R.id.ltTujuan);
        harga = findViewById(R.id.ltHarga);

        progressDialog = new ProgressDialog(LihatTiket.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil Data...");

        //mengambil data dari halaman history
        Intent intent = getIntent();

        if(intent != null){
            nama.setText(intent.getStringExtra("nama"));
            nik.setText(intent.getStringExtra("nik"));
            alamat.setText(intent.getStringExtra("alamat"));
            jenisk.setText(intent.getStringExtra("jenis kelamin"));
            asal.setText(intent.getStringExtra("asal"));
            nokursi.setText(intent.getStringExtra("nokursi"));
            tujuan.setText(intent.getStringExtra("tujuan"));
            harga.setText(intent.getStringExtra("harga"));
        }
    }
}