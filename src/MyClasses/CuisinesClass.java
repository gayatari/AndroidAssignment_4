package MyClasses;

import android.database.Cursor;

import com.android.databases.CuisineDbAdapter;


public class CuisinesClass {
	String id;
	String name;
	public CuisinesClass() {
		super();
	}
	public CuisinesClass(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
//	public CuisinesClass(Cursor c) {
//		super();
//
//		this.id = c.getString(c
//				.getColumnIndex(CuisineDbAdapter.Id));
//		this.name = c.getString(c
//				.getColumnIndex(CuisineDbAdapter.Name));
//		
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CuisinesClass [id=" + id + ", name=" + name + "]";
	}
	

}
