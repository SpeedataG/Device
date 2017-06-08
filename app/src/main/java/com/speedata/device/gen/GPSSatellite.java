package com.speedata.device.gen;

import com.speedata.libutils.excel.Excel;
import com.speedata.libutils.excel.ExcelIgnore;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.text.DecimalFormat;

/**
 * Created by echo on 2017/5/15.
 */

@Excel
@Entity
public class GPSSatellite {

    @ExcelIgnore(ignore = false)
    @Id
    private Long id;

    @ExcelIgnore(ignore = true)
    @Property(nameInDb = "collectTime")
    String collectTime;

    @ExcelIgnore(ignore = true)
    @Property(nameInDb = "Ephemeris")
    boolean Ephemeris;

    @ExcelIgnore(ignore = true)
    @Property(nameInDb = "Almanac")
    boolean Almanac;

    @ExcelIgnore(ignore = true)
    @Property(nameInDb = "Prn")
    int Prn;

    @ExcelIgnore(ignore = true)
    @Property(nameInDb = "Snr")
    float Snr;

    @ExcelIgnore(ignore = true)
    @Property(nameInDb = "Elevation")
    float Elevation;

    @ExcelIgnore(ignore = false)
    @Property(nameInDb = "Azimuth")
    float Azimuth;

    @Generated(hash = 75162614)
    public GPSSatellite(Long id, String collectTime, boolean Ephemeris,
            boolean Almanac, int Prn, float Snr, float Elevation, float Azimuth) {
        this.id = id;
        this.collectTime = collectTime;
        this.Ephemeris = Ephemeris;
        this.Almanac = Almanac;
        this.Prn = Prn;
        this.Snr = Snr;
        this.Elevation = Elevation;
        this.Azimuth = Azimuth;
    }

    @Generated(hash = 563287379)
    public GPSSatellite() {
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEphemeris() {
        return Ephemeris;
    }

    public void setEphemeris(boolean ephemeris) {
        Ephemeris = ephemeris;
    }

    public boolean isAlmanac() {
        return Almanac;
    }

    public void setAlmanac(boolean almanac) {
        Almanac = almanac;
    }

    public int getPrn() {
        return Prn;
    }

    public void setPrn(int prn) {
        Prn = prn;
    }

    public float getSnr() {
        return Snr;
    }

    public void setSnr(float snr) {
        DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p=decimalFormat.format(snr);//format 返回的是字符串
        Snr = Float.parseFloat(p);
    }

    public float getElevation() {
        return Elevation;
    }

    public void setElevation(float elevation) {
        Elevation = elevation;
    }

    public float getAzimuth() {
        return Azimuth;
    }

    public void setAzimuth(float azimuth) {
        Azimuth = azimuth;
    }

    @Override
    public String toString() {
        return "GPSSatellite{" +
                "id=" + id +
                ", Ephemeris=" + Ephemeris +
                ", Almanac=" + Almanac +
                ", Prn=" + Prn +
                ", Snr=" + Snr +
                ", Elevation=" + Elevation +
                ", Azimuth=" + Azimuth +
                '}';
    }

    public boolean getEphemeris() {
        return this.Ephemeris;
    }

    public boolean getAlmanac() {
        return this.Almanac;
    }
}
