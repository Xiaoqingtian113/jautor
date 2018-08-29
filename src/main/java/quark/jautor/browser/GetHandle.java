/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 获取页面句柄关键字的节点
 */

package quark.jautor.browser;

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

public class GetHandle extends BaseNode {
	private static final long serialVersionUID = 3933864822452397715L;
	private String handleVar = "";// 变量名

	public String getHandleVar() {
		return handleVar;
	}

	public void setHandleVar(String handleVar) {
		this.handleVar = handleVar;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "GetHandle.handleVar"));
		if (handleVar != null) {
			paraXmlNode1.setText(handleVar);
		}
		childXmlNode.add(paraXmlNode1);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("GetHandle.handleVar")) {
				setHandleVar(elem.getText());
			}
		}
	}
	@Override
	public void exec (DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			scriptContext.setAttribute(handleVar, driver.getWindowHandle(), ScriptContext.ENGINE_SCOPE);
		}
	}
}
