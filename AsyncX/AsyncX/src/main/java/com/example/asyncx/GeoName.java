package com.example.asyncx;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by BForte on 12/18/13.
 */
public class GeoName implements Parcelable {

	private String mAdminCode3 = "adminCode3";
	private String mAdminName2 = "adminName2";
	private String mAdminCode2 = "adminCode2";
	private String mPostalCode = "postalCode";
	private String mAdminCode1 = "adminCode1";
	private String mCountryCode = "countryCode";
	private String mLng = "lng";
	private String mPlaceName = "placeName";
	private String mLat = "lat";
	private String mAdminName1 = "adminName1";

	private static String TAG = "DEBUG";

	private String[] geoNameFields;

	public GeoName() {
		String[] geoNameFields = {mAdminCode3, mAdminName2, mAdminCode2, mPostalCode, mAdminCode1, mCountryCode, mLng, mPlaceName, mLat, mAdminName1};
		this.geoNameFields = geoNameFields;

	}

	// PARCELABLE CONSTRUCTOR:
	public GeoName(Parcel in) {
		Log.d(TAG, "parcel in");
		mAdminCode3 = in.readString();
		mAdminCode2 = in.readString();
		mAdminCode1 = in.readString();
		mAdminName1 = in.readString();
		mAdminName2 = in.readString();
		mPostalCode = in.readString();
		mCountryCode = in.readString();
		mLat = in.readString();
		mLng = in.readString();
		mPlaceName = in.readString();
	}

	public String[] getGeoNameFields() {
		return geoNameFields;
	}

	public String getAdminCode3() {
		return mAdminCode3;
	}

	public void setAdminCode3(String adminCode3) {
		this.mAdminCode3 = adminCode3;
	}

	public String getAdminName2() {
		return mAdminName2;
	}

	public void setAdminName2(String adminName2) {
		this.mAdminName2 = adminName2;
	}

	public String getAdminCode2() {
		return mAdminCode2;
	}

	public void setAdminCode2(String adminCode2) {
		this.mAdminCode2 = adminCode2;
	}

	public String getAdminCode1() {
		return mAdminCode1;
	}

	public void setAdminCode1(String adminCode1) {
		this.mAdminCode1 = adminCode1;
	}

	public String getCountryCode() {
		return mCountryCode;
	}

	public void setCountryCode(String countryCode) {
		this.mCountryCode = countryCode;
	}

	public String getLng() {
		return mLng;
	}

	public void setLng(String lng) {
		this.mLng = lng;
	}

	public String getPlaceName() {
		return mPlaceName;
	}

	public void setPlaceName(String placeName) {
		this.mPlaceName = placeName;
	}

	public String getLat() {
		return mLat;
	}

	public void setLat(String lat) {
		this.mLat = lat;
	}

	public String getAdminName1() {
		return mAdminName1;
	}

	public void setAdminName1(String adminName1) {
		this.mAdminName1 = adminName1;
	}

	public String getPostalCode() {
		return mPostalCode;
	}

	public void setPostalCode(String postalCode) {
		this.mPostalCode = postalCode;
	}

	// PARCELABLE METHOD:

	public static final Parcelable.Creator<GeoName> CREATOR = new Parcelable.Creator<GeoName>() {
		public GeoName createFromParcel(Parcel in) {
			Log.d(TAG, "createFromParcel()");
			return new GeoName(in);
		}

		public GeoName[] newArray(int size) {
			Log.d(TAG, "createFromParcel() newArray ");
			return new GeoName[size];
		}
	};

	// PARCELABLE METHOD:
	@Override
	public int describeContents() {
		Log.d(TAG, "describe()");
		return 0;
	}

	// PARCELABLE METHOD:
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.d(TAG, "writeToParcel");
		dest.writeString(mAdminCode1);
		dest.writeString(mAdminCode2);
		dest.writeString(mAdminCode3);
		dest.writeString(mAdminName1);
		dest.writeString(mAdminName2);
		dest.writeString(mPostalCode);
		dest.writeString(mCountryCode);
		dest.writeString(mLat);
		dest.writeString(mLng);
		dest.writeString(mPlaceName);

	}
}
