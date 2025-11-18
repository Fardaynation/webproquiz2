package com.proyekquiz.model;

public class FrsDetail {
    private int frsId;
    private String kodeMk;
    private String namaMk;
    private int sks;
    public FrsDetail() {}
    public int getFrsId() {
        return frsId;
    }
    public void setFrsId(int frsId) {
        this.frsId = frsId;
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