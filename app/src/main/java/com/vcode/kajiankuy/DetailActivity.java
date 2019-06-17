package com.vcode.kajiankuy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.vcode.kajiankuy.data.FavoriteDbHelper;
import com.vcode.kajiankuy.model.DataKajian;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    //inisialisasi variabel
    TextView tv_nama, tv_lembaga, tv_tema, tv_penceramah, tv_jam, tv_tanggal, tv_tempat;
    ImageView img, btnFav;
    private DataKajian favorite;
    private FavoriteDbHelper favoriteDbHelper;
    private List<DataKajian> dataKajianList = new ArrayList<>();
    private final AppCompatActivity activity = DetailActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //get inten dari kajian activity
        final String dtPamflet = getIntent().getExtras().getString("img_pamflet");
        final String dtNama = getIntent().getExtras().getString("nama_kajian");
        final String dtLembaga = getIntent().getExtras().getString("lembaga");
        final String dtTema = getIntent().getExtras().getString("tema");
        final String dtPenceramah = getIntent().getExtras().getString("penceramah");
        final String dtJam = getIntent().getExtras().getString("jam");
        final String dtTanggal = getIntent().getExtras().getString("tanggal");
        final String dtTempat = getIntent().getExtras().getString("tempat");
        final int dtKajianId = getIntent().getExtras().getInt("kajianId");

        //inisialisasi id kommponen view ke variabel
        tv_nama = findViewById(R.id.dt_agenda);
        tv_lembaga = findViewById(R.id.dt_ld);
        tv_tema = findViewById(R.id.dt_tema);
        tv_penceramah = findViewById(R.id.dt_penceramah);
        tv_jam = findViewById(R.id.dt_jam);
        tv_tanggal = findViewById(R.id.dt_tanggal);
        tv_tempat = findViewById(R.id.dt_tempat);
        img = findViewById(R.id.dt_pamflet);
        btnFav = findViewById(R.id.btn_favorite);

        //set text untuk detail
        tv_nama.setText(dtNama);
        tv_lembaga.setText(dtLembaga);
        tv_tema.setText(dtTema);
        tv_penceramah.setText(dtPenceramah);
        tv_jam.setText(dtJam);
        tv_tanggal.setText(dtTanggal);
        tv_tempat.setText(dtTempat);

        //ini load gambar
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
        Glide.with(this).load(dtPamflet).apply(requestOptions).into(img);

        getSupportActionBar().setTitle(dtNama);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //check favorite
        favoriteDbHelper = new FavoriteDbHelper(this);
        if(favoriteDbHelper.isFavorites(dtKajianId)){
            btnFav.setImageResource(R.drawable.ic_favorite);
        }

        //ganti status dari clicklistener
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!favoriteDbHelper.isFavorites(dtKajianId)){
                    saveFavorite(dtKajianId, dtPamflet, dtNama, dtLembaga, dtTema,
                            dtPenceramah, dtJam, dtTanggal, dtTempat);
                    btnFav.setImageResource(R.drawable.ic_favorite);
                    Snackbar.make(btnFav, "Jadwal ditambahkan ke favorit", Snackbar.LENGTH_SHORT).show();
                }else {
                    favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                    favoriteDbHelper.deleteFavorite(dtKajianId);
                    btnFav.setImageResource(R.drawable.ic_favorite_border);
                    Snackbar.make(btnFav, "Jadwal dihapus dari favorit", Snackbar.LENGTH_SHORT).show();
                }
            }
        });


//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        materialFavoriteButtonNice.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                    @Override
//                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                        if(favorite){
//                            SharedPreferences.Editor editor = getSharedPreferences
//                                    ("com.vcode.kajiankuy.DetailActivity", MODE_PRIVATE).edit();
//                            editor.putBoolean("Tambah Favorit", true);
//                            editor.commit();
//                            saveFavorite(dtKajianId, dtPamflet, dtNama, dtLembaga, dtTema,
//                                    dtPenceramah, dtJam, dtTanggal, dtTempat);
//                            Snackbar.make(buttonView, "Jadwal ditambahkan ke favorit", Snackbar.LENGTH_SHORT).show();
//                        }else {
//                            int kajianId = getIntent().getExtras().getInt("kajianId");
//                            favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
//                            favoriteDbHelper.deleteFavorite(kajianId);
//
//                            SharedPreferences.Editor editor = getSharedPreferences
//                                    ("com.vcode.kajiankuy.DetailActivity", MODE_PRIVATE).edit();
//                            editor.putBoolean("Hapus Favorit", true);
//                            editor.commit();
//                            Snackbar.make(buttonView, "Jadwal dihapus dari favorit", Snackbar.LENGTH_SHORT).show();
//
//
//                        }
//                    }
//                }
//        );

    }

    private void saveFavorite(int fKajianId, String fPamflet, String fNama, String fLembaga,
                              String fTema, String fPenceramah, String fJam, String fTanggal, String fTempat) {
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new DataKajian();

        favorite.setKajian_id(fKajianId);
        favorite.setUrl_gambar(fPamflet);
        favorite.setNama(fNama);
        favorite.setLembaga(fLembaga);
        favorite.setTema(fTema);
        favorite.setPemateri(fPenceramah);
        favorite.setJam(fJam);
        favorite.setTanggal(fTanggal);
        favorite.setTempat(fTempat);

        favoriteDbHelper.tambahFavorite(favorite);
    }


}
