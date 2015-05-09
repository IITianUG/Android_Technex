package com.hackathonround2.HAC4819;

import com.hackathonround2.HAC4819.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class RegisterActivity extends Activity {
	
	EditText name,username,pass,conf_pass;
	Button btnregister;
	TextView btnLogin;
	String sname,susername,spass,confpass;
	AlertDialogManager alert=new AlertDialogManager();
	SharedPreferences myprefs;
	SharedPreferences.Editor editor;
	DbHelper dbHelper;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		//initializing db
		//dbHelper=new DbHelper(getApplicationContext());
		
		
		myprefs=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		name=(EditText)findViewById(R.id.Name);
		username=(EditText)findViewById(R.id.username);
		pass=(EditText)findViewById(R.id.password);
		conf_pass=(EditText)findViewById(R.id.conf_password);
		btnregister=(Button)findViewById(R.id.btnregister);
		btnLogin=(TextView)findViewById(R.id.btnlogin);
		
		btnregister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sname=name.getText().toString();
				susername=username.getText().toString();
				spass=pass.getText().toString();
				confpass=conf_pass.getText().toString();
				
				if(!sname.contentEquals("")){
					//dialog for name
					if(!susername.contentEquals(""))
					{
					  if(!spass.contentEquals(""))
					  {
						if(confpass.contentEquals(spass))
						{
							int res=DbMethods.SaveUser(getApplicationContext(), sname, susername, spass);
							
							if(res==0){
								//already a user
								Toast.makeText(getApplicationContext(), "Username not available!!!", Toast.LENGTH_SHORT).show();
								pass.setText("");
								conf_pass.setText("");
							}else{
								//new user
								editor=myprefs.edit();
								editor.putString("name", sname);
								editor.putString("username", susername);
								editor.putInt("loggedin",1);
								editor.commit();
								
								
								//goto home activity
								Intent calllogin=new Intent(RegisterActivity.this,HomeActivity.class);
								startActivity(calllogin);
								finish();
								
							}
							
							
							
							
						}else{
							alert.showAlertDialog(RegisterActivity.this, "Passwords not matching", "please check your passwords and try again.", false);
							
						}
					  }else{
						  alert.showAlertDialog(RegisterActivity.this, "Password cannot be empty", "create a password.", false);
							
					  }
					  
					}else{
						alert.showAlertDialog(RegisterActivity.this, "Username cannot be empty", "please fill up username", false);
						
					}
				}else{
					//name empty
					alert.showAlertDialog(RegisterActivity.this, "Name cannot be empty", "please fill up your name", false);
				}
				
			}
		});
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent calllogin=new Intent(RegisterActivity.this,LoginActivity.class);
				startActivity(calllogin);
				finish();
				
			}
		});
		
	}

	
}
