package com.android.MyAdapters;

import java.util.ArrayList;

import com.android.MyAdapters.CuisinesAdapter.ViewHolder;
import com.android.ZomatoApplication.R;

import MyClasses.CuisinesClass;
import MyClasses.RestaurantModel;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class RestaurantAdapter extends ArrayAdapter<RestaurantModel>{
	Activity context;
	ArrayList<RestaurantModel> restaurantArray;
	
	public RestaurantAdapter(Activity context,ArrayList<RestaurantModel> restaurantArray) {
		super(context,R.layout.list_item,R.id.text,restaurantArray);
		this.context=context;
		this.restaurantArray=restaurantArray;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public RestaurantModel getItem(int position) {
		return restaurantArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		RestaurantModel c=restaurantArray.get(position);
		if(convertView==null){
			LayoutInflater inflater=context.getLayoutInflater();
			convertView=inflater.inflate(R.layout.customrestaurants,null);
			
			holder = new ViewHolder();
			Log.d("Rest", restaurantArray.toString());
			holder.name = (TextView) convertView.findViewById(R.id.Name);
			holder.address = (TextView) convertView.findViewById(R.id.Address);
			holder.cuisines = (TextView) convertView.findViewById(R.id.Cuisines);
			holder.rating = (RatingBar) convertView.findViewById(R.id.Rating);
			convertView.setTag(holder);
		}
		else {
			holder=(ViewHolder)convertView.getTag();
		}
	//	text=(TextView)layout.findViewById(R.id.text);
	//	text.setText(info.getiName()+"\t"+info.getiAge());
		
	
		holder.name.setText(c.getName());
		Log.d("addr", c.getAddress());
	    holder.address.setText(c.getAddress());
	    holder.cuisines.setText(c.getCuisines());
	    holder.rating.setRating(Float.parseFloat(c.getRating()));
		
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;	
		TextView address;	
		TextView cuisines;	
		RatingBar rating;	
	}
	


}
