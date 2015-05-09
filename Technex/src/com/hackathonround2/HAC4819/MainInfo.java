package com.hackathonround2.HAC4819;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.TextView;

public class MainInfo extends Activity {
    TextView info;
    SharedPreferences myprefs;
    String inf;
    private static final String DATABASE_TABLE1 = "technoapp";
    public static final String KEY_Event = "Event";
    public static final String KEY_Introduction = "Introduction";
	public static final String KEY_OurInspiration = "OurInspiration";
	public static final String KEY_StructureAndRules = "StructureAndRules";
	public static final String KEY_ContactUs = "ContactUs";
	String str;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_info);
		
		info=(TextView)findViewById(R.id.tvinfo);
		
		myprefs=PreferenceManager.getDefaultSharedPreferences(this);
		inf=myprefs.getString("event", "");
		
		Intent intent = getIntent();
		String type = intent.getStringExtra("type");
		
		DataBaseHelper myDbHelper = new DataBaseHelper(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		} catch (Exception e) {

		}
	
		final Cursor c = myDbHelper.getRows3(type, inf);
		if(c.moveToFirst()){
			str=c.getString(c.getColumnIndex(type));
		}
		info.setText(str);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_info, menu);
		return true;
	}

}
