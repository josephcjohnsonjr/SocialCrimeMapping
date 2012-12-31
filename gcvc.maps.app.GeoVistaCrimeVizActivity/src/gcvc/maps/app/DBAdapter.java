package gcvc.maps.app;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimeZone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;

public class DBAdapter {
	
	//------------------Column names---------------
	public static final String KEY_ROWID = "id";
	public static final String KEY_TIMESTAMP = "time_added";
	public static final String KEY_INCIDENT = "incident";
	public static final String KEY_DATE = "date";
	public static final String KEY_TIME = "time";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LONGITUDE = "longitude";
	private static final String TAG = "DBAdapter";
	
	private static final String DATABASE_NAME = "SCMDATABASE";
	private static final String DATABASE_TABLE = "INCIDENTS";
	private static final int DATABASE_VERSION = 2;
	
	private static final String DATE_DATABASE = "DATE_DB";
	private static final String DATE_DBTABLE = "DATE";
	//---------------------------------------------
	
	
	
	
	
	
	
	//----------------------------------------SQL commands to create Tables on the applications first time use------------------------------
	private static final String DATABASE_CREATE = " create table if not exists SCMDATABASE (id integer primary key " +
			"autoincrement, "+ "incident VARCHAR not null, time_added time, date date, time time, address VARCHAR, latitude FLOAT, longitude FLOAT);";
	
	private static final String DATE_CREATE = "create table if not exists DATE (DATE date)";
	
	private static final String DATABASE_CREATE_HOSPITALS = "create table if not exists hospitals (id integer primary key autoincrement, hospital_name VARCHAR not null, timestamp time, address VARCHAR, latitude FLOAT, longitude FLOAT);" ;
	
	private static final String DATABASE_CREATE_POLICEDEPT = "create table if not exists policedept (id integer primary key autoincrement, dept_name VARCHAR not null, timestamp time, address VARCHAR, latitude FLOAT, longitude FLOAT);" ;
	
	private static final String DATABASE_CREATE_FIREDEPT = "create table if not exists firedept (id integer primary key autoincrement, dept_name VARCHAR not null, timestamp time, address VARCHAR, latitude FLOAT, longitude FLOAT);" ;
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	public String todaysdate;
	
	private final Context context;
	
	public DatabaseHelper DBHelper;
	public SQLiteDatabase db;
	
	public DBAdapter(Context context) {
		this.context = context;
		DBHelper = new DatabaseHelper(context);
	}
	
	public class DatabaseHelper extends SQLiteOpenHelper
	{
		public DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			try{
				
				Log.d(TAG,  "onCreate with SQL Command" + DATABASE_CREATE);
				
				db.execSQL(DATABASE_CREATE);
				db.execSQL(DATE_CREATE);
				Calendar date = Calendar.getInstance();
				int month = date.get(date.MONTH);
				int year = date.get(date.YEAR);
				int day = date.get(date.DAY_OF_MONTH);
				todaysdate = month + "/" + day + "/" + year;
				db.execSQL("INSERT INTO DATE(DATE) VALUES("+todaysdate+");");
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + "to " +
					newVersion + ", which will destroy all old data");
			
			db.execSQL("DROP TABLE IF EXISTS crimes");
			onCreate(db);
		}
		
		
		
		
		
		
		//--Opens the database-----------
		public DatabaseHelper open() throws SQLException
		{
			db = DBHelper.getWritableDatabase();
			return this;
		}
		
		
		
		
		
		
		//---closes the database--------
		public void close()
		{
			DBHelper.close();
		}
		
		
		
		public long insertRecord(LinkedList<ContentValues> crimeREPORTS)
		{
			Time _time = new Time();
			_time.setToNow();
			
//			ContentValues initialValues = new ContentValues();
//			initialValues.put(KEY_INCIDENT, incident);
//			initialValues.put(KEY_TIMESTAMP, _time.toString());
//			initialValues.put(KEY_DATE, date);
//			initialValues.put(KEY_TIME, time);
//			initialValues.put(KEY_ADDRESS, address);
//			initialValues.put(KEY_LATITUDE, latitude);
//			initialValues.put(KEY_LONGITUDE, longitude);
			
			while (crimeREPORTS.size() > 1){
				db.insert(DATABASE_TABLE, null, crimeREPORTS.pop());
			}
			
			return db.insert(DATABASE_TABLE, null, crimeREPORTS.pop());
		}
		
		
		
		//--deletes a particular record------
		public boolean delete(long rowId)
		{
			return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
		}
		
		
		
		//--Retrieves all records-------
		public Cursor getAllRecords(long rowId)
		{
			return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_INCIDENT, KEY_TIMESTAMP, KEY_DATE, KEY_TIME, KEY_ADDRESS, KEY_LATITUDE, KEY_LONGITUDE},
					null, null, null, null, null);
		}
		
		
		
		
		//--retrieves a particular record--------
		public Cursor getRecord(long rowId) throws SQLException
		{
			Cursor mCursor = 
					db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_INCIDENT, KEY_TIMESTAMP, KEY_DATE, KEY_TIME, KEY_ADDRESS, KEY_LATITUDE, KEY_LONGITUDE}
					,KEY_ROWID + "=" + rowId, null, null, null, null, null);
			if(mCursor != null)
				mCursor.moveToFirst();
			return mCursor;
		}
		
		
		
		
		//----updates a record
		public boolean updateRecord(long rowId, String incident, String timestamp, String date, String time, String address, float latitude, float longitude)
		{
			ContentValues args = new ContentValues();
			args.put(KEY_INCIDENT, incident);
			args.put(KEY_TIMESTAMP, timestamp);
			args.put(KEY_DATE, date);
			args.put(KEY_TIME, time);
			args.put(KEY_ADDRESS, address);
			args.put(KEY_LATITUDE, latitude);
			args.put(KEY_LONGITUDE, latitude);
			return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
		}
		public String getDate(){
			Cursor timeData = getReadableDatabase().rawQuery("SELECT * FROM DATE", null);
			String date = timeData.getString(timeData.getColumnIndex("DATE"));
			return date;
		}
		
	}
	
}
