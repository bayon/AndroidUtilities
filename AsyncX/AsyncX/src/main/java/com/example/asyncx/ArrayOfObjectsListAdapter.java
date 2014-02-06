package com.example.asyncx;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BForte on 2/3/14.
 */
public class ArrayOfObjectsListAdapter extends BaseAdapter {

	private List<GeoName> mArrayOfGeoName;
	private LayoutInflater mLayoutInflater;

	public ArrayOfObjectsListAdapter(Context context, List<GeoName> mListOfGeoName) {
		this.mArrayOfGeoName = mListOfGeoName;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mArrayOfGeoName.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayOfGeoName.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.list_geoname_row_layout, null);
			holder = new ViewHolder();

			holder.adminCode3 = (TextView) convertView.findViewById(R.id.adminCode3);
			holder.adminCode2 = (TextView) convertView.findViewById(R.id.adminCode2);
			holder.adminCode1 = (TextView) convertView.findViewById(R.id.adminCode1);
			holder.adminName1 = (TextView) convertView.findViewById(R.id.adminName1);
			holder.adminName2 = (TextView) convertView.findViewById(R.id.adminName2);
			holder.postalCode = (TextView) convertView.findViewById(R.id.postalCode);
			holder.countryCode = (TextView) convertView.findViewById(R.id.countryCode);
			holder.lat = (TextView) convertView.findViewById(R.id.lat);
			holder.lng = (TextView) convertView.findViewById(R.id.lng);
			holder.placeName = (TextView) convertView.findViewById(R.id.placeName);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
        Log.d("DEBUG",mArrayOfGeoName.get(position).getPlaceName());// literally "placeName"
		holder.adminCode3.setText("" + mArrayOfGeoName.get(position).getAdminCode3());
		holder.adminCode2.setText("" + mArrayOfGeoName.get(position).getAdminCode2());
		holder.adminCode1.setText("" + mArrayOfGeoName.get(position).getAdminCode1());
		holder.adminName1.setText("" + mArrayOfGeoName.get(position).getAdminName1());
		holder.adminName2.setText("" + mArrayOfGeoName.get(position).getAdminName2());
		holder.postalCode.setText("" + mArrayOfGeoName.get(position).getPostalCode());
		holder.countryCode.setText("" + mArrayOfGeoName.get(position).getCountryCode());
		holder.lng.setText("" + mArrayOfGeoName.get(position).getLng());
		holder.lat.setText("" + mArrayOfGeoName.get(position).getLat());
		holder.placeName.setText("" + mArrayOfGeoName.get(position).getPlaceName());

		return convertView;
		//return null;
	}

	static class ViewHolder {
		TextView adminCode3;
		TextView adminName2;
		TextView adminCode2;
		TextView postalCode;
		TextView adminCode1;
		TextView countryCode;
		TextView lng;
		TextView placeName;
		TextView lat;
		TextView adminName1;

	}

}
