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

public class GetText extends LocateBaseNode{
	private static final long serialVersionUID = -9006000153520577687L;
	private String textVar = "";//保存元素值的变量名
	
	public String getTextVar() {
		return textVar;
	}

	public void setTextVar(String textVar) {
		this.textVar = textVar;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "GetText.textVar"));
		if (textVar != null) {
			paraXmlNode1.setText(textVar);
		}
		childXmlNode.add(paraXmlNode1);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("GetText.textVar")) {
				setTextVar(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				scriptContext.setAttribute(textVar, element.getText(), ScriptContext.GLOBAL_SCOPE);
			}
		}
	}
}
