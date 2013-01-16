package gcvc.maps.app;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.preference.PreferenceActivity.Header;

public class cUrl extends AsyncTask<String, Void, Void>{
	
	@Override
	protected Void doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("https://stream.twitter.com/1.1/statuses/filter.json?follow=SeattlePD01, SeattlePDO2");
		Header header = new Header();
		return null;
	}
}
