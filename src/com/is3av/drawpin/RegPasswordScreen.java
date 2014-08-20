package com.is3av.drawpin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegPasswordScreen extends Activity {
	private DrawingView dv ;   
	private Button saveButton;
	private Button clearButton;
	private TextView countText;
	private int ACCEPT = 1;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showDialog(ACCEPT);
		setContentView(R.layout.password);
		dv = (DrawingView)findViewById(R.id.drawview);
		
		saveButton = (Button)findViewById(R.id.save);
		saveButton.setOnClickListener(saveListener);
		saveButton.setText("Save this");
		handleClear();
		
		countText = (TextView)findViewById(R.id.count);
		
	}
	public void handleClear() {
		clearButton = (Button)findViewById(R.id.clear);
		clearButton.setOnClickListener(clearListener);
	}
	private OnClickListener saveListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dv.saveCanvas();
			
			
		}
	};
	
	private OnClickListener clearListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dv.clearCanvas();
			
		}
	};
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("Ok",dialogListener).setCancelable(false);
		switch(id) {
		case 1:
			builder.setMessage("Draw your pin 5 times"+"\n"+"Write a digit and click save");
			dialog  = builder.create();
			break;

		}
		return dialog;

	}

	private android.content.DialogInterface.OnClickListener dialogListener = new android.content.DialogInterface.OnClickListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			showDialog(ACCEPT);
			dv.clearCanvas();

		}
	};
}
