package com.hackathonround2.HAC4819;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Menux extends ListActivity{
	
	String classes[] = { "Introduction", "OurInspiration", "StructureAndRules", "ContactUs"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menux.this,
				R.layout.activity_list1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(Menux.this,MainInfo.class);
		String h=classes[position];
		i.putExtra("type",h);
		startActivity(i);

	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		return true;
	}

	
}
