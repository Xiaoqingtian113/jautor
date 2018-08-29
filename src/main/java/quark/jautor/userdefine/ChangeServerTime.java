package quark.jautor.userdefine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;
import quark.jautor.util.SSHUtils;

public class ChangeServerTime extends BaseNode{
	private static final long serialVersionUID = -2902779486382739627L;
	private GregorianCalendar calendar;
	private String ip;
	private String port;
	private String user;
	private String pwd;
	public GregorianCalendar getCalendar() {
		return calendar;
	}
	public void setCalendar(GregorianCalendar calendar) {
		this.calendar = calendar;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("IntProp");
		Element paraXmlNode2 = new DefaultElement("IntProp");
		Element paraXmlNode3 = new DefaultElement("IntProp");
		Element paraXmlNode4 = new DefaultElement("IntProp");
		Element paraXmlNode5 = new DefaultElement("IntProp");
		Element paraXmlNode6 = new DefaultElement("IntProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.year"));
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.month"));
		paraXmlNode3.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.day"));
		paraXmlNode4.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.hour"));
		paraXmlNode5.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.minute"));
		paraXmlNode6.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.second"));
		if (calendar != null) {
			paraXmlNode1.setText(String.valueOf(calendar.get(Calendar.YEAR)));
			paraXmlNode2.setText(String.valueOf(calendar.get(Calendar.MONTH)));
			paraXmlNode3.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
			paraXmlNode4.setText(String.valueOf(calendar.get(Calendar.HOUR)));
			paraXmlNode5.setText(String.valueOf(calendar.get(Calendar.MINUTE)));
			paraXmlNode6.setText(String.valueOf(calendar.get(Calendar.SECOND)));
		}
		childXmlNode.add(paraXmlNode1);
		childXmlNode.add(paraXmlNode2);
		childXmlNode.add(paraXmlNode3);
		childXmlNode.add(paraXmlNode4);
		childXmlNode.add(paraXmlNode5);
		childXmlNode.add(paraXmlNode6);
		Element paraXmlNode7 = new DefaultElement("StringProp");
		paraXmlNode7.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.ip"));
		if (ip != null) {
			paraXmlNode7.setText(ip);
		}
		childXmlNode.add(paraXmlNode7);
		Element paraXmlNode8 = new DefaultElement("StringProp");
		paraXmlNode8.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.port"));
		if (String.valueOf(port) != null) {
			paraXmlNode8.setText(port);
		}
		childXmlNode.add(paraXmlNode8);
		Element paraXmlNode9 = new DefaultElement("StringProp");
		paraXmlNode9.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.user"));
		if (user != null) {
			paraXmlNode9.setText(user);
		}
		childXmlNode.add(paraXmlNode9);
		Element paraXmlNode10 = new DefaultElement("StringProp");
		paraXmlNode10.add(new DefaultAttribute(new QName("testname"), "ChangeServerTime.pwd"));
		if (pwd != null) {
			paraXmlNode10.setText(pwd);
		}
		childXmlNode.add(paraXmlNode10);
		
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		int year=0,month=0,day=0,hour=0,minute=0,second=0;
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("ChangeServerTime.year"))
				year = Integer.parseInt(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.month"))
				month = Integer.parseInt(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.day"))
				day = Integer.parseInt(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.hour"))
				hour = Integer.parseInt(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.minute"))
				minute = Integer.parseInt(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.second"))
				second = Integer.parseInt(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.ip"))
				setIp(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.port"))
				setPort(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.user"))
				setUser(elem.getText());
			if (elem.attributeValue("testname").equals("ChangeServerTime.pwd"))
				setPwd(elem.getText());
		}
		calendar = new GregorianCalendar();
		calendar.set(year, month, day, hour, minute, second);
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String date = sdf.format(calendar.getTime());
			String result = SSHUtils.runSSHCMD("date -s \"" + date + "\"", ip, Integer.parseInt(port), user, pwd);
			if(!result.trim().endsWith("CST"))
				throw new Exception("执行ssh远程命令失败");
		}
	}
}
