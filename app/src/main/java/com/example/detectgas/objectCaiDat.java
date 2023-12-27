package com.example.detectgas;

public class objectCaiDat {
    private int id;
    private int nhietDo;
    private int DoAm;
    private String tenRau;

    public objectCaiDat(int id, int nhietDo, int doAm, String tenRau) {
        this.id = id;
        this.nhietDo = nhietDo;
        this.DoAm = doAm;
        this.tenRau = tenRau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(int nhietDo) {
        this.nhietDo = nhietDo;
    }

    public int getDoAm() {
        return DoAm;
    }

    public void setDoAm(int doAm) {
        DoAm = doAm;
    }

    public String getTenRau() {
        return tenRau;
    }

    public void setTenRau(String tenRau) {
        this.tenRau = tenRau;
    }
}
