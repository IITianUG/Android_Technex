package com.hackathonround2.HAC4819;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainCategory extends Activity{
    ListView events;
    Button home,share;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		home=(Button)findViewById(R.id.bhome);
		share=(Button)findViewById(R.id.bshare);
		
		
		events=(ListView)findViewById(R.id.listevent);
		
		DataBaseHelper myDbHelper = new DataBaseHelper(this);
		try{
			myDbHelper.createDataBase();
		}
		catch(IOException ioe){
		throw new Error("Unable to create database");
		}
		try{
			myDbHelper.openDataBase();
		}
		catch(Exception e){

		}
		  final Cursor c = myDbHelper.getRows();
		   MyCAdapter bcAdapter=new MyCAdapter(MainCategory.this,c);
		   events.setAdapter(bcAdapter);
		   events.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(MainCategory.this,MainEvents.class);
				TextView name=(TextView)arg1.findViewById(R.id.name);
				String h= (String)name.getText();
				i.putExtra("category",h);
				startActivity(i);
				
			}
		});
		   
		   home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainCategory.this,HomeActivity.class);
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
				intent.putExtra(Intent.EXTRA_TEXT, "Hey, I am participating in Technex!");
				startActivity(Intent.createChooser(intent, "Share with"));
				
			}
		});
	}

	
}
