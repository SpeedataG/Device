package com.speedata.device;

import android.content.Context;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.speedata.device.gen.GPSSatellite;
import com.speedata.device.gen.GPSSatelliteDao;
import com.speedata.ui.adapter.CommonAdapter;
import com.speedata.ui.adapter.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jxl.write.Colour;


public class GPSAct extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_gps, tv_satellites;
    private Button bt_Quit;
    private Button btnExport;
    private Button btnClear;
    LocationManager locationManager;
    private StringBuilder sb;
    private TextView tvCount;
    private ListView listView;
    private CommonAdapter<GpsSatellite> adapter;

    private TextView tvSrn, tvPrn, tvElevation, tvAzimuth;

    private TextView tvTime;
    private CheckBox checkBox;

    private ImageView imgBack;//返回图标
    private TextView title;//标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        imgBack = (ImageView)findViewById(R.id.img_back);
        title = (TextView)findViewById(R.id.tv_title);

        tvTime = (TextView) findViewById(R.id.tv_time);
        btnClear = (Button) findViewById(R.id.btn_clear);
        listView = (ListView) findViewById(R.id.list_satellites);
        tvCount = (TextView) findViewById(R.id.tv_count);
        tv_satellites = (TextView) this.findViewById(R.id.tv_satellites);
        tv_gps = (TextView) this.findViewById(R.id.tv_gps);
        bt_Quit = (Button) this.findViewById(R.id.bt_quit_gps);
        btnExport = (Button) findViewById(R.id.bt_export);
        btnExport.setOnClickListener(this);

        tvSrn = (TextView) findViewById(R.id.tv_srn);
        tvPrn = (TextView) findViewById(R.id.tv_prn);
        tvElevation = (TextView) findViewById(R.id.tv_elevation);
        tvAzimuth = (TextView) findViewById(R.id.tv_azimuth);
        tvPrn.setOnClickListener(this);
        tvSrn.setOnClickListener(this);
        tvElevation.setOnClickListener(this);
        tvAzimuth.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.checkbox_order);
        openGPSSettings();

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;
        Location location = locationManager.getLastKnownLocation(provider);
        updateMsg(location);

        LocationListener ll = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                String locInfo = updateMsg(location);
                tv_gps.setText(null);
                tv_gps.setText(locInfo);
            }

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        };

        locationManager.requestLocationUpdates(provider, 1000 * 60, 1, ll);

        title.setText(R.string.GPS_title);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSAct.this.finish();
            }
        });
