package gcvc.maps.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;




public class GeoVistaCrimeVizActivity extends com.google.android.maps.MapActivity {
    /** Called when the activity is first created. */
	public class OAuthentication{
	    public static final String ConsumerKey = "e5llIrQKB7xl3Lz8eXXig";
	    public static final String ConsumerSecret = "SvdmQvwQspguSvwHzUaYJIl3iJ39Y8E93X5JIoAkgc";

	    public static final String RequestTokenURL = "https://api.twitter.com/oauth/request_token";
	    public static final String AuthorizeURL = "https://api.twitter.com/oauth/authorize";
	    public static final String AccessTokenURL = "https://api.twitter.com/oauth/access_token";
	    Scanner sc = new Scanner(System.in);
		StringTokenizer t;
	}
	
	//-----Special variable, used to query the state of the user's Internet connection
	public static ConnectivityManager networkConnection;
	//----
	
	private String geoURL = "http://maps.googleapis.com/maps/api/geocode/xml?address=";
	private String zip ="";
	private String sensor = "&sensor=true";
	private String query = "";
    
	
	
	private MapView mapV;
	private MyLocationOverlay myLocationOverlay;

    
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
//        networkConnection = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = networkConnection.getActiveNetworkInfo();
//        if(!networkInfo.isConnected()){
//        	networkInfo.getState().toString();
//        	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
//        						alertBuilder.setMessage(R.string.NetworkInfo);
//        						alertBuilder.setTitle(R.string.NetworkModerator);
//        						alertBuilder.setPositiveButton(R.string.OK_Button, new DialogInterface.OnClickListener(){
//        					           public void onClick(DialogInterface dialog, int id) {
//        					               dialog.dismiss();
//        					           }
//        					       });
//        	AlertDialog alertDialog = alertBuilder.create();
//        	alertDialog.show();
//        }
            	
       mapV = (MapView) findViewById(R.id.mapview);
       mapV.displayZoomControls(true);
       mapV.setBuiltInZoomControls(true);
       
       
       
       myLocationOverlay = new FixedMyLocationOverlay(this, mapV);
       mapV.getOverlays().add(myLocationOverlay);
       zoomToMyLocation();
       
       GeoPoint p = new GeoPoint((int) (50.5448481 * 1E6), (int) (-130.2744919 * 1E6));
       mapV.getController().animateTo(p);
       mapV.getController().setZoom(12);
       
       List<Overlay> mapOverlays = mapV.getOverlays();
       Drawable drawable = this.getResources().getDrawable(R.drawable.theft);
       
       
       ItemizedOverlays itemizedoverlay = new ItemizedOverlays(drawable, this);
   	   
       OverlayItem overlayitem = new OverlayItem(p, "Account: @SeattlePDO1", "Feed: SHOPLIFT - THEFT at 23XX BLOCK OF RAINIER AVE S reported on 10/30/2012 5:54 PM");
       
       
       
       itemizedoverlay.addOverlay(overlayitem);
       
       mapOverlays.add(itemizedoverlay);

       
    }

    @Override
	protected void onResume() {
	    super.onResume();
	    // when our activity resumes, we want to register for location updates
	    myLocationOverlay.enableMyLocation();
	    super.onResume();
	}
	 
	
	@Override
	protected void onPause() {
	    super.onPause();
	    // when our activity pauses, we want to remove listening for location updates
	    myLocationOverlay.disableMyLocation();
	    super.onPause();
}

	@Override
	protected void onStop(){
		super.onStop();
	}
	
	private void zoomToMyLocation() {
		GeoPoint myLocationGeoPoint = myLocationOverlay.getMyLocation();
		if(myLocationGeoPoint != null) {
			mapV.getController().animateTo(myLocationGeoPoint);
			mapV.getController().setZoom(16);

		}
	}	
	
@Override
	protected void onDestroy(){
	super.onDestroy();
}
	
