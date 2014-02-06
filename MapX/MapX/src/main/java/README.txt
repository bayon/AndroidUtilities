// GRADLE

dependencies {
    compile 'com.google.android.gms:play-services:4.0.30'
    compile 'com.android.support:support-v4:19.0.1+'
}


// MANIFEST
  <permission
        android:name="com.mocura.gmap1.permission.MAPS_RECEIVE"
        android:protectionLevel="signature" />

    <uses-feature
        android:glEsVersion="0x00020000"
        android:required="true" />

    <uses-permission android:name="com.mocura.gmap1.permission.MAPS_RECEIVE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />


    <!-- ... then,  inside application tags -->
     <meta-data
                android:name="com.google.android.maps.v2.API_KEY"
                android:value="AIzaSyDtZ2rRf1s8U1yYST_94OeqRZrk8sg7k2w" />
            <meta-data
                android:name="com.google.android.gms.version"
                android:value="4030500" />

                <!-- 4030500 or @integer/google_play_services_version   -->

// XML
activity_main.xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.mapx.MainActivity"
    tools:ignore="MergeRootFrame" />

fragment_about_us.xml
<?xml version="1.0" encoding="utf-8"?>

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:id="@+id/containerDeAboutUs"
    android:layout_alignParentLeft="true">
    <LinearLayout
        android:orientation="vertical"
        android:layout_width="fill_parent"
        android:layout_height="100dp"
        android:layout_alignParentTop="true"
        android:background="@android:color/white">
        <ImageView
            android:autoLink="web"
            android:text="http://www.mocura.com"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/imageLink"
            android:background="@drawable/ic_launcher"
            android:layout_gravity="center_horizontal"
            android:layout_marginTop="10dp" />
    </LinearLayout>
    <RelativeLayout
        android:id="@+id/smallMap"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="100dp">
    </RelativeLayout>
</RelativeLayout>



// fragment_main.xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context="com.example.mapx.MainActivity$PlaceholderFragment">
    <TextView
        android:text="@string/hello_world"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</RelativeLayout>



// JAVA
MainActivity.java
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import map.AboutUsFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AboutUsFragment())
                    .commit();
        }
    }
}


// JAVA

AboutUsFragment.java

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mapx.R;

/**
 * Created by BForte on 1/30/14.
 */
public class AboutUsFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container,
                false);

        getFragmentManager().beginTransaction().add(R.id.smallMap, new AboutUsMapFragment()).commit();

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://mocura.com"));
        startActivity(intent);
    }
}


// JAVA

AboutUsMapFragment.java

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.mapx.R;
/**
 * Created by BForte on 1/30/14.
 */
public class AboutUsMapFragment extends MapFragment   {

    static final LatLng INDATUS = new LatLng(38.2865, -85.8235);

    public AboutUsMapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        ImageView imgLink=(ImageView) getActivity().findViewById(R.id.imageLink);
        imgLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mocura.com"));
                startActivity(link);
            }
        });
        getMap().moveCamera((CameraUpdateFactory.newLatLngZoom(INDATUS, 15)));
        getMap().setMyLocationEnabled(true);
        getMap().addMarker(new MarkerOptions().position(INDATUS)
                .title("Indatus"));

    }

}








