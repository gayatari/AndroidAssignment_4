package com.android.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZomatoDbHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "ZomataDb";
	private static final int DATABASE_VERSION = 1;

	public ZomatoDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			String createSql = "CREATE TABLE Cuisines (Id TEXT , "
					+ "Name TEXT );";
			Log.v("Zomato", "########Creating Cuisines: " + createSql);
			db.execSQL(createSql);
			
			createSql = null;
			createSql = "CREATE TABLE Restaurents (CuisineId TEXT, "
					+ "Name TEXT	 , "
					+ "Address TEXT , "
					+ "Cuisines TEXT, "
					+ "Rating TEXT );";
			Log.v("Zomato", "########Creating Restaurents: " + createSql);
			db.execSQL(createSql);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("Zomato", "##############db creation failed: " + e.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("Zomato", "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS");
		onCreate(db);
	}

}