//        bt_Quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GPSAct.this.finish();
//            }
//        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSSatelliteDao dap = MyApplication.getDaoInstant().getGPSSatelliteDao();
                dap.deleteAll();
            }
        });
        locationManager.addGpsStatusListener(statusListener);
        initList();
    }

    private String updateMsg(Location loc) {
        sb = null;
        sb = new StringBuilder("位置信息：\n");
        if (loc != null) {
            double lat = loc.getLatitude();
            double lng = loc.getLongitude();


            sb.append("纬度：" + lat + "\n经度：" + lng);

            if (loc.hasAccuracy()) {
                sb.append("\n精度：" + loc.getAccuracy());
            }

            if (loc.hasAltitude()) {
                sb.append("\n海拔：" + loc.getAltitude() + "m");
            }

            if (loc.hasBearing()) {// 偏离正北方向的角度
                sb.append("\n方向：" + loc.getBearing());
            }

            if (loc.hasSpeed()) {
                if (loc.getSpeed() * 3.6 < 5) {
                    sb.append("\n速度：0.0km/h");
                } else {
                    sb.append("\n速度：" + loc.getSpeed() * 3.6 + "km/h");
                }

            }
            if (isFirst && lng != 0) {
                tvTime.setText("首次定位时间：" + (System.currentTimeMillis() - time) + "ms");//+sb.toString());
                isFirst = false;
            }
        } else {
            sb.append("没有位置信息！");
        }

        return sb.toString();
    }

    private long time;
    private boolean isFirst = true;

    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
            return;
        }

        Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            GPSAct.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 卫星状态监听器
     */
    private List<GpsSatellite> numSatelliteList = new ArrayList<GpsSatellite>(); // 卫星信号

    private final GpsStatus.Listener statusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) { // GPS状态变化时的回调，如卫星数
            LocationManager locationManager = (LocationManager) GPSAct.this.getSystemService(Context.LOCATION_SERVICE);
            GpsStatus status = locationManager.getGpsStatus(null); //取当前状态
            String satelliteInfo = updateGpsStatus(event, status);
            tv_satellites.setText(null);
            tv_satellites.setText(satelliteInfo);
        }
    };

    private String updateGpsStatus(int event, GpsStatus status) {
        StringBuilder sb2 = new StringBuilder("");
        if (status == null) {
            sb2.append("搜索到卫星个数：" + 0);
        } else if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
            int maxSatellites = status.getMaxSatellites();
            status.getSatellites();
            Iterator<GpsSatellite> it = status.getSatellites().iterator();
            numSatelliteList.clear();
            int count = 0;
            while (it.hasNext() && count <= maxSatellites) {
                GpsSatellite s = it.next();
                if (s.getSnr() > 0) {
                    numSatelliteList.add(s);
                    count++;
                }
            }
            GPSSatelliteDao dap = MyApplication.getDaoInstant().getGPSSatelliteDao();

            sb2.append("搜索到卫星个数：" + numSatelliteList.size());
            for (GpsSatellite gpsSatellite : numSatelliteList) {
                //卫星的方位角，浮点型数据
                System.out.println(gpsSatellite.getAzimuth());
                //卫星的高度，浮点型数据
                System.out.println(gpsSatellite.getElevation());
                //卫星的伪随机噪声码，整形数据
                System.out.println(gpsSatellite.getPrn());
                //卫星的信噪比，浮点型数据
                float snr = gpsSatellite.getSnr();
                //卫星是否有年历表，布尔型数据
                System.out.println(gpsSatellite.hasAlmanac());
                //卫星是否有星历表，布尔型数据
                System.out.println(gpsSatellite.hasEphemeris());
                //卫星是否被用于近期的GPS修正计算
                System.out.println(gpsSatellite.hasAlmanac());
                System.out.println(snr);
//                if(snr>0) {
//                sb2.append("\n" + gpsSatellite.getAzimuth() + "  " + gpsSatellite.getPrn() + " " + gpsSatellite.getElevation() + " " + gpsSatellite.getSnr());
                GPSSatellite gps = new GPSSatellite();
                gps.setPrn(gpsSatellite.getPrn());
                gps.setAzimuth(gpsSatellite.getAzimuth());
                gps.setElevation(gpsSatellite.getElevation());
                gps.setEphemeris(gpsSatellite.hasEphemeris());
                gps.setSnr((int) snr);
                gps.setEphemeris(gpsSatellite.hasEphemeris());
                SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日 HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                gps.setCollectTime(formatter.format(curDate));
                dap.insert(gps);
                tvCount.setText("记录" + dap.count());
//                }
            }
            paixu(CODE_ELEVATION, false);
//            numSatelliteList.get(0).
        }

        return sb2.toString();
    }

    private void initList() {
        adapter = new CommonAdapter<GpsSatellite>(this, numSatelliteList, R.layout.adapter_gps_) {
            @Override
            public void convert(ViewHolder helper, GpsSatellite item) {
                helper.setText(R.id.tv_1, item.getSnr() + "");
                helper.setText(R.id.tv_2, item.getPrn() + "");
                helper.setText(R.id.tv_3, item.getElevation() + "");
                helper.setText(R.id.tv_4, item.getAzimuth() + "");
            }
        };
        listView.setAdapter(adapter);
    }

    private final int CODE_SNR = 0;
    private final int CODE_PRN = 1;
    private final int CODE_ELEVATION = 2;
    private final int CODE_AZIMUTH = 3;

    /**
     * @param code           按照某个字段排序
     * @param is_order_sheng 升序或者降序
     * @return
     */
    private void paixu(int code, final boolean is_order_sheng) {

        switch (code) {
            case CODE_SNR:
                Collections.sort(numSatelliteList, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (is_order_sheng)
                            return new Double(((GpsSatellite) o1).getSnr()).compareTo(new Double(((GpsSatellite) o2).getSnr()));
                        else
                            return new Double(((GpsSatellite) o2).getSnr()).compareTo(new Double(((GpsSatellite) o1).getSnr()));
                    }

                });
                break;
            case CODE_PRN:
                Collections.sort(numSatelliteList, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (is_order_sheng)
                            return new Double(((GpsSatellite) o1).getPrn()).compareTo(new Double(((GpsSatellite) o2).getPrn()));
                        else
                            return new Double(((GpsSatellite) o2).getPrn()).compareTo(new Double(((GpsSatellite) o1).getPrn()));
                    }

                });
                break;
            case CODE_ELEVATION:
                Collections.sort(numSatelliteList, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (is_order_sheng)
                            return new Double(((GpsSatellite) o1).getElevation()).compareTo(new Double(((GpsSatellite) o2).getElevation()));
                        else
                            return new Double(((GpsSatellite) o2).getElevation()).compareTo(new Double(((GpsSatellite) o1).getElevation()));
                    }

                });
                break;
            case CODE_AZIMUTH:
                Collections.sort(numSatelliteList, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (is_order_sheng)
                            return new Double(((GpsSatellite) o1).getAzimuth()).compareTo(new Double(((GpsSatellite) o2).getAzimuth()));
                        else
                            return new Double(((GpsSatellite) o2).getAzimuth()).compareTo(new Double(((GpsSatellite) o1).getAzimuth()));
                    }

                });
                break;
        }
