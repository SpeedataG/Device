package com.speedata.device.helper;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class DeviceControl {
	private BufferedWriter CtrlFile;
	private Context mContext;

	DeviceControl(String path, Context context) throws IOException {
		File DeviceName = new File(path);
		mContext = context;
		CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false)); // open
																			// file
	}

	void PowerOnDevice(String power_on) throws IOException // poweron
																	// barcode
																	// device
	{
		CtrlFile.write(power_on);
		CtrlFile.flush();
	}

	void PowerOffDevice(String power_off) throws IOException // poweroff
																	// barcode
																	// device
	{
		CtrlFile.write(power_off);
		CtrlFile.flush();
	}

	public void TriggerOnDevice() throws IOException // make barcode begin to
														// scan
	{
		CtrlFile.write("trig");
		CtrlFile.flush();
	}

	public void TriggerOffDevice() throws IOException // make barcode stop scan
	{
		CtrlFile.write("trigoff");
		CtrlFile.flush();
	}

	public void DeviceClose() throws IOException // close file
	{
		CtrlFile.close();
	}

	public void MTGpioOn() {
		try {
			CtrlFile.write("-wdout64 1");
			CtrlFile.flush();
			Toast.makeText(mContext, "open mtgpio driver success",
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	void MTGpioOff() {
		try {
			CtrlFile.write("-wdout64 0");
			CtrlFile.flush();
			Toast.makeText(mContext, "close mtgpio driver success",
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}