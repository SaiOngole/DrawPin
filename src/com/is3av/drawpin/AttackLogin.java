package com.is3av.drawpin;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AttackLogin extends Activity {
	
	private Button loginButton;
	private Button clearButton;
	private DrawingView dv ;   
	private TextView message;
	private int count = 0;
	private char[] character = new char[20];
	private String pin = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		System.out.println("sets content");
		Intent intent = getIntent();
		String result = intent.getExtras().getString("result");
		Log.d("String from intent", result);
		character = result.toCharArray();
		for(int i=3;i<7;i++) {
			
			System.out.println("Index Attack: "+ i + "Character is: "+character[i]);
			pin += character[i];
		}
		dv = (DrawingView) findViewById(R.id.drawview);
		handleLogin();		
		handleClear();
		message = (TextView) findViewById(R.id.count);
		message.setText(" ");
		showDialog(1);
	}
	private void handleLogin() {
		loginButton = (Button)findViewById(R.id.save);
		loginButton.setText("Next");
		loginButton.setOnClickListener(loginListener);
	}
	
	private void handleClear() {
		
		clearButton = (Button) findViewById(R.id.clear);
		clearButton.setOnClickListener(clearListener);
	}
	private OnClickListener loginListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			count++;
			if(count<4) {
				try {
					dv.saveCanvas();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(count>=3) {
				loginButton.setText("Login");
			}
			if(count==4) {
			
			try {
				dv.saveCanvas();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dv.authenticate();
			}
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
			//builder.setMessage("Draw your pin"+"\n"+"One digit at a time");
			builder.setMessage("Please draw the pin " + pin);
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
			showDialog(1);
			dv.clearCanvas();

		}
	};
}

