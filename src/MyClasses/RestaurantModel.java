package MyClasses;

import android.database.Cursor;

import com.android.databases.CuisineDbAdapter;
import com.android.databases.RestaurentDbAdapter;

public class RestaurantModel {
	String name;
	String address;
	String cuisines;
	String rating;
	public RestaurantModel() {
		super();
	}
	public RestaurantModel(String name, String address, String cuisines,
			String rating) {
		super();
		this.name = name;
		this.address = address;
		this.cuisines = cuisines;
		this.rating = rating;
	}
//	public RestaurantModel(Cursor c) {
//		super();
//
//		this.name = c.getString(c
//				.getColumnIndex(RestaurentDbAdapter.Name));
//		this.address = c.getString(c
//				.getColumnIndex(RestaurentDbAdapter.Address));
//		this.cuisines = c.getString(c
//				.getColumnIndex(RestaurentDbAdapter.Cuisines));
//		this.rating = c.getString(c
//				.getColumnIndex(RestaurentDbAdapter.Rating));
//		
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "RestaurantModel [name=" + name + ", address=" + address
				+ ", cuisines=" + cuisines + ", rating=" + rating + "]";
	}
	
	
	
	

}
