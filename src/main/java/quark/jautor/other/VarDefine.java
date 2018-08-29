package quark.jautor.other;

import java.util.List;
import java.util.Properties;

import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;

public class VarDefine extends BaseNode{
	private static final long serialVersionUID = -7096508048594943399L;
	private String vaName = "";
	private String vaValue = "";
	
	public String getVaName() {
		return vaName;
	}

	public void setVaName(String vaName) {
		this.vaName = vaName;
	}

	public String getVaValue() {
		return vaValue;
	}

	public void setVaValue(String vaValue) {
		this.vaValue = vaValue;
	}

	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "VarDefine.vaName"));
		if (vaName != null) {
			paraXmlNode1.setText(vaName);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "VarDefine.vaValue"));
		if (vaValue != null) {
			paraXmlNode2.setText(vaValue);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}

	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("VarDefine.vaName")) {
				setVaName(elem.getText());
			}
			if (elem.attributeValue("testname").equals("VarDefine.vaValue")) {
				setVaValue(elem.getText());
			}
		}
	}

	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception {
		if(isEnabled()){
			scriptContext.setAttribute(vaName, vaValue, ScriptContext.ENGINE_SCOPE);
		}
	}

}
