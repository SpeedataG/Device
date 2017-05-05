package com.speedata.device.helper;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.speedata.device.R;

class SetOtherSerialPort extends Dialog implements View.OnClickListener {

	/**
	 * 
	 */
	private final MyInterface interface1;
	private EditText eport;

	private Button ok;
	private Button cancel;
	private Context mContext;

	SetOtherSerialPort(MyInterface interface1, Context context) {
		super(context);

		this.interface1 = interface1;
		mContext = context;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		eport = (EditText) findViewById(R.id.port);
		ok = (Button) findViewById(R.id.ok);
		cancel = (Button) findViewById(R.id.cancel);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if (v == ok) {
			String ss = eport.getText().toString();
			if (!ss.equals("")) {
				interface1.setSerialPort(ss);
				dismiss();
			} else
				Toast.makeText(mContext, "can't be empty", Toast.LENGTH_SHORT).show();
		} else if (v == cancel) {
			dismiss();
			// SetDialog.p_Spinner.setAdapter(SetDialog.p_adapter);
			interface1.setBaurdrateSpinner(0);
		}

	}

}