package com.hackathonround2.HAC4819;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class MainOptions extends Activity {
	TextView heading;
    ListView events;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		heading = (TextView) findViewById(R.id.tvHeading);
		Intent intent = getIntent();
		String even = intent.getStringExtra("event");
		heading.setText("Options");

		events = (ListView) findViewById(R.id.listevent);

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
		final Cursor c = myDbHelper.getRows2(even);
		MyCAdapter2 bcAdapter = new MyCAdapter2(MainOptions.this, c);
		events.setAdapter(bcAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_events, menu);
		return true;
	}

}
