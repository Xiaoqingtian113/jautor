package quark.jautor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class CmdUtil {
	public static void runCmd(String cmd,long waitTime) throws IOException, InterruptedException {
		Runtime.getRuntime().exec(cmd);
		Thread.sleep(waitTime);
	}
	public static boolean runCmdAndWait(String cmd, int timeout) throws IOException, InterruptedException{
		Process pro = Runtime.getRuntime().exec(cmd);
		boolean isover = pro.waitFor(timeout, TimeUnit.SECONDS);
		return isover;
	}
	public static String runCmdWithResult(String cmd) throws IOException{
		Process pro = Runtime.getRuntime().exec(cmd);
		BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream(),"gbk"));
		String str = "";
		StringBuffer result = new StringBuffer();
		while((str=reader.readLine())!=null){
			result = result.append(str + "\n");
		}
		reader.close();
		return result.toString();
	}
}
