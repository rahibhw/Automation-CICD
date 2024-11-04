package org.sdet.myProject.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub - should we re-run the Flaky failed test
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
