package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend  = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

    }

    public void emailWithAnAttachment(){
        Intent emailWithAttachmentsIntent = new Intent(Intent.ACTION_SEND);
        emailWithAttachmentsIntent.setType("text/plain");
        emailWithAttachmentsIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"email@example.com"});
        emailWithAttachmentsIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
        emailWithAttachmentsIntent.putExtra(Intent.EXTRA_TEXT, "body text");
        File root = Environment.getExternalStorageDirectory();
        String pathToMyAttachedFile="temp/attachement.xml";
        File file = new File(root, pathToMyAttachedFile);
        if (!file.exists() || !file.canRead()) {
            return;
        }
        Uri uri = Uri.fromFile(file);
        emailWithAttachmentsIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(emailWithAttachmentsIntent, "Pick an Email provider"));
    }

    public void sendBasicEmail(){
        Intent intentEmail = new Intent(Intent.ACTION_SEND);
        intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"bforte@indatus.com"});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Android Email");
        intentEmail.putExtra(Intent.EXTRA_TEXT, "sendBasicEmail");
        intentEmail.setType("message/rfc822");
        startActivity(Intent.createChooser(intentEmail, "Choose an email provider :"));
    }

    @Override
    public void onClick(View v) {
        sendBasicEmail();
    }
}
