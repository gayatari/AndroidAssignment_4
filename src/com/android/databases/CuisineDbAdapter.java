package com.android.databases;

import java.util.ArrayList;


import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class CuisineDbAdapter extends DbAdapter {
	public String strTableName;
	public CuisineDbAdapter(Context context, String strTableName) {
		super(context, strTableName);
		Log.i("Cuisines db adapter.......", "!!!!!!!!!!");
		this.strTableName=strTableName;
		setDbName();
		setDbColumns();
	

}
	public static final String Id = "Id";
	public static final String Name = "Name";
	
	@Override
	protected void setDbName() {
		// TODO Auto-generated method stub
		this.dbColumns = new String[] { Id,Name };
		Log.i("Db Comolmn Set", dbColumns.toString());
	}
	@Override
	protected void setDbColumns() {
		// TODO Auto-generated method stub
		this.dbName = strTableName;
		Log.i("DB Name Set", dbName);
	}
	public long create(ContentValues CuisineValues) {
	      return super.create(CuisineValues);
		}
	public ArrayList<CuisinesClass> getCuisinesList(Context context) {
		Cursor CuisinesC = super.fetchAll(null, null);
		
		ArrayList<CuisinesClass> cuisinesList = new ArrayList<CuisinesClass>();
         Log.d("fetch", "fetch");
		if(CuisinesC.moveToFirst()) {
			do{
				  Log.d("fetch", "fetch");
				  
			CuisinesClass cuisines_data = new CuisinesClass();
	
			
			
			cuisines_data.setId(CuisinesC.getString(CuisinesC.getColumnIndexOrThrow(Id)));
			cuisines_data.setName(CuisinesC.getString(CuisinesC.getColumnIndex(Name)));
			
			
			Log.d("Cuisne", cuisines_data.toString());
			cuisinesList.add(cuisines_data);
			
			}while(CuisinesC.moveToNext());
		}
		CuisinesC.close();
		Log.d("cuisine data", cuisinesList.toString());
		return cuisinesList;
		
		
	}
	public void deleteAll() {
		try {
			db.beginTransaction();
			this.delete();
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

	}

}
