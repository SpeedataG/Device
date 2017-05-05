package com.speedata.device.helper;

interface MyInterface {
	void setSerialPort(String path);

	void setBaurdrate(int baurdrate);

	void setBaurdrateSpinner(int position);

	void setSerialPortSpinner(int position);

	void setPower(String poweron, String poweroff);

	void setPowerSpinner(int position);
}