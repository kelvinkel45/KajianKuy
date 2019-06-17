package com.vcode.kajiankuy;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vcode.kajiankuy.adapter.KajianAdapter;
import com.vcode.kajiankuy.model.DataKajian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KajianActivity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.Adapter adapter ;
    private String URL_JSON = "http://kajiankuy.000webhostapp.com/lihat_data.php";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<DataKajian> dataKajianList = new ArrayList<>();
    private RecyclerView rvKajian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kajian);

        getSupportActionBar().setTitle("Daftar Kajian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvKajian = findViewById(R.id.rv_kajian);
        adapter = new KajianAdapter(getApplicationContext(), dataKajianList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(rvKajian.getContext(), linearLayoutManager.getOrientation());
        rvKajian.setHasFixedSize(true);
        rvKajian.setLayoutManager(linearLayoutManager);
        rvKajian.addItemDecoration(dividerItemDecoration);
        rvKajian.setAdapter(adapter);
        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        DataKajian dataKajian = new DataKajian();
                        dataKajian.setKajian_id(jsonObject.getInt("id"));
                        dataKajian.setTema(jsonObject.getString("tema"));
                        dataKajian.setPemateri(jsonObject.getString("penceramah"));
                        dataKajian.setNama(jsonObject.getString("agenda"));
                        dataKajian.setTanggal(jsonObject.getString("tanggal"));
                        dataKajian.setJam(jsonObject.getString("jam"));
                        dataKajian.setUrl_gambar(jsonObject.getString("pamflet"));
                        dataKajian.setLembaga(jsonObject.getString("lembaga"));
                        dataKajian.setTempat(jsonObject.getString("tempat"));
                        dataKajianList.add(dataKajian);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}











