package com.kucingapes.latihanretrofit_quran.restapi;

import com.kucingapes.latihanretrofit_quran.model.Ayat;
import com.kucingapes.latihanretrofit_quran.model.Cek;
import com.kucingapes.latihanretrofit_quran.model.Data;
import com.kucingapes.latihanretrofit_quran.model.Surah;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("quran/id.indonesian")
    Call<Data> getData();

    @GET("quran/quran-uthmani")
    Call<Cek> getDataArabic();

    @GET("quran/{lang}")
    Call<Cek> getCek(@Path("lang")String lang);

    @GET("surah/1/id.indonesian")
    Call<Cek> getSurahTranslation(/*@Path("id")int id*/);

    // id.indonesian
}
