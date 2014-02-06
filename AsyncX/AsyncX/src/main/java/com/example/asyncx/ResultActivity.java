package com.example.asyncx;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ResultActivity extends Activity {

	private ListView mListView;
	private List<GeoName> mListOfGeoName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		mListOfGeoName = getIntent().getParcelableArrayListExtra("data");

        GeoName geo = mListOfGeoName.get(0);
		arrayOfGeoNameToListView();
	}

	public void arrayOfGeoNameToListView() {
		mListView = (ListView) findViewById(R.id.hashMap_list);
		try {
			ArrayOfObjectsListAdapter arrayOfObjectsListAdapter = new ArrayOfObjectsListAdapter(ResultActivity.this, mListOfGeoName);
			mListView.setAdapter(arrayOfObjectsListAdapter);
		}
		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
			finish();
		}
	}

}
