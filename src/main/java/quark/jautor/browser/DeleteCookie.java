package quark.jautor.browser;

import java.util.List;
import java.util.Properties;

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
import quark.jautor.core.VarNotFoundException;
import quark.jautor.util.StringUtils;

public class DeleteCookie extends BaseNode{
	private static final long serialVersionUID = 1826642844066568413L;
	private String cookie = "";
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "DeleteCookie.cookie"));
		if (cookie != null) {
			paraXmlNode.setText(cookie);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("DeleteCookie.cookie")) {
				setCookie(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws VarNotFoundException {
		if(isEnabled()){
			if(cookie == null){
				driver.manage().deleteAllCookies();
				return;
			}
			driver.manage().deleteCookie((Cookie)StringUtils.getVarValue(cookie, scriptContext));
		}
	}
}
