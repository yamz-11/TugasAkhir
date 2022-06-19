package com.example.tugasakhir.model;

public class PesanModel {

    private String id,nama, nik, alamat, jenisKelamin, Asal, Nokursi, Tujuan, Harga;

    public PesanModel(String nama, String nik, String alamat, String jenisKelamin, String asal, String nokursi, String tujuan, String harga) {
        this.nama = nama;
        this.nik = nik;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        Asal = asal;
        Nokursi = nokursi;
        Tujuan = tujuan;
        Harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAsal() {
        return Asal;
    }

    public void setAsal(String asal) {
        Asal = asal;
    }

    public String getNokursi() {
        return Nokursi;
    }

    public void setNokursi(String nokursi) {
        Nokursi = nokursi;
    }
    public String getTujuan() {
        return Tujuan;
    }

    public void setTujuan(String tujuan) {
        Tujuan = tujuan;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }
}
