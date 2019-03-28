package com.speedata.device.telephony;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

import com.speedata.device.R;

import java.util.List;

/**
 * 读取基站信息demo
 */

public class BaseStationActivity extends Activity {

    private static final String TAG = "BaseStationActivity";

    private TextView tvShowMsg;

    @SuppressLint({"MissingPermission", "NewApi", "LocalSuppress", "SetTextI18n"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsmcell_location);
        tvShowMsg = (TextView) findViewById(R.id.tv_show_msg);

        findViewById(R.id.button2).setOnClickListener(v -> tvShowMsg.setText(""));

        // 获取基站信息
        //            @RequiresApi(api = Build.VERSION_CODES.M)
        findViewById(R.id.button1).setOnClickListener(v -> {

            TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            // 返回值MCC + MNC
            String operator = mTelephonyManager.getNetworkOperator();
            int mcc = 0;
            int mnc = 0;
            if (operator.length() > 3) {
                mcc = Integer.parseInt(operator.substring(0, 3));
                mnc = Integer.parseInt(operator.substring(3));
            } else {
                tvShowMsg.setText(getString(R.string.no_card_inserted) + "\n");
                return;
            }

            GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();

            int lac = location.getLac();
            int cellId = location.getCid();
            tvShowMsg.append(" MCC = " + mcc + "\n MNC = " + mnc + "\n LAC = " + lac + "\n CID = " + cellId + "\n");
            int phoneType = mTelephonyManager.getPhoneType();
            tvShowMsg.append("phoneType:" + phoneType + "\n");

            // 获取邻区基站信息 替换 mTelephonyManager.getNetworkCountryIso()
            List<CellInfo> infoLists = mTelephonyManager.getAllCellInfo();
            if (infoLists == null) {
                tvShowMsg.append(getString(R.string.obtaining_base_stat));
                return;
            }
            StringBuffer sb = new StringBuffer(getString(R.string.total) + infoLists.size() + "\n");
            int strength = 0;
            int cid = 0;
            for (CellInfo info : infoLists) {
                if (info instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) info;
                    CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
                    CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
                    strength = cellSignalStrengthCdma.getCdmaDbm();
                    cid = cellIdentityCdma.getBasestationId();
                    sb.append(" cdma : ");
                } else if (info instanceof CellInfoGsm) {
                    CellInfoGsm gsm = (CellInfoGsm) info;
                    CellIdentityGsm cellIdentityCdma = gsm.getCellIdentity();
                    CellSignalStrengthGsm cellSignalStrengthgsm = gsm.getCellSignalStrength();
                    strength = cellSignalStrengthgsm.getDbm();
                    cid = cellIdentityCdma.getCid();
                    sb.append(" gsm : ");
                } else if (info instanceof CellInfoWcdma) {
                    CellInfoWcdma wcdma = (CellInfoWcdma) info;
                    CellIdentityWcdma cellIdentity = wcdma.getCellIdentity();
                    CellSignalStrengthWcdma signalStrengthWcdma = wcdma.getCellSignalStrength();
                    strength = signalStrengthWcdma.getDbm();
                    cid = cellIdentity.getCid();
                    sb.append(" wcdma : ");
                }
                // 强度
                sb.append(" strength : ").append(strength);
                // 取出当前邻区的CID
                sb.append(" CID : ").append(cid);
                sb.append("  \n" );
            }
            tvShowMsg.append(getString(R.string.obtaining_base) + sb.toString());
            Log.i(TAG, " 获取邻区基站信息:" + sb.toString());

        });

    }
}
