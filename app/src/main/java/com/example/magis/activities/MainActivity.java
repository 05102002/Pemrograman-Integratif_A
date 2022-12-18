package com.example.magis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.magis.R;
import com.example.magis.adapter.DaftarBarangAdapter;
import com.example.magis.api.ApiConfig;
import com.example.magis.model.Barang;
import com.example.magis.model.BarangResponse;
import com.example.magis.util.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlTambahBarang;
    private RecyclerView rvDaftarBarang;

    private DaftarBarangAdapter daftarBarangAdapter;

    private List<Barang> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlTambahBarang = findViewById(R.id.rl_tambah_barang);
        rvDaftarBarang = findViewById(R.id.rv_daftar_barang);

        rlTambahBarang.setOnClickListener(view -> {
            Intent tambahBarangIntent = new Intent(this, TambahBarangActivity.class);
            startActivity(tambahBarangIntent);
        });

        rvDaftarBarang.setLayoutManager(new WrapContentLinearLayoutManager(this));

        Call<BarangResponse> getProducts = ApiConfig.getApiService().getProducts();
        getProducts.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        list = response.body().getData();
                        daftarBarangAdapter = new DaftarBarangAdapter(list);
                        rvDaftarBarang.setAdapter(daftarBarangAdapter);
                        daftarBarangAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<BarangResponse> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<BarangResponse> getProducts = ApiConfig.getApiService().getProducts();
        getProducts.enqueue(new Callback<BarangResponse>() {
            @Override
            public void onResponse(Call<BarangResponse> call, Response<BarangResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        list = response.body().getData();
                        daftarBarangAdapter = new DaftarBarangAdapter(list);
                        rvDaftarBarang.setAdapter(daftarBarangAdapter);
                        daftarBarangAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<BarangResponse> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}