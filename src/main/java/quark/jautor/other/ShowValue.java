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
import quark.jautor.util.StringUtils;

public class ShowValue extends BaseNode{
	private static final long serialVersionUID = 2242043659950988633L;
	private String varName = "";
	private String varValue = "";
	private ShowValueGUI gui;
	
	public void setGUI(ShowValueGUI gui){
		this.gui = gui;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	
	public String getVarValue() {
		return varValue;
	}
	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "ShowValue.varName"));
		if (varName != null) {
			paraXmlNode1.setText(varName);
		}
		childXmlNode.add(paraXmlNode1);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("ShowValue.varName")) {
				setVarName(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po,
			Properties prop, WebDriver driver) throws Exception {
		if(isEnabled()){
			Object attr =scriptContext.getAttribute(StringUtils.replaceVar(varName));
			if(attr == null){
				varValue = "此变量未定义";
			}else
				varValue = attr.toString();
			if(gui != null)
				gui.updateGUI(varValue);
		}
	}
}
