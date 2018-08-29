package quark.jautor.element;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;
import quark.jautor.util.StringUtils;

public class SendKeys extends LocateBaseNode{
	private static final long serialVersionUID = -6706986329258640491L;
	public static Logger logger2 = Logger.getLogger("seleniuminfo");
	private String input = "";//输入值
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "SendKeys.input"));
		if (input != null) {
			paraXmlNode1.setText(input);
		}
		childXmlNode.add(paraXmlNode1);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("SendKeys.input")) {
				setInput(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				element.sendKeys((String)StringUtils.getVarValue(input, scriptContext));
			}
		}
	}
}
