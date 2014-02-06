
//TESTX : unit testing and automation


// Gradle : remember to sync gradle afterwards.
dependencies {
    compile 'com.jayway.android.robotium:robotium-solo:4.3.1@jar'
}



 defaultConfig {

        testPackageName "com.example.testx.test"
        testInstrumentationRunner "com.example.testx.test.Runner"

        OR

         testPackageName "com.mocura.moduleseventests.test"
                testInstrumentationRunner "com.mocura.moduleseventests.test.Runner"
    }




// JAVA : RUNNER
    package com.mocura.moduleseventests.test;

    import android.test.InstrumentationTestRunner;
    import android.test.InstrumentationTestSuite;

    import com.mocura.moduleseventests.MainActivityTest;

    import junit.framework.TestSuite;

    /**
     * Created by swav on 21/08/13.
     */
    public class Runner extends InstrumentationTestRunner {

    	@Override
    	public TestSuite getAllTests() {
    		InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
    		suite.addTestSuite(MainActivityTest.class);

    		//suite.addTestSuite(DatabaseTest.class);
    		//suite.addTestSuite(InstrumentationTest.class);

    		return suite;
    	}

    	@Override
    	public ClassLoader getLoader() {
    		return Runner.class.getClassLoader();
    	}

    }



// JAVA : DATABASE TEST
package com.mocura.moduleseventests.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.AndroidTestCase;
import android.util.Log;

import com.mocura.moduleseven.helper.Helper;
import com.mocura.moduleseven.models.Hour;

import java.util.List;

/**
 * Created by BForte on 1/16/14.
 */

/**
 * This class is a unit test for the CRUD methods of the Hours class.
 */

public class DatabaseTest extends AndroidTestCase {

	private Context mContext;
	private boolean mDatabaseExists = false;
	public Helper mXHelper;

	/**
	 * This method makes sure the database exists before trying to do any tests by creating a boolean flag, mDatabaseExists.
	 *
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getContext();

		mXHelper = new Helper(mContext);
		mXHelper.open();

		String pathToDatabase = String.valueOf(mContext.getDatabasePath("Hour"));
		SQLiteDatabase checkDB = null;
		// SQLiteDatabase mDatabase;
		try {
			checkDB = SQLiteDatabase.openDatabase(pathToDatabase, null, SQLiteDatabase.OPEN_READONLY);
			checkDB.close();
		}
		catch (SQLiteException e) {
			// database doesn't exist yet.
		}

		if (checkDB != null) {
			mDatabaseExists = true;
			//mDatabase = checkDB;
		} else {
			mDatabaseExists = false;
		}
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mXHelper.close();
	}

	/**
	 * This method tests whether an Hours object was created by verifying that the company name sent in, equals the company name that gets sent out.
	 */
	public void testCRUD() {
		if (mDatabaseExists) {

			try {

				//create
				Hour xhour1 = new Hour("TestCompanyName", 1.2, "1/22/2014");
				mXHelper.createXHour(xhour1);

				//read
				List<Hour> hourList = mXHelper.readAllXHour();
				Hour lastHour = hourList.get(hourList.size() - 1);
				assertEquals(lastHour.getCompany(), "TestCompanyName");

				//update
				lastHour.setCompany("UpdatedCompany");
				mXHelper.updateHours(lastHour);
				List<Hour> xUpdatedHourList = mXHelper.readAllXHour();
				Hour lastUpdatedHour = hourList.get(xUpdatedHourList.size() - 1);
				assertEquals(lastUpdatedHour.getCompany(), "UpdatedCompany");

				//delete
				int countBeforeDelete = xUpdatedHourList.size() - 1;
				mXHelper.deleteXHour(lastUpdatedHour);
				List<Hour> xDeletedHourList = mXHelper.readAllXHour();
				int countAfterDelete = xDeletedHourList.size() - 1;
				assertTrue("Delete Test Failed:", countBeforeDelete - 1 == countAfterDelete);

			}
			catch (Exception e) {
				Log.d("DEBUG", e.toString());
			}

		} else {
			String database = "database";
			assertEquals("databaseDoesNotExist", database);
		}
	}

}


// JAVA : Robotium Automation
package com.mocura.moduleseventests.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.jayway.android.robotium.solo.Solo;
import com.mocura.moduleseven.AddWorkActivity;
import com.mocura.moduleseven.EditActivity;
import com.mocura.moduleseven.MainActivity;

/**
 * Created by BForte on 1/20/14.
 */

/**
 * This class is a test to automate the application and verify that certain tasks are completed correctly. Your instrumentationTests should not be a subpackage
 * 'tests'; rather they should be their own directory and package entirely.
 */

public class InstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	public int countBeforeDeletion;
	public int countAfterDeletion;

	public InstrumentationTest() {

		super(MainActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());

	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();

	}

	public void testCreateButton1() {
		solo.assertCurrentActivity("wrong activity", MainActivity.class);
		solo.clickOnActionBarItem(com.mocura.moduleseven.R.id.addWork);
		solo.assertCurrentActivity("on the Add Activity screen", AddWorkActivity.class);
		solo.enterText(0, "Indatus");
		solo.enterText(1, "22.7");
		solo.enterText(2, "1/19/2014");
		solo.clickOnButton("OK");
		solo.sleep(1000);
	}

	public void testCreateButton2() {
		solo.assertCurrentActivity("wrong activity", MainActivity.class);
		solo.clickOnActionBarItem(com.mocura.moduleseven.R.id.addWork);
		solo.assertCurrentActivity("on the Add Activity screen", AddWorkActivity.class);
		solo.enterText(0, "Mocura");
		solo.enterText(1, "11.02");
		solo.enterText(2, "1/20/2014");
		solo.clickOnButton("OK");
		solo.sleep(1000);
	}

	public void testCreateButton3() {
		solo.assertCurrentActivity("wrong activity", MainActivity.class);
		solo.clickOnActionBarItem(com.mocura.moduleseven.R.id.addWork);
		solo.assertCurrentActivity("on the Add Activity screen", AddWorkActivity.class);
		solo.enterText(0, "Google");
		solo.enterText(1, "26.54");
		solo.enterText(2, "1/21/2014");
		solo.clickOnButton("OK");
		solo.sleep(1000);
	}

	public void testDelete() {
		ListView list = (ListView) solo.getView(com.mocura.moduleseven.R.id.main_mHoursList);
		countBeforeDeletion = list.getAdapter().getCount();
		/* Long click 3 rows and Delete them */
		solo.clickLongInList(countAfterDeletion);
		solo.clickOnButton("Yes");
		solo.clickLongInList(countAfterDeletion - 1);
		solo.clickOnButton("Yes");
		solo.clickLongInList(countAfterDeletion - 2);
		solo.clickOnButton("Yes");
		solo.sleep(3000); //wait and make sure process is completed.
		/* verify that they were deleted */
		int countAfterDeletionOf3Records = list.getAdapter().getCount();
		if (countBeforeDeletion == countAfterDeletionOf3Records + 3) {
			//force a success
			solo.assertCurrentActivity("TEST-SUCCESS ", MainActivity.class);
		} else {
			//force a fail
			solo.assertCurrentActivity("TEST-FAIL ", EditActivity.class);
		}
	}

}

// JAVA : MainActivity Test
package com.mocura.moduleseventests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.example.testx.MainActivity;

/**
 * Created by swav on 21/08/13.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
	public MainActivityTest() {
		super(MainActivity.class);
	}

	@MediumTest
	public void testToFail() {
		fail("nil");
	}

}


// Run > Edit Configurations >
    select the module and the runner appropriately.



