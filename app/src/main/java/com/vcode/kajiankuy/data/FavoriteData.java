package com.vcode.kajiankuy.data;

import android.provider.BaseColumns;

public class FavoriteData {

    public static class FavoriteInput implements BaseColumns{
        public static final String TB_NAMA = "tb_favorite";
        public static final String COL_ID = "fav_id";
        public static final String COL_PAMFLET = "pamflet";
        public static final String COL_LEMBAGA = "lembaga";
        public static final String COL_NAMA = "nama";
        public static final String COL_TEMA = "tema";
        public static final String COL_PEMATERI = "pemateri";
        public static final String COL_JAM = "jam";
        public static final String COL_TANGGAL = "tanggal";
        public static final String COL_TEMPAT = "tempat";
    }
}