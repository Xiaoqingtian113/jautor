package quark.jautor.other;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;
import quark.jautor.util.StringUtils;

public class ExecJavascript extends BaseNode{
	private static final long serialVersionUID = -6015562488408098496L;
	private String script = "";
	private String params = "";
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "ExecJavascript.script"));
		if (script != null) {
			paraXmlNode1.setText(script);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "ExecJavascript.params"));
		if (params != null) {
			paraXmlNode2.setText(params);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("ExecJavascript.script")) {
				setScript(elem.getText());
			}
			if (elem.attributeValue("testname").equals("ExecJavascript.params")) {
				setParams(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			String[] paramsArray = ((String) StringUtils.getVarValue(params, scriptContext)).split(",");
			JavascriptExecutor je = (JavascriptExecutor)driver;
			je.executeScript(script, (Object[])paramsArray);
		}
	}
}
