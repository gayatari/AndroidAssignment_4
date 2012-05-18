package com.android.databases;

import java.util.ArrayList;


import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class RestaurentDbAdapter extends DbAdapter{

	public String strTableName;
	public RestaurentDbAdapter(Context context, String strTableName) {
		super(context, strTableName);
		Log.i("Restaurent db adapter.......", "!!!!!!!!!!");
		this.strTableName=strTableName;
		setDbName();
		setDbColumns();
	

}
	public static final String CuisineId = "CuisineId";
	public static final String Name = "Name";
	public static final String Address = "Address";
	public static final String Cuisines = "Cuisines";
	public static final String Rating = "Rating";
	@Override
	protected void setDbName() {
		// TODO Auto-generated method stub
		this.dbColumns = new String[] { CuisineId,Name,Address,Cuisines,Rating };
		Log.i("Db Comolmn Set", dbColumns.toString());
	}
	@Override
	protected void setDbColumns() {
		// TODO Auto-generated method stub
		this.dbName = strTableName;
		Log.i("DB Name Set", dbName);
	}
	public long create(ContentValues victoriesValues) {
	     Log.d("VDb", "Vdb");
			return super.create(victoriesValues);
		}
	public ArrayList<RestaurantModel> getRestarentsList(Context context) {
		Cursor RestuarentC = this.fetchAll(null, null);
					
			ArrayList<RestaurantModel> restuarentList = new ArrayList<RestaurantModel>();
	         Log.d("fetch", "fetch");
			if(RestuarentC.moveToFirst()) {
				do{
					  Log.d("fetch", "fetch");
					  RestaurantModel restuarent_data = new RestaurantModel();
					 restuarent_data.setName(RestuarentC.getString(RestuarentC.getColumnIndex(Name)));
					 restuarent_data.setAddress(RestuarentC.getString(RestuarentC.getColumnIndex(Address)));
					 restuarent_data.setCuisines(RestuarentC.getString(RestuarentC.getColumnIndex(Cuisines)));
					 restuarent_data.setRating(RestuarentC.getString(RestuarentC.getColumnIndex(Rating)));
				Log.d("Cuisne", restuarent_data.toString());
				restuarentList.add(restuarent_data);
				
				}while(RestuarentC.moveToNext());
			}
			RestuarentC.close();
			Log.d("cuisine data", restuarentList.toString());
			
		
		return restuarentList;
		
	}
	public ArrayList<RestaurantModel> getRestuarents(String Cuisine_ID, Context context) {
		Cursor RestuarentC = this.fetchAll(
				"CuisineId =" +Cuisine_ID, null);
		ArrayList<RestaurantModel> restuarentList = new ArrayList<RestaurantModel>();
        Log.d("fetch", "fetch");
		if(RestuarentC.moveToFirst()) {
			do{
				  Log.d("fetch", "fetch");
				  RestaurantModel restuarent_data = new RestaurantModel();
				 restuarent_data.setName(RestuarentC.getString(RestuarentC.getColumnIndex(Name)));
				 restuarent_data.setAddress(RestuarentC.getString(RestuarentC.getColumnIndex(Address)));
				 restuarent_data.setCuisines(RestuarentC.getString(RestuarentC.getColumnIndex(Cuisines)));
				 restuarent_data.setRating(RestuarentC.getString(RestuarentC.getColumnIndex(Rating)));
			Log.d("Cuisne", restuarent_data.toString());
			restuarentList.add(restuarent_data);
			
			}while(RestuarentC.moveToNext());
		}
		RestuarentC.close();
		Log.d("cuisine data", restuarentList.toString());
		return restuarentList;
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
