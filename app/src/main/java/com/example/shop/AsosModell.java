package com.example.shop;

import java.io.Serializable;

public class AsosModell implements Serializable {
    Integer id;
    Integer clientId;
    Integer userId;
    Integer xodimId;
    Integer haridorId;
    Integer dilerId;
    Integer turOper;
    String sana;
    Integer nomer;
    Integer del_flag;
    Integer dollar;
    Double kurs;
    Double sum_d;
    Integer kol;

    public AsosModell() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getXodimId() {
        return xodimId;
    }

    public void setXodimId(Integer xodimId) {
        this.xodimId = xodimId;
    }

    public Integer getHaridorId() {
        return haridorId;
    }

    public void setHaridorId(Integer haridorId) {
        this.haridorId = haridorId;
    }

    public Integer getDilerId() {
        return dilerId;
    }

    public void setDilerId(Integer dilerId) {
        this.dilerId = dilerId;
    }

    public Integer getTurOper() {
        return turOper;
    }

    public void setTurOper(Integer turOper) {
        this.turOper = turOper;
    }

    public String getSana() {
        return sana;
    }

    public void setSana(String sana) {
        this.sana = sana;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public Integer getDollar() {
        return dollar;
    }

    public void setDollar(Integer dollar) {
        this.dollar = dollar;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public Double getSum_d() {
        return sum_d;
    }

    public void setSum_d(Double sum_d) {
        this.sum_d = sum_d;
    }

    public Integer getKol() {
        return kol;
    }

    public void setKol(Integer kol) {
        this.kol = kol;
    }
}
