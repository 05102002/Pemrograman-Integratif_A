package com.example.magis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.magis.R;
import com.example.magis.api.ApiConfig;
import com.example.magis.model.Barang;
import com.example.magis.model.InsertProductResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBarangActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TextInputLayout inputNamaBarang, inputBeratBarang, inputJumlahBarang, inputKodeBarang;
    private TextInputEditText formNamaBarang, formBeratBarang, formJumlahBarang, formKodeBarang;
    private Button btnEditBarang;
    private String namaBarang;
    private int jumlahBarang, kodeBarang, beratBarang;

    private Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        ivBack = findViewById(R.id.iv_back);
        inputNamaBarang = findViewById(R.id.input_nama_barang);
        inputBeratBarang = findViewById(R.id.input_berat_barang);
        inputJumlahBarang = findViewById(R.id.input_jumlah_barang);
        inputKodeBarang = findViewById(R.id.input_kode_barang);
        formNamaBarang = findViewById(R.id.form_nama_barang);
        formBeratBarang = findViewById(R.id.form_berat_barang);
        formJumlahBarang = findViewById(R.id.form_jumlah_barang);
        formKodeBarang = findViewById(R.id.form_kode_barang);
        btnEditBarang = findViewById(R.id.btn_edit_barang);

        barang = (Barang) getIntent().getSerializableExtra("barang");

        if (barang != null){
            formNamaBarang.setText(barang.getNama());
            String berat = String.valueOf(barang.getBerat());
            formBeratBarang.setText(berat);
            String jumlah = String.valueOf(barang.getQty());
            formJumlahBarang.setText(jumlah);
            String kode = String.valueOf(barang.getKode());
            formKodeBarang.setText(kode);
        }

        ivBack.setOnClickListener(this);
        btnEditBarang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_edit_barang:
                namaBarang = formNamaBarang.getText().toString();
                beratBarang = Integer.valueOf(formBeratBarang.getText().toString());
                jumlahBarang = Integer.valueOf(formJumlahBarang.getText().toString());
                kodeBarang = Integer.valueOf(formKodeBarang.getText().toString());
                if (namaBarang.isEmpty()) {
                    //Jika form kosong, maka eror akan muncul
                    inputNamaBarang.setError("Harus Diisi!");
                } else if (beratBarang == 0) {
                    //Jika form kosong, maka eror akan muncul
                    inputBeratBarang.setError("Harus Diisi!");
                } else if (jumlahBarang == 0) {
                    //Jika form kosong, maka eror akan muncul
                    inputJumlahBarang.setError("Harus Diisi!");
                }  else if (kodeBarang == 0) {
                    //Jika form kosong, maka eror akan muncul
                    inputKodeBarang.setError("Harus Diisi!");
                } else {
                    inputNamaBarang.setError(null);
                    inputBeratBarang.setError(null);
                    inputJumlahBarang.setError(null);
                    inputKodeBarang.setError(null);
                    editBarang();
                }
                break;
        }
    }

    private void editBarang() {
        Call<InsertProductResponse> client = ApiConfig.getApiService()
                .updateProduct(barang.getId(), kodeBarang, namaBarang, beratBarang, jumlahBarang);
        client.enqueue(new Callback<InsertProductResponse>() {
            @Override
            public void onResponse(Call<InsertProductResponse> call, Response<InsertProductResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(EditBarangActivity.this, "Berhasil mengupdate barang", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<InsertProductResponse> call, Throwable t) {
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });
    }
}