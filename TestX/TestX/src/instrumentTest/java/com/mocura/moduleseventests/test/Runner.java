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
