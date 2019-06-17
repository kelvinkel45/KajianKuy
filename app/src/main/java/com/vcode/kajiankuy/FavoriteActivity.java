package com.vcode.kajiankuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vcode.kajiankuy.adapter.FavoriteAdapter;
import com.vcode.kajiankuy.data.FavoriteDbHelper;
import com.vcode.kajiankuy.model.DataKajian;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private FavoriteAdapter adapter ;
    private List<DataKajian> dataKajianList = new ArrayList<>();
    private RecyclerView rvFavorite;
    private FavoriteDbHelper favoriteDbHelper;
    private AppCompatActivity activity = FavoriteActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("Jadwal Favorite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favoriteDbHelper = new FavoriteDbHelper(activity);
        dataKajianList.addAll(favoriteDbHelper.getAllFavorite());

        rvFavorite = findViewById(R.id.rv_favorite);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(rvFavorite.getContext(), linearLayoutManager.getOrientation());

        rvFavorite.setHasFixedSize(true);
        rvFavorite.setLayoutManager(linearLayoutManager);
        rvFavorite.addItemDecoration(dividerItemDecoration);


        adapter = new FavoriteAdapter(getApplicationContext(), dataKajianList);
        rvFavorite.setAdapter(adapter);
        Toast.makeText(this, "Jumlah data : "+ String.valueOf(favoriteDbHelper.getNotesCount()), Toast.LENGTH_SHORT).show();
    }

}
