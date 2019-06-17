package com.vcode.kajiankuy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcode.kajiankuy.DetailActivity;
import com.vcode.kajiankuy.KajianActivity;
import com.vcode.kajiankuy.R;
import com.vcode.kajiankuy.data.FavoriteDbHelper;
import com.vcode.kajiankuy.model.DataKajian;

import java.util.List;

public class KajianAdapter extends RecyclerView.Adapter<KajianAdapter.MyViewHolder> {
    RequestOptions options;
    private Context mContext;
    private List<DataKajian> mData;
    private FavoriteDbHelper favoriteDbHelper;
    private DataKajian favorite;

    public KajianAdapter(Context mContext, List<DataKajian> mData){
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions().fitCenter().placeholder(R.drawable.loading).error(R.drawable.loading);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.kajian_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        final DataKajian dataKajian = mData.get(i);
        final String obImg = dataKajian.getUrl_gambar();
        final String obNama = dataKajian.getNama();
        final String obLembaga = dataKajian.getLembaga();
        final String obTema = dataKajian.getTema();
        final String obPenceramah = dataKajian.getPemateri();
        final String obJam = dataKajian.getJam();
        final String obTanggal = dataKajian.getTanggal();
        final String obTempat = dataKajian.getTempat();
        final int obKajianId = dataKajian.getKajian_id();

        holder.tvTema.setText(mData.get(i).getTema());
        holder.tvNama.setText(mData.get(i).getNama());
        holder.tvLembaga.setText(mData.get(i).getLembaga());

        Glide.with(mContext).load(mData.get(i).getUrl_gambar()).apply(options).into(holder.imgPamflet);

        //check favorite
        favoriteDbHelper = new FavoriteDbHelper(mContext);
        if(favoriteDbHelper.isFavorites(obKajianId)){
            holder.btnFav.setImageResource(R.drawable.ic_favorite);
        }

        //ganti status dari clicklistener
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!favoriteDbHelper.isFavorites(obKajianId)){
                    saveFavorite(obKajianId, obImg, obNama, obLembaga, obTema,
                            obPenceramah, obJam, obTanggal, obTempat);
                    holder.btnFav.setImageResource(R.drawable.ic_favorite);
                    Snackbar.make(holder.btnFav, "Jadwal ditambahkan ke favorit", Snackbar.LENGTH_SHORT).show();
                }else {
                    favoriteDbHelper = new FavoriteDbHelper(mContext);
                    favoriteDbHelper.deleteFavorite(obKajianId);
                    holder.btnFav.setImageResource(R.drawable.ic_favorite_border);
                    Snackbar.make(holder.btnFav, "Jadwal dihapus dari favorit", Snackbar.LENGTH_SHORT).show();
                }
            }
        });


        holder.view_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("img_pamflet", obImg);
                intent.putExtra("nama_kajian", obNama);
                intent.putExtra("lembaga", obLembaga);
                intent.putExtra("tema", obTema);
                intent.putExtra("penceramah", obPenceramah);
                intent.putExtra("jam", obJam);
                intent.putExtra("tanggal", obTanggal);
                intent.putExtra("tempat", obTempat);
                intent.putExtra("kajianId", obKajianId);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTema, tvLembaga, tvNama;
        ImageView imgPamflet, btnFav;
        LinearLayout view_linear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_linear = itemView.findViewById(R.id.linear);
            btnFav = itemView.findViewById(R.id.btn_favorite);
            tvLembaga = itemView.findViewById(R.id.tvLembaga);
            tvNama = itemView.findViewById(R.id.tv_agenda);
            imgPamflet = itemView.findViewById(R.id.img_pamflet);
            tvTema = itemView.findViewById(R.id.tv_tema);
        }
    }
    private void saveFavorite(int fKajianId, String fPamflet, String fNama, String fLembaga,
                              String fTema, String fPenceramah, String fJam, String fTanggal, String fTempat) {
        favoriteDbHelper = new FavoriteDbHelper(mContext);
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











