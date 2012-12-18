package gcvc.maps.app;

public class Keywords {
	//----This part of the code is used to identify what type of crime the tweet is reporting
	public	enum CRIME_CATEGORIZATION  {Theft, Robbery, Homicide, Arson, TrafficViolation, DisturbingThePeace};  //---Categorization of crimes that will be identified in tweets
	
	
	//---Complete Dictionary of Common Keywords & Phrases that are used to associate crimes with a Type
	public	String[] THEFT = {"(Theft)", "(Car Prowl)", "(Tresspass)", "(Tresspassing)", "(Mugging)", "(Aggrevated Assault)"};
	public	String[] ROBBERY = {"(Robbery)", "(Residential Burglary)", "(Commercial Burglary)", "(Thievary)", "(Housebreaking)", "(Breaking and Entering)", "(Shop Lifting)", "(Stickup)", "(Heist)", "(Larcen)", "(Grand Larcen)", "(Larcency)", "(Kidnapping)"};
	public	String[] HOMICIDE = {"(Homicide)", "(Murder)", "(Man Slaughter)", "(Killing)", "(Attempted Murder)", "(Murder Suicide)", "(Suicide)", "()"};
	public	String[] ARSON = {"(Arson)", "(Fire)", "(Property Fire)", "(Fire Property)", "(Incendiarism)", "(Incendary)"};
	public	String[] TRAFFICVIOLATION = {"(Traffic Violation)", "(Traffic)", "(Car)", "(Vehicle)", "(Signals)", "(Signal)", "(Accident)", "(Motorist Assist)"};
	public	String[] DISTURBINGTHEPEACE = {"(Disturbance)", "(Intoxicated Persons)", "(Intoxicated Person)", "(Narcotics)", "(Drugs)", "(Shooting)", "(Drive-By Shooting)", "()"};
	//----------
}
