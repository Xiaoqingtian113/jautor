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
import quark.jautor.util.RandomUtil;

public class RandomName extends BaseNode{
	private static final long serialVersionUID = -2100349040510428848L;
	private String name = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "RandomName.name"));
		if (name != null) {
			paraXmlNode.setText(name);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("RandomName.name")) {
				setName(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			scriptContext.setAttribute(name, RandomUtil.getName(),ScriptContext.ENGINE_SCOPE);
		}
	}
}
