package com.proyekquiz.model;

public class MataKuliah {
    private int id;
    private String kodeMk;
    private String namaMk;
    private int sks;
    public MataKuliah() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
}