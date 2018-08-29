package quark.jautor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.SimpleScriptContext;

import org.apache.log4j.Logger;

import quark.jautor.core.VarNotFoundException;

public class StringUtils {
	public static Logger logger2 = Logger.getLogger("seleniuminfo");
	/**
	 * 获取替换变量名称后的字符串
	 * @author chunqingzhu
	 * @param 原字符串
	 * @param envMap 存储变量对应的键值对
	 * @return 如果匹配上${}格式，则返回变量的值，如果字符串部分可以匹配，则在字符串中查找并且替换变量,返回替换后的字符串，如果没有找到匹配，则返回原字符串。
	 * @throws VarNotFoundException envMap中没有变量的定义时抛出异常
	 */
	public static Object getVarValue(String input,SimpleScriptContext scriptContext) throws VarNotFoundException{
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("\\$\\{[^\\$\\{\\}]+\\}");//匹配${}的正则表达式
        Matcher m = pattern.matcher(input);
        while(m.find()){
        	String group = m.group();
        	String varName = group.substring(2,group.length()-1);//读取{}中间的子串
        	if(scriptContext.getAttribute(varName)== null){
        		logger2.error("字符串中的变量【" + input + "】没有定义");
        		throw new VarNotFoundException("字符串中的变量【" + input + "】没有定义");
        	}
			m.appendReplacement(sb, (String)scriptContext.getAttribute(varName));//从scriptContext中查找变量的值替换后，保存到sb中
        }
        m.appendTail(sb);
        return sb.toString();
	}
	
	/**
	 * 去掉${}后的字符串
	 * @author chunqingzhu
	 * @param 原字符串
	 * @return 去掉${}后的字符串
	 */
	public static String replaceVar(String input){
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("\\$\\{[^\\$\\{\\}]+\\}");//匹配${}的正则表达式
        Matcher m = pattern.matcher(input);
        while(m.find()){
        	String group = m.group();
        	String varName = group.substring(2,group.length()-1);//读取{}中间的子串
			m.appendReplacement(sb, varName);//去掉${}，保存到sb中
        }
        m.appendTail(sb);
        return sb.toString();
	}
	
}
