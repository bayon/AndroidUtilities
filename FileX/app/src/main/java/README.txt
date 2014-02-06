http://developer.android.com/training/basics/data-storage/files.html



//manifest
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />


//Internal or External Storage

//INTERNAL
//create and save
File file = new File(context.getFilesDir(), filename);

//write text to a file
String filename = "myfile";
String string = "Hello world!";
FileOutputStream outputStream;

try {
  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
  outputStream.write(string.getBytes());
  outputStream.close();
} catch (Exception e) {
  e.printStackTrace();
}


//cache some files
public File getTempFile(Context context, String url) {
    File file;
    try {
        String fileName = Uri.parse(url).getLastPathSegment();
        file = File.createTempFile(fileName, null, context.getCacheDir());
    catch (IOException e) {
        // Error while creating file
    }
    return file;
}



//EXTERNAL

//save
/* Checks if external storage is available for read and write */
public boolean isExternalStorageWritable() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
        return true;
    }
    return false;
}

/* Checks if external storage is available to at least read */
public boolean isExternalStorageReadable() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state) ||
        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        return true;
    }
    return false;
}

//public or private ?

public File getAlbumStorageDir(String albumName) {
    // Get the directory for the user's public pictures directory.
    File file = new File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES), albumName);
    if (!file.mkdirs()) {
        Log.e(LOG_TAG, "Directory not created");
    }
    return file;
}


//Create Directory for photo album
public File getAlbumStorageDir(Context context, String albumName) {
    // Get the directory for the app's private pictures directory.
    File file = new File(context.getExternalFilesDir(
            Environment.DIRECTORY_PICTURES), albumName);
    if (!file.mkdirs()) {
        Log.e(LOG_TAG, "Directory not created");
    }
    return file;
}


// DELETE
//internal
myContext.deleteFile(fileName);
//external
myFile.delete();





////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Quick File Write and Read

//manifest
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />


 //java
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


//XML
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.app.MainActivity"
    tools:ignore="MergeRootFrame" >

    <LinearLayout
        android:orientation="vertical"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_gravity="center">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="New Text"
            android:id="@+id/text_view"
            android:layout_gravity="center_horizontal" />
    </LinearLayout>
</FrameLayout>





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

