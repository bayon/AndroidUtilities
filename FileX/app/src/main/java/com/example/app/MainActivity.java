package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private File root = new File(Environment.getExternalStorageDirectory(), "Notes");
    private String mAbsoluteStoragePath;
    private String mExternalStorageStatus;
    private  TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.text_view);

        // GET ABSOLUTE PATH IF NECESSARY
        mAbsoluteStoragePath =  Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("DEBUG mAbsoluteStoragePath:",mAbsoluteStoragePath);

        //CHECK IF EXTERNAL STORAGE IS AVAILABLE
        mExternalStorageStatus = Environment.getExternalStorageState();
        if(mExternalStorageStatus.equals("mounted")){
            generateNoteOnSD("foofile.txt","Hey there worldlings....");
        }

        readATextFile();
    }

    public void generateNoteOnSD(String sFileName, String sBody){

        try
        {
           root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public void readATextFile(){

         File file = new File(root,"foofile.txt");
         StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
         tv.setText(text);
    }

}
