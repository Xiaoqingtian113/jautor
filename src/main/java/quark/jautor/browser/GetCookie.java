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

public class GetCookie extends BaseNode{
	private static final long serialVersionUID = 2448914933507462754L;
	private String cookieName = "";
	private String cookieVar = "";
	
	public String getCookieName() {
		return cookieName;
	}
	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
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
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "CreateCookie.cookieName"));
		if (cookieName != null) {
			paraXmlNode1.setText(cookieName);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "CreateCookie.cookieVar"));
		if (cookieVar != null) {
			paraXmlNode2.setText(cookieVar);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("CreateCookie.cookieName")) {
				setCookieName(elem.getText());
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
			Cookie cookie = driver.manage().getCookieNamed(cookieName);
			scriptContext.setAttribute(cookieVar, cookie, ScriptContext.ENGINE_SCOPE);
		}
	}
}
