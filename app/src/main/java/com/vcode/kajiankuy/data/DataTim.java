package com.vcode.kajiankuy.data;

import com.vcode.kajiankuy.R;

import java.util.ArrayList;

public class DataTim {
    public static String[][] data = new String[][]{
            {"Dial Saks Robin", "1617051087", String.valueOf(R.drawable.robin), "Desain"},
            {"Fergie Nando DP","1617051110", String.valueOf(R.drawable.fergie), "Programmer"},
            {"Kelvin Putra","1617051086", String.valueOf(R.drawable.kelvin), "Programmer"},
            {"Sinta Karnia","1617051144", String.valueOf(R.drawable.sikaa), "Analis"}
    };

    public static ArrayList<Tim> getData(){
        Tim tim = null;
        ArrayList<Tim> list = new ArrayList<>();
        for (String[] mData: data){
            tim = new Tim();
            tim.setNama(mData[0]);
            tim.setNpm(mData[1]);
            tim.setImgTim(Integer.parseInt(mData[2]));
            tim.setTugas(mData[3]);
            list.add(tim);
        }
        return list;
    }
}
