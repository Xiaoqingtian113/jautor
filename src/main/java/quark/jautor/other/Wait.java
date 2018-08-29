package quark.jautor.other;

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

public class Wait extends BaseNode{
	private static final long serialVersionUID = 7846226528940943148L;
	private Long waitTime = 0L;
	public Long getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(Long waitTime) {
		this.waitTime = waitTime;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("LongProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "Wait.waitTime"));
		if (waitTime != null) {
			paraXmlNode.setText(String.valueOf(waitTime));
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("Wait.waitTime")) {
				setWaitTime(Long.parseLong(elem.getText()));
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			Thread.sleep(waitTime);
		}
	}
}
