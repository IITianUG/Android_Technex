package com.hackathonround2.HAC4819;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainEvents extends Activity {
	TextView heading;
	ListView events;
	SharedPreferences myprefs;
	SharedPreferences.Editor editor;
	Button home,share;
	String categor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		home=(Button)findViewById(R.id.bhome);
		share=(Button)findViewById(R.id.bshare);
		heading = (TextView) findViewById(R.id.tvHeading);
		myprefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Intent intent = getIntent();
		categor = intent.getStringExtra("category");
		heading.setText("Sub-Events");

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
		final Cursor c = myDbHelper.getRows1(categor);
		MyCAdapter1 bcAdapter = new MyCAdapter1(MainEvents.this, c);
		events.setAdapter(bcAdapter);
		events.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView name = (TextView) arg1.findViewById(R.id.name);
				String h = (String) name.getText();
				editor = myprefs.edit();
				editor.putString("event", h);
				editor.commit();
				Intent i = new Intent(MainEvents.this, Menux.class);
				startActivity(i);

			}
		});

		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainEvents.this, HomeActivity.class);
				startActivity(i);
				finish();

			}
		});

		share.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,
						"Hey, I am participating in "+categor+" event!");
				startActivity(Intent.createChooser(intent, "Share with"));

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_events, menu);
		return true;
	}

}
