package com.example.tugasakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tugasakhir.adapter.PesanAdapter;
import com.example.tugasakhir.model.PesanModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private Button tambah;
    private RecyclerView recyclerView;

    /*Firebase Firestore*/
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<PesanModel> list  = new ArrayList<>();
    private ProgressDialog progressDialog;
    private PesanAdapter pesanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tambah = findViewById(R.id.btnTambah);
        recyclerView = findViewById(R.id.daftarPesanan);

        // Penggunan Diaglong Loading
        progressDialog = new ProgressDialog(History.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        pesanAdapter = new PesanAdapter(getApplicationContext(), list);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(getApplicationContext(), Pesantiket.class);
                startActivity(data);
            }
        });


        pesanAdapter.setDialog(new PesanAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final  CharSequence[] dialogItem = {"Lihat Data", "Delete", "Edit Data"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(History.this);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){

                            /*Lihat Data*/
                            case 0:
                                Intent lihat = new Intent(getApplicationContext(), LihatTiket.class);
                                lihat.putExtra("nama", list.get(pos).getNama());
                                lihat.putExtra("nik", list.get(pos).getNik());
                                lihat.putExtra("alamat", list.get(pos).getAlamat());
                                lihat.putExtra("jenis kelamin", list.get(pos).getJenisKelamin());
                                lihat.putExtra("asal", list.get(pos).getAsal());
                                lihat.putExtra("tujuan", list.get(pos).getTujuan());
                                lihat.putExtra("harga", list.get(pos).getHarga());
                                startActivity(lihat);
                                break;
                            /*Hapus Data*/
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                            /*Edit Data*/
                            case 2:
                                Intent intent = new Intent(getApplicationContext(), Pesantiket.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("nama", list.get(pos).getNama());
                                intent.putExtra("nik", list.get(pos).getNik());
                                intent.putExtra("alamat", list.get(pos).getAlamat());
                                intent.putExtra("jenis kelamin", list.get(pos).getJenisKelamin());
                                intent.putExtra("asal", list.get(pos).getAsal());
                                intent.putExtra("tujuan",list.get(pos).getTujuan());
                                startActivity(intent);
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(pesanAdapter);
    }

    private void deleteData(String id) {
        progressDialog.show();

        db.collection("Tiket").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(History.this, "Data Gagal Di hapus", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(History.this, "Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();

        db.collection("Tiket")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();

                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                PesanModel pesanModel = new PesanModel(document.getString("nama"), document.getString("nik"),
                                        document.getString("alamat"), document.getString("jenis kelamin"), document.getString("asal"), document.getString("tujuan"),
                                        document.getString("harga"));
                                pesanModel.setId(document.getId());
                                list.add(pesanModel);
                            }
                            pesanAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(History.this, "Data Gagal di ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}