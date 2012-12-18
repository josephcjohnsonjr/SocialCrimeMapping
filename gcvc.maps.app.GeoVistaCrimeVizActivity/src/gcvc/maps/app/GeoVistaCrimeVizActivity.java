package gcvc.maps.app;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
	
	
	
	
	private String geoURL = "http://maps.googleapis.com/maps/api/geocode/xml?address=";
	private String zip ="";
	private String sensor = "&sensor=true";
	private String query = "";
    
	
	
	private MapView mapV;
	private MyLocationOverlay myLocationOverlay;

    
    private ItemizedOverlays HospitalLocations;
    private ItemizedOverlays PoliceDepartmentLocations;
    private ItemizedOverlays FireDepartmentLocations;
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
//        Button b = (Button)findViewById(R.id.buttonGoTo);
        
        
//        WifiManager wifimanager;
//        if(wifimanager.getWifiState() == wifimanager.WIFI_STATE_DISABLED){
//        	Toast.makeText(this, R.string.Wifi_State, Toast.LENGTH_LONG).show();	
//        }
        
        
//*******************************User Interface Component*********************************************************************        

    	//Spinner crimeList = (Spinner) findViewById(R.id.crimeList);
    	//ArrayAdapter<CharSequence> crimeAdapter = ArrayAdapter.createFromResource(this, R.array.CrimeType, android.R.layout.simple_spinner_item);    	
    	//crimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    	
    	//crimeList.setAdapter((SpinnerAdapter)crimeAdapter);
    	
    	
    	
    	//Spinner departmentList = (Spinner) findViewById(R.id.DepartmentList);
    	//ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(this, R.array.DepartmentList, android.R.layout.simple_spinner_item);
    	//departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	//departmentList.setAdapter((SpinnerAdapter)departmentAdapter);
    	
    	
    	
    	
    	//Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
    	//ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.MetroStations, android.R.layout.simple_spinner_item);
    	//spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	//spinner3.setAdapter((SpinnerAdapter)spinnerAdapter);   	
    	
    	
//***********************************User Interface Components********************************************************************
    	
       mapV = (MapView) findViewById(R.id.mapview);
       mapV.displayZoomControls(true);
       mapV.setBuiltInZoomControls(true);
       
       
       
       myLocationOverlay = new FixedMyLocationOverlay(this, mapV);
       mapV.getOverlays().add(myLocationOverlay);
       zoomToMyLocation();
       
       GeoPoint p = new GeoPoint((int) (47.5448481 * 1E6), (int) (-122.2744919 * 1E6));
       GeoPoint pt = new GeoPoint((int) (139.4987270 * 1E6), (int) (-85.4445080 * 1E6));
       mapV.getController().animateTo(p);
       mapV.getController().setZoom(12);
       
       List<Overlay> mapOverlays = mapV.getOverlays();
       Drawable drawable = this.getResources().getDrawable(R.drawable.arson);
       
       ItemizedOverlays itemizedoverlay = new ItemizedOverlays(drawable, this);
   	   
       OverlayItem overlayitem = new OverlayItem(p, "Account: @SeattlePDO1", "Feed: SHOPLIFT - THEFT at 23XX BLOCK OF RAINIER AVE S reported on 10/30/2012 5:54 PM");
       
       OverlayItem overlay = new OverlayItem(pt, "Second point","");
       
       itemizedoverlay.addOverlay(overlayitem);
       itemizedoverlay.addOverlay(overlay);
       
       mapOverlays.add(itemizedoverlay);
       
//       PostHospitals HospitalLocation_Thread = new PostHospitals(this);
//       HospitalLocation_Thread.execute("");
//       HospitalLocations = HospitalLocation_Thread.ReturnOverlay();
//       mapOverlays.add(HospitalLocations);
       
//       PostFireDepartments FireDepartmentLocation_Thread = new PostFireDepartments(this);
//       FireDepartmentLocation_Thread.execute("");
//       FireDepartmentLocations = FireDepartmentLocation_Thread.GetOverlay();
//       mapOverlays.add(FireDepartmentLocations);
       
