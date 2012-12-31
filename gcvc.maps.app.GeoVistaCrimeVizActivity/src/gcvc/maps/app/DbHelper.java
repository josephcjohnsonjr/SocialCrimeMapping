package gcvc.maps.app;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
	SQLiteDatabase db;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		//db.execSQL(DATE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

	
	//------------------Column names---------------
		public static final String KEY_TWEET = "Tweet";
		public static final String KEY_USERNAME = "Username";
		public static final String KEY_INCIDENT = "Incident";
		public static final String KEY_DATE = "Date";
		public static final String KEY_TIME = "Time";
		public static final String KEY_ADDRESS = "Address";
		public static final String KEY_LATITUDE = "Latitude";
		public static final String KEY_LONGITUDE = "Longitude";
		private static final String TAG = "DBAdapter";
		
		public static final String DATABASE_NAME = "INCIDENTS";
		private static final String DATABASE_TABLE = "INCIDENTS";
		private static final int DATABASE_VERSION = 2;
		
		private static final String DATE_DATABASE = "DATEDATABASE";
		private static final String DATE_DBTABLE = "DATE";
		//---------------------------------------------
		
		//----------------------------------------SQL commands to create Tables on the applications first time use------------------------------
		private static final String DATABASE_CREATE = "create table if not exists "+ DATABASE_NAME +  " (ID integer Primary Key, Tweet VARCHAR not null " +
				", "+ "Username VARCHAR not null, Address VARCHAR, Incident VARCHAR, Latitude FLOAT, Longitude FLOAT, Date VARCHAR, Time VARCHAR);";
		
		private static final String DATE_CREATE = "create table if not exists DATE (DATE date)";
		
		private static final String DATABASE_CREATE_HOSPITALS = "create table if not exists hospitals (id integer primary key autoincrement, hospital_name VARCHAR not null, timestamp time, address VARCHAR, latitude FLOAT, longitude FLOAT);" ;
		
		private static final String DATABASE_CREATE_POLICEDEPT = "create table if not exists policedept (id integer primary key autoincrement, dept_name VARCHAR not null, timestamp time, address VARCHAR, latitude FLOAT, longitude FLOAT);" ;
		
		private static final String DATABASE_CREATE_FIREDEPT = "create table if not exists firedept (id integer primary key autoincrement, dept_name VARCHAR not null, timestamp time, address VARCHAR, latitude FLOAT, longitude FLOAT);" ;
		//-------------------------------------------------------------------------------------------------------------------------------------
}
