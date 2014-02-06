package com.example.asyncx;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskActivity extends Activity implements View.OnClickListener {

	private EditText mInputEditText;
	private String mResponseString;
	private URLClass mURLClass;
	public static List<GeoName> mListOfGeoName = new ArrayList<GeoName>();

    public AsyncTaskActivity() {
        //pass in : baseurl
        // array of key values
        mURLClass = new URLClass();
    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		mInputEditText = (EditText) findViewById(R.id.editText);

        Button mBtnButton;
		mBtnButton = (Button) findViewById(R.id.readWebpage);
		mBtnButton.setOnClickListener(this);
	}

	public void onClick(View view) {
		try {
			String httpRequest = mURLClass.getURLWithPostalCode(mInputEditText.getText().toString());
			Log.d("DEBUG",httpRequest);
            DownloadWebPageTask task = new DownloadWebPageTask();
			task.execute(httpRequest);
		}
		catch (NullPointerException e) {
			Log.d("DEBUG EXCEPTION:", e.toString());
		}
	}

	public void asyncTaskCallBack() throws IndexOutOfBoundsException {
		mListOfGeoName = mURLClass.parseJson(mResponseString);
		try {
			Intent intent = new Intent(AsyncTaskActivity.this, ResultActivity.class);
			ArrayList<GeoName> geoNameList;
			geoNameList = (ArrayList<GeoName>) mListOfGeoName;

            Log.d("DEBUG  list: ", String.valueOf(geoNameList));
            for (GeoName geoname : geoNameList){
                Log.d("DEBUG: ", geoname.getPlaceName());
            }

			intent.putParcelableArrayListExtra("data",  geoNameList);
			startActivity(intent);
		}
		catch (Exception e) {
			Log.d("DEBUG", e.toString());
		}

	}

	private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
					String s;
					while ((s = buffer.readLine()) != null) {
						response += s;
					}

				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) throws IndexOutOfBoundsException {
			mResponseString = result;
            Log.d("DEBUG mResponseString",mResponseString);
			AsyncTaskActivity.this.asyncTaskCallBack();
		}
	}
}
