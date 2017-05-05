package com.speedata.device.helper;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.serialport.SerialPort;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.speedata.device.R;

import java.io.IOException;

import static android.serialport.SerialPort.TAG;

class SettingsDialog extends Dialog implements
		View.OnClickListener, MyInterface {

	/**
	 * 
	 */
	private final HelperActivity HelperActivity;
	private Context mContext;

	private SetOtherSerialPort setDialog;
	private SetOtherPowerPath setPowerDialog;
	private Setbaudrate setBaudrate;
	private SerialPort mSerialPort;
	private int fd;

	SettingsDialog(HelperActivity HelperActivity, Context context) {
		super(context);
		this.HelperActivity = HelperActivity;
		mContext = context;
		setDialog = new SetOtherSerialPort(this, context);
		setPowerDialog = new SetOtherPowerPath(this, context);
		setBaudrate = new Setbaudrate(this, context);
		mSerialPort = HelperActivity.getmSerialPort();
		DevCtrl = HelperActivity.getDevCtrl();
		ArrayBaudrate = mContext.getResources()
				.getStringArray(R.array.baudrate);
		ArraySerialPort = mContext.getResources().getStringArray(
				R.array.serial_port);
		ArrayPowerPath = mContext.getResources().getStringArray(
				R.array.power_path);
		ArrayStopBit = mContext.getResources().getStringArray(R.array.stopbit);
		ArrayCrc = mContext.getResources().getStringArray(R.array.crc);
	}

	// private Button ok;
	private Button goback;
	private Button poweron;
	private Button poweroff;
	private Button openSerial;
	private Button closeSerial;

	private String[] ArrayBaudrate;// = { "1200", "9600", "38400", "57600", "115200",
							// "other"
							// };
	private String[] ArraySerialPort;// = { "ttyG0", "ttyO0", "ttyO1", "ttyO2", "ttyO3",
	// "eser1", "eser2", "ttyG1", "ttyMT0", "ttyMT1", "ttyMT2", "ttyG2",
	// "other" };
	private final String[] ArrayPowerPath;// = { "/proc/driver/scan",
	// "/proc/driver/as3992", "/proc/driver/tda8029", "/proc/driver/sirf",
	// "/proc/driver/printer", "/proc/geomobile/lf", "MT GPIO" };
	private final String[] ArrayStopBit;// = { "1", "2" };
	private final String[] ArrayCrc;// = { "NONE", "ODD", "EVEN" };
	private Spinner b_Spinner;
	private Spinner p_Spinner;
	private Spinner s_Spinner;
	private Spinner crc_Spinner;
	private int baudrate = 0;
	private String serial_path = "";
	private String power_path;

	private Spinner path_Spinner;

	private int stopbitt;
	private int crc_num = 0;
	private int powercount = 0;
	private int serial = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		goback = (Button) this.findViewById(R.id.backto);
		poweron = (Button) this.findViewById(R.id.poweron);
		openSerial = (Button) this.findViewById(R.id.openss);
		closeSerial = (Button) this.findViewById(R.id.closess);
		poweroff = (Button) this.findViewById(R.id.poweroff);
		b_Spinner = (Spinner) this.findViewById(R.id.spinner2);
		p_Spinner = (Spinner) this.findViewById(R.id.spinner1);
		path_Spinner = (Spinner) this.findViewById(R.id.pathspinner);
		s_Spinner = (Spinner) this.findViewById(R.id.spinner3);
		crc_Spinner = (Spinner) this.findViewById(R.id.spinner4);
		goback.setOnClickListener(this);
		poweron.setOnClickListener(this);
		poweroff.setOnClickListener(this);
		openSerial.setOnClickListener(this);
		closeSerial.setOnClickListener(this);
		poweroff.setEnabled(false);
		closeSerial.setEnabled(false);

		ArrayAdapter<String> bAdapter = new ArrayAdapter<>(HelperActivity,
				android.R.layout.simple_spinner_item, ArrayBaudrate);

		bAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		b_Spinner.setAdapter(bAdapter);
		b_Spinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {
						arg0.setVisibility(View.VISIBLE);
						// "1200","9600","38400","57600","1152000","other"
						if (b_Spinner.getSelectedItem().toString()
								.equals("other")) {
							SettingsDialog.this.setBaudrate
									.setTitle(R.string.sure);
							SettingsDialog.this.setBaudrate.show();
						} else {
							baudrate = Integer.parseInt(b_Spinner
									.getSelectedItem().toString());
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		ArrayAdapter<String> pAdapter = new ArrayAdapter<>(HelperActivity,
				android.R.layout.simple_spinner_item, ArraySerialPort);
		pAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		p_Spinner.setAdapter(pAdapter);
		p_Spinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {
						arg0.setVisibility(View.VISIBLE);
						serial_path = SettingsDialog.this.p_Spinner
								.getSelectedItem().toString();
						if (serial_path.equals("other")) {
							setDialog.setTitle(R.string.sure);
							setDialog.show();
						} else {
							serial_path = "/dev/"
									+ SettingsDialog.this.p_Spinner
											.getSelectedItem().toString();
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		ArrayAdapter<String> pathAdapter = new ArrayAdapter<>(HelperActivity,
				android.R.layout.simple_spinner_item, ArrayPowerPath);
		pathAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		path_Spinner.setAdapter(pathAdapter);
		path_Spinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {
						// "/proc/driver/scan","/proc/driver/as3992","/proc/driver/tda3992","/proc/driver/sirf"
						arg0.setVisibility(View.VISIBLE);
						power_path = path_Spinner.getSelectedItem().toString();
						int select = path_Spinner.getSelectedItemPosition();
						switch (select) {
						case 0:
							actual_path = "/sys/class/misc/mtgpio/pin";
							power_on_write = "-wdout64 1";
							power_off_write = "-wdout64 0";
							break;
						case 1:
							actual_path = "/sys/class/misc/mtgpio/pin";
							power_on_write = "-wdout106 1";
							power_off_write = "-wdout106 0";
							break;

						default:
							actual_path = power_path;
							power_on_write = "on";
							power_off_write = "off";
							break;
						}

						if (power_path.equals("other")) {
							setPowerDialog.setTitle(R.string.sure);
							setPowerDialog.show();
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		ArrayAdapter<String> sAdapter = new ArrayAdapter<>(HelperActivity,
				android.R.layout.simple_spinner_item, ArrayStopBit);
		// Log.w(TAG,"WARN");
		sAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s_Spinner.setAdapter(sAdapter);
		s_Spinner
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
		ArrayAdapter<String> crcAdapter = new ArrayAdapter<>(HelperActivity,
				android.R.layout.simple_spinner_item, ArrayCrc);
		// Log.w(TAG,"WARN");
		crcAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		crc_Spinner.setAdapter(crcAdapter);
		crc_Spinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {
						arg0.setVisibility(View.VISIBLE);
						if (position == 0 && id == 0) {
							crc_num = 0;
						}
						if (position == 1 && id == 1) {
							crc_num = 2; // odd ��У��
						}
						if (position == 2 && id == 2) {
							crc_num = 1; // even żУ��
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		p_Spinner.setSelection(0);
		b_Spinner.setSelection(0);
		openSerial.requestFocus();
	}

	private DeviceControl DevCtrl;


	private String power_off_write = "";
	private String power_on_write = "";
	private String actual_path = "";

	@Override
	public void onClick(View v) {

		if (v == poweron) {

			try {

				System.out.println(actual_path);
				DevCtrl = new DeviceControl(actual_path, HelperActivity);
				DevCtrl.PowerOnDevice(power_on_write);
				DisplayToast("open driver success  " + actual_path);
				// }
				poweroff.setEnabled(true);
				poweron.setEnabled(false);
				path_Spinner.setEnabled(false);
				powercount = 1;
				Log.d(TAG, "open driver success");
			} catch (IOException e) {
				e.printStackTrace();
				DisplayToast("open driver " + actual_path + "failed");
				Log.e(TAG, "open driver failed" + power_path);
				// return;
			}
			// dismiss();
		}
		if (v == openSerial) {
			try {
				System.out.println("open_port:" + serial_path);
				HelperActivity.getmSerialPort().OpenSerial(serial_path, baudrate,
						8, stopbitt, crc_num);
				// System.out.println(Getstopbit() + "!!!!!!!ceshi");
				DisplayToast("open " + serial_path + " by  " + baudrate
						+ " baurate success");
				Log.d(TAG, "openSerialPort");
				fd = mSerialPort.getFd();
				Log.d(TAG, "open fd=" + fd);
				// 使能发送按键
				HelperActivity.sendButton.setEnabled(true);
				HelperActivity.setReadSerialTask();
				// 开始读串口
				// setReadSerialTask();
				closeSerial.setEnabled(true);
				openSerial.setEnabled(false);
				b_Spinner.setEnabled(false);
				p_Spinner.setEnabled(false);
				s_Spinner.setEnabled(false);
				crc_Spinner.setEnabled(false);
				serial = 1;
			} catch (SecurityException | IOException e) {

				DisplayToast("open " + serial_path + " by  " + baudrate
						+ " baurate failed");

				e.printStackTrace();
			}
		}
		if (v == poweroff) {
			power_path = path_Spinner.getSelectedItem().toString();
			if (power_path.equals("MT GPIO")) {
				System.out.println("MT GPIO off");
				DevCtrl.MTGpioOff();
			} else {
				try {
					DevCtrl.PowerOffDevice(power_off_write);
					Toast.makeText(mContext, "close success",
							Toast.LENGTH_SHORT).show();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			poweron.setEnabled(true);
			poweroff.setEnabled(false);
			path_Spinner.setEnabled(true);
			powercount = 0;
		}

		if (v == closeSerial) {
			mSerialPort.CloseSerial(fd);
			openSerial.setEnabled(true);
			closeSerial.setEnabled(false);
			b_Spinner.setEnabled(true);
			p_Spinner.setEnabled(true);
			s_Spinner.setEnabled(true);
			crc_Spinner.setEnabled(true);
			serial = 0;
			HelperActivity.sendButton.setEnabled(false);

		}
		if (v == goback) {
			dismiss();
			HelperActivity.ShowState();
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
				DevCtrl.PowerOffDevice(power_off_write);
				System.out.println("power_off_write" + power_off_write);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void setSerialPort(String path) {

		serial_path = "/dev/" + path;
	}

	@Override
	public void setBaurdrate(int baurdrate) {

		baudrate = baurdrate;
	}

	@Override
	public void setBaurdrateSpinner(int position) {

		p_Spinner.setSelection(position);
	}

	@Override
	public void setSerialPortSpinner(int position) {

		p_Spinner.setSelection(position);
	}

	void closeSerial() {

		if (serial != 0) {
			mSerialPort.CloseSerial(fd);
		}
	}

	int getSerial() {
		return serial;
	}

	int getPowercount() {
		return powercount;
	}

	int getBaudrate() {
		return baudrate;
	}

	String getSerial_path() {
		return serial_path;
	}

	String getPower_path() {
		return actual_path + " " + power_on_write;
	}


	@Override
	public void setPower(String poweron, String poweroff) {

		this.power_off_write = poweroff;
		this.power_on_write = poweron;
		this.actual_path = "/sys/class/misc/mtgpio/pin";
	}

	@Override
	public void setPowerSpinner(int position) {

		path_Spinner.setSelection(position);
	}

}