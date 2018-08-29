package quark.jautor.core;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconFactory {
	public static Map<String,Icon> enabledIconMap = new HashMap<>();
	public static Map<String,Icon> disabledIconMap = new HashMap<>();
	
	public static void init(){
		String dirToolbar = "res/icon/";
		Properties iconProp = PropertiesUtil.read("config/icon.properties");
		Enumeration<Object> keys = iconProp.keys();
		while(keys.hasMoreElements()){
			String key = (String) keys.nextElement();
			String iconPath = iconProp.getProperty(key);
			if(iconPath != null)
				registEnabledIcon(key,new ImageIcon(dirToolbar + iconPath));
		}
	}
	
	public static Icon getEnabledIcon(String key){
		return enabledIconMap.get(key);
	}
	
	public static Icon getDisabledIcon(String key){
		return disabledIconMap.get(key);
	}
	
	public static void registEnabledIcon(String key, Icon icon){
		enabledIconMap.put(key, icon);
	}
	
	public static void registDisabledIcon(String key, Icon icon){
		disabledIconMap.put(key, icon);
	}
}
