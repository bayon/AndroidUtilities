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
