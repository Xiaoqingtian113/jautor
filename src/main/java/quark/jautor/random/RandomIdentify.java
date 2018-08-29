package quark.jautor.random;

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
import quark.jautor.util.IdCardGenerator;

public class RandomIdentify extends BaseNode{
	private static final long serialVersionUID = -3594661764813691153L;
	private String identify = "";
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "RandomIdentify.identify"));
		if (identify != null) {
			paraXmlNode.setText(identify);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("RandomIdentify.identify")) {
				setIdentify(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			scriptContext.setAttribute(identify, IdCardGenerator.generate(),ScriptContext.ENGINE_SCOPE);
		}
	}
}
