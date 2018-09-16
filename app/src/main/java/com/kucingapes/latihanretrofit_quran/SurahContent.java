package com.kucingapes.latihanretrofit_quran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kucingapes.latihanretrofit_quran.adapter.AyatAdapter;
import com.kucingapes.latihanretrofit_quran.model.Ayat;
import com.kucingapes.latihanretrofit_quran.model.Cek;
import com.kucingapes.latihanretrofit_quran.model.Data;
import com.kucingapes.latihanretrofit_quran.model.Surah;
import com.kucingapes.latihanretrofit_quran.restapi.ApiClient;
import com.kucingapes.latihanretrofit_quran.restapi.ApiInterface;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahContent extends AppCompatActivity {

    private static final String indo = "id.indonesian";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_content);

        String json = getIntent().getStringExtra("jsonlist");
        String jsonIndo = getIntent().getStringExtra("jsonlistIndo");
        Type type = new TypeToken<List<Ayat>>() {}.getType();

        Gson gson = new Gson();
        List<Ayat> ayatList = gson.fromJson(json, type);
        List<Ayat> ayatListIndo = gson.fromJson(jsonIndo, type);

        RecyclerView recyclerAyat = findViewById(R.id.ayat_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        AyatAdapter ayatAdapter = new AyatAdapter(this, ayatList, ayatListIndo);
        recyclerAyat.setLayoutManager(layoutManager);
        recyclerAyat.addItemDecoration(itemDecoration);
        recyclerAyat.setAdapter(ayatAdapter);

    }
}
