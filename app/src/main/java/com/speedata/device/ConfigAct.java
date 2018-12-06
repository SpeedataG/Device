package com.speedata.device;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.speedata.libutils.CommonUtils;
import com.speedata.libutils.ConfigUtils;
import com.speedata.libutils.ReadBean;

public class ConfigAct extends AppCompatActivity {


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
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showView();
    }

    private void showView() {
        if (ConfigUtils.isConfigFileExists()) {
            setTitle("V" + CommonUtils.getAppVersionName(this) + "    " + getString(R.string.config_content));
        } else {
            setTitle("V" + CommonUtils.getAppVersionName(this) + "    " + getString(R.string.config_not_exists));
        }
        mRead = ConfigUtils.readConfig(this);
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

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateData() {

        getId2();
        getUhf();
        getR6();
        getPrint();
        getPsam();
        getFinger();
        getDist();
        getTemp();
        getLF1();
        getLF2();
        getSp433();
        getScan();
        getZigbee();
        getInfrared();
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


    private void getInfrared() {
        ReadBean.InfraredBean temp = new ReadBean.InfraredBean();
        temp.setBraut(mMvInfrared.getBraut());
        temp.setSerialPort(mMvInfrared.getSerialPort());
        temp.setPowerType(mMvInfrared.getPowerType());
        temp.setGpio(mMvInfrared.getGpio());
        mRead.setInfrared(temp);
    }

    private void setZigbee() {
        mMvZigbee.setModule("Zigbee");
        mMvZigbee.setSerialPort(mRead.getZigbee().getSerialPort());
        mMvZigbee.setBraut(String.valueOf(mRead.getZigbee().getBraut()));
        mMvZigbee.setPowerType(mRead.getZigbee().getPowerType());
        mMvZigbee.setGpio(String.valueOf(mRead.getZigbee().getGpio()));
    }

    private void getZigbee() {
        ReadBean.ZigbeeBean temp = new ReadBean.ZigbeeBean();
        temp.setBraut(mMvZigbee.getBraut());
        temp.setSerialPort(mMvZigbee.getSerialPort());
        temp.setPowerType(mMvZigbee.getPowerType());
        temp.setGpio(mMvZigbee.getGpio());
        mRead.setZigbee(temp);
    }


    private void setScan() {
        mMvScan.setModule("串口扫头");
        mMvScan.setSerialPort(mRead.getScan().getSerialPort());
        mMvScan.setBraut(String.valueOf(mRead.getScan().getBraut()));
        mMvScan.setPowerType(mRead.getScan().getPowerType());
        mMvScan.setGpio(String.valueOf(mRead.getScan().getGpio()));
    }

    private void getScan() {
        ReadBean.ScanBean temp = new ReadBean.ScanBean();
        temp.setBraut(mMvScan.getBraut());
        temp.setSerialPort(mMvScan.getSerialPort());
        temp.setPowerType(mMvScan.getPowerType());
        temp.setGpio(mMvScan.getGpio());
        mRead.setScan(temp);
    }

    private void setSp433() {
        mMvSp433.setModule("Sp433");
        mMvSp433.setSerialPort(mRead.getSp433().getSerialPort());
        mMvSp433.setBraut(String.valueOf(mRead.getSp433().getBraut()));
        mMvSp433.setPowerType(mRead.getSp433().getPowerType());
        mMvSp433.setGpio(String.valueOf(mRead.getSp433().getGpio()));
    }

    private void getSp433() {
        ReadBean.Sp433Bean temp = new ReadBean.Sp433Bean();
        temp.setBraut(mMvSp433.getBraut());
        temp.setSerialPort(mMvSp433.getSerialPort());
        temp.setPowerType(mMvSp433.getPowerType());
        temp.setGpio(mMvSp433.getGpio());
        mRead.setSp433(temp);
    }

    private void setLf2() {
        mMvLf2.setModule("Lf2");
        mMvLf2.setSerialPort(mRead.getLf2().getSerialPort());
        mMvLf2.setBraut(String.valueOf(mRead.getLf2().getBraut()));
        mMvLf2.setPowerType(mRead.getLf2().getPowerType());
        mMvLf2.setGpio(String.valueOf(mRead.getLf2().getGpio()));
    }

    private void getLF2() {
        ReadBean.Lf2Bean temp = new ReadBean.Lf2Bean();
        temp.setBraut(mMvLf2.getBraut());
        temp.setSerialPort(mMvLf2.getSerialPort());
        temp.setPowerType(mMvLf2.getPowerType());
        temp.setGpio(mMvLf2.getGpio());
        mRead.setLf2(temp);
    }

    private void setLf1() {
        mMvLf1.setModule("Lf1");
        mMvLf1.setSerialPort(mRead.getLf1().getSerialPort());
        mMvLf1.setBraut(String.valueOf(mRead.getLf1().getBraut()));
        mMvLf1.setPowerType(mRead.getLf1().getPowerType());
        mMvLf1.setGpio(String.valueOf(mRead.getLf1().getGpio()));
    }

    private void getLF1() {
        ReadBean.Lf1Bean temp = new ReadBean.Lf1Bean();
        temp.setBraut(mMvLf1.getBraut());
        temp.setSerialPort(mMvLf1.getSerialPort());
        temp.setPowerType(mMvLf1.getPowerType());
        temp.setGpio(mMvLf1.getGpio());
        mRead.setLf1(temp);
    }

    private void setTemp() {
        mMvTemp.setModule("测温");
        mMvTemp.setSerialPort(mRead.getTemp().getSerialPort());
        mMvTemp.setBraut(String.valueOf(mRead.getTemp().getBraut()));
        mMvTemp.setPowerType(mRead.getTemp().getPowerType());
        mMvTemp.setGpio(String.valueOf(mRead.getTemp().getGpio()));
    }

    private void getTemp() {
        ReadBean.TempBean temp = new ReadBean.TempBean();
        temp.setBraut(mMvTemp.getBraut());
        temp.setSerialPort(mMvTemp.getSerialPort());
        temp.setPowerType(mMvTemp.getPowerType());
        temp.setGpio(mMvTemp.getGpio());
        mRead.setTemp(temp);
    }

    private void setDist() {
        mMvDist.setModule("测距");
        mMvDist.setSerialPort(mRead.getDist().getSerialPort());
        mMvDist.setBraut(String.valueOf(mRead.getDist().getBraut()));
        mMvDist.setPowerType(mRead.getDist().getPowerType());
        mMvDist.setGpio(String.valueOf(mRead.getDist().getGpio()));
    }

    private void getDist() {
        ReadBean.DistBean temp = new ReadBean.DistBean();
        temp.setBraut(mMvDist.getBraut());
        temp.setSerialPort(mMvDist.getSerialPort());
        temp.setPowerType(mMvDist.getPowerType());
        temp.setGpio(mMvDist.getGpio());
        mRead.setDist(temp);
    }

    private void setFinger() {
        mMvFinger.setModule("指纹");
        mMvFinger.setSerialPort(mRead.getFinger().getSerialPort());
        mMvFinger.setBraut(String.valueOf(mRead.getFinger().getBraut()));
        mMvFinger.setPowerType(mRead.getFinger().getPowerType());
        mMvFinger.setGpio(String.valueOf(mRead.getFinger().getGpio()));
    }

    private void getFinger() {
        ReadBean.FingerBean temp = new ReadBean.FingerBean();
        temp.setBraut(mMvFinger.getBraut());
        temp.setSerialPort(mMvFinger.getSerialPort());
        temp.setPowerType(mMvFinger.getPowerType());
        temp.setGpio(mMvFinger.getGpio());
        mRead.setFinger(temp);
    }

    private void setPasm() {
        mMvPasm.setModule("Pasm");
        mMvPasm.setSerialPort(mRead.getPasm().getSerialPort());
        mMvPasm.setBraut(String.valueOf(mRead.getPasm().getBraut()));
        mMvPasm.setPowerType(mRead.getPasm().getPowerType());
        mMvPasm.setGpio(String.valueOf(mRead.getPasm().getGpio()));
    }

    private void getPsam() {
        ReadBean.PasmBean temp = new ReadBean.PasmBean();
        temp.setBraut(mMvPasm.getBraut());
        temp.setSerialPort(mMvPasm.getSerialPort());
        temp.setPowerType(mMvPasm.getPowerType());
        temp.setGpio(mMvPasm.getGpio());
        mRead.setPasm(temp);
    }

    private void setPrint() {
        mMvPrint.setModule("打印");
        mMvPrint.setSerialPort(mRead.getPrint().getSerialPort());
        mMvPrint.setBraut(String.valueOf(mRead.getPrint().getBraut()));
        mMvPrint.setPowerType(mRead.getPrint().getPowerType());
        mMvPrint.setGpio(String.valueOf(mRead.getPrint().getGpio()));
    }


    private void getPrint() {
        ReadBean.PrintBean temp = new ReadBean.PrintBean();
        temp.setBraut(mMvPrint.getBraut());
        temp.setSerialPort(mMvPrint.getSerialPort());
        temp.setPowerType(mMvPrint.getPowerType());
        temp.setGpio(mMvPrint.getGpio());
        mRead.setPrint(temp);
    }

    private void setR6() {
        mMvR6.setModule("R6");
        mMvR6.setSerialPort(mRead.getR6().getSerialPort());
        mMvR6.setBraut(String.valueOf(mRead.getR6().getBraut()));
        mMvR6.setPowerType(mRead.getR6().getPowerType());
        mMvR6.setGpio(String.valueOf(mRead.getR6().getGpio()));

    }

    private void getR6() {

        ReadBean.R6Bean temp = new ReadBean.R6Bean();
        temp.setBraut(mMvR6.getBraut());
        temp.setSerialPort(mMvR6.getSerialPort());
        temp.setPowerType(mMvR6.getPowerType());
        temp.setGpio(mMvR6.getGpio());
        mRead.setR6(temp);
    }

    private void setUhf() {
        mMvUhf.setModule("Uhf");
        mMvUhf.setSerialPort(mRead.getUhf().getSerialPort());
        mMvUhf.setBraut(String.valueOf(mRead.getUhf().getBraut()));
        mMvUhf.setPowerType(mRead.getUhf().getPowerType());
        mMvUhf.setGpio(String.valueOf(mRead.getUhf().getGpio()));
    }

    private void getUhf() {

        ReadBean.UhfBean uhfBean = new ReadBean.UhfBean();
        uhfBean.setBraut(mMvUhf.getBraut());
        uhfBean.setSerialPort(mMvUhf.getSerialPort());
        uhfBean.setPowerType(mMvUhf.getPowerType());
        uhfBean.setGpio(mMvUhf.getGpio());
        mRead.setUhf(uhfBean);
    }

    private void setId2() {
        mMvId2.setModule("身份证Id");
        mMvId2.setSerialPort(mRead.getId2().getSerialPort());
        mMvId2.setBraut(String.valueOf(mRead.getId2().getBraut()));
        mMvId2.setPowerType(mRead.getId2().getPowerType());
        mMvId2.setGpio(String.valueOf(mRead.getId2().getGpio()));
    }

    private void getId2() {

        ReadBean.Id2Bean id2Bean = new ReadBean.Id2Bean();
        id2Bean.setBraut(mMvId2.getBraut());
        id2Bean.setSerialPort(mMvId2.getSerialPort());
        id2Bean.setPowerType(mMvId2.getPowerType());
        id2Bean.setGpio(mMvId2.getGpio());
        mRead.setId2(id2Bean);
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
        //        mMvUr2k = (ModuleView) findViewById(R.id.mv_ur2k);
        mMvZigbee = (ModuleView) findViewById(R.id.mv_zigbee);
        mMvInfrared = (ModuleView) findViewById(R.id.mv_infrared);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                ConfigUtils.writeConfig(mRead);
                finish();
            }
        });
    }

    //    ReadBean getData() {
    ////        mRead.setId2(new ReadBean.Id2Bean(mMvId2.getSerialPort(), mMvId2.getBraut(), mMvId2.getPowerType(), mMvId2.getGpio()));
    //    }


}
