package com.speedata.device.helper;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.speedata.device.R;

class Setbaudrate extends Dialog implements View.OnClickListener {
	/**
	 * 
	 */
	private final MyInterface interface1;
	private EditText baudrate;
	private Button ok;
	private Button cancel;
	private Context mContext;

	Setbaudrate(MyInterface mainActivity, Context context) {
		super(context);

		interface1 = mainActivity;
		mContext = context;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setbaudrate);
		baudrate = (EditText) findViewById(R.id.bb);
		ok = (Button) findViewById(R.id.ok1);
		cancel = (Button) findViewById(R.id.cancle1);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		// baudrate.setInputType()
	}

	@Override
	public void onClick(View v) {

		if (v == ok) {
			String temp_b = baudrate.getText().toString();
			if (!temp_b.equals("")) {
				interface1.setBaurdrate(Integer.parseInt(temp_b));
				dismiss();
			} else
				Toast.makeText(mContext, "can't be empty", Toast.LENGTH_SHORT).show();
		}
		if (v == cancel) {
			dismiss();
			interface1.setSerialPortSpinner(0);
		}

	}

}