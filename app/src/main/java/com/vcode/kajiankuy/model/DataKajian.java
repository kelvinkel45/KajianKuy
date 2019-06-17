package com.vcode.kajiankuy.model;

public class DataKajian {
    private String tema;
    private String pemateri;
    private String nama;
    private String tanggal;
    private String jam;
    private String url_gambar;
    private String tempat;
    private String lembaga;
    private int kajian_id;

    public DataKajian(String tema, String pemateri, String nama, String tanggal,  String jam,
                      String url_gambar, String tempat, String lembaga, int kajian_id){
        this.tema=tema;
        this.pemateri=pemateri;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jam = jam;
        this.url_gambar = url_gambar;
        this.tempat= tempat;
        this.lembaga = lembaga;
        this.kajian_id = kajian_id;
    }
    public DataKajian(){

    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getUrl_gambar() {
        return url_gambar;
    }

    public void setUrl_gambar(String url_gambar) {
        this.url_gambar = url_gambar;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getPemateri() {
        return pemateri;
    }

    public void setPemateri(String pemateri) {
        this.pemateri = pemateri;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getLembaga() {
        return lembaga;
    }

    public void setLembaga(String lembaga) {
        this.lembaga = lembaga;
    }

    public int getKajian_id() {
        return kajian_id;
    }

    public void setKajian_id(int kajian_id) {
        this.kajian_id = kajian_id;
    }
}
