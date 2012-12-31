package gcvc.maps.app;

public class Keywords {
	//----This part of the code is used to identify what type of crime the tweet is reporting
	//----Alphabetized
	public	enum CRIME_CATEGORIZATION  {Arson, AggrevatedAssault, DisturbingThePeace, Homicide, Robbery, Theft, TrafficAccident};  //---Categorization of crimes that will be identified in tweets
	
	
	//---Complete Word Base of Common Keywords & Phrases that are used to associate crimes with a the enum above
	public String[] AGGREVATEDASSAULT = {"(Aggrevated Assault)","(Assault)", "(Mugging)", "(Domestic Disturbance)", "(Domestic Violence)", "()"};
	public	String[] ARSON = {"(Arson)", "(Fire)", "(Property Fire)", "(Fire Property)", "(Incendiarism)", "(Incendary)"};
	public	String[] DISTURBINGTHEPEACE = {"(Disturbance)", "(Intoxicated Persons)", "(Intoxicated Person)", "(Narcotics)", "(Drugs)", "(Mental Complaint)"};
	public	String[] HOMICIDE = {"(Homicide)", "(Murder)", "(Man Slaughter)", "(Killing)", "(Attempted Murder)", "(Murder Suicide)", "(Suicide)", "(Shooting)", "(Shooter)"};
	public	String[] ROBBERY = {"(Robbery)", "(Residential Burglary)", "(Commercial Burglary)", "(Thievary)", "(Housebreaking)", "(Breaking and Entering)", "(Shop Lifting)", "(Stickup)", "(Heist)", "(Larcen)", "(Grand Larcen)", "(Larcency)", "(Kidnapping)"};
	public	String[] THEFT = {"(Theft)", "(Car Prowl)", "(Tresspass)", "(Tresspassing)", "(Mugging)", "(Aggrevated Assault)"};
	public	String[] TRAFFICACCIDENT = {"(Traffic Accident)", "(Traffic Violation)", "(Traffic)", "(Car)", "(Hit and Run)", "(Vehicle)", "(Signals)", "(Signal)", "(Accident)", "(Motorist Assist)"};
	//----------
}
