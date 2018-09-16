package com.kucingapes.latihanretrofit_quran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kucingapes.latihanretrofit_quran.adapter.SurahAdapter;
import com.kucingapes.latihanretrofit_quran.model.Cek;
import com.kucingapes.latihanretrofit_quran.model.Data;
import com.kucingapes.latihanretrofit_quran.model.Edition;
import com.kucingapes.latihanretrofit_quran.model.Surah;
import com.kucingapes.latihanretrofit_quran.restapi.ApiClient;
import com.kucingapes.latihanretrofit_quran.restapi.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String arabic = "quran-uthmani";
    private static final String indo = "id.indonesian";

    private List<Surah> surahsArabic = new ArrayList<>();
    private List<Surah> surahsIndo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerSurah = findViewById(R.id.surah_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerSurah.setLayoutManager(layoutManager);
        recyclerSurah.addItemDecoration(itemDecoration);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<Cek> call = apiInterface.getCek(arabic);
        Call<Cek> callIndo = apiInterface.getCek(indo);

        getDataListArabic(recyclerSurah, call);
        getDataTarjim(callIndo);
    }

    private void getDataTarjim(Call<Cek> callIndo) {
        callIndo.enqueue(new Callback<Cek>() {
            @Override
            public void onResponse(Call<Cek> call, Response<Cek> response) {
                Data data = response.body().getData();
                surahsIndo = data.getSurahs();
            }

            @Override
            public void onFailure(Call<Cek> call, Throwable t) {

            }
        });
    }

    private void getDataListArabic(final RecyclerView recyclerSurah, Call<Cek> call) {
        call.enqueue(new Callback<Cek>() {
            @Override
            public void onResponse(Call<Cek> call, Response<Cek> response) {
                Data data = response.body().getData();
                surahsArabic = data.getSurahs();

                SurahAdapter surahAdapter = new SurahAdapter(MainActivity.this, surahsArabic, surahsIndo);
                recyclerSurah.setAdapter(surahAdapter);
                findViewById(R.id.loadingbar).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cek> call, Throwable t) {
                Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                Log.d("anjir", t.getMessage());

            }
        });
    }
}
