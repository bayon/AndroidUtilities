package com.example.asyncx;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BForte on 2/5/14.
 */
public class URLClass {

    private String postalCode;
    private String softURL;

    public String getURLWithPostalCode(String code){
        this.postalCode = code;
        this.softURL = "http://api.geonames.org/postalCodeLookupJSON?postalcode="+postalCode+"&username=indatustest";

        return this.softURL;
    }

    public ArrayList<GeoName> parseJson(String responseString ) {
        ArrayList<GeoName> mListOfGeoName = new ArrayList<GeoName>();
        JSONObject jsonResponse;
        try {
            jsonResponse = new JSONObject(responseString);

            JSONArray jsonMainNode = jsonResponse.optJSONArray("postalcodes");
            int lengthJsonArr = jsonMainNode.length();

            for (int i = 0; i < lengthJsonArr; i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                GeoName geoName = new GeoName();

                String[] arrayOfFieldnames = geoName.getGeoNameFields();
                HashMap<String, String> arrayOfData = new HashMap<String, String>();
                for (int j = 0; j < arrayOfFieldnames.length; j++) {
                    String key = arrayOfFieldnames[j];
                    String value = jsonChildNode.optString(arrayOfFieldnames[j]).toString();
                    arrayOfData.put(key, value);
                    if(key.equals("adminCode3")){
                        geoName.setAdminCode3(value);
                    }
                    if(key.equals("adminCode2")){
                        geoName.setAdminCode2(value);
                    }
                    if(key.equals("adminCode1")){
                        geoName.setAdminCode1(value);
                    }
                    if(key.equals("adminName2")){
                        geoName.setAdminName2(value);
                    }
                    if(key.equals("adminName1")){
                        geoName.setAdminName1(value);
                    }
                    if(key.equals("lat")){
                        geoName.setLat(value);
                    }
                    if(key.equals("lng")){
                        geoName.setLng(value);
                    }
                    if(key.equals("postalcode")){
                        geoName.setPostalCode(value);
                    }
                    if(key.equals("countryCode")){
                        geoName.setCountryCode(value);

                    }
                    if(key.equals("placeName")){
                        geoName.setPlaceName(value);
                        Log.d("DEBUG placeName value: ", value);
                    }

                }
                mListOfGeoName.add(geoName);

            } // END LOOP through all results

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mListOfGeoName;
    }

}
