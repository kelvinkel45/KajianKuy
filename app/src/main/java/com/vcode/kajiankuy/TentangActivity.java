package com.vcode.kajiankuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vcode.kajiankuy.adapter.TentangAdapter;
import com.vcode.kajiankuy.data.DataTim;
import com.vcode.kajiankuy.data.Tim;

import java.util.ArrayList;

public class TentangActivity extends AppCompatActivity {
    private RecyclerView rvTentang;
    private RecyclerView.Adapter tentangAdapter;
    private ArrayList<Tim> timList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        getSupportActionBar().setTitle("Tentang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvTentang = findViewById(R.id.rv_tentang);
        rvTentang.setHasFixedSize(true);
        rvTentang.setLayoutManager(new LinearLayoutManager(this));
        tentangAdapter = new TentangAdapter(getApplicationContext(), timList);
        rvTentang.setAdapter(tentangAdapter);
        timList.addAll(DataTim.getData());
    }
}
