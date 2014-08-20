package com.is3av.drawpin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterScreen extends Activity{
	private Button signUp;
	private Button goBack;
	private EditText newUser;
	private String userN;
	private TextView warning;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		setButtons();
		setTextField();
		warning = (TextView) findViewById(R.id.warning);
		
		warning.setText("Please use a valid email ID"+"\n"+"We will need it for further communication");
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
			System.out.println("here");
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