@Override
	protected boolean isRouteDisplayed(){
	return false;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.options_menu, menu);
    	
    	return true;
    }
    
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	//Handle item selection for every item that is contained in a menu and has an id attribute
    	switch(item.getItemId()){
    	//------------------Handles events for items selected in "Map Settings" menu-------------
    	case R.id.SatelliteView:
    		setSatelliteView();
    		item.setChecked(true);
    		break;
    	case R.id.MapView:
    		setMapView();
    		item.setChecked(true);
    		break;
    	case R.id.TrafficView:
    		setTrafficView();
    		item.setChecked(true);
    		break;
    	case R.id.HeatMap:
    		//setHeatMapView()
    		item.setChecked(true);
    		break;
    	//---------------------------------------------------------------------------------------
    		
    		
    	//--------------Handles events for items in "Incident Filter" menu-----------------------
    	case R.id.AggrevatedAssault_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.Arson_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.DisturbingThePeace_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.Homicide_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.Robbery_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.Theft_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.TrafficAccident_Item:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
        		item.setChecked(true);			//Eventually add in variable to change the query to the database
    	case R.id.DateFilter_Item:
    		showDatePickerDialog(this.findViewById(R.id.DateFilter_Item));
    	case R.id.update:
    		DataBaseQuery();
        //---------------------------------------------------------------------------------------
    	default:
    		return super.onOptionsItemSelected(item);
    	}
		return false;
	}
    
    public void setMapView(){
    	mapV.setTraffic(false);
    	mapV.setSatellite(false);
    }
    
    public void setSatelliteView(){
    	mapV.setTraffic(false);
    	mapV.setSatellite(true);
    }
    public void setTrafficView(){
    	mapV.setSatellite(false);
    	mapV.setTraffic(true);
    }
    public void setHeatMap(){
    	
    }
    
    public void DataBaseQuery(){
    	GeoPoint p;
    	double latitude;
    	double longitude;
    	String CrimeType;
    	Drawable drawable = this.getResources().getDrawable(R.drawable.homicide);
    	
    	
    	DataLayer d = new DataLayer(getBaseContext());
    	d.AddIncident("SHOPLIFT - THEFT at 23XX BLOCK OF RAINIER AVE S reported on 10/30/2012 5:54 PM",
    			"SeattlePDO1", "Seattle, Washington", "Theft", 47.5448481, -122.2744919, "Month ago", "TIME");
    	ArrayList<QueryResults>results = d.SelectIncidents();
    	String query = "";
    	if(results.size() > 0)
    			query += results.get(results.size()-1).toString() +"\n\n";
    	CrimeType = results.get(results.size() - 1).Incident;
    	p = new GeoPoint((int)(results.get(results.size()-1).Latitude	*1E6), (int)(results.get(results.size()-1).Longitude*1E6));
    	
    	if(CrimeType.equalsIgnoreCase("Theft"))
    		drawable = this.getResources().getDrawable(R.drawable.theft);
    	
        ItemizedOverlays itemizedoverlay = new ItemizedOverlays(drawable, this);
        
        OverlayItem overlayitem = new OverlayItem(p, results.get(results.size() - 1).Username, results.get(results.size() - 1).toString());
        
        
        itemizedoverlay.addOverlay(overlayitem);
        mapV.getOverlays().add(itemizedoverlay);
        
    	mapV.getController().animateTo(p);
    	Toast.makeText(this, query, Toast.LENGTH_LONG).show();
    }
    	
    
    public void showDatePickerDialog(View v) {
        DialogFragment startDate = new DatePickerFragment();
        startDate.show(getFragmentManager(), "datePicker");
    }
    
    
    
/* ********************************************************************************************
 * *****************************User Interface Events******************************************
 * ***************This section of code creates threads on each event*************************** 
 * **********************apart from the main Activity Thread
 * ********************************************************************************************
 * ******************************************************************************************/
    
    
    
    private class ZipCodeQuery extends AsyncTask<String, Void, String> {
		/** The system calls this to perform work in a worker thread and
		 * delivers it the parameters given to AsyncTask.execute() */
    	
    	
    	GeoPoint positioning = null;
		
		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			try{
				BufferedReader in = null;
	    		URL url = new URL(query);
				in = new BufferedReader(new InputStreamReader(url.openStream()));

				String fetchText = "";
				String XMLresponse = "";
				
				
				while ((fetchText = in.readLine()) != null) {
					XMLresponse += fetchText;
					}
				
				final String XML = XMLresponse;
				in.close();
			    
				double latitude =  Double.parseDouble(XML.substring(XML.indexOf("<lat>")+5, XML.indexOf("</lat>")));
			    double longitude = Double.parseDouble(XML.substring(XML.indexOf("<lng>")+5, XML.indexOf("</lng>")));
				
			    positioning = new GeoPoint((int)(latitude*1e6),(int)(longitude*1e6));
				
			}
	    	
	            catch(Exception e){
	            	System.out.print(e.getMessage());
	            }
	            
			return response;
		}


		/** The system calls this to perform work in the UI thread and delivers
		 * the result from doInBackground() */
	
//		protected void onPostExecute(String text) {
//			MapView map = (MapView) findViewById(R.id.mapview);
//			map.getController().animateTo(positioning);
//			map.getController().setZoom(13);
//			
//		}
}
    
    
    /*
     * When instantiated in the main thread, this worker thread establishes a POST connection to Twitter
     * servers using HTTP GET and POST. Every response is parsed using the ParsingEngine.java class
     */
    public class TWITTERPOST extends AsyncTask<Void, Void, Void>{
    	String access_token_url = OAuthentication.AccessTokenURL;
    	String authorize_url = OAuthentication.AuthorizeURL;
    	String consumerKey = OAuthentication.ConsumerKey;
    	String consumer_secret = OAuthentication.ConsumerSecret;
    	String request_token_url = OAuthentication.RequestTokenURL;
    	
    	NetworkInfo networkInfo;
    	
		@Override
		protected Void doInBackground(Void... arg0) {
			networkInfo = networkConnection.getActiveNetworkInfo();
			if(networkInfo.isConnected()){
				
			}
			
			return null;
		}
		
		protected void onPostExecute(Void... arg0){
			
		}
    }
    
    
        
    public void UpdateMapView(View view){
    	//Take all of the Spinner components and make a query based on the selected items in the Spinner Controls    	
    }
    
/* ********************************************************************************************
 *  * *****************************User Interface Events******************************************
 * *********************************************************************************************
 */
    
    
}