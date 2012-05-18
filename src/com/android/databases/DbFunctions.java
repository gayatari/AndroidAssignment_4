package com.android.databases;

import java.util.ArrayList;

import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.content.ContentValues;
import android.content.Context;



public class DbFunctions {

	

	public DbFunctions(Context context) {
		super();
	}

	// ///// Store data in Feeds model
	public void storeCuisineDataInDB(ArrayList<CuisinesClass> cuisineArray, CuisineDbAdapter cuisineDbAdapter) {
		cuisineDbAdapter.deleteAll();

		for (CuisinesClass c: cuisineArray) {
			
			ContentValues CuisineValues=new ContentValues();
			CuisineValues.put("Id", c.getId());
			CuisineValues.put("Name", c.getName());
			cuisineDbAdapter.create(CuisineValues);
			
		}

	}

public void storeRestuarentDataInDB(ArrayList<RestaurantModel> restuarentArray, RestaurentDbAdapter restuarentDbAdapter, String cuisineId) {
		
	restuarentDbAdapter.deleteAll();
     for (RestaurantModel R: restuarentArray) {
		
		ContentValues restuarentValues=new ContentValues();
		restuarentValues.put("CuisineId", cuisineId);
		restuarentValues.put("Name", R.getName());
		restuarentValues.put("Address", R.getAddress());
		restuarentValues.put("Cuisines", R.getCuisines());
		restuarentValues.put("Rating", R.getRating());
		restuarentDbAdapter.create(restuarentValues);
		
	}
		

	}
	
}