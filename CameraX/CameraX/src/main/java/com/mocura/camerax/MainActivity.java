package com.mocura.camerax;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener {



    private Button btn01;
    private Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01 = (Button) findViewById(R.id.btn01);
        btn01.setOnClickListener(this);
        btn02 = (Button) findViewById(R.id.btn02);
        btn02.setOnClickListener(this);
        
        

    }



    @Override
    public void onClick(View v) {

            Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
            imagesFolder.mkdirs(); // <----
            File image = new File(imagesFolder, "image_001.jpg");
            Uri uriSavedImage = Uri.fromFile(image);
            imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
            startActivityForResult(imageIntent,0);

        //Now this will store your camera captured images in MyImages directory in sdcard with image_001.jpg name.

        String photoPath = Environment.getExternalStorageDirectory()+"/image_001.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);



    }



}
