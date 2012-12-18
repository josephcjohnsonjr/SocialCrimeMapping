package gcvc.maps.app;
//We'll replace this source/Class once we find a creditable source
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.util.Log;

public class SpotCrimeCrawler {
	public static final String TAG = "CLASS: SPOTCRIMECRAWLER ";
	
	private URL url;
	private LinkedList<String> Reports = new LinkedList<String>();
	private LinkedList<String> Incidents = new LinkedList<String>();
	
	public SpotCrimeCrawler() {
		
		
		String currentDateString;
		
		
		try{
			//---Establishes connection with source
			url = new URL("http://spotcrime.com/tx/houston/daily/");
		}catch(MalformedURLException e){
			Log.d("SpotCrimeCrawler ", "Error instantiating URL object");
		}
	}
	//Method returns a queue of Content Values that are sent directly to the database insertion method. 
	public LinkedList<ContentValues> crawlSource(){
		LinkedList<ContentValues> IncidentsDB = new LinkedList<ContentValues>();
		ContentValues DatabaseValues = new ContentValues();
		BufferedReader htmlpipeline;
		String htmlSource = "";
		
		
		try{
			htmlpipeline = new BufferedReader(new InputStreamReader(url.openStream()));
			while(htmlpipeline != null){
				htmlSource.concat(htmlpipeline.readLine());
			}
			htmlpipeline.close();
			
			//-----Searches and returns the index starting & ending position
			//-----of the table containing crime data in the HTML source code
			Pattern datatable_start = Pattern.compile("*(<table)");
			Pattern datatable_end = Pattern.compile("(</table>)");
			
			Matcher dtStart = datatable_start.matcher(htmlSource);
			Matcher dtEnd  = datatable_end.matcher(htmlSource);

			//------Variable to store the data table
			String crimeTable = htmlSource.substring(dtStart.start(), dtEnd.start());
			
			Pattern $Row = Pattern.compile("(<tr>)");
			Pattern $Row_End = Pattern.compile("(</tr>)");
			
			return IncidentsDB;
			
		}catch(MalformedURLException e){
			Log.d(TAG, "MalformedURLException: Unable to issue URL request");
		}catch(IOException e){
			Log.d(TAG, "IOException: Error Parsing HTML source.");
		}
		//---Code to parse table data entries
		//--Incident Type, Date/Time, Address
		
		
		return IncidentsDB;
	}
	public String Today(){	//returns a string containing today's data yy/mm/dd
		return "";
	}

}
