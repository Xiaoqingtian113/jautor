package quark.jautor.assertion;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;
import quark.jautor.util.StringUtils;

public class MatchRegex extends BaseNode {
	private static final long serialVersionUID = -708991213911869339L;
	private String actual = "";
	private String regex = "";
	public String getActual() {
		return actual;
	}
	public void setActual(String actual) {
		this.actual = actual;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "MatchRegex.actual"));
		if (actual != null) {
			paraXmlNode1.setText(actual);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "MatchRegex.regex"));
		if (regex != null) {
			paraXmlNode2.setText(regex);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("MatchRegex.actual")) {
				setActual(elem.getText());
			}
			if (elem.attributeValue("testname").equals("MatchRegex.regex")) {
				setRegex(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			String actualValue = (String) StringUtils.getVarValue(actual, scriptContext);
			if(!actualValue.matches(regex)){
				Assert.fail("不匹配");
			}
		}
	}
}
