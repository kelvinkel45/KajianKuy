package com.vcode.kajiankuy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcode.kajiankuy.R;
import com.vcode.kajiankuy.data.FavoriteDbHelper;
import com.vcode.kajiankuy.model.DataKajian;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    RequestOptions options;
    private Context mContext;
    private List<DataKajian> mData;
    private FavoriteDbHelper favoriteDbHelper;
    private DataKajian favorite;

    public FavoriteAdapter(Context mContext, List<DataKajian> mData){
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions().fitCenter().placeholder(R.drawable.loading).error(R.drawable.loading);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.favorite_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final int kajianId = mData.get(i).getKajian_id();

        myViewHolder.tvTema.setText(mData.get(i).getTema());
        myViewHolder.tvPemateri.setText(mData.get(i).getPemateri());
        myViewHolder.tvNama.setText(mData.get(i).getNama());
        myViewHolder.tvTanggal.setText(mData.get(i).getTanggal());
        myViewHolder.tvJam.setText(mData.get(i).getJam());

        Glide.with(mContext).load(mData.get(i).getUrl_gambar()).apply(options).into(myViewHolder.imgPamflet);

        //check favorite
        favoriteDbHelper = new FavoriteDbHelper(mContext);
        if(favoriteDbHelper.isFavorites(kajianId)){
            myViewHolder.btnFav.setImageResource(R.drawable.ic_favorite);
        }

        //ganti status dari clicklistener
        myViewHolder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favoriteDbHelper.isFavorites(kajianId)){
                    favoriteDbHelper = new FavoriteDbHelper(mContext);
                    favoriteDbHelper.deleteFavorite(kajianId);
                    myViewHolder.btnFav.setImageResource(R.drawable.ic_favorite_border);
                    Snackbar.make(myViewHolder.btnFav, "Jadwal dihapus dari favorit", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTema, tvPemateri, tvNama, tvTanggal, tvJam;
        ImageView imgPamflet, btnFav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnFav = itemView.findViewById(R.id.btn_favorite);
            tvTema = itemView.findViewById(R.id.fav_tema);
            tvPemateri = itemView.findViewById(R.id.fav_pemateri);
            tvNama = itemView.findViewById(R.id.fav_agenda);
            tvTanggal = itemView.findViewById(R.id.fav_tanggal);
            tvJam = itemView.findViewById(R.id.fav_jam);
            imgPamflet = itemView.findViewById(R.id.fav_pamflet);
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
