package com.is3av.drawpin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterScreen extends Activity{
	private Button signUp;
	private Button goBack;
	private EditText newUser;
	private String userN;
	
	private char[] character = new char[20];
	private char invisibleBit;
	Spinner genderSpinner;
	Spinner ageSpinner;
	private String gender;
	private String age;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		Intent intent = getIntent();
		String temp = intent.getExtras().getString("result");
		character = temp.toCharArray();
		invisibleBit = character[9];
		setButtons();
		setTextField();
		genderSpinner = (Spinner)findViewById(R.id.genderSpinner);
		gender = genderSpinner.getSelectedItem().toString();
		ageSpinner = (Spinner)findViewById(R.id.ageSpinner);
		age = ageSpinner.getSelectedItem().toString();
	}
	private void setButtons() {
		signUp = (Button)findViewById(R.id.signUp);
		signUp.setOnClickListener(signuplist);
		goBack = (Button)findViewById(R.id.goBack);
		goBack.setOnClickListener(gobacklist);
	}
	private void setTextField() {
		newUser = (EditText)findViewById(R.id.userNew);
		userN = newUser.getText().toString();
	}
	private OnClickListener signuplist = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent signupIntent = new Intent(RegisterScreen.this,RegPasswordScreen.class);
			signupIntent.putExtra("invisible", character[9]);
			startActivity(signupIntent);
			
		}
	};
	private OnClickListener gobacklist = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent gobackIntent = new Intent(RegisterScreen.this,LoginScreen.class);
			startActivity(gobackIntent);
			
		}
	};
}
