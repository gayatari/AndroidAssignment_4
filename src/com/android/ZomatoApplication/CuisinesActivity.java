package com.android.ZomatoApplication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

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
import com.android.tasks.CityTask;
import com.android.tasks.CuisineTask;

import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CuisinesActivity extends Activity {
	String cityId;
	ListView list;
	ArrayList<CuisinesClass> cuArray;
	ArrayList<CuisinesClass> cuiArray;
	RestaurantActivity contextR;
	AppStatus appStatus;
	CuisineDbAdapter cuisineDbAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cuisines);
		cuisineDbAdapter=new CuisineDbAdapter(CuisinesActivity.this, Constants.strCuisinesTableName);
		checkConnectivity();
	}


	public void checkConnectivity()
	{
		appStatus = AppStatus.getInstance(this);
		if (appStatus.isOnline(CuisinesActivity.this)) {
			Log.v("SPLASH_SCREEN", "App is online");
			if (appStatus.isRegistered()) {
				Log.v("SPLASH_SCREEN", "App is registered!");
				Intent intent=getIntent();
				cityId=(String)intent.getSerializableExtra("cityId");
				Log.d("City id: cuisines", cityId);
				CuisineTask mcuisineTask=new CuisineTask(CuisinesActivity.this);
				mcuisineTask.execute(cityId);
			
			} 
			
		} 
		
		else {
			
			cuArray=cuisineDbAdapter.getCuisinesList(getApplicationContext());
			displayCuisinesWhenOffline(cuArray);
			
		}
	}
	public void getCuisinesInfo(ArrayList<CuisinesClass> cuisineArray)

	{
	
		DbFunctions db=new DbFunctions(getApplicationContext());
		cuisineDbAdapter.deleteAll();
		db.storeCuisineDataInDB(cuisineArray, cuisineDbAdapter);
		cuArray=cuisineDbAdapter.getCuisinesList(getApplicationContext());
		
		displayCuisines(cuArray);	

	}

	public void displayCuisines(ArrayList<CuisinesClass> cuisineArray) {
		// TODO Auto-generated method stub
		list=(ListView)findViewById(R.id.list);
		CuisinesAdapter adapter=new CuisinesAdapter(CuisinesActivity.this, cuisineArray);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {



			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				CuisinesClass cuisine=cuArray.get(position);
				Intent intent=new Intent(CuisinesActivity.this,RestaurantActivity.class);
				intent.putExtra("cuisineId", cuisine.getId());
				Log.d("cusineId", cuisine.getId());
				intent.putExtra("cityId", cityId);
				startActivity(intent);
			}
		});
	}
	public void displayCuisinesWhenOffline(ArrayList<CuisinesClass> cuisineArray) {
		// TODO Auto-generated method stub
		list=(ListView)findViewById(R.id.list);
		CuisinesAdapter adapter=new CuisinesAdapter(CuisinesActivity.this, cuisineArray);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {



			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				CuisinesClass cuisine=cuArray.get(position);
				Intent intent=new Intent(CuisinesActivity.this,RestaurantActivity.class);
				intent.putExtra("cuisineId", cuisine.getId());
				startActivity(intent);


			}
		});
	}
}



