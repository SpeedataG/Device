package com.speedata.device.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.serialport.DeviceControlSpd;
import android.serialport.SerialPortSpd;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.speedata.device.R;

import java.io.IOException;

import static android.serialport.SerialPortSpd.TAG;


class SettingsDialog extends Dialog implements
        View.OnClickListener, MyInterface {
    private final HelperActivity HelperActivity;
    private Context mContext;

    private SetOtherSerialPort setDialog;
    private Setbaudrate setBaudrate;
    private SerialPortSpd mSerialPortBackup;
    private int fd;
    private DeviceControlSpd DevCtrl;
    private int dataBit = 8;

    SettingsDialog(HelperActivity HelperActivity, Context context) {
        super(context);
        this.HelperActivity = HelperActivity;
        mContext = context;
        setDialog = new SetOtherSerialPort(this, context);
        setBaudrate = new Setbaudrate(this, context);
        mSerialPortBackup = HelperActivity.getmSerialPortBackup();
        DevCtrl = HelperActivity.getDevCtrl();
        ArrayBaudrate = mContext.getResources().getStringArray(R.array.baudrate);
        ArraySerialPort = mContext.getResources().getStringArray(R.array.serial_port);
        ArrayPowerPath = mContext.getResources().getStringArray(R.array.power_path);
        ArrayStopBit = mContext.getResources().getStringArray(R.array.stopbit);
        ArrayCrc = mContext.getResources().getStringArray(R.array.crc);
        Arraydatabit = mContext.getResources().getStringArray(R.array.databit);
    }

    private ImageView imggoback;
    private Button btnAddGpio, btnClearGpio;
    private TextView tvGpioShow;
    private Button poweron;
    private Button poweroff;
    private Button openSerial;
    private Button closeSerial;
    private String[] ArrayBaudrate;
    private String[] ArraySerialPort;
    private final String[] ArrayPowerPath;
    private final String[] ArrayStopBit;
    private final String[] ArrayCrc;
    private Spinner bSpinner;
    private Spinner pSpinner;
    private Spinner sSpinner;
    private Spinner crcSpinner;
    private int baudrate = 0;
    private String serialPath = "";
    private String powerPath;
    private String powerPathshow;
    private Spinner pathSpinner, databitSpinner;
    private int stopbitt;
    private int crcNum = 0;
    private int powercount = 0;
    private int serial = 0;
    private ArrayAdapter<String> databit_adapter;
    private final String[] Arraydatabit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		设置布局背景为透明
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.serial_layout);
        imggoback = (ImageView) this.findViewById(R.id.imgbackto);
        poweron = (Button) this.findViewById(R.id.poweron);
        openSerial = (Button) this.findViewById(R.id.openss);
        closeSerial = (Button) this.findViewById(R.id.closess);
        poweroff = (Button) this.findViewById(R.id.poweroff);
        bSpinner = (Spinner) this.findViewById(R.id.spinner2);
        pSpinner = (Spinner) this.findViewById(R.id.spinner1);
        pathSpinner = (Spinner) this.findViewById(R.id.pathspinner);
        sSpinner = (Spinner) this.findViewById(R.id.spinner3);
        crcSpinner = (Spinner) this.findViewById(R.id.spinner4);
        btnAddGpio = this.findViewById(R.id.btn_add_gpio);
        tvGpioShow = this.findViewById(R.id.tv_gpio);
        btnClearGpio = this.findViewById(R.id.btn_clear_gpio);
        databitSpinner = this.findViewById(R.id.spinner_data);
        btnClearGpio.setOnClickListener(this);
        btnAddGpio.setOnClickListener(this);

        imggoback.setOnClickListener(this);
        poweron.setOnClickListener(this);
        poweroff.setOnClickListener(this);
        openSerial.setOnClickListener(this);
        closeSerial.setOnClickListener(this);
        poweroff.setEnabled(false);
        closeSerial.setEnabled(false);
        ArrayAdapter<String> bAdapter = new ArrayAdapter<>(HelperActivity, android.R.layout.simple_spinner_item, ArrayBaudrate);
        bAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bSpinner.setAdapter(bAdapter);
        bSpinner
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long id) {
                        arg0.setVisibility(View.VISIBLE);
                        // "1200","9600","38400","57600","1152000","other"
                        if (bSpinner.getSelectedItem().toString().equals("other")) {
                            SettingsDialog.this.setBaudrate.setTitle(R.string.sure);
                            SettingsDialog.this.setBaudrate.show();
                        } else {
                            baudrate = Integer.parseInt(bSpinner.getSelectedItem().toString());
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }

                });
        ArrayAdapter<String> pAdapter = new ArrayAdapter<>(HelperActivity, android.R.layout.simple_spinner_item, ArraySerialPort);
        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pSpinner.setAdapter(pAdapter);
        pSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                arg0.setVisibility(View.VISIBLE);
                serialPath = SettingsDialog.this.pSpinner
                        .getSelectedItem().toString();
                if (serialPath.equals("other")) {
                    setDialog.setTitle(R.string.sure);
                    setDialog.show();
                } else {
                    serialPath = "/dev/" + SettingsDialog.this.pSpinner.getSelectedItem().toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        ArrayAdapter<String> pathAdapter = new ArrayAdapter<>(HelperActivity, android.R.layout.simple_spinner_item, ArrayPowerPath);
        pathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pathSpinner.setAdapter(pathAdapter);
        pathSpinner.setSelection(0);
        powerPath = pathSpinner.getSelectedItem().toString();
        setPower_path(powerPath);
        pathSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                // "/proc/driver/scan","/proc/driver/as3992","/proc/driver/tda3992","/proc/driver/sirf"
                arg0.setVisibility(View.VISIBLE);
                powerPath = pathSpinner.getSelectedItem().toString();
                int select = pathSpinner.getSelectedItemPosition();
                setPower_path(powerPath);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        databit_adapter = new ArrayAdapter<>(HelperActivity, android.R.layout.simple_spinner_item, Arraydatabit);
        databit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        databitSpinner.setAdapter(databit_adapter);
        databitSpinner.setSelection(0);

        databitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    dataBit = Integer.parseInt(databitSpinner
                            .getSelectedItem().toString());
                } else if (position == 1) {
                    showInputDialog();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> sAdapter = new ArrayAdapter<>(HelperActivity, android.R.layout.simple_spinner_item, ArrayStopBit);

        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSpinner.setAdapter(sAdapter);
        sSpinner
                .setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int position, long id) {
                        arg0.setVisibility(View.VISIBLE);
                        if (position == 0 && id == 0) {
                            stopbitt = 1;
                        }
                        if (position == 1 && id == 1) {
                            stopbitt = 2;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }

                });
        ArrayAdapter<String> crcAdapter = new ArrayAdapter<>(HelperActivity, android.R.layout.simple_spinner_item, ArrayCrc);
        crcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crcSpinner.setAdapter(crcAdapter);
        crcSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                arg0.setVisibility(View.VISIBLE);
                if (position == 0 && id == 0) {
                    crcNum = 0;
                }
                if (position == 1 && id == 1) {
                    crcNum = 2;
                }
                if (position == 2 && id == 2) {
                    crcNum = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        pSpinner.setSelection(0);
        bSpinner.setSelection(0);
        openSerial.requestFocus();
    }

    private int[] gpios;

    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(HelperActivity);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(HelperActivity);
        inputDialog.setTitle(R.string.dialog_databit).setView(editText);
        inputDialog.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBit = Integer.parseInt(editText.getText().toString());
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        if (v == poweron) {
            try {
                System.out.println(powerPath);
                if (tvGpioShow.getText().toString().equals("")) {
                    Toast.makeText(HelperActivity, R.string.please_gpio, Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] strs = tvGpioShow.getText().toString().split(",");
                gpios = new int[strs.length];
                for (int i = 0, len = strs.length; i < len; i++) {
                    gpios[i] = Integer.parseInt(strs[i]);
                    System.out.println(strs[i]);
                }
                setPower_path(powerPath + " gpio:"+tvGpioShow.getText().toString());
                DevCtrl = new DeviceControlSpd(powerPath, gpios);
                DevCtrl.PowerOnDevice();
                DisplayToast("open driver success  " + powerPath);
                poweroff.setEnabled(true);
                poweron.setEnabled(false);
                pathSpinner.setEnabled(false);
                powercount = 1;
                Log.d(TAG, "open driver success");
            } catch (IOException e) {
                e.printStackTrace();
                DisplayToast("open driver " + powerPath + "failed");
                Log.e(TAG, "open driver failed" + powerPath);
            }
        } else if (v == openSerial) {
            try {
                System.out.println("open_port:" + serialPath);
                HelperActivity.getmSerialPortBackup().OpenSerial(serialPath, baudrate,
                        dataBit, stopbitt, crcNum);
                DisplayToast("open " + serialPath + " by  " + baudrate
                        + " baurate success");
                Log.d(TAG, "openSerialPort");
                fd = mSerialPortBackup.getFd();
                Log.d(TAG, "open fd=" + fd);
                // 使能发送按键
                HelperActivity.sendButton.setEnabled(true);
                HelperActivity.setReadSerialTask();
                // 开始读串口
                // setReadSerialTask();
                closeSerial.setEnabled(true);
                openSerial.setEnabled(false);
                bSpinner.setEnabled(false);
                pSpinner.setEnabled(false);
                sSpinner.setEnabled(false);
                crcSpinner.setEnabled(false);
                serial = 1;
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
                DisplayToast("open " + serialPath + " by  " + baudrate + " baurate failed");
            }
        } else if (v == poweroff) {
            powerPath = pathSpinner.getSelectedItem().toString();

            try {
                if (DevCtrl != null) {
                    DevCtrl.PowerOffDevice();
                }
                Toast.makeText(mContext, "close success", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(mContext, "close faild", Toast.LENGTH_SHORT).show();
            }
            poweron.setEnabled(true);
            poweroff.setEnabled(false);
            pathSpinner.setEnabled(true);
            powercount = 0;
        } else if (v == closeSerial) {
            mSerialPortBackup.CloseSerial(fd);
            openSerial.setEnabled(true);
            closeSerial.setEnabled(false);
            bSpinner.setEnabled(true);
            pSpinner.setEnabled(true);
            sSpinner.setEnabled(true);
            crcSpinner.setEnabled(true);
            serial = 0;
            HelperActivity.sendButton.setEnabled(false);

        } else if (v == imggoback) {
            dismiss();
            HelperActivity.ShowState();
        } else if (btnAddGpio == v) {
            final EditText editText = new EditText(HelperActivity);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            AlertDialog.Builder inputDialog = new AlertDialog.Builder(HelperActivity);
            inputDialog.setTitle(R.string.input_gpio).setView(editText);
            inputDialog.setPositiveButton(R.string.ok,
                    new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvGpioShow.append(editText.getText().toString() + ",");
                        }
                    }).show();
        } else if (btnClearGpio == v) {
            tvGpioShow.setText("");
            gpios = null;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                sendBroadcastKey("down");
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                sendBroadcastKey("up");
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void sendBroadcastKey(String action) {
        Intent intent = new Intent("wrist.action." + action);
        mContext.sendBroadcast(intent);
    }

    private void DisplayToast(String str) {
        Toast toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 220);
        toast.show();
    }

    void closeDev() {
        if (powercount > 0) {
            try {
                DevCtrl.PowerOffDevice();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSerialPort(String path) {
        serialPath = "/dev/" + path;
    }

    @Override
    public void setBaurdrate(int baurdrate) {
        baudrate = baurdrate;
    }

    @Override
    public void setBaurdrateSpinner(int position) {
        pSpinner.setSelection(position);
    }

    @Override
    public void setSerialPortSpinner(int position) {
        pSpinner.setSelection(position);
    }

    void closeSerial() {
        if (serial != 0) {
            mSerialPortBackup.CloseSerial(fd);
        }
    }

    int getSerial() {
        return serial;
    }

    int getPowercount() {
        return powercount;
    }

    public int getBaudrate() {
        return baudrate;
    }

    String getSerialPath() {
        return serialPath;
    }

    public String getPower_path() {
        return powerPathshow;
    }

    public void setPower_path(String powerPath) {
        this.powerPathshow = powerPath;
    }


}