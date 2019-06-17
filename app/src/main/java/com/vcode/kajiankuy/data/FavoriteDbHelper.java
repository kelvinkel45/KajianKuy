package com.vcode.kajiankuy.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vcode.kajiankuy.model.DataKajian;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite_db";
    private static final int DATABASE_VERSION = 1;
    private static final String LOGTAG = "FAVORITE";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    public FavoriteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        Log.i(LOGTAG, "Database dibuka");
        db = dbHandler.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE = "CREATE TABLE " + FavoriteData.FavoriteInput.TB_NAMA + " ( " +
                FavoriteData.FavoriteInput._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavoriteData.FavoriteInput.COL_ID + " INTEGER NOT NULL, " +
                FavoriteData.FavoriteInput.COL_PAMFLET + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_LEMBAGA + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_NAMA + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_TEMA + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_PEMATERI + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_JAM + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_TANGGAL + " TEXT NOT NULL, " +
                FavoriteData.FavoriteInput.COL_TEMPAT + " TEXT NOT NULL " +
                " ); ";
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteData.FavoriteInput.TB_NAMA);
        onCreate(db);
    }

    public void tambahFavorite(DataKajian dataKajian){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FavoriteData.FavoriteInput.COL_ID, dataKajian.getKajian_id());
        values.put(FavoriteData.FavoriteInput.COL_PAMFLET, dataKajian.getUrl_gambar());
        values.put(FavoriteData.FavoriteInput.COL_LEMBAGA, dataKajian.getLembaga());
        values.put(FavoriteData.FavoriteInput.COL_NAMA, dataKajian.getNama());
        values.put(FavoriteData.FavoriteInput.COL_TEMA, dataKajian.getTema());
        values.put(FavoriteData.FavoriteInput.COL_PEMATERI, dataKajian.getPemateri());
        values.put(FavoriteData.FavoriteInput.COL_JAM, dataKajian.getJam());
        values.put(FavoriteData.FavoriteInput.COL_TANGGAL, dataKajian.getTanggal());
        values.put(FavoriteData.FavoriteInput.COL_TEMPAT, dataKajian.getTempat());

        db.insert(FavoriteData.FavoriteInput.TB_NAMA, null, values);
        db.close();
    }

    public void deleteFavorite(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteData.FavoriteInput.TB_NAMA, FavoriteData.FavoriteInput.COL_ID+ "=" +id, null);
    }

    public boolean isFavorites(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "+ FavoriteData.FavoriteInput.COL_ID + " FROM " + FavoriteData.FavoriteInput.TB_NAMA +
        " WHERE "+ FavoriteData.FavoriteInput.COL_ID + " = "+ id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <=0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public List<DataKajian> getAllFavorite(){
        String[]kolom = {
                FavoriteData.FavoriteInput._ID,
                FavoriteData.FavoriteInput.COL_ID,
                FavoriteData.FavoriteInput.COL_PAMFLET,
                FavoriteData.FavoriteInput.COL_LEMBAGA,
                FavoriteData.FavoriteInput.COL_NAMA,
                FavoriteData.FavoriteInput.COL_TEMA,
                FavoriteData.FavoriteInput.COL_PEMATERI,
                FavoriteData.FavoriteInput.COL_JAM,
                FavoriteData.FavoriteInput.COL_TANGGAL,
                FavoriteData.FavoriteInput.COL_TEMPAT
        };
        String order = FavoriteData.FavoriteInput.COL_TANGGAL+ " DESC";
        List<DataKajian> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteData.FavoriteInput.TB_NAMA, kolom,
                null,null,null,null, order);

        if (cursor.moveToFirst()){
            do{
                DataKajian dataKajian = new DataKajian();
                dataKajian.setKajian_id(cursor.getInt(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_ID)));
                dataKajian.setUrl_gambar(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_PAMFLET)));
                dataKajian.setLembaga(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_LEMBAGA)));
                dataKajian.setNama(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_NAMA)));
                dataKajian.setTema(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_TEMA)));
                dataKajian.setPemateri(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_PEMATERI)));
                dataKajian.setJam(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_JAM)));
                dataKajian.setTanggal(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_TANGGAL)));
                dataKajian.setTempat(cursor.getString(cursor.getColumnIndex(FavoriteData.FavoriteInput.COL_TEMPAT)));
                list.add(dataKajian);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }
    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + FavoriteData.FavoriteInput.TB_NAMA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }


}