//       PostPoliceDepartments PoliceDepartmentLocation_Thread = new PostPoliceDepartments(this);
//       PoliceDepartmentLocation_Thread.execute("");
//       PoliceDepartmentLocations = PoliceDepartmentLocation_Thread.GetOverlay();
//       mapOverlays.add(PoliceDepartmentLocations);

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
    	case R.id.TrafficViolations:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.Robberies:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.AggrevatedAssault:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);
    	case R.id.SuspiciousPersons:
    		if(item.isChecked())
    			item.setChecked(false);
    		else
    			item.setChecked(true);			//Eventually add in variable to change the query to the database
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
    
    
    
    
    
/* ********************************************************************************************
 * *****************************User Interface Events******************************************
 * ***************This section of code creates threads on each event*************************** 
 * **********************apart from the main Activity Thread
 * ********************************************************************************************
 * ******************************************************************************************/
    
//    public void ZipQuery(final View view)
//    {    	
//    	    	EditText editText = (EditText) findViewById(R.id.editText);
//	        	boolean isZip = false;    	
//	        	String zip = editText.getText().toString();
//	        	
//	        	if(zip.length() == 5){
//	        		isZip = true;
//	        	}
//	        	
//	        	if(isZip){
//	        		query = geoURL + zip + sensor;
//	        		try{
//	        		new ZipCodeQuery().execute(query);
//	        		}
//	        		catch(Exception e){
//	        			System.out.print(e.getMessage());
//	        		}
//	        	}
//    }
    
    private class ZipCodeQuery extends AsyncTask<String, Void, String> {
		/** The system calls this to perform work in a worker thread and
		 * delivers it the parameters given to AsyncTask.execute() */
    	
    	
    	GeoPoint positioning = null;
		
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
    	
		@Override
		protected Void doInBackground(Void... arg0) {
			HttpGet httpget = new HttpGet();
			
			return null;
		}
		
		protected void onPostExecute(Void... arg0){
			
		}
    }
    
    
    //Creates a seperate thread to Display the hospitals on the map
    public class PostHospitals extends AsyncTask<String, Void, String> {
		/** Creates a worker thread to parse the "Hospitals" XML file
		 *  and displays everyone on the map view*/
    	
		private GeoPoint geoPoint;
		private HospitalData hospitaldata;
		
		private ItemizedOverlays itemOverlays;
		private Context mapContext;
		
		public PostHospitals(Context context){
			mapContext = context;
			hospitaldata = new HospitalData();
			
		}
		protected String doInBackground(String... urls) {
			String response = "";
			try{
					hospitaldata.GetHospitalData();
					hospitaldata.parseDocument();
					itemOverlays = hospitaldata.DisplayOnMap(mapContext);
					
			}
	    	
	            catch(Exception e){
	            	System.out.print(e.getMessage());
	            }
	            
			return response;
		}


		/** The system calls this to perform work in the UI thread and delivers
		 * the result from doInBackground() */
	
		protected void onPostExecute(String text) {
			
			MapView map = (MapView) findViewById(R.id.mapview);
		}
		public ItemizedOverlays ReturnOverlay(){
			return itemOverlays;
		}
}
    
    public class HospitalData extends com.google.android.maps.Overlay{
    	private Document dom;

    	
    	private ArrayList<Hospitalinfo> hospitalList = new ArrayList<Hospitalinfo>();
    	
    	private String Namebuffer;
    	private String Addressbuffer;
    	private String Contactbuffer;
    	private String hospitalDatabuffer;
    	
    	
    	
    	public void GetHospitalData(){
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	try{
        		DocumentBuilder db = dbf.newDocumentBuilder();
        		
        		dom = db.parse(getApplicationContext().getAssets().open("Hospitals.xml"));
        	}
        	
        	catch(IOException e){
        		e.printStackTrace();
        	} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
        }
    	
    	public void parseDocument(){
    		//get the root element
    		
    		Element element = dom.getDocumentElement();
    		
    		//get a nodelist of elements
    		
	    		NodeList elementList = element.getElementsByTagName("Hospital");
	    		Hospitalinfo hospitalInformation;
	    		if(elementList != null && elementList.getLength() > 0){
	    			for(int i = 0; i < elementList.getLength(); i++){
	    				
	    				//get the Hospital Element
	    				Element hospital = (Element)elementList.item(i);
	    				
	    				
	    				Addressbuffer = getTextValue(hospital,"Address");
	    				Contactbuffer = getTextValue(hospital,"Contact");
	
	    				Namebuffer = hospital.getAttribute("Name");
	    				
	    				hospitalDatabuffer = "Name: " + Namebuffer + "\nLocation: " + Addressbuffer
	    								+ "\nContact: " + Contactbuffer;
	    				
	    				hospitalInformation = new Hospitalinfo(Addressbuffer, Contactbuffer, Namebuffer, hospitalDatabuffer);
	    				hospitalList.add(hospitalInformation);
	    			}
	    		}
    	}
    	
    	private String getTextValue(Element ele, String tagName) {
    		String textVal = null;
    		NodeList nl = ele.getElementsByTagName(tagName);
    		if(nl != null && nl.getLength() > 0) {
    			Element el = (Element)nl.item(0);
    			textVal = el.getFirstChild().getNodeValue();
    		}

    		return textVal;
    	}
    	
    	//Processes the hospital list and displays the hospital on the map

    	public ItemizedOverlays DisplayOnMap(Context mapContext){
    		Drawable marker = getResources().getDrawable(R.drawable.hospital);
    		ItemizedOverlays itemOverlays = new ItemizedOverlays(marker, mapContext);
    		
    		String query = "";
    		
    		GeoPoint location;
    		Hospitalinfo hospitalinfo;
    		
    		
    	        for(int i = 0; i < hospitalList.size(); i++){
    	        	hospitalinfo = hospitalList.get(i);
    	        	try{
    	        	query = "http://maps.googleapis.com/maps/api/geocode/xml?address="+ hospitalinfo.Address + "&sensor=true";
    	        	query = query.replace(" ", "%20");
    	        	//urlBuilder = AddressURL.matcher(query);
    	        	//urlBuilder.replaceAll("%20");
    	        
    	        	
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
    		
        			location = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
        			OverlayItem overlayitem = new OverlayItem(location, hospitalinfo.Name, hospitalinfo.hospitalData);
        			itemOverlays.addOverlay(overlayitem);
    	        	}catch(Exception e){
    	        		System.out.print(e.getMessage());
    	        	}
    	        	
    	        }
    	        return itemOverlays;
    			
    			
    		
    		
    	}
    }
    	
    	
        
    	
    }
    //Hospital info is a Data structure that stores information about each hospital such as (address, contact information, etc)
    class Hospitalinfo{
    	public String Address;
        public String Contact;
        public String Name;
        public String hospitalData;
        public float latitude;
        public float longitude;
        
    	public Hospitalinfo(String address, String contact, String name, String hospitaldata){
    		Address = address;
    		Contact = contact;
    		Name = name;
    		hospitalData = hospitaldata;
    	}
    	public void setCoordinates(float Latitude, float Longitude){
    		latitude = Latitude;
    		longitude = Longitude;
    	}
    }
    
    
    
    //Creates a separate thread to display Police Stations on the mapview
    class PostPoliceDepts extends AsyncTask<String, Void, String> {
		/** Creates a worker thread to parse the "Hospitals" XML file
		 *  and displays everyone on the map view*/
    	
		GeoPoint geoPoint;		//used to store the location coordinates of the Police Department
		
		protected String doInBackground(String... urls) {
			String response = "";
			try{
				
			}
	    	
	            catch(Exception e){
	            	System.out.print(e.getMessage());
	            }
	            
			return response;
		}


		/** The system calls this to perform work in the UI thread and delivers
		 * the result from doInBackground() */
	
		protected void onPostExecute(String text) {
			//MapView map = (MapView) findViewById(R.id.mapview);
			//map.getController().animateTo(geoPoint);
			
		}
    

    
    
    
    
    
    
    public void UpdateMapView(View view){
    	//Take all of the Spinner components and make a query based on the selected items in the Spinner Controls    	
    }
    
/* ********************************************************************************************
 *  * *****************************User Interface Events******************************************
 * *********************************************************************************************
 */
    
    
}