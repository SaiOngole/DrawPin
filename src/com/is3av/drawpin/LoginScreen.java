package com.is3av.drawpin;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginScreen extends Activity {
	public Button loginButton;
	public Button register;
	public EditText userName;
	public String userN;
	public char[] character = new char[100];
	private String result;
	private DrawingView dv;
	private int bit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		String serverURL = "http://toannv.com/setting.txt";
		ServerVariable task = new ServerVariable();
		task.execute(serverURL);
		register = (Button)findViewById(R.id.register);
		register.setOnClickListener(registerListener);
		userName = (EditText)findViewById(R.id.username);
		userN = userName.getText().toString();
		loginButton = (Button)findViewById(R.id.login);
		loginButton.setOnClickListener(loginListener);
	}
	private void returnControl(String response) {
		result = response;
		character = response.toCharArray();
		for(int i=0;i<character.length;i++) {
			System.out.println("Index is: "+ i + "Character is: "+character[i]);
		}
		

	}
	private OnClickListener loginListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(character[0]=='1') {
				Intent intent = new Intent(LoginScreen.this,AttackLogin.class);
				// pass value of pin from here
				intent.putExtra("result",result);
				Log.d("String passed",result);
				startActivity(intent);
			}
			else {
				Intent intent = new Intent(LoginScreen.this,PasswordScreen.class);
				// check for invisible pin and use a condition statement to decide the color of drawing
				intent.putExtra("result", result);
				startActivity(intent);
			}
		}


	};
	private OnClickListener registerListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent registerIntent = new Intent(LoginScreen.this,RegisterScreen.class);
			registerIntent.putExtra("result",result);
			startActivity(registerIntent);

		}
	};

	class ServerVariable extends AsyncTask<String, Void, Void> {

		private final HttpClient Client = new DefaultHttpClient();
		private String Content;
		private String Error = null;
		private ProgressDialog Dialog = new ProgressDialog(LoginScreen.this);

		char[] character = new char[100];
		String result;

		protected void onPreExecute() {
			// NOTE: You can call UI Element here.
			Dialog.setMessage("Downloading source..");
			Dialog.show();
		}

		// Call after onPreExecute method
		protected Void doInBackground(String... urls) {
			try {                                
				// Server url call by GET method
				HttpGet httpget = new HttpGet(urls[0]);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				Content = Client.execute(httpget, responseHandler);

			} catch (ClientProtocolException e) {
				Error = e.getMessage();
				cancel(true);
			} catch (IOException e) {
				Error = e.getMessage();
				cancel(true);
			}

			return null;
		}

		protected void onPostExecute(Void unused) { 
			// Close progress dialog
			Dialog.dismiss();             
			if (Error != null) {                
				result = Error;
				System.out.println(Error);                 
			} else {
				result = Content;
				returnControl(result);

			}

		}
	}
}