package com.android.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import com.android.ZomatoApplication.CuisinesActivity;
import com.android.ZomatoApplication.SplashActivity;
import com.android.helpers.AppStatus;
import com.android.helpers.Constants;
import com.android.restClient.RestClient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;



public class CityTask extends AsyncTask<String, Void, String> {
	private SplashActivity context;
	AppStatus mAppStatus;
	
	
	String lon;
	String lat;
	Handler mhHandler;
	String cityName="",cityId="";
	

	public CityTask(SplashActivity context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		mAppStatus = new AppStatus();
		
		

	}

	@Override
	protected String doInBackground(String... LocationId) {
		// TODO Auto-generated method stub
	
		List<NameValuePair> params = new ArrayList<NameValuePair>(3);	
		Log.d("lat", LocationId[0]);
		
		String strJsonReponse="";
		try {
			params.add(new BasicNameValuePair("lat",LocationId[0]));
			params.add(new BasicNameValuePair("lon",LocationId[1]));
			params.add(new BasicNameValuePair("apikey", Constants.AUTH_KEY));
			strJsonReponse = RestClient.getInstance(context).doApiCall(Constants.strLocation, "GET", params);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Str Response", strJsonReponse);
		return strJsonReponse;
	}

	protected void onPostExecute(String strJsonReponse) {
	
		Log.i("STRJSON RESPONSE::::", String.valueOf(strJsonReponse));
		Log.i("STRJSON VictoryId::::", String.valueOf(strJsonReponse));
		if (strJsonReponse == null) {
			

		} else {
			/* switch on next 'VictoryDetailActivity' activity */
			
			try{
			JSONObject responseObj=new JSONObject(strJsonReponse);
			JSONObject localityObj=responseObj.getJSONObject("locality");
			
			cityName=localityObj.getString("city_name");
			Log.d("cityname", cityName);
			cityId=localityObj.getString("city_id");
			Log.d("city Id", cityId);
			Log.d("city Name", cityName);
			}catch (Exception e) {
				// TODO: handle exception
			}	
			
			Intent intent = new Intent(context, CuisinesActivity.class);
			intent.putExtra("cityId",cityId);
			
			

			context.startActivity(intent);
			context.finish();
		}
		

	}
		
}
