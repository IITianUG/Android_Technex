package com.hackathonround2.HAC4819;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyCAdapter2 extends CursorAdapter {
	public static final String KEY_ROWID = "ID";	// Name of the Columns
	public static final String KEY_Category = "Category";
	public static final String KEY_Event = "Event";
	public static final String KEY_Introduction = "Introduction";
	public static final String KEY_OurInspiration = "OurInspiration";
	public static final String KEY_StructureAndRules = "StructureAndRules";
	public static final String KEY_ContactUs = "ContactUs";

	public MyCAdapter2(Context context, Cursor c) {
		super(context, c);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		// TODO Auto-generated method stub
		String category=cursor.getString(cursor.getColumnIndex(KEY_Event));

		TextView name=(TextView)view.findViewById(R.id.name);
		name.setText(category);
		
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflator=LayoutInflater.from(parent.getContext());
		View view=inflator.inflate(R.layout.list_layout, parent, false);
		
		return view;
	}

}
