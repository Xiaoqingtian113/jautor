package quark.jautor.testng;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import quark.jautor.util.DateUtil;

public class ScreenShotListener extends TestListenerAdapter {
	public void onTestFailure(ITestResult result) {
		super.onTestFailure(result);
		logScreenShot(result);
	}

	public void onTestSkipped(ITestResult result) {
		super.onTestSkipped(result);
		logScreenShot(result);
	}

	private void logScreenShot(ITestResult result) {
		if (!result.isSuccess()) {
			Date date = new Date();
			String imageName = DateUtil.dateToStr("yyyyMMddHHmmssSSS", date) + ".png";
			String ip = null;
			int port = 8080;
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			File screeshot = new File("res/icon/screenshot.png");
			result.setAttribute("imageName", imageName);
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Reporter.log("<a href=" + "http://" + ip + ":" + port + "/images/" + imageName + " target=_blank style=color:red>"
					+ "<img src=" + screeshot.getAbsolutePath() + " style=width:16px;height:16px align=absmiddle><b>" + imageName
					+ "</b></a>", true);
		}
	}
}
