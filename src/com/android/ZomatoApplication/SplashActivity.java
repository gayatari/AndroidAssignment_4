package com.android.ZomatoApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.android.MyAdapters.CuisinesAdapter;
import com.android.databases.CuisineDbAdapter;
import com.android.databases.RestaurentDbAdapter;
import com.android.helpers.AppStatus;
import com.android.helpers.Constants;
import com.android.restClient.RestClient;
import com.android.tasks.CityTask;

import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SplashActivity extends Activity {
	AppStatus appStatus;
	ProgressDialog progress;
	Handler handler;
	String cityName,cityId;
	double lat=18.52043,lon=73.856744;
	CuisinesActivity contextC;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new mylocationlistener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);

		appStatus = AppStatus.getInstance(this);
		handler = new Handler();
		progress = new ProgressDialog(this);

		startApp();
	}

	private void startApp() {
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				showprogress(true, "Loading", "In Process please wait...");
			}
				});
			t.start();
			
				if (appStatus.isOnline(SplashActivity.this)) {
					Log.v("SPLASH_SCREEN", "App is online");
					if (appStatus.isRegistered()) {
						Log.v("SPLASH_SCREEN", "App is registered!");


						getCityInfo();
												
						
					} 
					
				} 
				
				else {
					
					Intent intent=new Intent(SplashActivity.this,CuisinesActivity.class);
					startActivity(intent);
					
					
				}
				//showprogress(false, "", "");
			}
		
	
	private void getCityInfo() {
		
		try {
			
		
			CityTask mcityTask=new CityTask(SplashActivity.this);
			mcityTask.execute("18.52043","73.856744");

				
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	void showprogress(final boolean show, final String title, final String msg) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (show) {
					if (progress != null) {
						progress.setTitle(title);
						progress.setMessage(msg);
						progress.show();
					}
				} else {
					progress.cancel();
					progress.dismiss();
				}

			}
		});
	}

	void message(String msg) {
		final String mesage = msg;
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(SplashActivity.this, mesage, 8000);
				toast.show();
			}
		});
	}

public class mylocationlistener implements LocationListener{

	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
			Log.d("LOCATION CHANGED", location.getLatitude() + "");
			Log.d("LOCATION CHANGED", location.getLongitude() + "");
			//   Toast.makeText(SplashActivity.this,String.format("%f,%f",location.getLatitude(),location.getLongitude()),Toast.LENGTH_SHORT).show();
			Geocoder geocoder = new Geocoder(SplashActivity.this, Locale.getDefault());
			List<Address> addresses;
			try
			{
				addresses= geocoder.getFromLocation(18.52043,73.856744, 1);
				Log.d("Address: ", addresses.get(0).getLocality());
			}
			catch (Exception e) {
				// TODO: handle exception
			}


		}
	}
	

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}

}

