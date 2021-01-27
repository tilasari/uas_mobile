package com.hendra.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hendra.myapplication.model.Pahlawan;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton tambah;
    TextView txNoData;
    ListView listView;
    daftarpahlawanAdapter adapter;
    List<Pahlawan> daftarpahlawan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataPahlawan();
        setuplistView();
        }

        private void inisialisasiView() {
            tambah = findViewById(R.id.tambah);
            tambah.setOnClickListener(view -> bukaFormPahlawan());
            listView = findViewById(R.id.listView);
            txNoData = findViewById(R.id.tx_nodata);

        }

        private void setuplistView() {
            adapter = new daftarpahlawanAdapter(this, daftarpahlawan);
            listView.setAdapter(adapter);

        }

        private void loadDataPahlawan(){
            daftarpahlawan = SharedPreferences.getAllLPahlawan(this);
            if (daftarpahlawan.size() > 0) {
                txNoData.setVisibility(View.GONE);
            } else {
                txNoData.setVisibility(View.VISIBLE);
            }

        }

        private void refreshListView() {
            adapter.clear();
            loadDataPahlawan();
            adapter.addAll(daftarpahlawan);
        }

        private void bukaFormPahlawan(){
            Intent intent = new Intent(this,form_pahlawan.class);
            startActivity(intent);
        }

        @Override
        protected void onResume() {
            super.onResume();
            refreshListView();
        }


    }
