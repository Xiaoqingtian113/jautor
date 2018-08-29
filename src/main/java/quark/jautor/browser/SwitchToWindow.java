package quark.jautor.browser;

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
import quark.jautor.core.VarNotFoundException;
import quark.jautor.util.StringUtils;

public class SwitchToWindow extends BaseNode{
	private static final long serialVersionUID = -1967281415570719900L;
	private String handle = "";
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "SwitchToWindow.handle"));
		if (handle != null) {
			paraXmlNode.setText(handle);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("SwitchToWindow.handle")) {
				setHandle(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws VarNotFoundException {
		if(isEnabled())
			driver.switchTo().window((String)StringUtils.getVarValue(handle, scriptContext));
	}
}
