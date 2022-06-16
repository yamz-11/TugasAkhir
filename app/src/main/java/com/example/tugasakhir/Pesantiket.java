package com.example.tugasakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Pesantiket extends AppCompatActivity {

    private EditText nama,nik,alamat,jenisKelamin,asal,tujuan;
    private Button pesan;

    private String sTujuan;
    private int iHarga;

    /*Convert*/
    private String harga;

    /*Firebas Firestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesantiket);

        nama = findViewById(R.id.txNama);
        nik = findViewById(R.id.txNIK);
        alamat = findViewById(R.id.Alamat);
        jenisKelamin = findViewById(R.id.jenisKelamin);
        asal = findViewById(R.id.Asal);
        tujuan = findViewById(R.id.Tujuan);
        pesan = findViewById(R.id.btnPesan);

        /* Penggunaan Popup Loading */
        progressDialog = new ProgressDialog(Pesantiket.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Transaksi Berhasil...");

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sTujuan = tujuan.getText().toString();

                if(nama.getText().length() > 0 || nik.getText().length() > 0 || alamat.getText().length() > 0 ||
                jenisKelamin.getText().length() > 0 || asal.getText().length() > 0 || tujuan.getText().length() > 0){

                    if(sTujuan.equals("NTB") || sTujuan.equals("ntb")){
                        iHarga = 500000;
                    }
                    else if(sTujuan.equals("Bali") || sTujuan.equals("bali")){
                        iHarga =  300000;
                    }
                    else if (sTujuan.equals("Jakarta") || sTujuan.equals("jakarta")){
                        iHarga = 350000;
                    }

                    /*Convert int to String*/
                    harga = String.valueOf(iHarga);

                    createData(nama.getText().toString(), nik.getText().toString(), alamat.getText().toString(), jenisKelamin.getText().toString(),
                            asal.getText().toString(), sTujuan, harga);

                }
                else {
                    Toast.makeText(Pesantiket.this, "Data Harus di isi Semua", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Edit atau Update*/
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getStringExtra("id");
            nama.setText(intent.getStringExtra("nama"));
            nik.setText(intent.getStringExtra("nik"));
            alamat.setText(intent.getStringExtra("alamat"));
            jenisKelamin.setText(intent.getStringExtra("jenis kelamin"));
            asal.setText(intent.getStringExtra("asal"));
            tujuan.setText(intent.getStringExtra("tujuan"));
        }

    }

    private void createData(String nama, String nik, String alamat, String jenisKL, String asal, String sTujuan, String harga) {
        Map<String,Object> tiket = new HashMap<>();

        tiket.put("nama", nama);
        tiket.put("nik", nik);
        tiket.put("alamat", alamat);
        tiket.put("jenis kelamin", jenisKL);
        tiket.put("asal", asal);
        tiket.put("tujuan", sTujuan);
        tiket.put("harga", harga);

        progressDialog.show();

        if (id != null) {
            /**
             *  kode untuk edit data firestore dengan mengambil id
             */
            db.collection("Tiket").document(id)
                    .set(tiket)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Pesantiket.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Pesantiket.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            /**
             * Code untuk menambahkan data dengan add
             */
            db.collection("Tiket")
                    .add(tiket)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(Pesantiket.this, "Berhasil di simpan", Toast.LENGTH_SHORT).show();
                            Intent pindah = new Intent(getApplicationContext(), Home.class);
                            startActivity(pindah);
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Pesantiket.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}