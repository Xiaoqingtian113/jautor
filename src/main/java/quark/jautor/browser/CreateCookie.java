package quark.jautor.browser;

import java.util.List;
import java.util.Properties;

import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;

public class CreateCookie extends BaseNode{
	private static final long serialVersionUID = 912670333469222306L;
	private String key = "";
	private String value = "";
	private String cookieVar = "";
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCookieVar() {
		return cookieVar;
	}
	public void setCookieVar(String cookieVar) {
		this.cookieVar = cookieVar;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "CreateCookie.key"));
		if (key != null) {
			paraXmlNode1.setText(key);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "CreateCookie.value"));
		if (value != null) {
			paraXmlNode2.setText(value);
		}
		childXmlNode.add(paraXmlNode2);
		Element paraXmlNode3 = new DefaultElement("StringProp");
		paraXmlNode3.add(new DefaultAttribute(new QName("testname"), "CreateCookie.cookieVar"));
		if (cookieVar != null) {
			paraXmlNode3.setText(cookieVar);
		}
		childXmlNode.add(paraXmlNode3);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("CreateCookie.key")) {
				setKey(elem.getText());
			}
			if (elem.attributeValue("testname").equals("CreateCookie.value")) {
				setValue(elem.getText());
			}
			if (elem.attributeValue("testname").equals("CreateCookie.cookieVar")) {
				setCookieVar(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) {
		if(isEnabled()){
			Cookie cookie = new Cookie(key, value);
			scriptContext.setAttribute(cookieVar, cookie, ScriptContext.ENGINE_SCOPE);
		}
	}
}