//        numSatelliteList.clear();
//        numSatelliteList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v == btnExport) {
//            ProgressDialogUtils.showProgressDialog(GPSAct.this, "正在导出，请稍后");
            GPSSatelliteDao dap = MyApplication.getDaoInstant().getGPSSatelliteDao();
            if (dap.loadAll().size() == 0) {
                Toast.makeText(GPSAct.this, getString(R.string.no_data_to_export), Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {

//                    String[] test = {"id", "srn", "prn", "elevation", "azimuth", "time"};
////                    ExcelUtils.initExcel(getSDPath() + "/test.xls", test);
//                    ExcelUtils.writeListToExcel(dap.loadAll(), getSDPath() + "/test.xls", GPSAct.this, "GPSSatellite", test);
                    com.speedata.libutils.excel.ExcelUtils.getInstance()
                            .setSHEET_NAME("测试Sheet")//设置表格名称
                            .setFONT_COLOR(jxl.format.Colour.BLUE)//设置标题字体颜色
                            .setFONT_TIMES(8)//设置标题字体大小
                            .setFONT_BOLD(true)//设置标题字体是否斜体
                            .setBACKGROND_COLOR(Colour.GRAY_25)//设置标题背景颜色
                            .setContent_list_Strings(dap.loadAll())//设置excel内容
                            .createExcel(GPSAct.this);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ProgressDialogUtils.dismissProgressDialog();
//                        }
//                    });

                }
            }).start();

        } else if (v == tvSrn) {
            tvSrn.setBackgroundColor(getResources().getColor(R.color.colorRed));
            tvAzimuth.setBackgroundColor(getResources().getColor(R.color.blue));
            tvPrn.setBackgroundColor(getResources().getColor(R.color.blue));
            tvElevation.setBackgroundColor(getResources().getColor(R.color.blue));
            paixu(CODE_SNR, checkBox.isChecked());
        } else if (v == tvElevation) {
            tvSrn.setBackgroundColor(getResources().getColor(R.color.blue));
            tvAzimuth.setBackgroundColor(getResources().getColor(R.color.blue));
            tvPrn.setBackgroundColor(getResources().getColor(R.color.blue));
            tvElevation.setBackgroundColor(getResources().getColor(R.color.colorRed));
            paixu(CODE_ELEVATION, checkBox.isChecked());
        } else if (v == tvPrn) {
            tvSrn.setBackgroundColor(getResources().getColor(R.color.blue));
            tvAzimuth.setBackgroundColor(getResources().getColor(R.color.blue));
            tvPrn.setBackgroundColor(getResources().getColor(R.color.colorRed));
            tvElevation.setBackgroundColor(getResources().getColor(R.color.blue));
            paixu(CODE_PRN, checkBox.isChecked());
        } else if (v == tvAzimuth) {
            tvSrn.setBackgroundColor(getResources().getColor(R.color.blue));
            tvAzimuth.setBackgroundColor(getResources().getColor(R.color.colorRed));
            tvPrn.setBackgroundColor(getResources().getColor(R.color.blue));
            tvElevation.setBackgroundColor(getResources().getColor(R.color.blue));
            paixu(CODE_AZIMUTH, checkBox.isChecked());
        }

    }


}