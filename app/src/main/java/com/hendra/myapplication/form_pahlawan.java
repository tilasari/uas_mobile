package com.hendra.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.hendra.myapplication.model.Pahlawan;

public class form_pahlawan extends AppCompatActivity {
    TextInputLayout til_nama, til_profil,til_thnlahir,til_thnwafat ;
    Spinner asal;
    Button btnsimpan;
    final String[] tipePahlawan = {Pahlawan.BANDUNG, Pahlawan.YOGYAKARTA, Pahlawan.LOMBOK};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pahlawan);
        inisialisasiView();
    }
    private void inisialisasiView(){
        btnsimpan=findViewById(R.id.btn_simpan);
        btnsimpan.setOnClickListener(view -> simpan());
        til_nama=findViewById(R.id.til_nama);
        til_profil=findViewById(R.id.til_profil);
        til_thnlahir=findViewById(R.id.til_thnlahir);
        til_thnwafat=findViewById(R.id.til_thnwafat);
        asal=findViewById(R.id.til_asal);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipePahlawan
        );

        asal.setAdapter(adapter);
        asal.setSelection(0);

    }
    private void simpan() {
        if (isDataValid()) {
            Pahlawan tx = new Pahlawan();
            tx.setnama(til_nama.getEditText().getText().toString());
            tx.setProfil(til_profil.getEditText().getText().toString());
            tx.setthnlahir(til_thnlahir.getEditText().getText().toString());
            tx.setThnwafat(til_thnwafat.getEditText().getText().toString());
            tx.setasal(asal.getSelectedItem().toString());
            SharedPreferences.addPahlawan(this, tx);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() { finish();
                }
            }, 500);

        }
    }

    private boolean isDataValid() {
        if (til_nama.getEditText().getText().toString().isEmpty()
                || til_profil.getEditText().getText().toString().isEmpty()
                || til_thnlahir.getEditText().getText().toString().isEmpty()
                || til_thnwafat.getEditText().getText().toString().isEmpty()
                || asal.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this, "Data tidak bileh ada yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}