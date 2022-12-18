package com.example.magis.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magis.R;
import com.example.magis.activities.EditBarangActivity;
import com.example.magis.api.ApiConfig;
import com.example.magis.model.Barang;
import com.example.magis.model.InsertProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBarangAdapter extends RecyclerView.Adapter<DaftarBarangAdapter.MyViewHolder> {

    private List<Barang> daftarBarang;

    public DaftarBarangAdapter(List<Barang> daftarBarang) {
        this.daftarBarang = daftarBarang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftar_barang, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvNamaBarang.setText(daftarBarang.get(position).getNama());
        String berat = String.valueOf(daftarBarang.get(position).getBerat());
        holder.tvBeratBarang.setText(berat);
        String jumlah = String.valueOf(daftarBarang.get(position).getQty());
        holder.tvJumlahBarang.setText(jumlah);
        holder.tvEdit.setOnClickListener(view -> {
            Intent editIntent = new Intent(holder.itemView.getContext(), EditBarangActivity.class);
            editIntent.putExtra("barang", daftarBarang.get(position));
            holder.itemView.getContext().startActivity(editIntent);
        });
        holder.tvHapus.setOnClickListener(view -> {
            Call<InsertProductResponse> client = ApiConfig.getApiService().deleteProduct(daftarBarang.get(position).getId());
            client.enqueue(new Callback<InsertProductResponse>() {
                @Override
                public void onResponse(Call<InsertProductResponse> call, Response<InsertProductResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Toast.makeText(view.getContext(), "Berhasil menghapus data!", Toast.LENGTH_SHORT).show();
                            daftarBarang.remove(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e("", "onFailure: " + response.message());
                        }
                    }
                }

                @Override
                public void onFailure(Call<InsertProductResponse> call, Throwable t) {

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return daftarBarang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaBarang, tvBeratBarang, tvJumlahBarang, tvEdit, tvHapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang);
            tvBeratBarang = itemView.findViewById(R.id.tv_berat_barang);
            tvJumlahBarang = itemView.findViewById(R.id.tv_jumlah_barang);
            tvEdit = itemView.findViewById(R.id.tv_edit);
            tvHapus = itemView.findViewById(R.id.tv_hapus);
        }
    }
}
