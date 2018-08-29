package quark.jautor.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	public static Logger logger1 = Logger.getLogger("jautorinfo");
	public static Properties read(String path){
		Properties prop = new Properties();
		FileInputStream fins = null;
		try {
			fins = new FileInputStream(path);
			prop.load(fins);
			logger1.info("加载配置文件【" + path + "】成功");
		} catch (FileNotFoundException e1) {
			logger1.error("配置文件【" + path + "】没有找到");
			e1.printStackTrace();
		} catch (IOException e1) {
			logger1.error("io异常");
			e1.printStackTrace();
		} finally {
			try {
				fins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
	public static void save(Properties prop, String path){
		FileOutputStream fous = null;
		try {
			fous = new FileOutputStream(path);
			prop.store(fous, "runtime properties");
			logger1.info("保存配置文件【" + path + "】成功");
		} catch (FileNotFoundException e) {
			logger1.error("配置文件【" + path + "】没有找到");
			e.printStackTrace();
		} catch (IOException e) {
			logger1.error("io异常");
			e.printStackTrace();
		} finally {
			try {
				fous.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
