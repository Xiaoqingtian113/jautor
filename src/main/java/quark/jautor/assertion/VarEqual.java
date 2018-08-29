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

public class VarEqual extends BaseNode{
	private static final long serialVersionUID = -7094648706015383719L;
	private String actual = "";
	private String expect = "";
	public String getActual() {
		return actual;
	}
	public void setActual(String actual) {
		this.actual = actual;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "VarEqual.actual"));
		if (actual != null) {
			paraXmlNode1.setText(actual);
		}
		childXmlNode.add(paraXmlNode1);
		Element paraXmlNode2 = new DefaultElement("StringProp");
		paraXmlNode2.add(new DefaultAttribute(new QName("testname"), "VarEqual.expect"));
		if (expect != null) {
			paraXmlNode2.setText(expect);
		}
		childXmlNode.add(paraXmlNode2);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("VarEqual.actual")) {
				setActual(elem.getText());
			}
			if (elem.attributeValue("testname").equals("VarEqual.expect")) {
				setExpect(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			try{
				Assert.assertEquals(StringUtils.getVarValue(actual, scriptContext), StringUtils.getVarValue(expect, scriptContext));
	        }catch(AssertionError e){
	        	Assert.fail();
	        }
		}
	}
}
