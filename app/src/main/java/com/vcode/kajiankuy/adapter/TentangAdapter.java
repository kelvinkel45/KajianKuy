package com.vcode.kajiankuy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcode.kajiankuy.R;
import com.vcode.kajiankuy.data.Tim;

import java.util.ArrayList;

public class TentangAdapter extends RecyclerView.Adapter<TentangAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Tim> mData;

    public TentangAdapter(Context mContext, ArrayList<Tim> mData){
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public TentangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.tentang_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TentangAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.tvNama.setText(mData.get(i).getNama());
        myViewHolder.tvNpm.setText(mData.get(i).getNpm());
        myViewHolder.imgtim.setImageResource(mData.get(i).getImgTim());
        myViewHolder.tvTugas.setText(mData.get(i).getTugas());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvNpm, tvTugas;
        ImageView imgtim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.nama_tim);
            tvNpm = itemView.findViewById(R.id.npm_tim);
            imgtim = itemView.findViewById(R.id.img_tim);
            tvTugas = itemView.findViewById(R.id.tugas_tim);
        }
    }
}
