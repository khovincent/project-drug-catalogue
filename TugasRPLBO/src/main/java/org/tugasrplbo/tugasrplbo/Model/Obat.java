package org.tugasrplbo.tugasrplbo.Model;

import javafx.beans.property.*;

public class Obat {
    private IntegerProperty kodeObat;
    private StringProperty namaObat;
    private StringProperty kategori;
    private StringProperty merekObat;
    private DoubleProperty harga;
    private StringProperty deskripsi;
    private int jumlah;

    public Obat(int kodeObat, String namaObat, String kategori, String merekObat, double harga, String deskripsi) {
        this.kodeObat = new SimpleIntegerProperty(kodeObat);
        this.namaObat = new SimpleStringProperty(namaObat);
        this.kategori = new SimpleStringProperty(kategori);
        this.merekObat = new SimpleStringProperty(merekObat);
        this.harga = new SimpleDoubleProperty(harga);
        this.deskripsi = new SimpleStringProperty(deskripsi);
    }

    public int getKodeObat() {
        return kodeObat.get();
    }

    public void setKodeObat(int kodeObat) {
        this.kodeObat.set(kodeObat);
    }

    public IntegerProperty kodeObatProperty() {
        return kodeObat;
    }

    public String getNamaObat() {
        return namaObat.get();
    }

    public void setNamaObat(String namaObat) {
        this.namaObat.set(namaObat);
    }

    public StringProperty namaObatProperty() {
        return namaObat;
    }

    public String getKategori() {
        return kategori.get();
    }

    public void setKategori(String kategori) {
        this.kategori.set(kategori);
    }

    public StringProperty kategoriProperty() {
        return kategori;
    }

    public String getMerekObat() {
        return merekObat.get();
    }

    public void setMerekObat(String merekObat) {
        this.merekObat.set(merekObat);
    }

    public StringProperty merekObatProperty() {
        return merekObat;
    }

    public double getHarga() {
        return harga.get();
    }

    public void setHarga(double harga) {
        this.harga.set(harga);
    }

    public DoubleProperty hargaProperty() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi.get();
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi.set(deskripsi);
    }

    public StringProperty deskripsiProperty() {
        return deskripsi;
    }
    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
