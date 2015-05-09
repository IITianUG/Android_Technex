package com.hackathonround2.HAC4819;

import com.hackathonround2.HAC4819.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	EditText etusername,etpassword,etforgot;
	Button btnlogin,recover;
	String username,password;
	TextView btnregister,forgotpass;
	SharedPreferences myprefs;
	SharedPreferences.Editor editor;
	DbHelper dbHelper;
	String passrec;
	SQLiteDatabase db;
	String name,returned;
	AlertDialogManager alert=new AlertDialogManager();
	int login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//dbHelper=new DbHelper(getApplicationContext());
		//db=dbHelper.getWritableDatabase();
		
		etusername=(EditText)findViewById(R.id.username);
		etpassword=(EditText)findViewById(R.id.password);
		etforgot=(EditText)findViewById(R.id.etforgotpass);
		btnlogin=(Button)findViewById(R.id.btnlogin);
		recover=(Button)findViewById(R.id.recoverpass);
		btnregister=(TextView)findViewById(R.id.btnregister);
		forgotpass=(TextView)findViewById(R.id.forgotpass);
		
		myprefs=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		if( myprefs.getInt("loggedin",-1)==-1){
			editor=myprefs.edit();
			editor.putInt("loggedin",0);
			editor.commit();
		}
		
		if(myprefs.getInt("loggedin",0)==0){
			//no user is logged in let the activity run
			
			
			
		}else{
			Intent callhome=new Intent(this,HomeActivity.class);
			startActivity(callhome);
			finish();
			
		}
		
		forgotpass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etforgot.setVisibility(View.VISIBLE);
				recover.setVisibility(View.VISIBLE);
				
			}
		});
		
		recover.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passrec=etforgot.getText().toString();
				returned= DbMethods.recoverPassword(getApplicationContext(),passrec);
				if(returned!="")
			    forgotpass.setText("Your Password is: " + returned);
				else
					forgotpass.setText("You are not registered!!");
				etforgot.setVisibility(View.INVISIBLE);
				recover.setVisibility(View.INVISIBLE);
				
					
				
			}
		});
		
		
		btnlogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username=etusername.getText().toString();
				password=etpassword.getText().toString();
				
				if(!username.contentEquals("")&& !password.contentEquals("")){
					//check credentials
					//db=dbHelper.getWritableDatabase();
					name=DbMethods.checkUser(getApplicationContext(), username, password);
					//db.close();
					
					if(name.contentEquals("")){
						//user not registered
						//show alert dialog
						Toast.makeText(getApplicationContext(), "not registered", Toast.LENGTH_SHORT).show();
						//alert.showAlertDialog(getApplicationContext(), "You are not registered", "Please register yourself with us", false);
						
					}else{
						//user registered
						//save into shdpfs and call homeactivity
						editor=myprefs.edit();
						editor.putInt("loggedin",1);
						editor.putString("name",name);
						editor.putString("username", username);
						editor.commit();
						Intent callhome=new Intent(LoginActivity.this,HomeActivity.class);
						startActivity(callhome);
						finish();
					}
					
					
				}else{
					Toast.makeText(getApplicationContext(),"Fields can't be empty!!",Toast.LENGTH_SHORT).show();
					
				}
				
				
			}
		});
		
		btnregister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callregister=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(callregister);
				finish();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
