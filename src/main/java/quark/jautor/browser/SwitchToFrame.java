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
import org.openqa.selenium.WebElement;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;
import quark.jautor.core.VarNotFoundException;
import quark.jautor.util.StringUtils;

public class SwitchToFrame extends BaseNode {
	private static final long serialVersionUID = 451130333462932651L;
	private String frame = "";
	
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "SwitchToFrame.frame"));
		if (frame != null) {
			paraXmlNode.setText(frame);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("SwitchToFrame.frame")) {
				setFrame(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws VarNotFoundException {
		if(isEnabled()){
			try{
				int index = Integer.parseInt(frame);
				driver.switchTo().frame(index);
			}catch(NumberFormatException e){
				Object frameValue = StringUtils.getVarValue(frame, scriptContext);
				if(frameValue instanceof String){
					driver.switchTo().frame((String)frameValue);
					return;
				}
				if(frameValue instanceof WebElement){
					driver.switchTo().frame((WebElement)frameValue);
					return;
				}
			}
		}
	}
}
