package com.android.Parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import MyClasses.CuisinesClass;

public class CuisinesParser {

	ArrayList<CuisinesClass> cuisinesArray;

	public ArrayList<CuisinesClass> parseCuisines(String strJsonReponse)
	{

		cuisinesArray=new ArrayList<CuisinesClass>();
		try
		{

			JSONObject responseCuisines = new JSONObject(strJsonReponse);
			JSONArray cuisinesObj = responseCuisines.getJSONArray("cuisines");

			for(int i=0;i < cuisinesObj.length();i++)
			{
				CuisinesClass c=new CuisinesClass();
				JSONObject jObj=cuisinesObj.getJSONObject(i);
				JSONObject cuisine=jObj.getJSONObject("cuisine");

				c.setId(cuisine.getString("cuisine_id").toString());
				c.setName(cuisine.getString("cuisine_name").toString());
				cuisinesArray.add(c);

			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}

		return cuisinesArray;
	}

}
