package gcvc.maps.app;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataLayer {
	private DbHelper _dbHelper;
	
	public DataLayer(Context c) {
		_dbHelper = new DbHelper(c);
		_dbHelper.onCreate(_dbHelper.getReadableDatabase());
	}
	
	public void AddIncident(String Tweet, String Username,String Address, 
			String Incident, double Latitude, double Longitude, String Date, String Time){
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		try{
			ContentValues values = new ContentValues();
			values.put(DbHelper.KEY_TWEET, Tweet);
			values.put(DbHelper.KEY_USERNAME, Username);
			values.put(DbHelper.KEY_ADDRESS, Address);
			values.put(DbHelper.KEY_INCIDENT, Incident);
			values.put(DbHelper.KEY_LATITUDE, Latitude);
			values.put(DbHelper.KEY_LONGITUDE, Longitude);
			values.put(DbHelper.KEY_DATE, Date);
			values.put(DbHelper.KEY_TIME, Time);
			
			db.insert(DbHelper.DATABASE_NAME, "", values);
		}finally{
			if(db!= null)
				db.close();
		}
	}
	
	public ArrayList<QueryResults> SelectIncidents(){
		SQLiteDatabase db = _dbHelper.getReadableDatabase();
		try{
			ArrayList<QueryResults> results = new ArrayList<QueryResults>();
			Cursor c = db.rawQuery("SELECT * FROM " + DbHelper.DATABASE_NAME, null);
			if(c.getCount() > 0){
				c.moveToFirst();
				do{
					results.add(new QueryResults(
							c.getString(c.getColumnIndex(DbHelper.KEY_TWEET)),
							c.getString(c.getColumnIndex(DbHelper.KEY_USERNAME)),
							c.getString(c.getColumnIndex(DbHelper.KEY_ADDRESS)),
							c.getString(c.getColumnIndex(DbHelper.KEY_INCIDENT)),
							c.getDouble(c.getColumnIndex(DbHelper.KEY_LATITUDE)),
							c.getDouble(c.getColumnIndex(DbHelper.KEY_LONGITUDE)),
							c.getString(c.getColumnIndex(DbHelper.KEY_DATE)),
							c.getString(c.getColumnIndex(DbHelper.KEY_TIME))));
				}while(c.moveToNext());
			}
			return results;
		}finally{
			if(db != null)
				db.close();
		}
	}

}
