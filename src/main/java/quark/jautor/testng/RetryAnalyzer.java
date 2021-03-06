package quark.jautor.testng;

import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import quark.jautor.core.PropertiesUtil;

public class RetryAnalyzer implements IRetryAnalyzer {
	private static int maxRetryCount;
	static {
		Properties prop = PropertiesUtil.read("config/global.properties");
		maxRetryCount = Integer.parseInt(prop.getProperty("maxRetryCount"));
	}
	private int retryCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			Reporter.log("第" + retryCount + "次Retry");
			retryCount++;
			return true;
		}
		return false;
	}
}