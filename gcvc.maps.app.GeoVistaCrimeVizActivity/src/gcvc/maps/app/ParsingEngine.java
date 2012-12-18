package gcvc.maps.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//-------All Crime identification terms are stored in a seperate file called "Keywords.java"
public class ParsingEngine {
	private final String REGEX_ADDRESS = "(\\w{1,8}? \\w{1,3}? \\w{1,3} \\w{1,9} \\w{2})";
	private final String REGEX_DATE  ="(\\d{1,2}/\\d{1,2}/\\d{4}?)";
	private final String REGEX_TIME = "\\d{1,2}:\\d{1,2}\\s?(AM|PM)?";
	

	
	
	private String TWEET = "";
	private String TWEET_CRIME;
	private String TWEET_DATE;
	private String TWEET_TIME;
	public ParsingEngine() {
		
	}
	
	private String IdentifyCrime(){				//---Determines the type of crime the tweet is reporting
		String crimeType = "";
		
		TWEET_CRIME = crimeType;
		
		return "Some Crime Type";///***Come Back Here***
	}
	
	private String IdentifyDate(){			//identifies the date the tweet was reported
		String date = "";
		Pattern datePattern = Pattern.compile(REGEX_DATE);
		Matcher dateMatcher = datePattern.matcher(TWEET);
		if(dateMatcher.find())
			date = TWEET.substring(dateMatcher.start(), dateMatcher.end());
		TWEET_DATE = date;
		return date;
	}
	
	private String IdentifyTime(){			//identifies the time the tweet was reported
		String time = "";
		Pattern timePattern = Pattern.compile(REGEX_TIME);
		Matcher timeMatcher = timePattern.matcher(REGEX_TIME);
		if(timeMatcher.find())
			time = TWEET.substring(timeMatcher.start(), timeMatcher.end());
		return time;
	}

}
