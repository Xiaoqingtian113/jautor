package quark.jautor.element;

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

import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;

public class GetAttribute extends LocateBaseNode{
	private static final long serialVersionUID = 3697423554430181872L;
	private String attribueName = "";
	private String attribue = "";//保存属性的变量名
	
	public String getAttribue() {
		return attribue;
	}
	public void setAttribue(String attribue) {
		this.attribue = attribue;
	}
	public String getAttribueName() {
		return attribueName;
	}
	public void setAttribueName(String attribueName) {
		this.attribueName = attribueName;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "GetAttribute.attribue"));
		if (attribue != null) {
			paraXmlNode1.setText(attribue);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "GetAttribute.attribueName"));
		if (attribueName != null) {
			paraXmlNode2.setText(attribueName);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("GetAttribute.attribue")) {
				setAttribue(elem.getText());
			}
			if (elem.attributeValue("testname").equals("GetAttribute.attribueName")) {
				setAttribue(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				scriptContext.setAttribute(attribue, element.getAttribute(attribueName), ScriptContext.GLOBAL_SCOPE);
			}
		}
	}
}
