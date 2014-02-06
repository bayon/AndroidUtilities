package map;

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