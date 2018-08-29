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

public class GetCssValue extends LocateBaseNode{
	private static final long serialVersionUID = -5005929876285967033L;
	private String cssName = "";
	private String css = "";//保存属性的变量名

	public String getCssName() {
		return cssName;
	}
	public void setCssName(String cssName) {
		this.cssName = cssName;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "GetCssValue.css"));
		if (css != null) {
			paraXmlNode1.setText(css);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "GetCssValue.cssName"));
		if (cssName != null) {
			paraXmlNode2.setText(cssName);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("GetCssValue.css")) {
				setCss(elem.getText());
			}
			if (elem.attributeValue("testname").equals("GetCssValue.cssName")) {
				setCssName(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				scriptContext.setAttribute(css, element.getCssValue(cssName), ScriptContext.GLOBAL_SCOPE);
			}
		}
	}
}
