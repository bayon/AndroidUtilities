

// GRADLE

dependencies {
    compile 'com.android.support:support-v4:19.0.1'
}



// XML

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:layout_gravity="center_horizontal|top">
    <EditText
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:inputType="date"
        android:ems="10"
        android:id="@+id/add_work_inputDate"
        android:layout_gravity="center_horizontal"
        android:hint="Date" />
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="OK"
        android:id="@+id/add_work_btnCreate" />


</LinearLayout>


// JAVA

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener, View.OnTouchListener  {

    private Button btnCreate;
    private EditText inputDate;
    private boolean mFired = false;

    /**
     * This method sets up the UI, including a "Date Dialog".
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate = (Button) findViewById(R.id.add_work_btnCreate);
        btnCreate.setOnClickListener(this);
        inputDate = (EditText) findViewById(R.id.add_work_inputDate);
        inputDate.setFocusable(false);
        inputDate.setClickable(true);
        inputDate.setOnTouchListener(this);
    }

    /**
     * This method is the OnClickListener delegate method that receives the user input, parses the data accordingly, and if requirements are met, creates a new
     * Hours record. Otherwise it prompts the user to correct their errors.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        String str_date = String.valueOf(inputDate.getText());
            //Screen Input Data
            if (!str_date.equals("")) {
                // DO SOMETHING WITH THE DATE FROM THE TEXT FIELD
                Log.d("DEBUG",str_date);
            } else {
                alertUserToFixDataEntered();
            }
    }

    /**
     * This method creates and displays the inputDate dialog.
     */
    public void alertUserToFixDataEntered() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Make sure the data you entered is correct.Thank you.");
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //Now users are aware that they need to re-enter a piece of data.
            }
        });
        alertDialog.show();
    }

    /**
     * This method creates a date picker when the input text for date is clicked.
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        showDatePickerDialog(v);
        return false;
    }

    /**
     * NEW DATE PICKER
     */

    public void showDatePickerDialog(View v) {
        if (mFired) {
            Log.d("DEBUG", "Double showDatePickerDialog dismiss duplicate fire.(per android Issue 64895: Issue 34860 )");
        } else {
            mFired = true;
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");
        }
    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String time = Integer.toString(month + 1) + " / " + Integer.toString(day) + " / " + Integer.toString(year);
            inputDate.setText(time);

            mFired = false;
        }
    }

}