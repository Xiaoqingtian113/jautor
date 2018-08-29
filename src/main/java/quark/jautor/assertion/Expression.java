package quark.jautor.assertion;

import java.util.List;
import java.util.Properties;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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

public class Expression extends BaseNode{
	private static final long serialVersionUID = 3463870179801847421L;
	private String expression = "";
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode = new DefaultElement("StringProp");
		paraXmlNode.add(new DefaultAttribute(new QName("testname"), "Expression.expression"));
		if (expression != null) {
			paraXmlNode.setText(expression);
		}
		childXmlNode.add(paraXmlNode);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("Expression.expression")) {
				setExpression(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			try{
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByMimeType("text/javascript");
				engine.setContext(scriptContext);
				Object obj = engine.eval(StringUtils.replaceVar(expression));
				System.out.println(obj.getClass().getName());
	            Assert.assertTrue((boolean)obj);
	        }catch(AssertionError e){
	        	Assert.fail();
	        }
		}
	}
}
