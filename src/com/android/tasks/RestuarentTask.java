package com.android.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.android.Parsers.CuisinesParser;
import com.android.Parsers.RestaurantParser;
import com.android.ZomatoApplication.RestaurantActivity;
import com.android.helpers.AppStatus;
import com.android.helpers.Constants;
import com.android.restClient.RestClient;

import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;



public class RestuarentTask extends AsyncTask<String, Void, String> {
	private RestaurantActivity context;
	AppStatus mAppStatus;
	
	ArrayList<RestaurantModel> restaurantArray;
	

	public RestuarentTask(RestaurantActivity context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		mAppStatus = new AppStatus();
		
	}

	@Override
	protected String doInBackground(String... RestId) {
		// TODO Auto-generated method stub
		//showLoading(true, "Loading", "In Process please wait...");
		String strJsonReponse = null;
		
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);			
			params.add(new BasicNameValuePair("city_id",RestId[1]));
			params.add(new BasicNameValuePair("cuisine_id", RestId[0]));
			params.add(new BasicNameValuePair("apikey", Constants.AUTH_KEY));

			strJsonReponse = RestClient.getInstance(context).doApiCall(Constants.strSearch, "GET", params);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Log.i("Victory Id in Background::::", String.valueOf(victoryId[0]));
//		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//		params.add(new BasicNameValuePair(Constants.strVictoryId, victoryId[0]));
//		params.add(new BasicNameValuePair(Constants.AUTH_KEY, mAppStatus
//				.getSharedStringValue(Constants.AUTH_KEY)));
//		try {
//			strJsonReponse = RestClient.getInstance(context).doApiCall(
//					Constants.strVictoryDetail, "GET", params);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Log.i("Victory Detail Response....", String.valueOf(strJsonReponse));
		return strJsonReponse;
	}

	protected void onPostExecute(String strJsonReponse) {
		Log.i("STRJSON RESPONSE::::", String.valueOf(strJsonReponse));
		Log.i("STRJSON VictoryId::::", String.valueOf(strJsonReponse));
		if (strJsonReponse == null) {
			// showLoading(false, "", "");
			// showMessage("Data not found...!!");

		} else {
			/* switch on next 'VictoryDetailActivity' activity */

			RestaurantParser parser=new RestaurantParser();
			restaurantArray=parser.parseRestaurants(strJsonReponse);
			context.getRestaurantsInfo(restaurantArray);
		}
		//showLoading(false, "", "");

	}
		
}
