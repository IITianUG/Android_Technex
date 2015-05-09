package com.hackathonround2.HAC4819;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbMethods {
	
	
	
	//crud
	//return type cursor
	static public int SaveUser(Context context,String name,String username,String password){
		DbHelper dbHelper=new DbHelper(context);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		
		values.put(COLName, name);
		values.put(COLUsername, username);
		values.put(COLPassword, password);
		int res=-1;
		
		Cursor cursor;
		cursor=db.rawQuery("SELECT * FROM "+TBLUsers+ " WHERE "+COLUsername+" =?", new String[]{username});
		if(cursor.getCount()>0){
			//update
			res=0;
		}else{
			//insert
			Long id=db.insert(TBLUsers, null, values);
			Log.d("db", id+" INSERTED");
			res=1;
		}
	
		
		db.close();
	
		return res;
	}
	
	static public int SaveScore(Context context,String name,String username,String score,int x){
		DbHelper dbHelper=new DbHelper(context);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		switch(x)
		{
		case 1:
			ContentValues values=new ContentValues();
			values.put(COLName, name);
			values.put(COLUsername, username);
			values.put(COLScoresEasy, score);

		    
			
			Cursor cursor1;
			cursor1=db.rawQuery("SELECT * FROM "+TBLHall+ " WHERE "+COLUsername+" =?", new String[]{username});
			if(cursor1.getCount()>0){
				//update
				cursor1=db.rawQuery("SELECT * FROM "+TBLHall+ " WHERE "+COLUsername+" =?", new String[]{username});
				if(cursor1.moveToFirst()){
					do{
					String sscore=cursor1.getString(cursor1.getColumnIndex(COLScoresEasy));
					String sscore2=cursor1.getString(cursor1.getColumnIndex(COLScoresMed));
					String sscore3=cursor1.getString(cursor1.getColumnIndex(COLScoresHard));
					int prevscore=Integer.parseInt(sscore);
					int currscore=Integer.parseInt(score);
					if(currscore > prevscore){
						values.put(COLScoresMed, sscore2);
						values.put(COLScoresHard, sscore3);
						int res=db.update(TBLHall, values, COLUsername+" =? ",new String[]{username});
						Log.d("db", res+" UPDATED");
					}
					}while(cursor1.moveToNext());
				}
				
			}else{
				//insert
				values.put(COLScoresMed, 0);
				values.put(COLScoresHard, 0);
				Long id=db.insert(TBLHall, null, values);
				Log.d("db", id+" INSERTED");
			}
			break;
		case 2:
			ContentValues values2=new ContentValues();
			values2.put(COLName, name);
			values2.put(COLUsername, username);
			values2.put(COLScoresMed, score);
		    
			
			Cursor cursor;
			cursor=db.rawQuery("SELECT * FROM "+TBLHall+ " WHERE "+COLUsername+" =?", new String[]{username});
			if(cursor.getCount()>0){
				//update
				cursor=db.rawQuery("SELECT * FROM "+TBLHall+ " WHERE "+COLUsername+" =?", new String[]{username});
				if(cursor.moveToFirst()){
					do{
					String sscore=cursor.getString(cursor.getColumnIndex(COLScoresMed));
					String sscore2=cursor.getString(cursor.getColumnIndex(COLScoresEasy));
					String sscore3=cursor.getString(cursor.getColumnIndex(COLScoresHard));
					int prevscore=Integer.parseInt(sscore);
					int currscore=Integer.parseInt(score);
					if(currscore > prevscore){
						values2.put(COLScoresEasy, sscore2);
						values2.put(COLScoresHard, sscore3);
						int res=db.update(TBLHall, values2, COLUsername+" =? ",new String[]{username});
						Log.d("db", res+" UPDATED");
					}
					}while(cursor.moveToNext());
				}
				
			}else{
				//insert
				values2.put(COLScoresEasy, 0);
				values2.put(COLScoresHard, 0);
				Long id=db.insert(TBLHall, null, values2);
				Log.d("db", id+" INSERTED");
			}
			break;
		case 3:
			ContentValues values3=new ContentValues();
			values3.put(COLName, name);
			values3.put(COLUsername, username);
			values3.put(COLScoresHard, score);
			
	
			Cursor cursor3;
			cursor3=db.rawQuery("SELECT * FROM "+TBLHall+ " WHERE "+COLUsername+" =?", new String[]{username});
			if(cursor3.getCount()>0){
				//update
				cursor3=db.rawQuery("SELECT * FROM "+TBLHall+ " WHERE "+COLUsername+" =?", new String[]{username});
				if(cursor3.moveToFirst()){
					do{
					String sscore=cursor3.getString(cursor3.getColumnIndex(COLScoresHard));
					String sscore2=cursor3.getString(cursor3.getColumnIndex(COLScoresEasy));
					String sscore3=cursor3.getString(cursor3.getColumnIndex(COLScoresMed));
					int prevscore=Integer.parseInt(sscore);
					int currscore=Integer.parseInt(score);
					if(currscore > prevscore){
						values3.put(COLScoresEasy, sscore2);
						values3.put(COLScoresMed, sscore3);
						int res=db.update(TBLHall, values3, COLUsername+" =? ",new String[]{username});
						Log.d("db", res+" UPDATED");
					}
					}while(cursor3.moveToNext());
				}
				
			}else{
				//insert
				values3.put(COLScoresEasy, 0);
				values3.put(COLScoresMed, 0);
				Long id=db.insert(TBLHall, null, values3);
				Log.d("db", id+" INSERTED");
			}
			break;
		}
	    
		
		db.close();
		
		//db.execSQL("INSERT INTO "+TBLUsers+ " ( "+COLName+" , "+COLUsername+" , "+COLPassword+") VALUES ( "+name+" , "+username+" , "+password+" );");
		
		return 1;
	}
	
	public  static String recoverPassword(Context context,String email){
	       DbHelper dbHelper;
	       SQLiteDatabase db;
	       dbHelper=new DbHelper(context);
	       db=dbHelper.getReadableDatabase();
	       Cursor cursor;
	       String pass="";
	       cursor= db.rawQuery("SELECT "+COLPassword +" FROM "+TBLUsers+" WHERE "+COLUsername+" =?",new String[]{email});
	      if(cursor.moveToFirst()){
	          do{
	            pass=  cursor.getString(cursor.getColumnIndex(COLPassword));
	          }while (cursor.moveToNext());
	      }
	       cursor.close();
	       return pass;
	   }
	
	
	static public String TBLUsers="table_users";
	static public String COLId="_id";
	static public String COLName="name";
	static public String COLUsername="username";
	static public String COLPassword="password";
	
	static public String TBLHall="halloffame";
	
	static public String COLScoresEasy="scoreseasy";
	static public String COLScoresMed="scoresmed";
	static public String COLScoresHard="scoreshard";
	static public String COLStatus="status";
	static public String COLTime="time";
	
	
	public static String checkUser(Context context, String username,
			String password) {
		
		DbHelper dbHelper=new DbHelper(context);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		// TODO Auto-generated method stub
		String name="";
		//Cursor cursor=db.rawQuery("SELECT * FROM "+TBLUsers+" WHERE "+COLUsername+" =? AND "+COLPassword+" =?", new String[] { username,password });
		Cursor cursor=db.rawQuery("SELECT * FROM "+TBLUsers+" WHERE "+COLUsername+" =? AND "+COLPassword+ " =? ", new String[]{username,password});
		//Cursor cursor=db.query(TBLUsers,new String[]{COLUsername,COLPassword},COLUsername+" =? AND "+COLPassword+ " =?", new String[]{username,password}, null, null, null);
		Log.d("db", cursor.getCount()+"");
		if(cursor.getCount()>0){
			if(cursor.moveToFirst()){
				do{
				    
				name=cursor.getString(cursor.getColumnIndex(COLName));	
				}while(cursor.moveToNext());
			}
			
		}else{
			name="";
		}
		cursor.close();
		db.close();
		return name;
		
	}
	public static Cursor queryHall(Context context,int x){
		DbHelper dbHelper=new DbHelper(context);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		Cursor cursor=null;
		// TODO Auto-generated method stub
		switch(x)
		{
		case 1:
			//Cursor cursor=db.rawQuery("SELECT * FROM "+TBLUsers+" WHERE "+COLUsername+" =? AND "+COLPassword+" =?", new String[] { username,password });
			cursor=db.rawQuery("SELECT * FROM "+TBLHall+" ORDER BY "+COLScoresEasy+" DESC",null);
			break;
		case 2:
			
			//Cursor cursor=db.rawQuery("SELECT * FROM "+TBLUsers+" WHERE "+COLUsername+" =? AND "+COLPassword+" =?", new String[] { username,password });
			cursor=db.rawQuery("SELECT * FROM "+TBLHall+" ORDER BY "+COLScoresMed+" DESC",null);
			break;
		case 3:
			
			//Cursor cursor=db.rawQuery("SELECT * FROM "+TBLUsers+" WHERE "+COLUsername+" =? AND "+COLPassword+" =?", new String[] { username,password });
			cursor=db.rawQuery("SELECT * FROM "+TBLHall+" ORDER BY "+COLScoresHard+" DESC",null);
			break;
		}
		return cursor;
		
	}

	

	

}
