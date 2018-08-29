package quark.jautor.assertion;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import quark.jautor.core.BaseNode;
import quark.jautor.core.POData;
import quark.jautor.util.StringUtils;

public class VarInclude extends BaseNode{
	private static final long serialVersionUID = -2775608354588174389L;
	private String result = "";
	private String inclue = "";
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getInclue() {
		return inclue;
	}
	public void setInclue(String inclue) {
		this.inclue = inclue;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "VarInclude.result"));
		if (result != null) {
			paraXmlNode1.setText(result);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "VarInclude.inclue"));
		if (inclue != null) {
			paraXmlNode2.setText(inclue);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("VarInclude.result")) {
				setResult(elem.getText());
			}
			if (elem.attributeValue("testname").equals("VarInclude.inclue")) {
				setInclue(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			String resultValue = (String) StringUtils.getVarValue(result, scriptContext);
			String includeValue = (String) StringUtils.getVarValue(inclue, scriptContext);
			if(resultValue.indexOf(includeValue) == -1){
				Assert.fail("不包含");
			}
		}
	}
}
