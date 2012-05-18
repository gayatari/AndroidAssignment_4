package com.android.ZomatoApplication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.android.MyAdapters.CuisinesAdapter;
import com.android.MyAdapters.RestaurantAdapter;
import com.android.Parsers.CuisinesParser;
import com.android.Parsers.RestaurantParser;
import com.android.databases.CuisineDbAdapter;
import com.android.databases.DbFunctions;
import com.android.databases.RestaurentDbAdapter;
import com.android.helpers.AppStatus;
import com.android.helpers.Constants;
import com.android.restClient.RestClient;
import com.android.tasks.CuisineTask;
import com.android.tasks.RestuarentTask;

import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RestaurantActivity extends Activity {
	String cuisineId,cityId;
	ArrayList<RestaurantModel> restaurantsArray;
	ListView list;
	AppStatus appStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurants);
		Intent intent=getIntent();
		cuisineId=(String)intent.getSerializableExtra("cuisineId");
		cityId=(String)intent.getSerializableExtra("cityId");
		getRestaurantsInfo();
		
	}
	
public void getRestaurantsInfo() {
		
	appStatus = AppStatus.getInstance(this);
	if (appStatus.isOnline(RestaurantActivity.this)) {
		Log.v("SPLASH_SCREEN", "App is online");
		if (appStatus.isRegistered()) {
			
			RestuarentTask mrestuarentTask=new RestuarentTask(RestaurantActivity.this);
			mrestuarentTask.execute(cuisineId,cityId);
		}
	}else
	{
		displayRestaurentsWhenOffline();
	}
				
		
	}
public void getRestaurantsInfo(ArrayList<RestaurantModel> restaurantsArray){
	
	RestaurentDbAdapter restaurentDbAdapter=new RestaurentDbAdapter(RestaurantActivity.this, Constants.strRestaurentsTableName);
	DbFunctions db=new DbFunctions(getApplicationContext());
	restaurentDbAdapter.deleteAll();
	db.storeRestuarentDataInDB(restaurantsArray, restaurentDbAdapter, cuisineId);
	displayRestaurents(restaurantsArray);
	
  }

public void displayRestaurents(ArrayList<RestaurantModel> restaurantsArray2) {
	// TODO Auto-generated method stub
	list=(ListView)findViewById(R.id.list);
	RestaurantAdapter adapter=new RestaurantAdapter(RestaurantActivity.this, restaurantsArray2);
	list.setAdapter(adapter);
}

public void displayRestaurentsWhenOffline() {
	// TODO Auto-generated method stub
	
	Intent intent=getIntent();
	cuisineId=(String)intent.getSerializableExtra("cuisineId");
	cityId=(String)intent.getSerializableExtra("cityId");
	RestaurentDbAdapter restaurentDbAdapter=new RestaurentDbAdapter(RestaurantActivity.this, Constants.strRestaurentsTableName);
	ArrayList<RestaurantModel> restuarent=restaurentDbAdapter.getRestuarents(cuisineId, getApplicationContext());
	Log.d("Yes", restuarent.toString());
	list=(ListView)findViewById(R.id.list);
	RestaurantAdapter adapter=new RestaurantAdapter(RestaurantActivity.this, restuarent);
	list.setAdapter(adapter);
}



}

