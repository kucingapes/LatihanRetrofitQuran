package com.kucingapes.latihanretrofit_quran.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kucingapes.latihanretrofit_quran.R;
import com.kucingapes.latihanretrofit_quran.SurahContent;
import com.kucingapes.latihanretrofit_quran.model.Surah;

import java.util.ArrayList;
import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.Holder> {

    private Context context;
    private List<Surah> list;
    private List<Surah> listIndo;
    private View view;

    public SurahAdapter(Context context, List<Surah> list, List<Surah> listIndo) {
        this.context = context;
        this.list = list;
        this.listIndo = listIndo;
    }

    public SurahAdapter(Context context, List<Surah> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new Holder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final Surah surah = list.get(position);
        final Surah surahIndo = listIndo.get(position);

        holder.setIsRecyclable(false);
        holder.titleSurah.setText(surah.getName());
        holder.translationTitle.setText(surah.getTranslateName());
        holder.typeTitle.setText(surah.getType());
        holder.ayatSize.setText(String.valueOf(surah.getAyatList().size())+" ayat");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(surah.getAyatList());
                String jsonIndo = gson.toJson(surahIndo.getAyatList());
                Intent intent = new Intent(context, SurahContent.class);
                intent.putExtra("jsonlist", json);
                intent.putExtra("jsonlistIndo", jsonIndo);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView titleSurah, translationTitle, typeTitle, ayatSize;
        public Holder(View itemView) {
            super(itemView);
            titleSurah = itemView.findViewById(R.id.title_surah);
            translationTitle = itemView.findViewById(R.id.translation_title);
            typeTitle = itemView.findViewById(R.id.type_surah);
            ayatSize = itemView.findViewById(R.id.ayat_size);
        }
    }
}
