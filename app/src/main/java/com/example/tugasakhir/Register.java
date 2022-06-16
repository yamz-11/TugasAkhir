package com.example.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText edNama, edEmail, edPass, edRepas;
    String nama, email, password, repassword;
    Button btRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btRegis = findViewById(R.id.bRegister);
        edNama = findViewById(R.id.txNama);
        edEmail = findViewById(R.id.txEmail);
        edPass = findViewById(R.id.regPass);
        edRepas = findViewById(R.id.regRepass);

        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = edNama.getText().toString();
                email = edEmail.getText().toString();
                password = edPass.getText().toString();
                repassword = edRepas.getText().toString();

                if (TextUtils.isEmpty(nama) && TextUtils.isEmpty(email)
                        && TextUtils.isEmpty(password) && TextUtils.isEmpty(repassword)){
                    Toast t = Toast.makeText(getApplicationContext(),"Harap isi semua kolom diatas",Toast.LENGTH_LONG);
                    t.show();

                    edNama.setError("Nama harus diisi");
                    edEmail.setError("Email harus diisi");
                }
                else if(TextUtils.isEmpty(nama) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)){
                    Toast t = Toast.makeText(getApplicationContext(),"Data harus Diisi!",Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    if (!password.equals(repassword)){
                        Toast t = Toast.makeText(getApplicationContext(),"Password harus sama!",Toast.LENGTH_LONG);
                        t.show();

                        edRepas.setError("Password tidak sama");
                    }
                    else {
                        Bundle b = new Bundle();

                        b.putString("name", nama.trim());
                        Toast t = Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_LONG);
                        t.show();

                        Intent i = new Intent(getApplicationContext(), Home.class);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
            }
        });
    }
}