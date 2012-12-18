package gcvc.maps.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import android.content.Intent;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;

public class GeoLocationFinder {
	private String geoURL = "http://maps.googleapis.com/maps/api/geocode/xml?address=";
	private String zip ="";
	private String sensor = "true";
	private String query = "";
	private double latitude = 0;
	private double longitude = 0;
	
	public GeoLocationFinder(String zipCode){
		zip = zipCode;
		query = geoURL + zip + "&sensor=" + sensor;
	}
	
	public GeoPoint determineLoc(){		
		BufferedReader in = null;
		try 
		{
			URL url = new URL(query);
			in = new BufferedReader(new InputStreamReader(url.openStream()), 2048);

			String fetchText = "";
			String XMLresponse = "";
			
			while ((fetchText = in.readLine()) != null) {
				XMLresponse += fetchText;
				}
			in.close();
			
			latitude =  Double.parseDouble(XMLresponse.substring(XMLresponse.indexOf("<lat>")+4, XMLresponse.indexOf("</lat>")));
			longitude = Double.parseDouble(XMLresponse.substring(XMLresponse.indexOf("<lng>")+4, XMLresponse.indexOf("</lng>")));
			GeoPoint geoP = new GeoPoint((int)(latitude*1e6),(int)(longitude*1e6));		
			return geoP;
		} 
		
		//Malformed URL exception
		catch (Exception e) {
			System.out.print(e.getMessage());
		} 
		finally 
		{
			if (in != null) 
			{
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return null;
	}


}

