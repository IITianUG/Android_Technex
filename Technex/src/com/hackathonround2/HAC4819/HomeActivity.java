package com.hackathonround2.HAC4819;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends Activity {
	Button logout,awe;
	TextView awesome;
	SharedPreferences myprefs;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		logout = (Button) findViewById(R.id.btnlogout);
		awe = (Button)findViewById(R.id.btnawesome);
		awesome = (TextView) findViewById(R.id.tvAwesome);
		
		myprefs=PreferenceManager.getDefaultSharedPreferences(this);

		logout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor = myprefs.edit();
				editor.putInt("loggedin", 0);
				editor.putString("username", "");
				editor.putString("name", "");
				editor.commit();
				// Toast.makeText(getApplicationContext(), "login called",
				// Toast.LENGTH_SHORT).show();
				Intent calllogin = new Intent(HomeActivity.this,
						LoginActivity.class);
				startActivity(calllogin);
				HomeActivity.this.finish();

			}

		});
		
		
		awesome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeActivity.this,MainCategory.class);
				startActivity(i);
			}
		});
		
		awe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeActivity.this,MainCategory.class);
				startActivity(i);
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
