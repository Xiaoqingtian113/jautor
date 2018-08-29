package quark.jautor.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SystemManager {
	/**
	 *  获取当前设备的Map
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String,String> getCurrentDevices() throws IOException {
		HashMap<String, String> result = new HashMap<String, String>();
		String baseCmd = "cmd /c ";
		if(!isMacOS())
			baseCmd = "";
		String androidCmd = baseCmd + "adb devices";
		String androidResp = CmdUtil.runCmdWithResult(androidCmd);
		String[] androidDeviceInfos = androidResp.split("\n");
		for (int i=1; i<androidDeviceInfos.length; i++) {
			String[] deviceInfo = androidDeviceInfos[i].split("\t");
			if (deviceInfo[1].trim().equals("device")) {
				result.put(new String(deviceInfo[0].trim()),"android");
			}
		}
		if(isMacOS()){
			String ioscmd="idevice_id -l";
			String iosResp = CmdUtil.runCmdWithResult(ioscmd);
			String[] iosDeviceInfos = iosResp.split("\n");
			for(int i=0; i<iosDeviceInfos.length; i++){
				result.put(iosDeviceInfos[i], "ios");
			}
		}
		return result;
	}
	/**
	 * 获取当前系统下的所有浏览器列表
	 * @return
	 * @throws IOException 
	 */
	public static List<String> getCurrentBrowsers() throws IOException {
		List<String> result = new ArrayList<String>();
		String cmd = "cmd /c testem launchers";
		String androidResp = CmdUtil.runCmdWithResult(cmd);
		String[] browserInfos = androidResp.split("\n");
		for (int i=1; i<browserInfos.length; i++) {
			String[] deviceInfo = browserInfos[i].split(" +");
			if(deviceInfo.length>1)
				if (deviceInfo[1].trim().equals("browser")) {
					result.add(new String(deviceInfo[0].trim().toLowerCase()));
				}
		}
		return result;
	}
	
	public static boolean isMacOS(){
		return System.getProperty("os.name").toLowerCase().contains("mac");
	}
	
	public static void main(String[] args) throws IOException{
		List<String> resp = getCurrentBrowsers();
		for(String str : resp){
			System.out.println(str);
		}
	}
}
