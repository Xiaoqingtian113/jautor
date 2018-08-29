/**
 * @author chunqingzhu
 * @version 1.0
 * @time 2017-8-16
 * 打开网址关键字的节点
 */

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

public class OpenURL extends BaseNode {
	private static final long serialVersionUID = -3293573958433930701L;
	private String url = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "OpenURL.url"));
		if (url != null) {
			paraXmlNode.setText(url);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("OpenURL.url")) {
				setUrl(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws VarNotFoundException {
		if(isEnabled()){
			if(driver == null)
				System.out.println("========driver null");
			driver.get((String)StringUtils.getVarValue(url, scriptContext));
		}
	}
}
