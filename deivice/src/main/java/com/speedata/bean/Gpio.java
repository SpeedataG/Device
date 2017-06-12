package com.speedata.bean;

/**
 * Created by brxu on 2017/3/22.
 * gpio实体类
 */

public class Gpio {
    private String num;
    private String mode;
    private String sel;
    private String din;
    private String dout;
    private String en;
    private String dir;
    private String ies;
    private String smt;

    public String getDout() {
        return dout;
    }

    public void setDout(String dout) {
        this.dout = dout;
    }

    public String getDin() {
        return din;
    }

    public void setDin(String din) {
        this.din = din;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getIes() {
        return ies;
    }

    public void setIes(String ies) {
        this.ies = ies;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    public String getSmt() {
        return smt;
    }

    public void setSmt(String smt) {
        this.smt = smt;
    }
}
