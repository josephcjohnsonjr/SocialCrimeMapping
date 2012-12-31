package gcvc.maps.app;

public class QueryResults {
	public String Tweet;
	public String Username;
	public String Address;
	public String Incident;
	public double Latitude;
	public double Longitude;
	public String Date;
	public String Time;
	
	public QueryResults(String Tweet, String Username,String Address, 
			String Incident, double Latitude, double Longitude, String Date, String Time) {
			
			this.Tweet = Tweet;
			this.Username = Username;
			this.Address=Address;
			this.Incident = Incident;
			this.Latitude = Latitude;
			this.Longitude = Longitude;
			this.Date = Date;
			this.Time = Time;
	}
	public String toString(){
		String text = "Tweet: " + this.Tweet+
					  "\nUsername: " + this.Username+
					  "\nAddress: " + this.Address +
					  "\nIncident: " + this.Incident +
					  "\nLatitude: " + this.Latitude +
					  "\nLongitude: " + this.Longitude +
					  "\nDate: " + this.Date +
					  "\nTime: " + this.Time;
		return text;
	}

}
