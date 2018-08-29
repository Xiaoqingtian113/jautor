package quark.jautor.element;

import java.util.List;
import java.util.Properties;

import javax.script.SimpleScriptContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import quark.jautor.core.LocateBaseNode;
import quark.jautor.core.POData;
import quark.jautor.selenium.BySet;

public class SelectOne extends LocateBaseNode{
	private static final long serialVersionUID = -3028495692418373751L;
	public static Logger logger2 = Logger.getLogger("seleniuminfo");
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
		paraXmlNode1.add(new DefaultAttribute(new QName("testname"), "SelectOne.option"));
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
			if (elem.attributeValue("testname").equals("SelectOne.option")) {
				setOption(elem.getText());
			}
		}
	}
	@Override
	public void exec(DefaultMutableTreeNode node, SimpleScriptContext scriptContext, POData po, Properties prop,
			WebDriver driver) throws Exception {
		if(isEnabled()){
			String clearlyTimeout = prop.getProperty("clearlyTimeout", "30");
			By by = BySet.getBy(po.getlocateType(page, elem), po.getLocateExp(page, elem));
			element = new WebDriverWait(driver,Long.parseLong(clearlyTimeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
			if(element == null){
				logger2.error("元素【" + page + "-" + elem + "】定位失败");
			}else
				logger2.error("元素【" + page + "-" + elem + "】定位成功");
			if(element != null){
				Select select = new Select(element);
				try{
					int index = Integer.parseInt(option);
					select.selectByIndex(index);
				}catch(NumberFormatException ex){
					try{
						select.selectByVisibleText(option);
					}catch(NoSuchElementException e){
						select.selectByValue(option);
					}
				}
			}
		}
	}
}
