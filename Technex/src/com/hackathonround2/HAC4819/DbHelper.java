package com.hackathonround2.HAC4819;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

	public DbHelper(Context context) {
		super(context, "my.db", null, 8);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
          db.execSQL("CREATE TABLE "+TBLUsers+
        	   "( "+COLId+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
        		   COLName+" TEXT ,"+
        		   COLUsername+ " TEXT ,"+
        		   COLPassword+ " TEXT );"
        		  );	
          
	     db.execSQL("CREATE TABLE "+TBLHall+
      	   "( "+COLId+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
	           COLScoresEasy+" TEXT ,"+
	           COLScoresMed+" TEXT ,"+
	           COLScoresHard+" TEXT ,"+
      	       COLTime+" TEXT ,"+
	           COLStatus+" TEXT ,"+
      	       COLName+" TEXT ,"+
      		   COLUsername+ " TEXT ,"+
      		   COLPassword+ " TEXT );"
      		  );	
        }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
             db.execSQL("DROP TABLE IF EXISTS "+TBLUsers);
             db.execSQL("DROP TABLE IF EXISTS "+TBLHall);
             onCreate(db);
	}
	
	public String TBLUsers="table_users";
	public String COLId="_id";
	public String COLName="name";
	public String COLUsername="username";
	public String COLPassword="password";
	
	public String TBLHall="halloffame";
	public String COLScoresEasy="scoreseasy";
	public String COLScoresMed="scoresmed";
	public String COLScoresHard="scoreshard";
	public String COLStatus="status";
	public String COLTime="time";

}
