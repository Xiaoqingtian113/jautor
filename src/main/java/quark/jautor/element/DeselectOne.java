package quark.jautor.element;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;

public class DeselectOne extends LocateBaseNode{
	private static final long serialVersionUID = -3028495692418373751L;
	private String option = "";//选项值或者index或选项名

	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	@Override
	public Element addToXML(Element xmlNode) {
		Element childXmlNode = super.addToXML(xmlNode);
		Element paraXmlNode1 = new DefaultElement("StringProp");
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "DeselectOne.option"));
		if (option != null) {
			paraXmlNode1.setText(option);
		}
		childXmlNode.add(paraXmlNode1);
		return childXmlNode;
	}
	@Override	@SuppressWarnings("unchecked")
	public void addToTree(Element xmlNode) {
		super.addToTree(xmlNode);
		List<Element> children = xmlNode.elements();
		for (Element elem : children) {
			if (elem.attributeValue("testname").equals("DeselectOne.option")) {
				setOption(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			super.exec(node, scriptContext, po, prop, driver);
			if(element != null){
				Select select = new Select(element);
				try{
					int index = Integer.parseInt(option);
					select.deselectByIndex(index);
				}catch(NumberFormatException e){
					try{
						select.deselectByVisibleText(option);
					}catch(NoSuchElementException ex){
						select.deselectByValue(option);
					}
				}
			}
		}
	}
}
