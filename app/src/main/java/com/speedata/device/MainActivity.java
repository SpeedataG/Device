package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.speedata.libutils.CommonUtils;
import com.speedata.libutils.ConfigUtils;
import com.speedata.libutils.ReadBean;

public class MainActivity extends AppCompatActivity {


    private ModuleView mMvId2;
    private ModuleView mMvUhf;
    private ModuleView mMvR6;
    private ModuleView mMvPrint;
    private ModuleView mMvPasm;
    private ModuleView mMvFinger;
    private ModuleView mMvDist;
    private ModuleView mMvTemp;
    private ModuleView mMvLf1;
    private ModuleView mMvLf2;
    private ModuleView mMvSp433;
    private ModuleView mMvScan;
    private ModuleView mMvUr2k;
    private ModuleView mMvZigbee;
    private ModuleView mMvInfrared;
    private ReadBean mRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showView();
    }

    private void showView() {
        if(ConfigUtils.isConfigFileExists()){
            setTitle("V"+CommonUtils.getAppVersionName(this) + "    " + getString(R.string.config_content));
        }else{
            setTitle("V"+CommonUtils.getAppVersionName(this) + "    " + getString(R.string.config_not_exists));
        }
        mRead= ConfigUtils.readConfig(this);
        setView();
    }

    private void setView() {

        setId2();
        setUhf();
        setR6();
        setPrint();
        setPasm();
        setFinger();
        setDist();
        setTemp();
        setLf1();
        setLf2();
        setSp433();
        setScan();
        setZigbee();
        setInfrared();
//        if(!ConfigUtils.isConfigFileExists()){
//            setUr2k();
//        }
    }

    private void setInfrared() {
        mMvInfrared.setModule("红外模块");
        mMvInfrared.setSerialPort(mRead.getInfrared().getSerialPort());
        mMvInfrared.setBraut(String.valueOf(mRead.getInfrared().getBraut()));
        mMvInfrared.setPowerType(mRead.getInfrared().getPowerType());
        mMvInfrared.setGpio(String.valueOf(mRead.getInfrared().getGpio()));
    }

    private void setZigbee() {
        mMvZigbee.setModule("Zigbee");
        mMvZigbee.setSerialPort(mRead.getZigbee().getSerialPort());
        mMvZigbee.setBraut(String.valueOf(mRead.getZigbee().getBraut()));
        mMvZigbee.setPowerType(mRead.getZigbee().getPowerType());
        mMvZigbee.setGpio(String.valueOf(mRead.getZigbee().getGpio()));
    }

    private void setUr2k() {
        mMvUr2k.setModule("UR2K");
        mMvUr2k.setSerialPort(mRead.getUr2k().getSerialPort());
        mMvUr2k.setBraut(String.valueOf(mRead.getUr2k().getBraut()));
        mMvUr2k.setPowerType(mRead.getUr2k().getPowerType());
        mMvUr2k.setGpio(String.valueOf(mRead.getUr2k().getGpio()));
    }

    private void setScan() {
        mMvScan.setModule("串口扫头");
        mMvScan.setSerialPort(mRead.getScan().getSerialPort());
        mMvScan.setBraut(String.valueOf(mRead.getScan().getBraut()));
        mMvScan.setPowerType(mRead.getScan().getPowerType());
        mMvScan.setGpio(String.valueOf(mRead.getScan().getGpio()));
    }

    private void setSp433() {
        mMvSp433.setModule("Sp433");
        mMvSp433.setSerialPort(mRead.getSp433().getSerialPort());
        mMvSp433.setBraut(String.valueOf(mRead.getSp433().getBraut()));
        mMvSp433.setPowerType(mRead.getSp433().getPowerType());
        mMvSp433.setGpio(String.valueOf(mRead.getSp433().getGpio()));
    }

    private void setLf2() {
        mMvLf2.setModule("Lf2");
        mMvLf2.setSerialPort(mRead.getLf2().getSerialPort());
        mMvLf2.setBraut(String.valueOf(mRead.getLf2().getBraut()));
        mMvLf2.setPowerType(mRead.getLf2().getPowerType());
        mMvLf2.setGpio(String.valueOf(mRead.getLf2().getGpio()));
    }

    private void setLf1() {
        mMvLf1.setModule("Lf1");
        mMvLf1.setSerialPort(mRead.getLf1().getSerialPort());
        mMvLf1.setBraut(String.valueOf(mRead.getLf1().getBraut()));
        mMvLf1.setPowerType(mRead.getLf1().getPowerType());
        mMvLf1.setGpio(String.valueOf(mRead.getLf1().getGpio()));
    }

    private void setTemp() {
        mMvTemp.setModule("测温");
        mMvTemp.setSerialPort(mRead.getTemp().getSerialPort());
        mMvTemp.setBraut(String.valueOf(mRead.getTemp().getBraut()));
        mMvTemp.setPowerType(mRead.getTemp().getPowerType());
        mMvTemp.setGpio(String.valueOf(mRead.getTemp().getGpio()));
    }

    private void setDist() {
        mMvDist.setModule("测距");
        mMvDist.setSerialPort(mRead.getDist().getSerialPort());
        mMvDist.setBraut(String.valueOf(mRead.getDist().getBraut()));
        mMvDist.setPowerType(mRead.getDist().getPowerType());
        mMvDist.setGpio(String.valueOf(mRead.getDist().getGpio()));
    }

    private void setFinger() {
        mMvFinger.setModule("指纹");
        mMvFinger.setSerialPort(mRead.getFinger().getSerialPort());
        mMvFinger.setBraut(String.valueOf(mRead.getFinger().getBraut()));
        mMvFinger.setPowerType(mRead.getFinger().getPowerType());
        mMvFinger.setGpio(String.valueOf(mRead.getFinger().getGpio()));
    }

    private void setPasm() {
        mMvPasm.setModule("Pasm");
        mMvPasm.setSerialPort(mRead.getPasm().getSerialPort());
        mMvPasm.setBraut(String.valueOf(mRead.getPasm().getBraut()));
        mMvPasm.setPowerType(mRead.getPasm().getPowerType());
        mMvPasm.setGpio(String.valueOf(mRead.getPasm().getGpio()));
    }

    private void setPrint() {
        mMvPrint.setModule("打印");
        mMvPrint.setSerialPort(mRead.getPrint().getSerialPort());
        mMvPrint.setBraut(String.valueOf(mRead.getPrint().getBraut()));
        mMvPrint.setPowerType(mRead.getPrint().getPowerType());
        mMvPrint.setGpio(String.valueOf(mRead.getPrint().getGpio()));
    }

    private void setR6() {
        mMvR6.setModule("R6");
        mMvR6.setSerialPort(mRead.getR6().getSerialPort());
        mMvR6.setBraut(String.valueOf(mRead.getR6().getBraut()));
        mMvR6.setPowerType(mRead.getR6().getPowerType());
        mMvR6.setGpio(String.valueOf(mRead.getR6().getGpio()));
    }

    private void setUhf() {
        mMvUhf.setModule("Uhf");
        mMvUhf.setSerialPort(mRead.getUhf().getSerialPort());
        mMvUhf.setBraut(String.valueOf(mRead.getUhf().getBraut()));
        mMvUhf.setPowerType(mRead.getUhf().getPowerType());
        mMvUhf.setGpio(String.valueOf(mRead.getUhf().getGpio()));
    }

    private void setId2() {
        mMvId2.setModule("身份证Id");
        mMvId2.setSerialPort(mRead.getId2().getSerialPort());
        mMvId2.setBraut(String.valueOf(mRead.getId2().getBraut()));
        mMvId2.setPowerType(mRead.getId2().getPowerType());
        mMvId2.setGpio(String.valueOf(mRead.getId2().getGpio()));
    }


    private void initView() {
        mMvId2 = (ModuleView) findViewById(R.id.mv_id2);
        mMvUhf = (ModuleView) findViewById(R.id.mv_uhf);
        mMvR6 = (ModuleView) findViewById(R.id.mv_r6);
        mMvPrint = (ModuleView) findViewById(R.id.mv_print);
        mMvPasm = (ModuleView) findViewById(R.id.mv_pasm);
        mMvFinger = (ModuleView) findViewById(R.id.mv_finger);
        mMvDist = (ModuleView) findViewById(R.id.mv_dist);
        mMvTemp = (ModuleView) findViewById(R.id.mv_temp);
        mMvLf1 = (ModuleView) findViewById(R.id.mv_lf1);
        mMvLf2 = (ModuleView) findViewById(R.id.mv_lf2);
        mMvSp433 = (ModuleView) findViewById(R.id.mv_sp433);
        mMvScan = (ModuleView) findViewById(R.id.mv_scan);
        mMvUr2k = (ModuleView) findViewById(R.id.mv_ur2k);
        mMvZigbee = (ModuleView) findViewById(R.id.mv_zigbee);
        mMvInfrared = (ModuleView) findViewById(R.id.mv_infrared);
    }
}
